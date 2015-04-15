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

package com.mvp4g.processor.data.client.main.historyConverter;

import com.google.inject.Inject;
import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.History.HistoryConverterType;
import com.mvp4g.client.history.HistoryConverter;
import com.mvp4g.processor.data.client.main.MainEventBus;
import com.mvp4g.processor.data.client.util.token.TokenGenerator;

@History(type = HistoryConverterType.SIMPLE)
public class MenuHistoryConverter
  implements HistoryConverter<MainEventBus> {

  @Inject
  private TokenGenerator tokenGenerator;

  public String convertToToken(String eventName,
                               int start,
                               int end) {
    return tokenGenerator.convertToToken("start",
                                         Integer.toString(start)) + "&"
           + tokenGenerator.convertToToken("end",
                                           Integer.toString(end));
  }

  public void convertFromToken(String eventType,
                               String param,
                               MainEventBus eventBus) {
    String[] paramTab = param.split("&");
    int start = Integer.parseInt(tokenGenerator.convertFromToken(paramTab[0]));
    int end = Integer.parseInt(tokenGenerator.convertFromToken(paramTab[1]));
    eventBus.dispatch(eventType,
                      start,
                      end);
  }

  public boolean isCrawlable() {
    return true;
  }

}
