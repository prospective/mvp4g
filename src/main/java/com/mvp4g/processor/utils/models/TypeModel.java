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

package com.mvp4g.processor.utils.models;

import javax.lang.model.element.TypeElement;

/**
 * Created by hoss on 10.04.15.
 */
public class TypeModel {

  private String      type;
  private TypeElement argument;

  public TypeModel(String type,
                   TypeElement argument) {
    this.type = type;
    this.argument = argument;
  }

  public String getType() {
    return type;
  }

  public TypeElement getArgument() {
    return argument;
  }
}
