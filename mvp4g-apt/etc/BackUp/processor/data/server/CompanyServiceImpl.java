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
import com.mvp4g.processor.data.client.company.CompanyService;
import com.mvp4g.processor.data.client.company.bean.CompanyBean;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceImpl
  extends RemoteServiceServlet
  implements CompanyService {

  /**
   *
   */
  private static final long serialVersionUID = 4546417863195659071L;

  public void deleteCompany(CompanyBean company) {
    // TODO Auto-generated method stub
  }

  public List<CompanyBean> getCompanies(int start,
                                        int end) {
    List<CompanyBean> companies = new ArrayList<CompanyBean>();
    for (int i = start; i <= end; i++) {
      companies.add(new CompanyBean(i,
                                    "Company " + i));
    }
    return companies;
  }

  public void updateCompany(CompanyBean company) {
    // TODO Auto-generated method stub
  }

  public void createCompany(CompanyBean company) {
    // TODO Auto-generated method stub
  }

}
