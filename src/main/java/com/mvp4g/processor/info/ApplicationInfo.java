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
  Map<String, ModuleInfo> modules;

//------------------------------------------------------------------------------

  public ApplicationInfo() {
    super();

    modules = new HashMap<>();
  }

  @Deprecated
  public ApplicationInfo(Map<String, ModuleInfo> modules,
                         Map<String, String> eventBus) {
  }

//------------------------------------------------------------------------------

  /**
   * Returns the ModuleInfo for the requested module
   *
   * @param module name of the module info
   * @return the modules
   */
  public void addModules(String module,
                         ModuleInfo info) {
    modules.put(module, info);
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
   *
   * @return the module info
   */
  public ModuleInfo getModuleInfoForEventBus(String eventBusName) {
    for (ModuleInfo info : modules.values()) {
      if (info.getEventBusInfo().getEventBus().getQualifiedName().toString().equals(eventBusName)) {
        return info;
      }
    }
    return null;
  }
}
