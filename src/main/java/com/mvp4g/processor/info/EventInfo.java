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

public class EventInfo {

  private String name;

  private Map<String, PresenterInfo> presenters;

//------------------------------------------------------------------------------

  private EventInfo() {
    this(null);
  }

  public EventInfo(String name) {
    super();

    presenters = new HashMap<String, PresenterInfo>();

    this.name = name;
  }

//------------------------------------------------------------------------------

//  /**
//   * Returns the name of the module
//   * @return module name
//   */
//  public String getName() {
//    return name;
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
