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

import com.mvp4g.processor.exceptions.ConfigurationException;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Mvp4gUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;

public class ModuleControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;
  /* state of the controller */
  private boolean               isValid;

  /* Controller */
  private EventBusControl    eventBusControl;
  private ChildModuleControl childModuleControl;

//------------------------------------------------------------------------------

  public ModuleControl(ApplicationInfo applicationInfo,
                       MessagerUtils messagerUtils,
                       ProcessingEnvironment processingEnv) {
    this.applicationInfo = applicationInfo;
    this.messagerUtils = messagerUtils;
    this.processingEnv = processingEnv;

    isValid = true;

    eventBusControl = new EventBusControl(applicationInfo,
                                          messagerUtils,
                                          processingEnv);

    childModuleControl = new ChildModuleControl(applicationInfo,
                                                messagerUtils,
                                                processingEnv);
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
   * @return true, if it is valid class
   */
  public boolean process(TypeElement element)
    throws ConfigurationException {
    // process
    for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
      if (Mvp4gUtils.EVENTS.equals(annotationMirror.getAnnotationType()
                                              .toString())) {
        if (!eventBusControl.process(element)) {
          return false;
        }
      } else if (Utils.CHILD_MODULES.equals(annotationMirror.getAnnotationType()
                                                            .toString())) {
        if (!childModuleControl.process(element,
                                        annotationMirror)) {
          return false;
        }
      }
    }
    return isValid(element);
  }

//------------------------------------------------------------------------------

  private boolean isValid(TypeElement element) {
    return true;
  }
}
