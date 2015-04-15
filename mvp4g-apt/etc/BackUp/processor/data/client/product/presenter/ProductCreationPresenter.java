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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.data.client.product.bean.ProductBean;
import com.mvp4g.processor.data.client.product.view.ProductCreationView;


@Presenter(view = ProductCreationView.class)
public class ProductCreationPresenter
  extends AbstractProductPresenter {

  public void onGoToCreation() {
    view.getName()
        .setValue("");
    eventBus.changeBody(view);
  }

  @Override
  protected void clickOnLeftButton(ClickEvent event) {
    product = new ProductBean();
    fillBean();
    service.createProduct(product,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {
                              eventBus.displayMessage("Creation Failed");
                            }

                            public void onSuccess(Void result) {
                              eventBus.productCreated(product);
                              eventBus.goToDisplay(product);
                              eventBus.displayMessage("Creation Succeeded");
                            }
                          });
  }

  @Override
  protected void clickOnRightButton(ClickEvent event) {
    clear();
  }

  @Override
  protected String getPageName() {
    return "Product Create Page";
  }

}
