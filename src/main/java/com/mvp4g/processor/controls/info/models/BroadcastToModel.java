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

package com.mvp4g.processor.controls.info.models;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hoss on 12.04.15.
 */
public class BroadcastToModel {

  /* name */
  private String                         name;
  /* type element */
  private TypeElement                    broadcastTo;
  /* used for method */
  private Map<String, ExecutableElement> methods;

  public BroadcastToModel(String name,
                          TypeElement broadcastTo,
                          ExecutableElement method) {
    this.methods = new HashMap<>();

    this.name = name;
    this.broadcastTo = broadcastTo;

    methods.put(method.getSimpleName()
                      .toString(),
                method);
  }

  public String getName() {
    return name;
  }

  public TypeElement getBroadcastTo() {
    return broadcastTo;
  }

  public void addMethod(ExecutableElement element) {
    methods.put(element.getSimpleName()
                       .toString(),
                element);
  }

  public ExecutableElement getMethod(String name) {
    return methods.get(name);
  }

  public Collection<ExecutableElement> getMethods() {
    return methods.values();
  }
}
