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

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

public class PresenterInfo {

  /* info name */
  private String      presenterName;
  /* type element of the presenter */
  private TypeElement presenter;
  /* attribute name*/
  private String      name;
  private TypeElement view;
  private String      viewName;
  private boolean     multiple;
  private TypeElement async;

  private TypeElement eventBus;

  private TypeElement injectedView;
  private TypeElement injectedEventBus;

  private ExecutableElement       bindMethod;
  private List<ExecutableElement> eventHandlingMethods;

//------------------------------------------------------------------------------

  private PresenterInfo() {
    this(null,
         null);
  }

  public PresenterInfo(String presenterName,
                       TypeElement presenter) {
    super();

    this.presenterName = presenterName;
    this.presenter = presenter;

    eventHandlingMethods = new ArrayList<>();
  }

//------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TypeElement getEventBus() {
    return eventBus;
  }

  public void setEventBus(TypeElement eventBus) {
    this.eventBus = eventBus;
  }

  public String getPresenterName() {
    return presenterName;
  }

  public TypeElement getView() {
    return view;
  }

  public void setView(TypeElement view) {
    this.view = view;
  }

  public String getViewName() {
    return viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  public boolean isMultiple() {
    return multiple;
  }

  public void setMultiple(boolean multiple) {
    this.multiple = multiple;
  }

  public TypeElement getAsync() {
    return async;
  }

  public void setAsync(TypeElement async) {
    this.async = async;
  }

  public TypeElement getPresenter() {
    return presenter;
  }

  public TypeElement getInjectedView() {
    return injectedView;
  }

  public void setInjectedView(TypeElement injectedView) {
    this.injectedView = injectedView;
  }

  public TypeElement getInjectedEventBus() {
    return injectedEventBus;
  }

  public void setInjectedEventBus(TypeElement injectedEventBus) {
    this.injectedEventBus = injectedEventBus;
  }

  public List<ExecutableElement> getEventHandlingMethods() {
    return eventHandlingMethods;
  }

  public void setEventHandlingMethods(List<ExecutableElement> eventHandlingMethods) {
    this.eventHandlingMethods = eventHandlingMethods;
  }

  public ExecutableElement getBindMethod() {
    return bindMethod;
  }

  public void setBindMethod(ExecutableElement bindMethod) {
    this.bindMethod = bindMethod;
  }
}
