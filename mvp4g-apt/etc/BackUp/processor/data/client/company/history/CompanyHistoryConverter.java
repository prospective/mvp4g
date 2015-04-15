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

package com.mvp4g.processor.data.client.company.history;

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.history.HistoryConverter;
import org.gwt4e.mvp4g.example.modules.client.company.CompanyEventBus;
import org.gwt4e.mvp4g.example.modules.client.company.bean.CompanyBean;

@History
public class CompanyHistoryConverter
  implements HistoryConverter<CompanyEventBus> {

  public void convertFromToken(String eventType,
                               String param,
                               CompanyEventBus eventBus) {
    String[] paramTab = param.split("&");
    CompanyBean company = new CompanyBean();
    company.setId(Integer.parseInt(paramTab[0].split("=")[1]));
    company.setName(paramTab[1].split("=")[1]);
    eventBus.goToDisplay(company);
  }

  public boolean isCrawlable() {
    return true;
  }

  public String onGoToDisplay(CompanyBean company) {
    return convertCompanyToToken(company);
  }

  private String convertCompanyToToken(CompanyBean company) {
    return "id=" + company.getId() + "&name=" + company.getName();
  }

}
