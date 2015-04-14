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

import javax.lang.model.element.TypeElement;

public class PresenterInfo
  extends EventHandlerInfo {

  private TypeElement view;
  private String      viewName;

  private TypeElement injectedView;

//------------------------------------------------------------------------------

  @SuppressWarnings("unused")
  private PresenterInfo() {
    this(null,
         null);
  }

  public PresenterInfo(String eventHandlerName,
                       TypeElement eventHandler) {
    super(eventHandlerName,
          eventHandler,
          true);
  }

//------------------------------------------------------------------------------

  public TypeElement getView() {
    return view;
  }

  public void setView(TypeElement view) {
    this.view = view;
  }

  @SuppressWarnings("unused")
  public String getViewName() {
    return viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  public TypeElement getInjectedView() {
    return injectedView;
  }

  public void setInjectedView(TypeElement injectedView) {
    this.injectedView = injectedView;
  }
}
