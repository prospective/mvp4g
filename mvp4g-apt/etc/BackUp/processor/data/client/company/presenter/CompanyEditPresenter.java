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
import com.mvp4g.client.history.NavigationConfirmationInterface;
import com.mvp4g.client.history.NavigationEventCommand;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;
import com.mvp4g.processor.data.client.company.view.CompanyEditView;

@Presenter(view = CompanyEditView.class)
public class CompanyEditPresenter
  extends AbstractCompanyPresenter
  implements NavigationConfirmationInterface {

  public void onGoToEdit(CompanyBean company) {
    this.company = company;
    fillView();
    eventBus.changeBody(view);
  }

  public void onNameSelected(String name) {
    view.getName()
        .setValue(name);
    view.alert("Name changed on edit page.");
  }

  @Override
  protected void clickOnLeftButton(ClickEvent event) {
    fillBean();
    service.updateCompany(company,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {
                              eventBus.displayMessage("Update Failed");
                            }

                            public void onSuccess(Void result) {
                              eventBus.setNavigationConfirmation(null);
                              eventBus.companyUpdated(company);
                              eventBus.backToList();
                              eventBus.displayMessage("Update Succeeded");
                            }
                          });
  }

  @Override
  protected void clickOnRightButton(ClickEvent event) {
    clear();
  }

  @Override
  public void onBeforeEvent() {
    eventBus.setNavigationConfirmation(this);
  }

  public void confirm(NavigationEventCommand event) {
    if ((view.getName()
             .getValue()
             .equals(company.getName())
        )
        || (view.confirm(" Your company hasn't been saved. Are you sure you want to navigate away from this page?"))) {
      event.fireEvent();
    }
  }

}
