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

package com.mvp4g.processor.data.client.product;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.Mvp4gModule;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.presenter.NoStartPresenter;
import com.mvp4g.processor.data.client.company.CompanyModule;
import com.mvp4g.processor.data.client.product.bean.ProductBean;
import com.mvp4g.processor.data.client.product.presenter.ProductCreationPresenter;
import com.mvp4g.processor.data.client.product.presenter.ProductDisplayPresenter;
import com.mvp4g.processor.data.client.product.presenter.ProductEditPresenter;
import com.mvp4g.processor.data.client.product.presenter.ProductListPresenter;
import com.mvp4g.processor.data.client.util.HasBeenThereHandler;

@Events(startPresenter = NoStartPresenter.class, module = ProductModule.class)
public interface ProductEventBus
  extends EventBus {

  /* Navigation events */
  @Event(handlers = ProductCreationPresenter.class, navigationEvent = true)
  void goToCreation();

  @Event(handlers = ProductListPresenter.class, navigationEvent = true)
  void backToList();

  @Event(handlers = ProductEditPresenter.class, navigationEvent = true)
  void goToEdit(ProductBean product);

  @Event(handlers = ProductDisplayPresenter.class, navigationEvent = true)
  void goToDisplay(ProductBean product);

  @Event(handlers = ProductListPresenter.class, navigationEvent = true)
  void goToProduct(Integer start,
                   Integer end);

  /* Business events */
  @Event(forwardToParent = true)
  void displayMessage(String message);

  @Event(forwardToParent = true)
  void changeBody(IsWidget body);

  @Event(forwardToParent = true)
  void selectProductMenu();

  @Event(handlers = ProductListPresenter.class)
  void productCreated(ProductBean product);

  @Event(handlers = ProductListPresenter.class)
  void productDeleted(ProductBean product);

  @Event(broadcastTo = HasBeenThereHandler.class, passive = true)
  void hasBeenThere();

  @Event(handlers = ProductListPresenter.class)
  void goToProduct2(String[] indexes);

  @Event(broadcastTo = HasBeenThereHandler.class, passive = true)
  void broadcastInfo(String[] info);

  @Event(forwardToModules = CompanyModule.class)
  void goToCompanyFromProduct(String info);

  @Event(broadcastTo = Mvp4gModule.class)
  void broadcastInfoFromProduct(String info);

  @Event(broadcastTo = Mvp4gModule.class, passive = true)
  void broadcastInfoFromProductPassive(String info);

}
