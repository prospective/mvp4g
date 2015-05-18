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

import com.mvp4g.client.annotation.History;
import com.mvp4g.processor.controls.info.*;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Messages;
import com.mvp4g.processor.utils.Mvp4gUtils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;


@Deprecated
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
    checkHistoryConvertersAndEventBus();
    checkEventHandlersAndEventBus();

    return valid;
  }

//==============================================================================
// TODO alles in die Einzelnen Controler bewegen ... und ausschließlich mit Annotation arbeiten ...
  private void checkModuleAndEventBus() {
//    for (ModuleInfo moduleInfo : applicationInfo.getModules()) {
//
//
//    }
  }

  private void checkBroadcastToDefinitions() {
    for (ModuleInfo moduleInfo : applicationInfo.getModules()) {
      EventBusInfo eventBusInfo = moduleInfo.getEventBusInfo();
      if (eventBusInfo.getEvents() != null) {
        for (EventInfo eventInfo : eventBusInfo.getEvents()) {
          if (eventInfo.getBroadcastTo() != null) {
            String eventHandlingMethodName = createEventHandlingMethodName(eventInfo.getEvent()
                                                                                    .getSimpleName()
                                                                                    .toString(),
                                                                           eventInfo.getCalledMethod());
            // check event handler
            for (EventHandlerInfo eventHandlerInfo : applicationInfo.getEventHandlers(true)) {
              if (processingEnv.getTypeUtils()
                               .isSubtype(eventHandlerInfo.getEventHandler()
                                                          .asType(),
                                          eventInfo.getBroadcastTo()
                                                   .asType())) {
                checkEventMethodImplemention(eventHandlerInfo.getEventHandler(),
                                             eventInfo.getEvent(),
                                             eventHandlingMethodName,
                                             Messages.INVALID_EVENT_METHOD_PRESENTER);
              }
            }
          }
        }
      }
    }
  }

  private void checkHistoryConvertersAndEventBus() {
    for (ModuleInfo moduleInfo : applicationInfo.getModules()) {
      EventBusInfo eventBusInfo = moduleInfo.getEventBusInfo();
      if (eventBusInfo.getEvents() != null) {
        for (EventInfo eventInfo : eventBusInfo.getEvents()) {
          if (eventInfo.getHistoryConverter() != null) {
            HistoryConverterInfo historyConverterInfo = applicationInfo.getHistoryConverter(eventInfo.getHistoryConverter()
                                                                                                     .getQualifiedName()
                                                                                                     .toString());
            if (historyConverterInfo == null) {
              messagerUtils.error(eventBusInfo.getEventBus(),
                                  Messages.MISSING_HISTORY_CONVERTER,
                                  moduleInfo.getModuleName(),
                                  eventInfo.getHistoryConverter()
                                           .getSimpleName()
                                           .toString());

            } else {
              // eventBus correct injected?
              if (!historyConverterInfo.getInjectedEventBus()
                                       .getSimpleName()
                                       .toString()
                                       .equals(eventBusInfo.getEventBus()
                                                           .getSimpleName()
                                                           .toString())) {
                messagerUtils.error(eventBusInfo.getEventBus(),
                                    Messages.INVALID_EVENT_BUS,
                                    eventBusInfo.getEventBus()
                                                .getSimpleName()
                                                .toString(),
                                    eventInfo.getHistoryConverterName(),
                                    eventBusInfo.getEventBus()
                                                .getSimpleName()
                                                .toString(),
                                    eventInfo.getHistoryConverterName());
              } else {
                if (historyConverterInfo.getType()
                                        .equals(History.HistoryConverterType.DEFAULT)) {
                  checkEventMethodImplemention(historyConverterInfo.getHistoryConverter(),
                                               eventInfo.getEvent(),
                                               createEventHandlingMethodName(eventInfo.getEvent()
                                                                                      .getSimpleName()
                                                                                      .toString(),
                                                                             eventInfo.getCalledMethod()),
                                               Messages.INVALID_EVENT_METHOD_HISTORY_CONVERTER);
                } else if (historyConverterInfo.getType()
                                               .equals(History.HistoryConverterType.SIMPLE)) {
                  List<ExecutableElement> convertToTokenMethods = Mvp4gUtils.getImplementingMethod(processingEnv,
                                                                                                   historyConverterInfo.getHistoryConverter(),
                                                                                                   "convertToToken");

                  boolean methodFound = false;
                  for (ExecutableElement convertToTokenMethod : convertToTokenMethods) {
                    List<VariableElement> listOfParameters = new ArrayList<>();
                    // remove first method because that the eventName
                    if (convertToTokenMethod.getParameters()
                                            .size() > 0) {
                      for (int i = 1; i < convertToTokenMethod.getParameters()
                                                              .size(); i++) {
                        listOfParameters.add(convertToTokenMethod.getParameters()
                                                                 .get(i));
                      }
                    }
                    if (Mvp4gUtils.hasSameSignature(listOfParameters,
                                                    eventInfo.getEvent()
                                                             .getParameters())) {
                      methodFound = true;
                      break;
                    }
                  }
                  if (!methodFound) {
                    messagerUtils.error(historyConverterInfo.getHistoryConverter(),
                                        Messages.INVALID_CONVERT_TO_TOKEN_HISTORY_CONVERTER);
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  private void checkEventHandlersAndEventBus() {
    for (ModuleInfo moduleInfo : applicationInfo.getModules()) {
      EventBusInfo eventBusInfo = moduleInfo.getEventBusInfo();
      if (eventBusInfo.getEvents() != null) {
        for (EventInfo eventInfo : eventBusInfo.getEvents()) {
          if (eventInfo.getHandlers() != null) {
            for (TypeElement handler : eventInfo.getHandlers()) {
              EventHandlerInfo eventHandlerInfo = applicationInfo.getEventHandler(handler.getQualifiedName()
                                                                                         .toString());
              if (eventHandlerInfo == null) {
                messagerUtils.error(eventBusInfo.getEventBus(),
                                    Messages.MISSING_PRESENTER,
                                    handler.getSimpleName()
                                           .toString());
              } else {
                // eventBus correct injected?
                if (!eventHandlerInfo.getInjectedEventBus()
                                     .getSimpleName()
                                     .toString()
                                     .equals(eventBusInfo.getEventBus()
                                                         .getSimpleName()
                                                         .toString())) {
                  messagerUtils.error(eventBusInfo.getEventBus(),
                                      Messages.INVALID_EVENT_BUS,
                                      eventBusInfo.getEventBus()
                                                  .getSimpleName()
                                                  .toString(),
                                      eventHandlerInfo.getEventHandlerName(),
                                      eventBusInfo.getEventBus()
                                                  .getSimpleName()
                                                  .toString(),
                                      eventHandlerInfo.getEventHandlerName());
                } else {
                  checkEventMethodImplemention(eventHandlerInfo.getEventHandler(),
                                               eventInfo.getEvent(),
                                               createEventHandlingMethodName(eventInfo.getEvent()
                                                                                      .getSimpleName()
                                                                                      .toString(),
                                                                             eventInfo.getCalledMethod()),
                                               Messages.INVALID_EVENT_METHOD_PRESENTER);
                }
              }
            }
          }
        }
      }
    }
  }

//==============================================================================

  private String createEventHandlingMethodName(String eventName,
                                               String calledMethod) {
    if (calledMethod != null &&
        calledMethod.trim()
                    .length() > 0) {
      return calledMethod;
    } else {
      String name = "on";
      name += eventName.substring(0,
                                  1)
                       .toUpperCase();
      if (eventName.length() > 1) {
        name += eventName.substring(1);
      }
      return name;
    }
  }

  private void checkEventMethodImplemention(TypeElement element,
                                            ExecutableElement eventMethod,
                                            String eventHandlingMethodName,
                                            String message) {
    if (!Mvp4gUtils.isImplementingMethod(processingEnv,
                                         element,
                                         eventMethod,
                                         eventHandlingMethodName)) {
      messagerUtils.error(element,
                          message,
                          element.getSimpleName()
                                 .toString(),
                          eventMethod.getSimpleName()
                                     .toString(),
                          eventHandlingMethodName);
    }
  }
}
