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

import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;

public class EventBusInfo {

  private String moduleName;
  private String eventBusName;

  private String      startPresenterName;
  private TypeElement startPresenter;

  private boolean historyOnStart;

  private TypeElement[] ginModules;
  private String[]      ginModuleProperties;

  private TypeElement eventBus;
  private TypeElement parentEventBus;

  private Map<String, EventInfo> events;

//------------------------------------------------------------------------------

  private EventBusInfo() {
    this(null);
  }

  public EventBusInfo(String moduleName) {
    this.moduleName = moduleName;

    this.events = new HashMap<>();
  }

//------------------------------------------------------------------------------

  public void addEvent(EventInfo info) {
    events.put(info.getEventName(), info);
  }

  public String getStartPresenterName() {
    return startPresenterName;
  }

  public void setStartPresenterName(String startPresenterName) {
    this.startPresenterName = startPresenterName;
  }

  public TypeElement getStartPresenter() {
    return startPresenter;
  }

  public void setStartPresenter(TypeElement startPresenter) {
    this.startPresenter = startPresenter;
  }

  public boolean isHistoryOnStart() {
    return historyOnStart;
  }

  public void setHistoryOnStart(boolean historyOnStart) {
    this.historyOnStart = historyOnStart;
  }

  public TypeElement[] getGinModules() {
    return ginModules;
  }

  public void setGinModules(TypeElement[] ginModules) {
    this.ginModules = ginModules;
  }

  public String[] getGinModuleProperties() {
    return ginModuleProperties;
  }

  public void setGinModuleProperties(String[] ginModuleProperties) {
    this.ginModuleProperties = ginModuleProperties;
  }

  public TypeElement getEventBus() {
    return eventBus;
  }

  public void setEventBus(TypeElement eventBus) {
    this.eventBus = eventBus;
  }

  public TypeElement getParentEventBus() {
    return parentEventBus;
  }

  public void setParentEventBus(TypeElement parentEventBus) {
    this.parentEventBus = parentEventBus;
  }

  public void setEventBusName(String eventBusName) {
    this.eventBusName = eventBusName;
  }

//------------------------------------------------------------------------------

  //  /**
  //   * Returns the module. if it is the root model, null will be return
  //   *
  //   * @return module null or the name of the child module
  //   */
  //  public TypeElement getModule() {
  //    return module;
  //  }
  //
  //  /**
  //   * Sets the value of the module attribute
  //   *
  //   * @param module
  //   */
  //  public void setModule(TypeElement module) {
  //    this.module = module;
  //  }
  //
  //  /**
  //   * @return the currentEventBus
  //   */
  //  public TypeElement getCurrentEventBus() {
  //    return currentEventBus;
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
  //   * @return the parentEventBus
  //   */
  //  public TypeElement getParentEventBus() {
  //    return parentEventBus;
  //  }
  //
  //  /**
  //   * @param parentEventBus the parentEventBus to set
  //   */
  //  public void setParentEventBus(TypeElement parentEventBus) {
  //    this.parentEventBus = parentEventBus;
  //  }
}
