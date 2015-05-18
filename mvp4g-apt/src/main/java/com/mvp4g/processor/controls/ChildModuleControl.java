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

import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.ModuleInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Mvp4gUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;
import java.util.List;

public class ChildModuleControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;

//------------------------------------------------------------------------------

  public ChildModuleControl(ApplicationInfo applicationInfo,
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
  public boolean process(TypeElement element,
                         AnnotationMirror annotationMirror) {
    // fill info
    createInfo(element,
               annotationMirror);
    // validate
    return isValid(element);
  }

//------------------------------------------------------------------------------

  private void createInfo(final TypeElement element,
                          AnnotationMirror annotationMirror) {
    for (Object value : annotationMirror.getElementValues()
                                        .values()) {
      ((AnnotationValue) value).accept(new SimpleAnnotationValueVisitor7<Void, Void>() {
                                         @Override
                                         public Void visitArray(List<? extends AnnotationValue> values,
                                                                Void aVoid) {
                                           for (AnnotationValue childModuleValue : (List<AnnotationValue>) values) {
                                             procesChildModule(element,
                                                               childModuleValue);
                                           }
                                           return null;
                                         }
                                       },
                                       null);
    }
  }

//------------------------------------------------------------------------------

  private boolean isValid(TypeElement element) {
    return true;
  }

  private void procesChildModule(final TypeElement element,
                                 AnnotationValue value) {
    value.accept(new SimpleAnnotationValueVisitor7<Void, Void>() {
                   @Override
                   public Void visitAnnotation(AnnotationMirror mirror,
                                               Void aVoid) {
                     fillInfo(element,
                              mirror);
                     return null;
                   }
                 },
                 null);
  }

  private void fillInfo(TypeElement element,
                        AnnotationMirror childModule) {

    boolean isAsync = true;
    boolean isAutoDisplay = true;
    TypeElement module;

    module = (TypeElement) ((DeclaredType) Utils.getAnnotationValue(Mvp4gUtils.ATTRIBUTE_MODULE_CLASS,
                                                                    childModule)
                                                .getValue()
    ).asElement();
    AnnotationValue amIsAsync = Utils.getAnnotationValue(Mvp4gUtils.ATTRIBUTE_ASYNC,
                                                         childModule);
    if (amIsAsync != null) {
      isAsync = (Boolean) amIsAsync.getValue();
    }
    AnnotationValue amIsAutoDisplay = Utils.getAnnotationValue(Mvp4gUtils.ATTRIBUTE_AUTO_DISPLAY,
                                                               childModule);
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
    info.setAutoDisplay(isAutoDisplay);
    info.setIsAsync(isAsync);
    info.getEventBusInfo()
        .setParentEventBus(element);
  }
}
