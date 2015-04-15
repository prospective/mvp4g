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

import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.mvp4g.processor.data.client.util.display.IndexDisplayer;

public class FirefoxMainView
  extends MainView {

  @Inject
  public FirefoxMainView(IndexDisplayer indexDisplayer) {
    super(indexDisplayer);
    mainPanel.insert(new Label("Welcome Firefox Users"),
                     0);
  }

}
