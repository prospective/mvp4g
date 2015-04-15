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

package com.mvp4g.processor.data.client.main.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.mvp4g.processor.data.client.main.presenter.MainPresenter;
import com.mvp4g.processor.data.client.util.display.IndexDisplayer;


public class MainView
  extends Composite
  implements MainPresenter.MainViewInterface {

  protected VerticalPanel mainPanel;

  private Label           c              = new Label("Company");
  private Label           p              = new Label("Product");
  private SimplePanel     bodyContainer  = new SimplePanel();
  private PopupPanel      wait           = new PopupPanel();
  private Label           message        = new Label();
  private HorizontalPanel bar            = new HorizontalPanel();
  private Label           clearHistory   = new Label("Clear History");
  private CheckBox        filter         = new CheckBox("Filter Main EventBus events");
  private CheckBox        filterActivate = new CheckBox("Filter WIth Activate");
  private CheckBox        filterModule   = new CheckBox("Filter Module");

  private ListBox startIndex = new ListBox();
  private ListBox lastIndex  = new ListBox();

  private Anchor hasBeenThere   = new Anchor("Has been there!");
  private Anchor broadcastInfo  = new Anchor("Broadcast Info");
  private Anchor activateStatus = new Anchor();
  private Anchor showStatus     = new Anchor("Show Status");

  @Inject
  public MainView(IndexDisplayer indexDisplayer) {

    c.setStyleName("tab");
    c.addStyleName("first");
    p.setStyleName("tab");

    message.setStyleName("messageBar");
    message.setVisible(false);

    int i;
    for (i = 0; i < 5; i++) {
      startIndex.addItem(indexDisplayer.getDisplay(i),
                         Integer.toString(i));
    }
    for (i = 5; i < 10; i++) {
      lastIndex.addItem(indexDisplayer.getDisplay(i),
                        Integer.toString(i));
    }

    startIndex.setSelectedIndex(0);
    lastIndex.setSelectedIndex(0);

    HorizontalPanel hp = new HorizontalPanel();
    hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
    hp.add(new Label("Start Index: "));
    hp.add(startIndex);
    hp.add(new Label("End Index: "));
    hp.add(lastIndex);
    hp.setSpacing(5);

    bar.add(c);
    bar.add(p);
    bar.setStyleName("tabs");

    mainPanel = new VerticalPanel();
    mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
    clearHistory.setStyleName("link");
    mainPanel.add(clearHistory);

    mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    mainPanel.add(hp);
    mainPanel.add(bar);
    mainPanel.add(message);
    mainPanel.add(bodyContainer);
    mainPanel.add(filter);
    mainPanel.add(filterActivate);
    mainPanel.add(filterModule);
    mainPanel.add(hasBeenThere);
    mainPanel.add(broadcastInfo);
    mainPanel.add(showStatus);
    mainPanel.add(activateStatus);
    wait.add(new Label("Wait"));

    initWidget(mainPanel);

    bodyContainer.setStyleName("body");
    bodyContainer.setWidget(new Label("Click on one of the tab to start."));
  }

  public HasClickHandlers getCompanyMenu() {
    return c;
  }

  public HasClickHandlers getProductMenu() {
    return p;
  }

  public void setBody(IsWidget newBody) {
    bodyContainer.setWidget(newBody);
  }

  public void displayErrorMessage(String error) {
    Window.alert("Error: " + error);
  }

  public void setWaitVisible(boolean visible) {
    if (visible) {
      wait.setPopupPosition(bodyContainer.getAbsoluteLeft(),
                            bodyContainer.getAbsoluteTop());
      wait.setPixelSize(bodyContainer.getOffsetWidth(),
                        bodyContainer.getOffsetHeight());
      wait.show();
    } else {
      wait.hide();
    }
  }

  public void displayText(String text) {
    message.setText(text);
    message.setVisible(text.length() > 0);
  }

  public void selectCompanyMenu() {
    p.removeStyleName("selected");
    c.addStyleName("selected");
  }

  public void selectProductMenu() {
    c.removeStyleName("selected");
    p.addStyleName("selected");
  }

  public void alert(String message) {
    Window.alert(message);
  }

  public HasClickHandlers getClearHistoryButton() {
    return clearHistory;
  }

  public int getLastIndex() {
    return lastIndex.getSelectedIndex() + 5;
  }

  public int getStartIndex() {
    return startIndex.getSelectedIndex();
  }

  public HasValue<Boolean> getFilter() {
    return filter;
  }

  public HasClickHandlers getHasBeenThere() {
    return hasBeenThere;
  }

  @Override
  public HasClickHandlers getBroadcastInfo() {
    return broadcastInfo;
  }

  @Override
  public HasValue<Boolean> getFilterByActivate() {
    return filterActivate;
  }

  @Override
  public HasClickHandlers getShowStatus() {
    return showStatus;
  }

  @Override
  public HasClickHandlers getActivateStatus() {
    return activateStatus;
  }

  @Override
  public void setActivateText(boolean showActivate) {
    activateStatus.setText((showActivate) ? "Activate Status" : "Deactivate Status");
  }

  @Override
  public HasValue<Boolean> getCompanyModuleFilter() {
    return filterModule;
  }

}
