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
  private String      historyConverterName;

  private TypeElement[] activate;
  private String[]      activateNames;

  private TypeElement[] deactivate;
  private String[]      deactivateNames;

  private String[]    name;
  private boolean     navigationEvent;
  private boolean     passive;
  private TypeElement broadcastTo;


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

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public void setForwardToParent(Boolean forwardToParent) {
    this.forwardToParent = forwardToParent;
  }

  public ExecutableElement getEvent() {
    return event;
  }

  public void setEvent(ExecutableElement event) {
    this.event = event;
  }

  public TypeElement[] getHandlers() {
    return handlers;
  }

  public void setHandlers(TypeElement[] handlers) {
    this.handlers = handlers;
  }

  public String[] getHandlerNames() {
    return handlerNames;
  }

  public void setHandlerNames(String[] handlerNames) {
    this.handlerNames = handlerNames;
  }

  public TypeElement[] getBind() {
    return bind;
  }

  public void setBind(TypeElement[] bind) {
    this.bind = bind;
  }

  public String[] getBindNames() {
    return bindNames;
  }

  public void setBindNames(String[] bindNames) {
    this.bindNames = bindNames;
  }

  public TypeElement[] getForwardToModules() {
    return forwardToModules;
  }

  public void setForwardToModules(TypeElement[] forwardToModules) {
    this.forwardToModules = forwardToModules;
  }

  public boolean isForwardToParent() {
    return forwardToParent;
  }

  public void setForwardToParent(boolean forwardToParent) {
    this.forwardToParent = forwardToParent;
  }

  public String getCalledMethod() {
    return calledMethod;
  }

  public void setCalledMethod(String calledMethod) {
    this.calledMethod = calledMethod;
  }

  public TypeElement getHistoryConverter() {
    return historyConverter;
  }

  public void setHistoryConverter(TypeElement historyConverter) {
    this.historyConverter = historyConverter;
  }

  public String getHistoryConverterName() {
    return historyConverterName;
  }

  public void setHistoryConverterName(String historyConverterName) {
    this.historyConverterName = historyConverterName;
  }

  public TypeElement[] getActivate() {
    return activate;
  }

  public void setActivate(TypeElement[] activate) {
    this.activate = activate;
  }

  public String[] getActivateNames() {
    return activateNames;
  }

  public void setActivateNames(String[] activateNames) {
    this.activateNames = activateNames;
  }

  public TypeElement[] getDeactivate() {
    return deactivate;
  }

  public void setDeactivate(TypeElement[] deactivate) {
    this.deactivate = deactivate;
  }

  public String[] getDeactivateNames() {
    return deactivateNames;
  }

  public void setDeactivateNames(String[] deactivateNames) {
    this.deactivateNames = deactivateNames;
  }

  public String[] getName() {
    return name;
  }

  public void setName(String[] name) {
    this.name = name;
  }

  public boolean isNavigationEvent() {
    return navigationEvent;
  }

  public void setNavigationEvent(boolean navigationEvent) {
    this.navigationEvent = navigationEvent;
  }

  public boolean isPassive() {
    return passive;
  }

  public void setPassive(boolean passive) {
    this.passive = passive;
  }

  public TypeElement getBroadcastTo() {
    return broadcastTo;
  }

  public void setBroadcastTo(TypeElement broadcastTo) {
    this.broadcastTo = broadcastTo;
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
