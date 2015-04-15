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
 * An Mvp4g Service configuration element.<br>
 *
 * @author javier
 */
public class ServiceElement
  extends SimpleMvp4gElement {

  public ServiceElement() {
    super("service");
  }

  public String getPath() {
    return getProperty("path");
  }

  public void setPath(String path) {
    setProperty("path",
                path);
  }

  public boolean hasPath() {
    return getPath() != null;
  }

  public String getGeneratedClassName() {
    String generatedClass = getProperty("generatedClassName");
    if (generatedClass == null) {
      generatedClass = getClassName() + "Async";
    }
    return generatedClass;
  }

  public void setGeneratedClassName(String generatedClassName) {
    setProperty("generatedClassName",
                generatedClassName);
  }

}
