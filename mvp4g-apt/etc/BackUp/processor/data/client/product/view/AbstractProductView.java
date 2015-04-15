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

package com.mvp4g.processor.data.client.product.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.mvp4g.processor.data.client.product.presenter.AbstractProductPresenter;

public abstract class AbstractProductView
  extends SimplePanel
  implements AbstractProductPresenter.ProductViewInterface {

  private Button leftButton  = null;
  private Button rightButton = null;

  public HasClickHandlers getLeftButton() {
    return leftButton;
  }

  public HasClickHandlers getRightButton() {
    return rightButton;
  }

  public void createView() {
    leftButton = new Button(getLeftButtonName());
    rightButton = new Button(getRightButtonName());

    Grid grid = new Grid(2,
                         2);
    grid.setText(0,
                 0,
                 "Name :");
    grid.setWidget(0,
                   1,
                   createAndGetNameWidget());

    HorizontalPanel buttons = new HorizontalPanel();
    buttons.add(leftButton);
    buttons.add(rightButton);

    grid.setWidget(1,
                   1,
                   buttons);

    setWidget(grid);
  }

  abstract protected String getLeftButtonName();

  abstract protected String getRightButtonName();

  abstract protected Widget createAndGetNameWidget();

  public void alert(String message) {
    Window.alert(message);
  }

}
