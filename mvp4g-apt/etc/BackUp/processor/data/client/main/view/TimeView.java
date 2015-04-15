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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Singleton;
import com.mvp4g.processor.data.client.main.presenter.TimePresenter;

import java.util.Date;

@Singleton
public class TimeView
  extends Composite
  implements TimePresenter.ITimeView {

  private Label status = new Label();

  public TimeView() {
    initWidget(status);
  }

  @Override
  public void setTime(Date date) {
    status.setText("Time: " + DateTimeFormat.getFormat(PredefinedFormat.TIME_FULL)
                                            .format(date));
  }

}
