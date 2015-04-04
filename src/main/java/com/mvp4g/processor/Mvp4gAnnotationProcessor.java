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
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.annotation.module.ChildModules;
import com.mvp4g.processor.controls.*;
import com.mvp4g.processor.info.ApplicationInfo;
import com.mvp4g.processor.info.PresenterInfo;
import com.mvp4g.processor.utils.MessagerUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.*;

import static javax.lang.model.util.ElementFilter.typesIn;

@AutoService(Processor.class)
public class Mvp4gAnnotationProcessor
  extends AbstractProcessor {

  /* info */
  private ApplicationInfo            applicationInfo;
  private Map<String, PresenterInfo> presenterInfos;
  /* message utils */
  private MessagerUtils              messagerUtils;
  /* state of validation */
  private boolean                    isValid;

//------------------------------------------------------------------------------

  public Mvp4gAnnotationProcessor() {
    super();
  }

//------------------------------------------------------------------------------

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotataions = new LinkedHashSet<String>();
    annotataions.add(Events.class.getCanonicalName());
    annotataions.add(ChildModules.class.getCanonicalName());
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
    presenterInfos = new HashMap<>();

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
                               RoundEnvironment roundEnv) {
    // processing modules (eventbus)
    processModules(annotations, roundEnv);


System.out.println("Stop");
    // TODO
//    // processing Presenter annotatiom
//    processPresenter(annotations,
//                     roundEnv);
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
                              RoundEnvironment roudEnv) {
    // create the control class
    ModuleControl control = new ModuleControl(applicationInfo,
                                              messagerUtils,
                                              processingEnv);
    // iterate over all classes which are annotated with @Events
    for (TypeElement element : typesIn(roudEnv.getElementsAnnotatedWith(Events.class))) {
      isValid = control.process(element);
    }
  }

