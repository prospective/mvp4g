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
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.annotation.module.ChildModules;
import com.mvp4g.processor.controls.EventHandlerControl;
import com.mvp4g.processor.controls.HistoryConverterControl;
import com.mvp4g.processor.controls.ModuleControl;
import com.mvp4g.processor.controls.PresenterControl;
import com.mvp4g.processor.exceptions.ConfigurationException;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.utils.MessagerUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.lang.model.util.ElementFilter.typesIn;

@AutoService(Processor.class)
public class Mvp4gAnnotationProcessor
  extends AbstractProcessor {

  /* info */
  private ApplicationInfo applicationInfo;
  /* message utils */
  private MessagerUtils   messagerUtils;
  /* state of validation */
  private boolean         isValid;

//------------------------------------------------------------------------------

  public Mvp4gAnnotationProcessor() {
    super();
  }

//------------------------------------------------------------------------------

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotataions = new LinkedHashSet<>();
    annotataions.add(ChildModules.class.getCanonicalName());
    annotataions.add(EventHandler.class.getCanonicalName());
    annotataions.add(Events.class.getCanonicalName());
    annotataions.add(History.class.getCanonicalName());
    annotataions.add(Presenter.class.getCanonicalName());
    return annotataions;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    System.out.println("Annotation processing -> getSupportedSourceVersion");
    return SourceVersion.latest();
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    // initialize
    applicationInfo = new ApplicationInfo();
    isValid = true;
    // set up
    messagerUtils = new MessagerUtils(processingEnv.getMessager());
  }

//------------------------------------------------------------------------------

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations");
      // scan annotations
      try {
        scanAnnotations(annotations,
                        roundEnv);
        // process only is all tests has passed successfully
        if (!isValid) {
          return true;
        }
        // validate
        validate(annotations,
                 roundEnv);
//
//
        // process only is all tests has passed successfully
        if (!isValid) {
          return true;
        }
//      // TODO may be we replace the generators with apt in the future ... :-)
//
      } catch (ConfigurationException e) {
        return false;
      }
    } else {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations finished");
//
//      // TODO übergreifende validierung
//
//
      // at least, we clear the appInfo
      applicationInfo = new ApplicationInfo();
    }
    return true;
  }

//==============================================================================

  private void scanAnnotations(Set<? extends TypeElement> annotations,
                               RoundEnvironment roundEnv)
    throws ConfigurationException {
    // processing modules (eventbus)
    processModules(annotations,
                   roundEnv);
    // processing presenter annotatiom
    processPresenter(annotations,
                     roundEnv);
    // processing event handler annotatiom
    processEventHandler(annotations,
                        roundEnv);
    // processing history annotatiom
    processHistory(annotations,
                   roundEnv);


    System.out.println("Finish");
    // TODO
//    // processing Events annotatiom
//    processEventBus(annotations,
//                    roundEnv);
//    // processing child modules
//    processChildModules(annotations,
//                        roundEnv);

  }

  private void validate(Set<? extends TypeElement> annotations,
                        RoundEnvironment roundEnv) {
//    // processing Events annotatiom
//    processEventBus(annotations,
//                    roundEnv);
//
//
//    // processing Presenter annotatiom
//    processPresenter(annotations,
//                     roundEnv);


  }


//------------------------------------------------------------------------------

  private void processModules(Set<? extends TypeElement> annotations,
                              RoundEnvironment roudEnv)
    throws ConfigurationException {
    // create the control class
    ModuleControl control = new ModuleControl(applicationInfo,
                                              messagerUtils,
                                              processingEnv);
    // iterate over all classes which are annotated with @Events
    for (TypeElement element : typesIn(roudEnv.getElementsAnnotatedWith(Events.class))) {
      if (!control.process(element)) {
        isValid = false;
      }
    }
  }

  private void processPresenter(Set<? extends TypeElement> annotations,
                                RoundEnvironment roundEnv) {
    // create the controll class
    PresenterControl control = new PresenterControl(applicationInfo,
                                                    messagerUtils,
                                                    processingEnv);
    // iterate over all classes which are annotated with @Presenter
    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(Presenter.class))) {
      if (!control.process(element)) {
        isValid = false;
      }
    }
  }

  private void processEventHandler(Set<? extends TypeElement> annotations,
                                   RoundEnvironment roundEnv) {
    // create the controll class
    EventHandlerControl control = new EventHandlerControl(applicationInfo,
                                                          messagerUtils,
                                                          processingEnv);
    // iterate over all classes which are annotated with @Presenter
    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(EventHandler.class))) {
      if (!control.process(element)) {
        isValid = false;
      }
    }
  }

  private void processHistory(Set<? extends TypeElement> annotations,
                              RoundEnvironment roudEnv)
    throws ConfigurationException {
    // create the control class
    HistoryConverterControl control = new HistoryConverterControl(applicationInfo,
                                                                  messagerUtils,
                                                                  processingEnv);
    // iterate over all classes which are annotated with @Events
    for (TypeElement element : typesIn(roudEnv.getElementsAnnotatedWith(History.class))) {
      if (!control.process(element)) {
        isValid = false;
      }
    }
  }
}
