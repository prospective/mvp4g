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

package com.mvp4g.processor.validation;

import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.ModuleInfo;
import com.mvp4g.processor.controls.info.PresenterInfo;
import com.mvp4g.processor.controls.info.models.BroadcastToModel;
import com.mvp4g.processor.utils.MessagerUtils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.util.ElementFilter;

/**
 * Created by hoss on 12.04.15.
 */
public class Mvp4gValidator {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;

  /* state of validation */
  private boolean valid;

//------------------------------------------------------------------------------

  public Mvp4gValidator(ApplicationInfo applicationInfo,
                        ProcessingEnvironment processingEnv,
                        MessagerUtils messagerUtils) {
    this.applicationInfo = applicationInfo;
    this.processingEnv = processingEnv;
    this.messagerUtils = messagerUtils;

    valid = true;
  }

//------------------------------------------------------------------------------

  public boolean validate() {
    checkModuleAndEventBus();
    checkBroadcastToDefinitions();

    return valid;
  }

//==============================================================================

  private void checkModuleAndEventBus() {
    for (ModuleInfo info : applicationInfo.getModules()) {
      System.out.println("Validate");

    }
  }

  private void checkBroadcastToDefinitions() {
    for (BroadcastToModel model : applicationInfo.getBroadcastTos()) {
      for (ExecutableElement eventMethod : model.getMethods()) {
        boolean isMethodImplemented = false;
        String eventHandlingMethodName = createEventHandlingMethodName(eventMethod.getSimpleName()
                                                                                  .toString());
        for (PresenterInfo presenterInfo : applicationInfo.getPresenter()) {
          if (processingEnv.getTypeUtils()
                           .isSubtype(presenterInfo.getPresenter()
                                                   .asType(),
                                      model.getBroadcastTo()
                                           .asType())) {
            // todo Super class checking

            for (ExecutableElement presenterMethod : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                                          .getAllMembers(presenterInfo.getPresenter()))) {


              if (presenterMethod.getSimpleName()
                                 .toString()
                                 .equals(eventHandlingMethodName)) {
                isMethodImplemented = true;
              }
            }
          }
        }
        if (!isMethodImplemented) {
          System.out.println("error");
        }
      }
    }
  }

//==============================================================================

  private String createEventHandlingMethodName(String eventName) {
    String name = "on";
    name += eventName.substring(0,
                                1)
                     .toUpperCase();
    if (eventName.length() > 1) {
      name += eventName.substring(1);
    }
    System.out.println("EventHandling Method name: " + name);
    return name;
  }
}
