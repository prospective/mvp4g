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

package com.mvp4g.processor.data.client.main.presenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.processor.data.client.main.MainEventBus;
import com.mvp4g.processor.data.client.main.StatusSplitter;
import com.mvp4g.processor.data.client.main.view.DateView;
import com.mvp4g.processor.data.client.util.HasBeenThereHandler;

import java.util.Date;

@Presenter(view = DateView.class, async = StatusSplitter.class)
public class DatePresenter
  extends BasePresenter<DatePresenter.IDateView, MainEventBus>
  implements HasBeenThereHandler {

  public void bind() {
    // doesn't make sense to call this in the bind method but I'm just tested this method is correctly called.
    setDate();
  }

  private void setDate() {
    view.setDate(new Date());
  }

  public void onHasBeenThere() {
    setDate();
  }

  public void onBroadcastInfo(String[] info) {
    setDate();
  }

  public interface IDateView {

    void setDate(Date date);

  }

}
