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

package com.mvp4g.processor;

import com.google.auto.service.AutoService;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.controls.PresenterControl;
import com.mvp4g.processor.exceptions.ConfigurationException;
import com.mvp4g.processor.utils.MessagerUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.LinkedHashSet;
import java.util.Set;

@AutoService(Processor.class)
public class Mvp4gProcessor
  extends AbstractProcessor {
//
//  /* info */
//  private ApplicationInfo applicationInfo;
  /* message utils */
  private MessagerUtils   messagerUtils;
  /* state of validation */
  private boolean         valid;

//------------------------------------------------------------------------------

  public Mvp4gProcessor() {
    super();
  }

//------------------------------------------------------------------------------

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotataions = new LinkedHashSet<>();
//    annotataions.add(ChildModules.class.getCanonicalName());
//    annotataions.add(EventHandler.class.getCanonicalName());
//    annotataions.add(Events.class.getCanonicalName());
//    annotataions.add(History.class.getCanonicalName());
    annotataions.add(Presenter.class.getCanonicalName());
    return annotataions;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    // initialize
//    applicationInfo = new ApplicationInfo();
    valid = true;
    // set up
    messagerUtils = new MessagerUtils(processingEnv.getMessager());
  }

//------------------------------------------------------------------------------

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      try {
        scanAnnotations(roundEnv);
      } catch (ConfigurationException e) {
        valid = false;
      }
    } else {
//      if (!roundEnv.errorRaised()) {
//        scanBroadcastTo();
//
//        Mvp4gValidator validator = new Mvp4gValidator(applicationInfo,
//                                                      processingEnv,
//                                                      messagerUtils);
//        valid = validator.validate();
////      // TODO may be we replace the generators with apt in the future ... :-)
//      }
//      // at least, we clear the appInfo
//      applicationInfo = new ApplicationInfo();
    }
    return valid;
  }

//==============================================================================

  private void scanAnnotations(RoundEnvironment roundEnv)
    throws ConfigurationException {
//    processModules(roundEnv);
    processPresenter(roundEnv);
//    processEventHandler(roundEnv);
//    processHistory(roundEnv);
  }

//  private void scanBroadcastTo() {
//    Map<String, BroadcastToModel> broadcastTos = new HashMap<>();
//    for (ModuleInfo moduleInfo : applicationInfo.getModules()) {
//      for (EventInfo eventInfo : moduleInfo.getEventBusInfo()
//                                           .getEvents()) {
//        if (eventInfo.getBroadcastTo() != null) {
//          BroadcastToModel model = broadcastTos.get(eventInfo.getBroadcastTo()
//                                                             .getSimpleName()
//                                                             .toString());
//          if (model == null) {
//            broadcastTos.put(eventInfo.getBroadcastTo()
//                                      .getSimpleName()
//                                      .toString(),
//                             new BroadcastToModel(eventInfo.getBroadcastTo()
//                                                           .getSimpleName()
//                                                           .toString(),
//                                                  eventInfo.getBroadcastTo(),
//                                                  eventInfo.getEvent()));
//
//
//          } else {
//            if (model.getMethod(eventInfo.getBroadcastTo()
//                                         .getSimpleName()
//                                         .toString()) == null) {
//              model.addMethod(eventInfo.getEvent());
//            }
//          }
//        }
//      }
//    }
//    applicationInfo.setBroadcastTos(broadcastTos);
//  }
//
////------------------------------------------------------------------------------
//
//  private void processModules(RoundEnvironment roudEnv)
//    throws ConfigurationException {
//    // create the control class
//    ModuleControl control = new ModuleControl(applicationInfo,
//                                              messagerUtils,
//                                              processingEnv);
//    // iterate over all classes which are annotated with @Events
//    for (TypeElement element : typesIn(roudEnv.getElementsAnnotatedWith(Events.class))) {
//      if (!control.process(element)) {
//        valid = false;
//      }
//    }
//  }

  private void processPresenter(RoundEnvironment roundEnv) {
    PresenterControl control = new PresenterControl(messagerUtils,
                                                    processingEnv);
    // iterate over all classes which are annotated with @Presenter
    for (Element element : roundEnv.getElementsAnnotatedWith(Presenter.class)) {
      if (!control.process(element)) {
        valid = false;
      }
    }
  }

//  private void processEventHandler(RoundEnvironment roundEnv) {
//    // create the controll class
//    EventHandlerControl control = new EventHandlerControl(applicationInfo,
//                                                          messagerUtils,
//                                                          processingEnv);
//    // iterate over all classes which are annotated with @Presenter
//    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(EventHandler.class))) {
//      if (!control.process(element)) {
//        valid = false;
//      }
//    }
//  }
//
//  private void processHistory(RoundEnvironment roudEnv)
//    throws ConfigurationException {
//    // create the control class
//    HistoryConverterControl control = new HistoryConverterControl(applicationInfo,
//                                                                  messagerUtils,
//                                                                  processingEnv);
//    // iterate over all classes which are annotated with @Events
//    for (TypeElement element : typesIn(roudEnv.getElementsAnnotatedWith(History.class))) {
//      if (!control.process(element)) {
//        valid = false;
//      }
//    }
//  }
}
