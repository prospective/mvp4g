package com.mvp4g.processor.info;

import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;

public class ModuleInfo {

  private String name;

  private TypeElement currentEventBus;
  private TypeElement parentEventBus;

  private Map<String, EventInfo> events;
  private Map<String, PresenterInfo>  presenters;

//------------------------------------------------------------------------------

  private ModuleInfo() {
    this(null);
  }

  public ModuleInfo(String name) {
    super();

    events = new HashMap<String, EventInfo>();
    presenters = new HashMap<String, PresenterInfo>();

    this.name = name;
  }

//------------------------------------------------------------------------------

  /**
   * Returns the name of the module
   * @return module name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the currentEventBus
   */
  public TypeElement getCurrentEventBus() {
    return currentEventBus;
  }

  /**
   * @return the parentEventBus
   */
  public TypeElement getParentEventBus() {
    return parentEventBus;
  }

  /**
   * @param currentEventBus the currentEventBus to set
   */
  public void setCurrentEventBus(TypeElement currentEventBus) {
    this.currentEventBus = currentEventBus;
  }

  /**
   * @param parentEventBus the parentEventBus to set
   */
  public void setParentEventBus(TypeElement parentEventBus) {
    this.parentEventBus = parentEventBus;
  }

}
