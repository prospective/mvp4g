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

import com.mvp4g.client.annotation.History;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.List;

public class HistoryConverterInfo {

  /* history converter element */
  private TypeElement                  historyConverter;
  /* name of the converter */
  private String                       name;
  /* event handling methods */
  private List<ExecutableElement>      eventHandlingMethods;
  /* injected eventbus */
  private TypeElement                  injectedEventBus;
  /* type of the history converter */
  private History.HistoryConverterType type;

//------------------------------------------------------------------------------

  @SuppressWarnings("unused")
  private HistoryConverterInfo() {
    this(null);
  }

  public HistoryConverterInfo(TypeElement historyConverter) {
    super();

    this.historyConverter = historyConverter;
    this.name = historyConverter.getSimpleName()
                                .toString();

    eventHandlingMethods = new ArrayList<>();
    type = History.HistoryConverterType.DEFAULT;
  }

//------------------------------------------------------------------------------

  public TypeElement getHistoryConverter() {
    return historyConverter;
  }

  public void setHistoryConverter(TypeElement historyConverter) {
    this.historyConverter = historyConverter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ExecutableElement> getEventHandlingMethods() {
    return eventHandlingMethods;
  }

  public void setEventHandlingMethods(List<ExecutableElement> eventHandlingMethods) {
    this.eventHandlingMethods = eventHandlingMethods;
  }

  public TypeElement getInjectedEventBus() {
    return injectedEventBus;
  }

  public void setInjectedEventBus(TypeElement injectedEventBus) {
    this.injectedEventBus = injectedEventBus;
  }

  public History.HistoryConverterType getType() {
    return type;
  }

  public void setType(History.HistoryConverterType type) {
    this.type = type;
  }
}
