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
import com.mvp4g.processor.data.client.product.view.ProductDisplayView;

@Presenter(view = ProductDisplayView.class)
public class ProductDisplayPresenter
  extends AbstractProductPresenter {

  public void onGoToDisplay(ProductBean product) {
    displayProduct(product);
    eventBus.selectProductMenu();
  }

  private void displayProduct(ProductBean product) {
    this.product = product;
    fillView();
    eventBus.changeBody(view);
  }

  @Override
  protected void clickOnLeftButton(ClickEvent event) {
    eventBus.goToEdit(product);
  }

  @Override
  protected void clickOnRightButton(ClickEvent event) {
    fillBean();
    service.deleteProduct(product,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {
                              eventBus.displayMessage("Deletion Failed");
                            }

                            public void onSuccess(Void result) {
                              eventBus.productDeleted(product);
                              eventBus.backToList();
                            }
                          });
  }

  @Override
  protected String getPageName() {
    return "Product Display Page";
  }

}
