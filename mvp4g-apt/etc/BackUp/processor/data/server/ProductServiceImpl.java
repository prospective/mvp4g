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

package com.mvp4g.processor.data.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mvp4g.processor.data.client.product.ProductService;
import com.mvp4g.processor.data.client.product.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl
  extends RemoteServiceServlet
  implements ProductService {

  /**
   *
   */
  private static final long serialVersionUID = 4546417863195659071L;

  public void deleteProduct(ProductBean product) {
    // TODO Auto-generated method stub
  }

  public List<ProductBean> getProducts(int start,
                                       int end) {
    List<ProductBean> companies = new ArrayList<ProductBean>();
    for (int i = start; i <= end; i++) {
      companies.add(new ProductBean(i,
                                    "Product " + i));
    }
    return companies;
  }

  public void updateProduct(ProductBean product) {
    // TODO Auto-generated method stub
  }

  public void createProduct(ProductBean product) {
    // TODO Auto-generated method stub
  }

}
