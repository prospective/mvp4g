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

package com.mvp4g.processor.data.client.company;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.annotation.Debug.LogLevel;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;
import com.mvp4g.processor.data.client.company.handler.CompanyListHandler;
import com.mvp4g.processor.data.client.company.history.CompanyCreationHistoryConverter;
import com.mvp4g.processor.data.client.company.history.CompanyHistoryConverter;
import com.mvp4g.processor.data.client.company.presenter.*;
import com.mvp4g.processor.data.client.product.presenter.ProductCreationPresenter;

import java.util.List;

@Events(startPresenter = CompanyListPresenter.class, module = CompanyModule.class)
@Debug(logLevel = LogLevel.DETAILED)
@Filters(filterClasses = CompanyEventFilter.class, filterForward = false)
public interface CompanyEventBus
  extends EventBus {

  /* Navigation Events */
  @Event(handlers = CompanyListPresenter.class, activate = CompanyRowPresenter.class, deactivate = {CompanyEditPresenter.class,
                                                                                                    CompanyDisplayPresenter.class,
                                                                                                    CompanyCreationPresenter.class}, navigationEvent = true)
  void goToCompany(int start,
                   int end);

  @Event(handlers = CompanyListPresenter.class, activate = CompanyRowPresenter.class, deactivate = {CompanyEditPresenter.class,
                                                                                                    CompanyDisplayPresenter.class,
                                                                                                    CompanyCreationPresenter.class})
  void backToList();

  @Event(handlers = CompanyCreationPresenter.class, activate = CompanyCreationPresenter.class, deactivate = {CompanyEditPresenter.class,
                                                                                                             CompanyDisplayPresenter.class}, historyConverter = CompanyCreationHistoryConverter.class, name = "create", navigationEvent = true)
  String goToCreation();

  //I have ProductCreationPresenter.class here just to test if Mvp4g ignores useless presenter for deactivate
  @Event(handlers = CompanyDisplayPresenter.class, historyConverter = CompanyHistoryConverter.class, activate = CompanyDisplayPresenter.class, deactivate = {
                                                                                                                                                              CompanyCreationPresenter.class,
                                                                                                                                                              CompanyEditPresenter.class,
                                                                                                                                                              ProductCreationPresenter.class,
                                                                                                                                                              CompanyRowPresenter.class}, name = "", navigationEvent = true)
  void goToDisplay(CompanyBean company);

  //I have ProductCreationPresenter.class here just to test if Mvp4g ignores useless presenter for activate
  @Event(handlers = CompanyEditPresenter.class, activate = {CompanyEditPresenter.class,
                                                            ProductCreationPresenter.class}, deactivate = {
                                                                                                            CompanyCreationPresenter.class,
                                                                                                            CompanyDisplayPresenter.class,
                                                                                                            CompanyRowPresenter.class}, navigationEvent = true)
  void goToEdit(CompanyBean company);

  /* Business Events */
  @Event(handlers = CompanyListPresenter.class, activate = CompanyListPresenter.class)
  void companyCreated(CompanyBean newBean);

  @Event(handlers = CompanyListPresenter.class, activate = CompanyListPresenter.class)
  void companyDeleted(CompanyBean newBean);

  @Event(handlers = CompanyRowPresenter.class, activate = CompanyRowPresenter.class)
  void companyUpdated(CompanyBean newBean);

  @Event(handlers = CompanyNameSelectorPresenter.class)
  void displayNameSelector();

  @Event(handlers = {CompanyCreationPresenter.class,
                     CompanyEditPresenter.class,
                     CompanyDisplayPresenter.class,
                     CompanyRowPresenter.class})
  void nameSelected(String name);

  //deactivate CompanyListHandler just to test event handler deactivation
  @Event(handlers = CompanyListPresenter.class, deactivate = CompanyListHandler.class)
  void companyListRetrieved(List<CompanyBean> companies);

  //activate CompanyListHandler just to test event handler activation
  @Event(handlers = CompanyListHandler.class, activate = CompanyListHandler.class)
  void getCompanyList(final int start,
                      int end);

  @Forward
  @Event(handlers = CompanyListPresenter.class)
  void forward();

  @Event(forwardToParent = true)
  void displayMessage(String message);

  @Event(forwardToParent = true)
  void changeBody(IsWidget body);

  @Event(forwardToParent = true)
  void selectCompanyMenu();

  @Event(handlers = CompanyListPresenter.class, passive = true)
  void hasBeenThere();

  @Event(handlers = CompanyListPresenter.class, passive = true)
  void broadcastInfo(String[] info);

  @Event(forwardToParent = true)
  String goToProduct(Integer start,
                     Integer end);

  @Event(handlers = CompanyListPresenter.class)
  void goToCompanyFromProduct(String info);

  @Event(handlers = CompanyListPresenter.class)
  void broadcastInfoFromProduct(String info);

  @Event(handlers = CompanyListPresenter.class)
  void broadcastInfoFromProductPassive(String info);

  @Event(handlers = CompanyTimePresenter.class)
  void showStatus(String status);

}
