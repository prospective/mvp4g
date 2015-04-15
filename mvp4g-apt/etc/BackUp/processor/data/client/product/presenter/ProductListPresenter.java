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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.LazyPresenter;
import com.mvp4g.client.view.LazyView;
import com.mvp4g.processor.data.client.product.ProductEventBus;
import com.mvp4g.processor.data.client.product.ProductServiceAsync;
import com.mvp4g.processor.data.client.product.bean.ProductBean;
import com.mvp4g.processor.data.client.product.view.ProductListView;

import java.util.List;

@Presenter(view = ProductListView.class)
public class ProductListPresenter
  extends LazyPresenter<ProductListPresenter.ProductListViewInterface, ProductEventBus> {

  @Inject
  private ProductServiceAsync service = null;

  private List<ProductBean> products = null;

  @Override
  public void bindView() {
    view.getCreateButton()
        .addClickHandler(new ClickHandler() {

          public void onClick(ClickEvent event) {
            eventBus.goToCreation();
          }

        });
    view.getCompanyButton()
        .addClickHandler(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            eventBus.goToCompanyFromProduct("Coming from Product");
          }

        });
    view.getInfoButton()
        .addClickHandler(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            eventBus.broadcastInfoFromProduct("Coming from Product");
          }

        });
    view.getPassiveInfoButton()
        .addClickHandler(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            eventBus.broadcastInfoFromProductPassive("Coming from Product");
          }

        });
  }

  public void onGoToProduct(Integer start,
                            Integer end) {
    service.getProducts(start,
                        end,
                        new AsyncCallback<List<ProductBean>>() {

                          public void onFailure(Throwable caught) {
                            eventBus.displayMessage("Failed to retrieve products");
                          }

                          public void onSuccess(List<ProductBean> result) {
                            products = result;
                            for (int i = 0; i < result.size(); i++) {
                              addProduct(result.get(i),
                                         i);
                            }
                            eventBus.changeBody(view);
                          }


                        });
    view.clearTable();
  }

  private void addProduct(final ProductBean product,
                          int row) {
    HasClickHandlers[] buttons = view.addProduct(product.getName(),
                                                 row);
    buttons[0].addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        eventBus.goToDisplay(product);
      }
    });
    buttons[1].addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        eventBus.goToEdit(product);
      }
    });
    buttons[2].addClickHandler(new ClickHandler() {

      public void onClick(ClickEvent event) {
        deleteProduct(product);
      }
    });
  }

  private void deleteProduct(final ProductBean product) {
    service.deleteProduct(product,
                          new AsyncCallback<Void>() {

                            public void onFailure(Throwable caught) {
                              eventBus.displayMessage("Deletion Failed");
                            }

                            public void onSuccess(Void result) {
                              finishDeletion(product);
                            }
                          });
  }

  private void finishDeletion(ProductBean Product) {
    int row = products.indexOf(Product);
    products.remove(row);
    view.removeProduct(row);
    eventBus.displayMessage("Deletion Succeeded");
  }

  public void onBackToList() {
    eventBus.changeBody(view);
  }

  public void onProductDeleted(ProductBean product) {
    finishDeletion(product);
  }

  public void onProductCreated(ProductBean product) {
    int row = products.size();
    products.add(product);
    view.addProduct(product.getName(),
                    row);
  }

  public void onGoToProduct2(String[] indexes) {
    for (String index : indexes) {
      Window.alert("Index= " + index);
    }
  }

  public interface ProductListViewInterface
    extends LazyView,
            IsWidget {
    HasClickHandlers getCreateButton();

    HasClickHandlers getCompanyButton();

    HasClickHandlers getInfoButton();

    HasClickHandlers getPassiveInfoButton();

    HasClickHandlers[] addProduct(String product,
                                  int row);

    void removeProduct(int row);

    void updateProduct(String product,
                       int row);

    void clearTable();
  }

}
