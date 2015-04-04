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

import com.mvp4g.client.Mvp4gModule;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.processor.info.ApplicationInfo;
import com.mvp4g.processor.info.ModuleInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.util.List;
import java.util.Map;

public class ModuleControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;
  /* state of the controller */
  private boolean               isValid;

//------------------------------------------------------------------------------

  public ModuleControl(ApplicationInfo applicationInfo,
                       MessagerUtils messagerUtils,
                       ProcessingEnvironment processingEnv) {
    this.applicationInfo = applicationInfo;
    this.messagerUtils = messagerUtils;
    this.processingEnv = processingEnv;

    isValid = true;
  }

//------------------------------------------------------------------------------

  /**
   * Checks the class if:
   * <ul>
   * <li>it is a class</li>
   * <li>that the class is not abstract</li>
   * <li>that class extends BasePresenter</li>
   * </ul>
   * <p/>
   * and creates the moduleInfo.
   *
   * @param element the annotated presenter to validate
   *
   * @return true, if it is valid class
   */
  public boolean process(TypeElement element) {
    for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
      if (Utils.EVENTS.equals(annotationMirror.getAnnotationType()
                                              .toString())) {
        processEvents(element);
      } else if (Utils.CHILD_MODULES.equals(annotationMirror.getAnnotationType()
                                                            .toString())) {
        processChildModules(element,
                            annotationMirror);
      }
    }

    // TODO validation


    return isValid;
  }

//------------------------------------------------------------------------------

  private void processEvents(TypeElement element) {
    String moduleName;

    Map<String, Object> annotationValues = Utils.getAnnotation(Events.class,
                                                               element);

    if (annotationValues.get("module")
                        .equals(Mvp4gModule.class)) {
      moduleName = Mvp4gModule.class.getCanonicalName();
    } else {
      moduleName = ((TypeElement) ((DeclaredType) annotationValues.get("module")).asElement()).getQualifiedName()
                                                                                              .toString();
    }

    // create info
    ModuleInfo info = applicationInfo.getModule(moduleName);
    if (info == null) {
      info = new ModuleInfo(moduleName);
    }
    // set Data in info
    info.getEventBusInfo()
        .setEventBus(element);
    info.getEventBusInfo()
        .setEventBusName(element.toString());

    if (!annotationValues.get(Utils.ATTRIBUTE_MODULE)
                         .equals(Mvp4gModule.class)) {
      info.setModule((TypeElement) ((DeclaredType) annotationValues.get(Utils.ATTRIBUTE_MODULE)).asElement());
    }
    info.getEventBusInfo()
        .setStartPresenterName((String) annotationValues.get(Utils.ATTRIBUTE_START_PRESENTER_NAME));
    info.getEventBusInfo()
        .setStartPresenter((TypeElement) ((DeclaredType) annotationValues.get(Utils.ATTRIBUTE_START_PRESENTER)).asElement());
    info.getEventBusInfo()
        .setHistoryOnStart((Boolean) annotationValues.get(Utils.ATTRIBUTE_HISTORY_ON_START));
    // ginModules
    if (annotationValues.get(Utils.ATTRIBUTE_GIN_MODULES) instanceof TypeElement[]) {
      info.getEventBusInfo()
          .setGinModules((TypeElement[]) annotationValues.get("ginModules"));
    }
    if (annotationValues.get(Utils.ATTRIBUTE_GIN_MODULE_PROPERTIES) instanceof String[]) {
      info.getEventBusInfo()
          .setGinModuleProperties((String[]) annotationValues.get("ginModuleProperties"));
    }

    applicationInfo.addModules(info.getModuleName(),
                               info);
  }

  private void processChildModules(TypeElement element,
                                   AnnotationMirror annotationMirror) {

    boolean     isAsync       = true;
    boolean     isAutoDisplay = true;
    TypeElement module;

    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues()
                                                                                                   .entrySet()) {
      for (AnnotationMirror mirror : (List<AnnotationMirror>) entry.getValue()
                                                                   .getValue()) {
        module = (TypeElement) ((DeclaredType) Utils.getAnnotationValue(Utils.ATTRIBUTE_MODULE_CLASS,
                                                                        mirror)
                                                    .getValue()).asElement();
        AnnotationValue amIsAsync = Utils.getAnnotationValue(Utils.ATTRIBUTE_ASYNC,
                                                             mirror);
        if (amIsAsync != null) {
          isAsync = (Boolean) amIsAsync.getValue();
        }
        AnnotationValue amIsAutoDisplay = Utils.getAnnotationValue(Utils.ATTRIBUTE_AUTO_DISPLAY,
                                                                   mirror);
        if (amIsAutoDisplay != null) {
          isAutoDisplay = (Boolean) amIsAsync.getValue();
        }
        // set up module infos
        ModuleInfo info = applicationInfo.getModule(module.toString());
        if (info == null) {
          info = new ModuleInfo(module.toString());
          applicationInfo.addModules(info.getModuleName(),
                                     info);
        }
        info.setAufoDisplay(isAutoDisplay);
        info.setIsAsync(isAsync);
        info.getEventBusInfo()
            .setParentEventBus(element);
      }
    }
  }
}
