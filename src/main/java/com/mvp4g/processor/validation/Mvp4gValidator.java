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

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.EventHandlerInfo;
import com.mvp4g.processor.controls.info.ModuleInfo;
import com.mvp4g.processor.controls.info.PresenterInfo;
import com.mvp4g.processor.controls.info.models.BroadcastToModel;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Messages;
import com.mvp4g.processor.utils.Mvp4gUtils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;

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
        String eventHandlingMethodName = createEventHandlingMethodName(eventMethod.getSimpleName()
                                                                                  .toString());
        for (EventHandlerInfo eventHandlerInfo : applicationInfo.getEventHandlers(true)) {
          if (processingEnv.getTypeUtils()
                           .isSubtype(eventHandlerInfo.getEventHandler()
                                                   .asType(),
                                      model.getBroadcastTo()
                                           .asType())) {
            if (!Mvp4gUtils.isImplementingMethod(processingEnv,
                                                 eventHandlerInfo.getEventHandler(),
                                                 eventMethod,
                                                 eventHandlingMethodName)) {
              messagerUtils.error(eventHandlerInfo.getEventHandler(),
                                  Messages.INVALID_EVENT_METHOD,
                                  eventHandlerInfo.getEventHandlerName(),
                                  eventMethod.getSimpleName().toString(),
                                  eventHandlingMethodName);
            }
          }
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
