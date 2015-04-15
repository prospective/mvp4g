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

package com.mvp4g.processor.data.client.company.handler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.event.BaseEventHandler;
import com.mvp4g.processor.data.client.company.CompanyEventBus;
import com.mvp4g.processor.data.client.company.CompanyServiceAsync;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;

import java.util.List;

//this is an example of how viewless handler can be used to proxy calls to a service
@EventHandler
public class CompanyListHandler
  extends BaseEventHandler<CompanyEventBus> {

  @Inject
  private CompanyServiceAsync service = null;

  private int               start     = 0;
  private List<CompanyBean> companies = null;

  public void onGetCompanyList(final int start,
                               int end) {
    int companyCount = (companies == null) ? 0 : companies.size();
    if ((start >= this.start) && (end < (this.start + companyCount))) {
      eventBus.companyListRetrieved(getSubList(start,
                                               end));
    } else {
      service.getCompanies(start,
                           end,
                           new AsyncCallback<List<CompanyBean>>() {

                             public void onFailure(Throwable caught) {

                             }

                             public void onSuccess(List<CompanyBean> result) {
                               companies = result;
                               CompanyListHandler.this.start = start;
                               eventBus.companyListRetrieved(result);
                             }
                           });
    }

  }

  public List<CompanyBean> getSubList(int start,
                                      int end) {
    int startIndex = start - this.start;
    int endIndex = end - this.start + 1;
    return companies.subList(startIndex,
                             endIndex);
  }

}
