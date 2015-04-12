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

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.history.HistoryConverter;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.HistoryConverterInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Messages;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.util.List;
import java.util.Map;

public class HistoryConverterControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;

  /* info */
  private HistoryConverterInfo info;

//------------------------------------------------------------------------------

  public HistoryConverterControl(ApplicationInfo applicationInfo,
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
    info = applicationInfo.getHistoryConverter(element.toString());
    if (info == null) {
      info = new HistoryConverterInfo(element);
    }

    for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
      if (Utils.HISTORY.equals(annotationMirror.getAnnotationType()
                                               .toString())) {
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues()
                                                                                                       .entrySet()) {
          String keyName = entry.getKey()
                                .getSimpleName()
                                .toString();
          if (Utils.ATTRIBUTE_NAME.equals(keyName)) {
            info.setName((String) entry.getValue()
                                       .getValue());
          } else if (Utils.ATTRIBUTE_TYPE.equals(keyName)) {
            if (entry.getValue() != null) {
              info.setType(History.HistoryConverterType.valueOf(entry.getValue()
                                                                     .getValue()
                                                                     .toString()));
            }
          }
        }
      }
    }

    if (!getTypeParameter(element)) {
      return false;
    }

    for (ExecutableElement executable : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                             .getAllMembers(element))) {
      if (executable.getSimpleName()
                    .toString()
                    .startsWith(Utils.METHOD_EVENT)) {
        info.getEventHandlingMethods()
            .add(executable);
      }
    }

    applicationInfo.addHistoryConverter(info.getName(),
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
    // check history control
    if (element != null) {
      // Check if the annotated file is a class
      if (element.getKind() != ElementKind.CLASS) {
        messagerUtils.error(element,
                            Messages.NOT_A_CLASS,
                            History.class.getSimpleName());
        return false;
      }
      // Check if the annotated class isnot abstract
      if (element.getModifiers()
                 .contains(Modifier.ABSTRACT)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_NOT_BE_ABSTRACT,
                            History.class.getSimpleName());
        return false;
      }
      // check if the class extends BasePresenter
      if (!Utils.isImplementingType(processingEnv,
                                    element,
                                    HistoryConverter.class)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_IMPLEMENTS_HISTORY_CONVERTER,
                            History.class.getSimpleName());
        return false;
      }
    }
    return true;
  }

  private boolean getTypeParameter(TypeElement element) {
    List<? extends TypeMirror> interfaces = element.getInterfaces();
    for (TypeMirror mirror : interfaces) {
      TypeElement el = (TypeElement) ((DeclaredType) mirror).asElement();
      if (el.toString()
            .equals(HistoryConverter.class.getCanonicalName())) {
        List<? extends TypeMirror> parameters = ((DeclaredType) mirror).getTypeArguments();
        if (parameters.size() > 0) {
          info.setInjectedEventBus((TypeElement) ((DeclaredType) parameters.get(0)).asElement());
          break;
        } else {
          messagerUtils.error(element,
                              Messages.MISSING_GENERICS_HISTORY,
                              History.class.getSimpleName());
          return false;
        }
      }
    }
    return true;
  }
}
