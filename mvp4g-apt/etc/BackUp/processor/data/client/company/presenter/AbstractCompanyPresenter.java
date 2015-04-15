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
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.presenter.CyclePresenter;
import com.mvp4g.client.view.CycleView;
import com.mvp4g.processor.data.client.company.CompanyEventBus;
import com.mvp4g.processor.data.client.company.CompanyServiceAsync;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;

public abstract class AbstractCompanyPresenter
  extends CyclePresenter<AbstractCompanyPresenter.CompanyViewInterface, CompanyEventBus> {

  protected CompanyBean company = null;

  @Inject
  protected CompanyServiceAsync service = null;

  public void bindView() {
    view.getLeftButton()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            clickOnLeftButton(event);
          }
        });

    view.getRightButton()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            clickOnRightButton(event);
          }
        });
    view.getSelectNameButton()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            eventBus.displayNameSelector();
          }
        });

  }

  abstract protected void clickOnLeftButton(ClickEvent event);

  abstract protected void clickOnRightButton(ClickEvent event);

  protected void fillView() {
    view.getName()
        .setValue(company.getName());
  }

  protected void fillBean() {
    company.setName(view.getName()
                        .getValue());
  }

  protected void clear() {
    view.getName()
        .setValue("");
  }

  public interface CompanyViewInterface
    extends CycleView,
            IsWidget {
    HasValue<String> getName();

    HasClickHandlers getLeftButton();

    HasClickHandlers getRightButton();

    HasClickHandlers getSelectNameButton();

    void alert(String message);

    boolean confirm(String message);

  }

}
