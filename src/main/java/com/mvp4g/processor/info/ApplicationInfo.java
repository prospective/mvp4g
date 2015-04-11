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

import java.util.HashMap;
import java.util.Map;

public class ApplicationInfo {

  /* modules of the application */
  private Map<String, ModuleInfo>           modules;
  /* event handler info */
  private Map<String, EventHandlerInfo>     eventHandlerInfos;
  /* presenter info */
  private Map<String, PresenterInfo>        presenters;
  /* history infos */
  private Map<String, HistoryConverterInfo> histroyConverterInfos;

//------------------------------------------------------------------------------

  public ApplicationInfo() {
    super();

    modules = new HashMap<>();
    presenters = new HashMap<>();
    histroyConverterInfos = new HashMap<>();
    eventHandlerInfos = new HashMap<>();
  }

  @Deprecated
  public ApplicationInfo(Map<String, ModuleInfo> modules,
                         Map<String, String> eventBus) {
  }

//------------------------------------------------------------------------------

  /**
   * Add a module info
   *
   * @param moduleName name of the module info
   * @param info       module info
   */
  public void addModules(String moduleName,
                         ModuleInfo info) {
    modules.put(moduleName,
                info);
  }

  /**
   * Add a history converter info
   *
   * @param historyConverterName name of the history converter info
   * @param info                 module info
   */
  public void addHistoryConverter(String historyConverterName,
                                  HistoryConverterInfo info) {
    histroyConverterInfos.put(historyConverterName,
                              info);
  }

  /**
   * Add a presenter info
   *
   * @param presenterName name of the presenter info
   * @param info          presenter info
   * @return the modules
   */
  public void addPresenter(String presenterName,
                           PresenterInfo info) {
    presenters.put(presenterName,
                   info);
  }

  /**
   * Add a event handler info
   *
   * @param eventHandlerName name of the event handler info
   * @param info             event handler info
   * @return the modules
   */
  public void addEventHandler(String eventHandlerName,
                              EventHandlerInfo info) {
    eventHandlerInfos.put(eventHandlerName,
                          info);
  }

  /**
   * Returns the ModuleInfo for the requested module
   *
   * @param module name of the module info
   * @return the modules
   */
  public ModuleInfo getModule(String module) {
    return modules.get(module);
  }

  /**
   * Returns module info for the requested event bus name
   *
   * @param eventBusName name of the event bus
   * @return the module info
   */
  public ModuleInfo getModuleInfoForEventBus(String eventBusName) {
    for (ModuleInfo info : modules.values()) {
      if (info.getEventBusInfo()
              .getEventBus() == null) {
        return null;
      } else {
        if (info.getEventBusInfo()
                .getEventBus()
                .getQualifiedName()
                .toString()
                .equals(eventBusName)) {
          return info;
        }
      }
    }
    return null;
  }

  /**
   * Returns history converter info for the requested history converter name
   *
   * @param historyConverterName name of the presenter
   * @return the history converter info
   */
  public HistoryConverterInfo getHistoryConverter(String historyConverterName) {
    for (HistoryConverterInfo info : histroyConverterInfos.values()) {
      if (info.getName()
              .equals(historyConverterName)) {
        return info;
      }
    }
    return null;
  }

  /**
   * Returns event handler info for the requested event handler name
   *
   * @param eventHandlerName name of the event handler
   * @return the event handler info
   */
  public EventHandlerInfo getEventHandler(String eventHandlerName) {
    for (EventHandlerInfo info : eventHandlerInfos.values()) {
      if (info.getEventHandlerName()
              .equals(eventHandlerName)) {
        return info;
      }
    }
    return null;
  }

  /**
   * Returns presenter info for the requested presenter name
   *
   * @param presenterName name of the presenter
   * @return the presenter info
   */
  public PresenterInfo getPresenter(String presenterName) {
    for (PresenterInfo info : presenters.values()) {
      if (info.getPresenterName()
              .equals(presenterName)) {
        return info;
      }
    }
    return null;
  }
}
