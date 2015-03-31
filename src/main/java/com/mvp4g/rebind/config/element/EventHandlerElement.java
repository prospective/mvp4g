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
package com.mvp4g.rebind.config.element;

/**
 * An Mvp4g EventHandler configuration element.<br>
 *
 * @author Dan Persa
 */
public class EventHandlerElement
  extends Mvp4gWithServicesElement {

  public EventHandlerElement() {
    super("eventHandler");
  }

  public EventHandlerElement(String tagName) {
    super(tagName);
  }

  public String getMultiple() {
    return getProperty("multiple");
  }

  public boolean isMultiple() {
    return Boolean.TRUE.toString().equalsIgnoreCase(getMultiple());
  }

  public void setMultiple(String multiple) {
    setProperty("multiple",
                multiple);
  }

  public String getAsync() {
    return getProperty("async");
  }

  public boolean isAsync() {
    return (getAsync() != null);
  }

  public void setAsync(String async) {
    setProperty("async",
                async);
  }
}
