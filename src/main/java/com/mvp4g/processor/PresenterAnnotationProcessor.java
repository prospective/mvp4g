package com.mvp4g.processor;

import com.google.auto.service.AutoService;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.controls.PresenterControl;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.util.LinkedHashSet;
import java.util.Set;

@AutoService(Processor.class)
public class PresenterAnnotationProcessor
  extends AbstractProcessor {

  private MessagerUtils messagerUtils;

//------------------------------------------------------------------------------

  public PresenterAnnotationProcessor() {
    super();
  }

//------------------------------------------------------------------------------

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    messagerUtils = new MessagerUtils(processingEnv.getMessager());
  }

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

//------------------------------------------------------------------------------

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations");

      // valdation
      if (!validate(annotations,
                    roundEnv)) {
        return true;
      }
      // TODO may be we replace the generators with apt in the future ... :-)

    } else {
      // create start message
      messagerUtils.note("[PresenterAnnotationProcessor] - processing annotations finished");
    }
    return true;
  }

//------------------------------------------------------------------------------

  private boolean validate(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
    // state of validation
    boolean state = true;
    // create the validator
    PresenterControl validator = new PresenterControl(messagerUtils);
    // iterate over all classes which are annotated with @Presenter
    for (TypeElement anotation : annotations) {
      for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(anotation))) {
        state = validator.validate(processingEnv,
                                   element);
      }
    }
    return state;
  }
}
