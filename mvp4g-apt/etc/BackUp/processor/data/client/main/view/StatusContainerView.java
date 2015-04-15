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

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.mvp4g.processor.data.client.main.presenter.StatusContainerPresenter;

public class StatusContainerView
  extends PopupPanel
  implements StatusContainerPresenter.IStatusContainerView {

  @Inject
  public StatusContainerView(TimeView time,
                             DateView date) {
    VerticalPanel vp = new VerticalPanel();
    vp.add(date);
    vp.add(time);
    setWidget(vp);

    setAutoHideEnabled(true);
    setAutoHideOnHistoryEventsEnabled(true);
  }

  @Override
  public void showPopup() {
    center();
  }

}
