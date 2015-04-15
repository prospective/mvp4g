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
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.mvp4g.processor.data.client.main.presenter.InfoReceiverPresenter;

public class InfoReceiverView
  extends DecoratedPopupPanel
  implements InfoReceiverPresenter.IInfoReceiverView {

  private static int position = 0;

  private Anchor close = new Anchor("Close");

  private Label info = new Label();

  public InfoReceiverView() {
    FlowPanel fp = new FlowPanel();
    fp.add(close);
    fp.add(info);
    setWidget(fp);
    setPopupPosition(position,
                     position);
    position += 20;
  }

  @Override
  public void setInfo(String[] info) {
    StringBuilder builder = new StringBuilder(info.length * 20);
    builder.append("Info: ");
    for (String s : info) {
      builder.append(s)
             .append(",");
    }
    this.info.setText(builder.toString());
  }

  @Override
  public HasClickHandlers getClose() {
    return close;
  }

}
