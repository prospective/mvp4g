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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.processor.data.client.company.CompanyEventBus;
import com.mvp4g.processor.data.client.company.CompanyServiceAsync;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;
import com.mvp4g.processor.data.client.company.view.CompanyRowView;


@Presenter(view = CompanyRowView.class, multiple = true)
public class CompanyRowPresenter
  extends BasePresenter<CompanyRowPresenter.ICompanyRowView, CompanyEventBus> {

  private CompanyBean         company;
  @Inject
  private CompanyServiceAsync service;
  private boolean calledQuickEdit = false;

  @Override
  public void bind() {

    view.getDisplay()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            eventBus.goToDisplay(company);
          }
        });
    view.getEdit()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            eventBus.goToEdit(company);
          }
        });
    view.getDelete()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            deleteCompany(company);
          }
        });
    view.getQuickEdit()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            calledQuickEdit = true;
            eventBus.displayNameSelector();
          }
        });
  }

  private void deleteCompany(final CompanyBean company) {
    service.deleteCompany(company,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {

                            }

                            public void onSuccess(Void result) {
                              eventBus.companyDeleted(company);
                            }
                          });
  }

  public void setCompany(CompanyBean company) {
    this.company = company;
    view.setName(company.getName());
  }

  public void onCompanyUpdated(CompanyBean newBean) {
    if (newBean.equals(company)) {
      company = newBean;
      view.setName(company.getName());
    }
  }

  public void onNameSelected(String name) {
    if (calledQuickEdit) {
      company.setName(name);
      view.setName(name);
      view.alert("Name changed with quick edit.");
      calledQuickEdit = false;
    }
  }

  public interface ICompanyRowView
    extends IsWidget {

    HasClickHandlers getDisplay();

    HasClickHandlers getEdit();

    HasClickHandlers getQuickEdit();

    HasClickHandlers getDelete();

    void setName(String name);

    void alert(String message);

  }

}
