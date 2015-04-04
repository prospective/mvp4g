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

public class ModuleInfo {

  private String       moduleName;
  private EventBusInfo eventBusInfo;

  private TypeElement module;

  private boolean isAsync;
  private boolean aufoDisplay;

//------------------------------------------------------------------------------

  private ModuleInfo() {
    this(null);
  }

  public ModuleInfo(String moduleName) {
    this.moduleName = moduleName;

    eventBusInfo = new EventBusInfo(moduleName);
    isAsync = false;
    aufoDisplay = false;
  }

//------------------------------------------------------------------------------

  /**
   * Name of the representing module
   *
   * @return name of the module
   */
  public String getModuleName() {
    return moduleName;
  }

  /**
   * EventBusInfo element
   *
   * @return the eventbus info element
   */
  public EventBusInfo getEventBusInfo() {
    return eventBusInfo;
  }

  /**
   * returns the module Element. In case of root module it
   * will return null
   *
   * @return null (root Module) or module
   */
  public TypeElement getModule() {
    return module;
  }

  /**
   * Set the module type element.
   *
   * @param module
   */
  public void setModule(TypeElement module) {
    this.module = module;
  }

  /**
   * Returns true if the module is to load async.<br>
   * Only for child modules
   *
   * @return true -> load async
   */
  public boolean isAsync() {
    return isAsync;
  }

  public void setIsAsync(boolean isAsync) {
    this.isAsync = isAsync;
  }


  /**
   * Returns true if the module displays the start view of the module at load.<br>
   * Only for child modules
   *
   * @return true -> show start view
   */
  public boolean isAufoDisplay() {
    return aufoDisplay;
  }

  public void setAufoDisplay(boolean aufoDisplay) {
    this.aufoDisplay = aufoDisplay;
  }

  //
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
  //
  //  public String getStartPresenterName() {
  //    return startPresenterName;
  //  }
  //
  //  public void setStartPresenterName(String startPresenterName) {
  //    this.startPresenterName = startPresenterName;
  //  }
  //
  //  public TypeElement getStartPresenter() {
  //    return startPresenter;
  //  }
  //
  //  public void setStartPresenter(TypeElement startPresenter) {
  //    this.startPresenter = startPresenter;
  //  }
  //
  //  public boolean isHistoryOnStart() {
  //    return historyOnStart;
  //  }
  //
  //  public void setHistoryOnStart(boolean historyOnStart) {
  //    this.historyOnStart = historyOnStart;
  //  }
  //
  //  public TypeElement[] getGinModules() {
  //    return ginModules;
  //  }
  //
  //  public void setGinModules(TypeElement[] ginModules) {
  //    this.ginModules = ginModules;
  //  }
  //
  //  public String getName() {
  //    return name;
  //  }
  //
  //  public String[] getGinModuleProperties() {
  //    return ginModuleProperties;
  //  }
  //
  //  public void setGinModuleProperties(String[] ginModuleProperties) {
  //    this.ginModuleProperties = ginModuleProperties;
  //  }
}
