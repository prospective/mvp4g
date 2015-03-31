package com.mvp4g.processor.info;

import com.mvp4g.client.Mvp4gModule;

import java.util.HashMap;
import java.util.Map;

public class ApplicationInfo {

  /* modules of the application */
  Map<String, ModuleInfo> modules;

//  Map<String, String> eventBus;
//

//------------------------------------------------------------------------------

  public ApplicationInfo() {
    super();

    modules = new HashMap<>();
    // add default module info
    modules.put(Mvp4gModule.class.getCanonicalName(),
                new ModuleInfo(Mvp4gModule.class.getCanonicalName()));
  }

  @Deprecated
  public ApplicationInfo(Map<String, ModuleInfo> modules,
                         Map<String, String> eventBus) {
//    super();
//    this.modules = modules;
//    this.eventBus = eventBus;
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

//   * @param modules the modules to set
//   */
//  public void setModules(Map<String, ModuleInfo> modules) {
//    this.modules = modules;
//  }
//
//  /**
//   * @return the eventBus
//   */
//  public Map<String, String> getEventBus() {
//    return eventBus;
//  }
//
//  /**
//   * @param eventBus the eventBus to set
//   */
//  public void setEventBus(Map<String, String> eventBus) {
//    this.eventBus = eventBus;
//  }
}
