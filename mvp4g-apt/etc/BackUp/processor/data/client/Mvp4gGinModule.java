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

package com.mvp4g.processor.data.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.mvp4g.processor.data.client.util.display.FullNameIndexDisplayer;
import com.mvp4g.processor.data.client.util.display.IndexDisplayer;
import com.mvp4g.processor.data.client.util.index.IndexGenerator;
import com.mvp4g.processor.data.client.util.index.SameIndexGenerator;
import com.mvp4g.processor.data.client.util.token.BaseTokenGenerator;
import com.mvp4g.processor.data.client.util.token.TokenGenerator;

public class Mvp4gGinModule
  extends AbstractGinModule {

  @Override
  protected void configure() {
    bind(IndexGenerator.class).to(SameIndexGenerator.class);
    bind(TokenGenerator.class).to(BaseTokenGenerator.class);
    bind(IndexDisplayer.class).to(FullNameIndexDisplayer.class);
  }

}
