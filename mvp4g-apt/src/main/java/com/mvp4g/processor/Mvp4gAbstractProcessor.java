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

package com.mvp4g.processor;

import javax.annotation.processing.AbstractProcessor;

/**
 * Created by hoss on 12.05.15.
 */
public abstract class Mvp4gAbstractProcessor
  extends AbstractProcessor {

  private static final String FILE_DELIMITER = "$";
  private static final String FILE_TYPE = "mvp4g";
  private static final String INFO_TYPE_DELIMITER = "_";


//------------------------------------------------------------------------------

  public Mvp4gAbstractProcessor() {
    super();
  }

//------------------------------------------------------------------------------

  String createFileName(String packageName,
                        String className,
                        InfoType type) {
    StringBuffer name = new StringBuffer();
    if (packageName != null) {
      name.append(packageName.replaceAll("\\\\",
                                         Mvp4gAbstractProcessor.FILE_DELIMITER));
      name.append(Mvp4gAbstractProcessor.FILE_DELIMITER);
    }
    name.append(className);
    name.append(Mvp4gAbstractProcessor.INFO_TYPE_DELIMITER);
    name.append(type.toString().toLowerCase());
    name.append(".");
    name.append(Mvp4gAbstractProcessor.FILE_TYPE);
    return name.toString();
  }

//------------------------------------------------------------------------------

  public enum InfoType {
    PRESENTER
  }

}
