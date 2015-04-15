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
import com.mvp4g.processor.data.client.main.view.TimeView;
import com.mvp4g.processor.data.client.util.HasBeenThereHandler;

import java.util.Date;

@Presenter(view = TimeView.class, async = StatusSplitter.class)
public class TimePresenter
  extends BasePresenter<TimePresenter.ITimeView, MainEventBus>
  implements HasBeenThereHandler {

  public void bind() {
    // doesn't make sense to call this in the bind method but I'm just tested this method is correctly called.
    setTime();
  }

  private void setTime() {
    view.setTime(new Date());
  }

  public void onHasBeenThere() {
    setTime();
  }

  public void onBroadcastInfo(String[] info) {
    setTime();
  }

  public interface ITimeView {

    void setTime(Date date);

  }

}
