package com.mvp4g.processor;

import com.google.auto.service.AutoService;
import com.mvp4g.client.Mvp4gModule;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.controls.EventBusControl;
import com.mvp4g.processor.controls.EventControl;
import com.mvp4g.processor.controls.PresenterControl;
import com.mvp4g.processor.info.ApplicationInfo;
import com.mvp4g.processor.info.ModuleInfo;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.lang.model.util.ElementFilter.typesIn;

@AutoService(Processor.class)
public class Mvp4gAnnotationProcessor
  extends AbstractProcessor {

  /* application info */
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
    Set<String> annotataions = new LinkedHashSet<String>();
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

    applicationInfo = new ApplicationInfo();
    isValid = true;

    messagerUtils = new MessagerUtils(processingEnv.getMessager());
  }

//------------------------------------------------------------------------------

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations");
      // processing Events annotatiom
      processEventBus(annotations,
                      roundEnv);


      // processing Presenter annotatiom
      processPresenter(annotations,
                       roundEnv);


      // process only is all tests has passed successfully
      if (!isValid) {
        return true;
      }
      // TODO may be we replace the generators with apt in the future ... :-)

    } else {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations finished");

      // TODO übergreifende validierung


      // at least, we clear the appInfo
      applicationInfo = new ApplicationInfo();
    }
    return true;
  }

//------------------------------------------------------------------------------

  private void processEventBus(Set<? extends TypeElement> annotations,
                               RoundEnvironment roundEnv) {
    // create the validator
    EventBusControl validator = new EventBusControl(messagerUtils);
    // iterate over all classes which are annotated with @Presenter
    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(Events.class))) {
      // valdation
      if (!validator.validate(processingEnv,
                              element)) {
        isValid = false;
      } else {
        // validation ok, just analyse the data
        ModuleInfo moduleInfo = getModuleInfo(element);
        // set the current EventBus
        moduleInfo.setCurrentEventBus(element);
        // process all events of the eventBus
        processEvents(element, roundEnv);




      }
    }
  }

  private void processEvents(TypeElement eventBusElement,
                             RoundEnvironment roundEnv) {
    // create the validator
    EventControl validator = new EventControl(messagerUtils);
    // iterate over all classes which are annotated with @Event
//    for (Annotation annotationMirror : eventBusElement.get.getAnnotation(Event.class)) {


//      AnnotationValue annotationValue = Utils.getAnnotationValue("module",
//                                                                 annotationMirror);
//      if (annotationValue != null) {
//        moduleInfoKey = ((TypeElement) ((DeclaredType) annotationValue.getValue()).asElement()).getQualifiedName().toString();
//        info = applicationInfo.getModule(moduleInfoKey);
//        if (info == null) {
//          applicationInfo.addModules(moduleInfoKey,
//                                     new ModuleInfo(moduleInfoKey));
//        }
//      }


//      // valdation
//      if (!validator.validate(processingEnv,
//                              element)) {
//        isValid = false;
//      } else {
//        // validation ok, just analyse the data
//        ModuleInfo moduleInfo = getModuleInfo(element);
//        // set the current EventBus
//        moduleInfo.setCurrentEventBus(element);
//        // process all events of the eventBus
//        processEvents(element, roundEnv);
//
//
//
//
//      }
//    }

  }

  private void processPresenter(Set<? extends TypeElement> annotations,
                                RoundEnvironment roundEnv) {
    // create the validator
    PresenterControl validator = new PresenterControl(messagerUtils);
    // iterate over all classes which are annotated with @Presenter
    for (TypeElement element : typesIn(roundEnv.getElementsAnnotatedWith(Presenter.class))) {
      // valdation
      if (!validator.validate(processingEnv,
                              element)) {
        isValid = false;
      } else {
        messagerUtils.note(element,
                           element.getTypeParameters()
                                  .toString());
        messagerUtils.note(element, element.getEnclosedElements().toString());
        messagerUtils.note(element, element.getEnclosingElement().toString());
        // todo create presenter control
      }
    }
  }

//------------------------------------------------------------------------------

  private ModuleInfo getModuleInfo(TypeElement element) {
    ModuleInfo info;
    String moduleInfoKey = Mvp4gModule.class.getCanonicalName();

    AnnotationMirror annotationMirror = Utils.getAnnotationMirror(Events.class.getCanonicalName(),
                                                                  element);
    if (annotationMirror != null) {
      AnnotationValue annotationValue = Utils.getAnnotationValue("module",
                                                                 annotationMirror);
      if (annotationValue != null) {
        moduleInfoKey = ((TypeElement) ((DeclaredType) annotationValue.getValue()).asElement()).getQualifiedName().toString();
        info = applicationInfo.getModule(moduleInfoKey);
        if (info == null) {
          applicationInfo.addModules(moduleInfoKey,
                                     new ModuleInfo(moduleInfoKey));
        }
      }
    }
    // set current eventbus
    info = applicationInfo.getModule(moduleInfoKey);

    return info;
  }

}
