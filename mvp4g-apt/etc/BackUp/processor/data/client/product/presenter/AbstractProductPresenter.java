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

package com.mvp4g.processor.data.client.product.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;
import com.mvp4g.processor.data.client.product.ProductEventBus;
import com.mvp4g.processor.data.client.product.ProductServiceAsync;
import com.mvp4g.processor.data.client.product.bean.ProductBean;
import com.mvp4g.processor.data.client.util.HasBeenThereHandler;

public abstract class AbstractProductPresenter
  extends LazyPresenter<AbstractProductPresenter.ProductViewInterface, ProductEventBus>
  implements HasBeenThereHandler {

  protected ProductBean product = null;

  @Inject
  protected ProductServiceAsync service = null;

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

  }

  abstract protected void clickOnLeftButton(ClickEvent event);

  abstract protected void clickOnRightButton(ClickEvent event);

  public void onHasBeenThere() {
    view.alert("Has been on " + getPageName());
  }

  abstract protected String getPageName();

  public void onBroadcastInfo(String[] info) {
    int size = info.length;
    StringBuilder builder = new StringBuilder(20 + size * 30);
    builder.append(getPageName());
    builder.append(" received this information: ");
    if (size > 0) {
      builder.append(info[0]);
      for (int i = 1; i < size; i++) {
        builder.append(", ");
        builder.append(info[i]);
      }
    }
    view.alert(builder.toString());
  }

  protected void fillView() {
    view.getName()
        .setValue(product.getName());
  }

  protected void fillBean() {
    product.setName(view.getName()
                        .getValue());
  }

  protected void clear() {
    view.getName()
        .setValue("");
  }

  public interface ProductViewInterface
    extends LazyView,
            IsWidget {
    HasValue<String> getName();

    HasClickHandlers getLeftButton();

    HasClickHandlers getRightButton();

    void alert(String message);
  }

}
