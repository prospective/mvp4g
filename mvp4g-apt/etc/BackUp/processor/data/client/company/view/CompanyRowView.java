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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.mvp4g.processor.data.client.company.presenter.CompanyRowPresenter;

public class CompanyRowView
  extends Composite
  implements CompanyRowPresenter.ICompanyRowView {

  private Image delete;
  private Image display;
  private Image edit;
  private Image quickEdit;

  private Label name;

  public CompanyRowView() {

    name = new Label();

    display = new Image("images/display.png");
    quickEdit = new Image("images/quickEdit.png");
    edit = new Image("images/edit.png");
    delete = new Image("images/delete.png");

    HorizontalPanel hp = new HorizontalPanel();
    hp.setSpacing(2);
    hp.add(name);
    hp.add(display);
    hp.add(quickEdit);
    hp.add(edit);
    hp.add(delete);

    initWidget(hp);

  }

  public HasClickHandlers getDelete() {
    return delete;
  }

  public HasClickHandlers getDisplay() {
    return display;
  }

  public HasClickHandlers getEdit() {
    return edit;
  }

  public void setName(String name) {
    this.name.setText(name);
  }

  public void alert(String message) {
    Window.alert(message);
  }

  public HasClickHandlers getQuickEdit() {
    return quickEdit;
  }

}
