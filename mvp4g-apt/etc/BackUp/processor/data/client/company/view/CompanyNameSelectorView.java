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
import com.google.gwt.user.client.ui.*;
import com.mvp4g.processor.data.client.company.presenter.CompanyNameSelectorPresenter;

public class CompanyNameSelectorView
  extends DialogBox
  implements CompanyNameSelectorPresenter.CompanyNameSelectorViewInterface {

  private ListBox names;
  private Button  select;

  public ListBox getNames() {
    return names;
  }

  public HasClickHandlers getSelectButton() {
    return select;
  }

  public void createView() {
    setText("Name Selector");

    names = new ListBox();
    select = new Button("Select");

    HorizontalPanel mainPanel = new HorizontalPanel();
    mainPanel.add(new Label("Name: "));
    mainPanel.add(names);
    mainPanel.add(select);

    setWidget(mainPanel);

  }

}
