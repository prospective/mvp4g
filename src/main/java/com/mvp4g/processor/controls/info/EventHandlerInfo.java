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

package com.mvp4g.processor.controls.info;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

public class EventHandlerInfo {

  /* info name */
  private String      eventHandlerName;
  /* type element of the presenter */
  private TypeElement eventHandler;
  /* attribute name*/
  private String      name;
  private boolean     multiple;
  private TypeElement async;

  private TypeElement eventBus;
  private TypeElement injectedEventBus;

  private ExecutableElement       bindMethod;
  private List<ExecutableElement> eventHandlingMethods;

//------------------------------------------------------------------------------

  private EventHandlerInfo() {
    this(null,
         null);
  }

  public EventHandlerInfo(String eventHandlerName,
                          TypeElement eventHandler) {
    super();

    this.eventHandlerName = eventHandlerName;
    this.eventHandler = eventHandler;

    eventHandlingMethods = new ArrayList<>();
  }

//------------------------------------------------------------------------------

  public String getEventHandlerName() {
    return eventHandlerName;
  }

  public TypeElement getEventHandler() {
    return eventHandler;
  }

  public String getName() {
    return name;
  }

  public boolean isMultiple() {
    return multiple;
  }

  public TypeElement getAsync() {
    return async;
  }

  public TypeElement getEventBus() {
    return eventBus;
  }

  public TypeElement getInjectedEventBus() {
    return injectedEventBus;
  }

  public ExecutableElement getBindMethod() {
    return bindMethod;
  }

  public List<ExecutableElement> getEventHandlingMethods() {
    return eventHandlingMethods;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMultiple(boolean multiple) {
    this.multiple = multiple;
  }

  public void setAsync(TypeElement async) {
    this.async = async;
  }

  public void setEventBus(TypeElement eventBus) {
    this.eventBus = eventBus;
  }

  public void setInjectedEventBus(TypeElement injectedEventBus) {
    this.injectedEventBus = injectedEventBus;
  }

  public void setBindMethod(ExecutableElement bindMethod) {
    this.bindMethod = bindMethod;
  }

  public void setEventHandlingMethods(List<ExecutableElement> eventHandlingMethods) {
    this.eventHandlingMethods = eventHandlingMethods;
  }
}
