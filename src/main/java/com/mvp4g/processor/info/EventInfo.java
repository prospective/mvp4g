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
