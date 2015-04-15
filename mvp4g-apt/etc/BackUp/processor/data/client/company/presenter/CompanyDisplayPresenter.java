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

package com.mvp4g.processor.data.client.company.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;
import com.mvp4g.processor.data.client.company.view.CompanyDisplayView;

@Presenter(view = CompanyDisplayView.class)
public class CompanyDisplayPresenter
  extends AbstractCompanyPresenter {

  public void onGoToDisplay(CompanyBean company) {
    displayCompany(company);
  }

  private void displayCompany(CompanyBean company) {
    this.company = company;
    fillView();
    eventBus.changeBody(view);
  }

  public void onCompanyCreated(CompanyBean company) {
    displayCompany(company);
  }

  public void onNameSelected(String name) {
    view.alert("Selected a name on the display page does nothing, sorry.");
  }

  @Override
  protected void clickOnLeftButton(ClickEvent event) {
    eventBus.goToEdit(company);
  }

  @Override
  protected void clickOnRightButton(ClickEvent event) {
    fillBean();
    service.deleteCompany(company,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {
                              eventBus.displayMessage("Deletion Failed");
                            }

                            public void onSuccess(Void result) {
                              eventBus.companyDeleted(company);
                              eventBus.backToList();
                            }
                          });
  }

}
