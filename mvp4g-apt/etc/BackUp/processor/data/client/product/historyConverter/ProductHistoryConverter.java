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

package com.mvp4g.processor.data.client.product.historyConverter;

import com.mvp4g.client.annotation.History;
import com.mvp4g.client.history.HistoryConverter;
import com.mvp4g.processor.data.client.product.ProductEventBus;
import com.mvp4g.processor.data.client.product.bean.ProductBean;


@History
public class ProductHistoryConverter
  implements HistoryConverter<ProductEventBus> {

  public void convertFromToken(String eventType,
                               String param,
                               ProductEventBus eventBus) {
    String[] paramTab = param.split("&");
    ProductBean product = new ProductBean();
    product.setId(Integer.parseInt(paramTab[0].split("=")[1]));
    product.setName(paramTab[1].split("=")[1]);
    eventBus.goToDisplay(product);
  }

  public boolean isCrawlable() {
    return false;
  }

  public String onGoToDisplay(ProductBean product) {
    return convertProductToToken(product);
  }

  public String convertProductToToken(ProductBean product) {
    return "id=" + product.getId() + "&name=" + product.getName();
  }

}
