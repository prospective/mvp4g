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

package com.mvp4g.processor.data.client.company.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.mvp4g.processor.data.client.company.presenter.CompanyListPresenter;

public class CompanyListView
  extends Composite
  implements CompanyListPresenter.CompanyListViewInterface {

  private Hyperlink createButton, goToProduct;
  private VerticalPanel table;
  private CheckBox      filter, moduleHistory, applicationHistory;

  public HasClickHandlers getCreateButton() {
    return createButton;
  }

  public void removeCompany(int row) {
    table.remove(row);
  }

  public void clearTable() {
    table.clear();
  }

  public void createView() {
    table = new VerticalPanel();
    createButton = new Hyperlink("Create Company",
                                 "");
    goToProduct = new Hyperlink("Go To Products",
                                "");
    filter = new CheckBox("Filter Company EventBus events");
    moduleHistory = new CheckBox("Disable Company Module History");
    applicationHistory = new CheckBox("Disable Application History");

    VerticalPanel mainPanel = new VerticalPanel();
    mainPanel.add(table);
    mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    mainPanel.add(createButton);
    mainPanel.add(goToProduct);
    mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    mainPanel.add(moduleHistory);
    mainPanel.add(applicationHistory);
    mainPanel.add(filter);

    initWidget(mainPanel);
  }

  public void addCompany(IsWidget w) {
    table.add(w);
  }

  public HasValue<Boolean> isFiltered() {
    return filter;
  }

  public void alert(String msg) {
    Window.alert(msg);
  }

  public void setGoToCreationToken(String token) {
    createButton.setTargetHistoryToken(token);
  }

  public void setGoToProductsToken(String token) {
    goToProduct.setTargetHistoryToken(token);
  }

  @Override
  public HasValue<Boolean> isDisabledApplicationHistory() {
    return applicationHistory;
  }

  @Override
  public HasValue<Boolean> isDisabledModuleHistory() {
    return moduleHistory;
  }

}
