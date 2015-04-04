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

package com.mvp4g.processor.info;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class EventInfo {

  /* name of the event */
  private String            eventName;
  private ExecutableElement event;

  /* event handlers */
  private TypeElement[] handlers;
  private String[]      handlerNames;

  private TypeElement[] bind;
  private String[]      bindNames;

  private TypeElement[] forwardToModules;
  private boolean       forwardToParent;

  private String calledMethod;

  private TypeElement historyConverter;

//------------------------------------------------------------------------------

  private EventInfo() {
    this(null);
  }

  public EventInfo(String eventName) {
    super();

    this.eventName = eventName;
  }

//------------------------------------------------------------------------------

  /**
   * Returns the name of the event
   *
   * @return name of the event
   */
  public String getEventName() {
    return eventName;
  }

  public void setHandlers(TypeElement[] handlers) {
    this.handlers = handlers;
  }

  public void setEvent(ExecutableElement event) {
    this.event = event;
  }

  public void setHandlerNames(String[] handlerNames) {
    this.handlerNames = handlerNames;
  }

  public void setBindNames(String[] bindNames) {
    this.bindNames = bindNames;
  }

  public void setBind(TypeElement[] bind) {
    this.bind = bind;
  }

  public void setForwardToModules(TypeElement[] forwardToModules) {
    this.forwardToModules = forwardToModules;
  }

  public void setForwardToParent(Boolean forwardToParent) {
    this.forwardToParent = forwardToParent;
  }

  public void setHistoryConverter(TypeElement historyConverter) {
    this.historyConverter = historyConverter;
  }

//  /**
//   * @return the currentEventBus
//   */
//  public TypeElement getCurrentEventBus() {
//    return currentEventBus;
//  }
//
//  /**
//   * @return the parentEventBus
//   */
//  public TypeElement getParentEventBus() {
//    return parentEventBus;
//  }
//
//  /**
//   * @param currentEventBus the currentEventBus to set
//   */
//  public void setCurrentEventBus(TypeElement currentEventBus) {
//    this.currentEventBus = currentEventBus;
//  }
//
//  /**
//   * @param parentEventBus the parentEventBus to set
//   */
//  public void setParentEventBus(TypeElement parentEventBus) {
//    this.parentEventBus = parentEventBus;
//  }

}
