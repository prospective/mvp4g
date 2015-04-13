/*
 * Copyright (C) 2015 Pierre-Laurent Coirier, Frank Hossfeld.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mvp4g.processor.controls;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.processor.utils.Messages;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.PresenterInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Mvp4gUtils;
import com.mvp4g.processor.utils.Utils;
import com.mvp4g.processor.controls.info.models.TypeModel;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

public class PresenterControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;

  /* info */
  private PresenterInfo info;

//------------------------------------------------------------------------------

  public PresenterControl(ApplicationInfo applicationInfo,
                          MessagerUtils messagerUtils,
                          ProcessingEnvironment processingEnv) {
    this.applicationInfo = applicationInfo;
    this.messagerUtils = messagerUtils;
    this.processingEnv = processingEnv;
  }

//------------------------------------------------------------------------------

  /**
   * Checks the class if:
   * <ul>
   * <li>it is a class</li>
   * <li>that the class is not abstract</li>
   * <li>that class extends BasePresenter</li>
   * </ul>
   *
   * @param element the annotated presenter to validate
   * @return true, if it is valid class
   */
  public boolean process(TypeElement element) {
    // fill eventBus info
    if (!createInfo(element)) {
      return false;
    }
    ;
    // validate
    return isValid(element);
  }

//------------------------------------------------------------------------------

  private boolean createInfo(TypeElement element) {
    // create info
    info = applicationInfo.getPresenter(element.toString());
    if (info == null) {
      info = new PresenterInfo(element.toString(),
                               element);
    }

    Map<String, Object> annotationValues = Utils.getAnnotation(Presenter.class,
                                                               element);
    if (annotationValues != null) {
      if (annotationValues.get(Mvp4gUtils.ATTRIBUTE_VIEW) != null) {
        info.setView((TypeElement) ((DeclaredType) annotationValues.get(Mvp4gUtils.ATTRIBUTE_VIEW)).asElement());
      }
      if (annotationValues.get(Mvp4gUtils.ATTRIBUTE_VIEW_NAME) != null) {
        info.setViewName((String) annotationValues.get(Mvp4gUtils.ATTRIBUTE_VIEW_NAME));
      }
      if (annotationValues.get(Mvp4gUtils.ATTRIBUTE_NAME) != null) {
        info.setName((String) annotationValues.get(Mvp4gUtils.ATTRIBUTE_NAME));
      }
      if (annotationValues.get(Mvp4gUtils.ATTRIBUTE_MULTIPLE) != null) {
        info.setMultiple((Boolean) annotationValues.get(Mvp4gUtils.ATTRIBUTE_MULTIPLE));
      }
      if (annotationValues.get(Mvp4gUtils.ATTRIBUTE_ASYNC) instanceof TypeElement) {
        info.setAsync((TypeElement) ((DeclaredType) annotationValues.get(Mvp4gUtils.ATTRIBUTE_ASYNC)).asElement());
      }
    }

    if (!getTypeParameter(element)) {
      return false;
    }

    for (ExecutableElement executable : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                             .getAllMembers(element))) {
      if (executable.getSimpleName()
                    .toString()
                    .equals(Mvp4gUtils.METHOD_BIND)) {
        info.setBindMethod(executable);
      } else if (executable.getSimpleName()
                           .toString()
                           .startsWith(Mvp4gUtils.METHOD_EVENT)) {
        info.getEventHandlingMethods()
            .add(executable);
      }
    }

    applicationInfo.addPresenter(info.getPresenterName(),
                                 info);

    return true;
  }

//------------------------------------------------------------------------------

  /**
   * Checks the class if:
   * <ul>
   * <li>it is a class</li>
   * <li>that the class is not abstract</li>
   * <li>that class extends BasePresenter</li>
   * </ul>
   *
   * @param element the annotated presenter to validate
   * @return true, if it is valid class
   */
  private boolean isValid(TypeElement element) {
    // check presenter
    if (element != null) {
      // Check if the annotated file is a class
      if (element.getKind() != ElementKind.CLASS) {
        messagerUtils.error(element,
                            Messages.NOT_A_CLASS,
                            Presenter.class.getSimpleName());
        return false;
      }
      // Check if the annotated file is a class
      if (element.getModifiers()
                 .contains(Modifier.ABSTRACT)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_NOT_BE_ABSTRACT,
                            Presenter.class.getSimpleName());
        return false;
      }
      // check if the class extends BasePresenter
      if (!Utils.isSubType(processingEnv,
                           element,
                           BasePresenter.class)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_EXTEND_BASE_PRESENTER,
                            Presenter.class.getSimpleName());
        return false;
      }
      if (!processingEnv.getTypeUtils()
                        .isSubtype(info.getView()
                                       .asType(),
                                   info.getInjectedView()
                                       .asType())) {
        messagerUtils.error(element,
                            Messages.INVALID_VIEW,
                            element.getSimpleName()
                                   .toString(),
                            info.getInjectedView()
                                .getSimpleName(),
                            info.getView()
                                .getSimpleName());
        return false;

      }
    }
    return true;
  }

  private boolean getTypeParameter(TypeElement element) {
    List<TypeModel> genericTypes = Utils.getTypeParameterFromClass(element);
    if (genericTypes.size() > 0) {
      for (TypeModel model : genericTypes) {
        if (model.getType()
                 .equals("E")) {
          info.setInjectedEventBus(model.getArgument());
        } else if (model.getType()
                        .equals("V")) {
          info.setInjectedView(model.getArgument());
        }
      }
    } else {
      messagerUtils.error(element,
                          Messages.MISSING_GENERICS_PRESENTER,
                          Presenter.class.getSimpleName());
      return false;
    }
    return true;
  }
}
