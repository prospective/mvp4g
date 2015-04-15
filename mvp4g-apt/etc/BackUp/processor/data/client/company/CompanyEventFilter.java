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

package com.mvp4g.processor.data.client.company;

import com.mvp4g.client.event.EventFilter;

public class CompanyEventFilter
  implements EventFilter<CompanyEventBus> {

  public boolean filterEvent(String eventType,
                             Object[] params,
                             CompanyEventBus eventBus) {
    eventBus.setFilteringEnabledForNextOne(false);
    eventBus.displayMessage("Company Event " + eventType + " has been filtered.");
    return false;
  }
}
