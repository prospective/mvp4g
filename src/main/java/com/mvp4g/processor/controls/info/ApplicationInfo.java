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

import com.mvp4g.processor.controls.info.models.BroadcastToModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ApplicationInfo {

  /* modules of the application */
  private Map<String, ModuleInfo>           modules;
  /* event handler info */
  private Map<String, EventHandlerInfo>     eventHandlerInfos;
  /* history infos */
  private Map<String, HistoryConverterInfo> histroyConverterInfos;
  /* list of all broadcast interfaces odf this application */
  private Map<String, BroadcastToModel>     broadcastTos;

//------------------------------------------------------------------------------

  public ApplicationInfo() {
    super();

    modules = new HashMap<>();
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
   * @param eventHandlerName name of the presenter info
   * @param info             presenter info
   */
  public void addPresenter(String eventHandlerName,
                           PresenterInfo info) {
    eventHandlerInfos.put(eventHandlerName,
                          info);
  }

  /**
   * Add a event handler info
   *
   * @param eventHandlerName name of the event handler info
   * @param info             event handler info
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
   *
   * @return the modules
   */
  public ModuleInfo getModule(String module) {
    return modules.get(module);
  }

  /**
   * Returns all module infos of this application
   *
   * @return all module info
   */
  public Collection<ModuleInfo> getModules() {
    return modules.values();
  }

  /**
   * Returns module info for the requested event bus name
   *
   * @param eventBusName name of the event bus
   *
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
   *
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
   * Returns event handler info for the requested event handler / presenter name.
   * (includes event handlers and presenters!)
   *
   * @param eventHandlerName name of the event handler
   *
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
   *
   * @return the presenter info
   */
  public PresenterInfo getPresenter(String presenterName) {
    for (PresenterInfo info : getPresenters()) {
      if (info.getEventHandlerName()
              .equals(presenterName)) {
        return info;
      }
    }
    return null;
  }

  /**
   * Returns all presenter infos of the application
   *
   * @return list of presenters of this application
   */
  public Collection<PresenterInfo> getPresenters() {
    Map<String, PresenterInfo> m = new HashMap<>();
    for (EventHandlerInfo info : eventHandlerInfos.values()) {
      if (info.isPresenter()) {
        m.put(info.getEventHandlerName(),
              (PresenterInfo) info);
      }
    }
    return m.values();
  }

  /**
   * Returns all presenter infos of the application
   *
   * @return list of presenters of this application
   */
  public Collection<EventHandlerInfo> getEventHandlers(boolean includePresenter) {
    Map<String, EventHandlerInfo> m = new HashMap<>();
    for (EventHandlerInfo info : eventHandlerInfos.values()) {
      if (info.isPresenter() && includePresenter) {
        m.put(info.getEventHandlerName(),
              info);
      }
    }
    return m.values();
  }

  /**
   * returns the list of broadcast Elements
   *
   * @return list of broadcast elements
   */
  public Collection<BroadcastToModel> getBroadcastTos() {
    return broadcastTos.values();
  }

  /**
   * set the list of broadcast elements of this application
   *
   * @param broadcastTos list of broadcast interfaces
   */
  public void setBroadcastTos(Map<String, BroadcastToModel> broadcastTos) {
    this.broadcastTos = broadcastTos;
  }
}