//  private void processPresenter(Set<? extends TypeElement> annotations,
//                                RoundEnvironment roundEnv) {
//    // create the controll class
//    PresenterControl validator = new PresenterControl(messagerUtils);
//    // iterate over all classes which are annotated with @Presenter
//    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(Presenter.class))) {
//      // valdation
//      if (!validator.validate(processingEnv,
//                              element)) {
//        isValid = false;
//      } else {
//        PresenterInfo into = getPresenterInfo(element);
//        messagerUtils.note(element,
//                           element.getTypeParameters()
//                                  .toString());
//        messagerUtils.note(element,
//                           element.getEnclosedElements()
//                                  .toString());
//        messagerUtils.note(element,
//                           element.getEnclosingElement()
//                                  .toString());
//        // todo create presenter control
//      }
//    }
//  }
//
//  private void processEventBus(Set<? extends TypeElement> annotations,
//                               RoundEnvironment roundEnv) {
//    // create the validator
//    EventBusControl validator = new EventBusControl(messagerUtils);
//    // iterate over all classes which are annotated with @Presenter
//    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(Events.class))) {
//      // valdation
//      if (!validator.validate(processingEnv,
//                              element)) {
//        isValid = false;
//      } else {
//        // validation ok, just analyse the data
//        ModuleInfo moduleInfo = getModuleInfo(element);
//        // set the current EventBus
//        moduleInfo.setCurrentEventBus(element);
//        // handle child modules
//
//        // TODO handle childs ....
//
//
//        // process all events of the eventBus
//        processEvents(element,
//                      roundEnv);
//
//
//      }
//    }
//  }
//
//  private void processChildModules(Set<? extends TypeElement> annotations,
//                                   RoundEnvironment roundEnv) {
//    // create the validator
//    ChildModuleControl validator = new ChildModuleControl(messagerUtils);
//    // iterate over all classes which are annotated with @Presenter
//    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(ChildModules.class))) {
//      // valdation
//      if (!validator.validate(processingEnv,
//                              element)) {
//        isValid = false;
//      } else {
//        ModuleInfo info = getModuleInfoForEventBus(element.toString());
//        if (info != null) {
//          for (AnnotationMirror annotation : element.getAnnotationMirrors()) {
//            if (Utils.EVENTS.equals(annotation.getAnnotationType().toString())) {
//
//            } else if (Utils.CHILD_MODULES.equals(annotation.getAnnotationType().toString())) {
//
//            }
//          }
//
//          List<AnnotationValue> children = Utils.getAnnotationValue(Utils.ATTRIBUTE_VALUE, element);
//
////				} else if ( ProcessorUtil.CHILD_MODULES.equals( annotation.getAnnotationType().toString() ) ) {
////					children = (List<AnnotationValue>)ProcessorUtil.getAnnotationValue( ProcessorUtil.ATTRIBUTE_VALUE, annotation ).getValue();
////					for ( AnnotationValue childValue : children ) {
////						child = (AnnotationMirror)childValue.getValue();
////						module = ( (DeclaredType)ProcessorUtil.getAnnotationValue( ProcessorUtil.ATTRIBUTE_MODULE_CLASS, child ).getValue() )
////								.toString();
////						info = childModules.get( module );
////						if ( info == null ) {
////							info = new ModuleInfo();
////							childModules.put( module, info );
////						}
////						if ( info.getParentEventBus() != null ) {
////							processingEnv.getMessager().printMessage( Kind.ERROR,
////									String.format( Messages.MODULE_TWO_PARENT_EVENT_BUS, module, info.getParentEventBus().asType(), e.asType() ), e );
////						} else {
////							info.setParentEventBus( e );
////						}
////					}
//
//          //
////		HandlerControl handlerControl = new HandlerControl();
////		HistoryConverterControl historyConverterControl = new HistoryConverterControl();
////		ChildEventControl childEventControl = new ChildEventControl();
////		ParentEventControl parentEventControl = new ParentEventControl();
////
////		List<? extends AnnotationValue> handlers, modulesToLoad;
////		String calledMethod;
////		AnnotationValue historyConverter;
////		boolean forwardToParent;
////          for (ExecutableElement el : ElementFilter.methodsIn(processingEnv.getElementUtils()
////                                                                           .getAllMembers(element))) {
////            for (AnnotationMirror am : el.getAnnotationMirrors()) {
////				if ( Utils.EVENT.equals( m.getAnnotationType().toString() ) ) {
////					handlers = null;
////					calledMethod = null;
////					historyConverter = null;
////					modulesToLoad = null;
////					forwardToParent = false;
////					for ( Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : m.getElementValues().entrySet() ) {
////						keyName = entry.getKey().getSimpleName().toString();
////						if ( ProcessorUtil.ATTRIBUTE_HANDLERS.equals( keyName ) ) {
////							handlers = (List<? extends AnnotationValue>)entry.getValue().getValue();
////						} else if ( ProcessorUtil.ATTRIBUTE_CALLED_METHOD.equals( keyName ) ) {
////							calledMethod = (String)entry.getValue().getValue();
////						} else if ( ProcessorUtil.ATTRIBUTE_HISTORY_CONVERTER.equals( keyName ) ) {
////							historyConverter = entry.getValue();
////						} else if ( ProcessorUtil.ATTRIBUTE_MODULES_TO_LOAD.equals( keyName ) ) {
////							modulesToLoad = (List<? extends AnnotationValue>)entry.getValue().getValue();
////						} else if ( ProcessorUtil.ATTRIBUTE_FORWARD_TO_PARENT.equals( keyName ) ) {
////							forwardToParent = (Boolean)entry.getValue().getValue();
////						}
////					}
////					handlerControl.control( processingEnv, e, calledMethod, handlers );
////					historyConverterControl.control( processingEnv, e, calledMethod, historyConverter );
////					childEventControl.control( processingEnv, e, element, modulesToLoad, applicationInfo );
////					parentEventControl.control( processingEnv, e, element, forwardToParent, applicationInfo );
////				}
////            }
////          }
//
//
//          AnnotationMirror mirror = Utils.getAnnotationMirror(ChildModules.class.getCanonicalName(),
//                                                              element);
//
//          AnnotationValue value = Utils.getAnnotationValue(ChildModules.class.getCanonicalName(),
//                                                           "value",
//                                                           element);
//          if (value != null) {
//            List object = (List) value.getValue();
//
//
//            TypeElement te = (TypeElement) ((DeclaredType) object.get(0)).asElement();
//            System.out.println("hier bin ich ....");
//          }
//
//
//          Map<String, Object> annotationValues = Utils.getAnnotation(ChildModule.class,
//                                                                     element);
//
//          if (annotationValues.get("value") instanceof Object[]) {
//            for (int i = 0; i < ((Object[]) annotationValues.get("value")).length; i++) {
//              AnnotationValue av = (AnnotationValue) ((Object[]) annotationValues.get("value"))[i];
//// TypeElement el = (TypeElement) ((DeclaredType) ((Object[]) annotationValues.get("value"))[i]).asElement();
////              Map<String, Object> annotationValuesChilds = Utils.getAnnotation(ChildModule.class,
////
////
////                       el);
//              TypeElement te = (TypeElement) ((DeclaredType) av.getValue()).asElement();
//              System.out.println("test");
//            }
//
//
////            for ()
//          }
//
//        }
////        applicationInfo.getModule()
//        System.out.println("TypeParameters: " + element.getTypeParameters()
//                                                       .toString());
//        System.out.println("EnclosingElements: " + element.getEnclosedElements()
//                                                          .toString());
//        System.out.println("EnclosingElement: " + element.getEnclosingElement()
//                                                         .toString());
////        // validation ok, just analyse the data
////        ModuleInfo moduleInfo = getModuleInfo(element);
////        // set the current EventBus
////        moduleInfo.setCurrentEventBus(element);
////
////        // TODO handle childs ....
////
////
////        // process all events of the eventBus
////        processEvents(element,
////                      roundEnv);
////
////
//      }
//    }
//
//    String keyName;
//  }
//
//  private PresenterInfo getPresenterInfo(TypeElement element) {
//    PresenterInfo info;
//
//    String presenterInfoKey = element.toString();
//
//    info = presenterInfos.get(presenterInfoKey);
//    if (info == null) {
//      info = new PresenterInfo(presenterInfoKey);
//      presenterInfos.put(presenterInfoKey,
//                         info);
//    }
//
//    return info;
//  }
//
////------------------------------------------------------------------------------
//
//  private ModuleInfo getModuleInfo(TypeElement element) {
//    ModuleInfo info;
//
//    Map<String, Object> annotationValues = Utils.getAnnotation(Events.class,
//                                                               element);
//
//    String moduleInfoKey;
//    if (annotationValues.get("module")
//                        .equals(Mvp4gModule.class)) {
//      moduleInfoKey = Mvp4gModule.class.getCanonicalName();
//    } else {
//      moduleInfoKey = ((TypeElement) ((DeclaredType) annotationValues.get("module")).asElement()).getQualifiedName()
//                                                                                                 .toString();
//    }
//
//    info = applicationInfo.getModule(moduleInfoKey);
//    if (info == null) {
//      applicationInfo.addModules(moduleInfoKey,
//                                 new ModuleInfo(moduleInfoKey));
//    }
//    info = applicationInfo.getModule(moduleInfoKey);
//
//    info.setCurrentEventBus(element);
//    if (!annotationValues.get("module")
//                         .equals(Mvp4gModule.class)) {
//      info.setModule((TypeElement) ((DeclaredType) annotationValues.get("module")).asElement());
//    }
//    info.setStartPresenterName((String) annotationValues.get("startPresenterName"));
//    info.setStartPresenter((TypeElement) ((DeclaredType) annotationValues.get("startPresenter")).asElement());
//    info.setHistoryOnStart((Boolean) annotationValues.get("historyOnStart"));
//    if (annotationValues.get("ginModules") instanceof TypeElement[]) {
//      info.setGinModules((TypeElement[]) annotationValues.get("ginModules"));
//
//    }
//    if (annotationValues.get("ginModuleProperties") instanceof String[]) {
//      info.setGinModuleProperties((String[]) annotationValues.get("ginModuleProperties"));
//    }
//    return info;
//  }
//
//  private void processEvents(TypeElement eventBusElement,
//                             RoundEnvironment roundEnv) {
//    // create the validator
//    EventControl validator = new EventControl(messagerUtils);
//    // iterate over all classes which are annotated with @Event
////    for (Annotation annotationMirror : eventBusElement.get.getAnnotation(Event.class)) {
//
//
////      AnnotationValue annotationValue = Utils.getAnnotationValue("module",
////                                                                 annotationMirror);
////      if (annotationValue != null) {
////        moduleInfoKey = ((TypeElement) ((DeclaredType) annotationValue.getValue()).asElement()).getQualifiedName().toString();
////        info = applicationInfo.getModule(moduleInfoKey);
////        if (info == null) {
////          applicationInfo.addModules(moduleInfoKey,
////                                     new ModuleInfo(moduleInfoKey));
////        }
////      }
//
//
////      // valdation
////      if (!validator.validate(processingEnv,
////                              element)) {
////        isValid = false;
////      } else {
////        // validation ok, just analyse the data
////        ModuleInfo moduleInfo = getModuleInfo(element);
////        // set the current EventBus
////        moduleInfo.setCurrentEventBus(element);
////        // process all events of the eventBus
////        processEvents(element, roundEnv);
////
////
////
////
////      }
////    }
//
//  }
//
//  private ModuleInfo getModuleInfoForEventBus(String eventBusName) {
//    return applicationInfo.getModuleInfoForEventBus(eventBusName);
//  }
//
//  private ModuleInfo getModuleInfo(String name) {
//    return applicationInfo.getModule(name);
//  }
}
