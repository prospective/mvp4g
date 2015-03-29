package com.mvp4g.processor.controls;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.processor.Messages;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class PresenterControl {

  private MessagerUtils messagerUtils;

//------------------------------------------------------------------------------

  public PresenterControl(MessagerUtils messagerUtils) {
    this.messagerUtils = messagerUtils;
  }

//------------------------------------------------------------------------------

  /**
   * Checks the class if:
   * <ul>
   * <li>it is a class</li>
   * <li>that the class is not abstract</li>
   * <li>that class extends BasePresenter</li>
   * </ul>
   *
   * @param processingEnv the processing enviremet
   * @param element the annotated presenter to validate
   * @return true, if it is valid class
   */
  public boolean validate(ProcessingEnvironment processingEnv,
                          TypeElement element) {
    // check presenter
    if (element != null) {
      // Check if the annotated file is a class
      if (element.getKind() != ElementKind.CLASS) {
        messagerUtils.error(element,
                            Messages.NOT_A_CLASS,
                            Presenter.class.getSimpleName());
        return false; // Exit processing
      }
      // Check if the annotated file is a class
      if (element.getModifiers()
                          .contains(Modifier.ABSTRACT)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_NOT_BE_ABSTRACT,
                            Presenter.class.getSimpleName());
        return false; // Exit processing
      }
      // check if the class extends BasePresenter
      if (!Utils.isSubType(processingEnv,
                           element,
                           BasePresenter.class)) {
        messagerUtils.error(element,
                            Messages.CLASS_SHOULD_EXTEND_BASE_PRESENTER,
                            Presenter.class.getSimpleName());
        return false; // Exit processing
      }
      // TODO
//      TypeMirror view = null;
//      for (ExecutableElement method : ElementFilter.methodsIn(processingEnv.getElementUtils()
//                                                                           .getAllMembers(element))) {
//        if ("getView".equals(method.getSimpleName()
//                                   .toString())) {
//          view = method.getReturnType();
//        }
//      }
//      checkView(processingEnv,
//                element,
//                view);
    }
    return true;
  }

//------------------------------------------------------------------------------

//   private void checkView(ProcessingEnvironment processingEnv,
//                         TypeElement annotatedElement,
//                         TypeMirror view) {
//    AnnotationMirror a = Utils.getAnnotationMirror(Utils.PRESENTER,
//                                                   annotatedElement);
//    if (a != null) {
//      AnnotationValue v = Utils.getAnnotationValue("view",
//                                                   a);
//      if (v != null) {
//        DeclaredType injectedView = (DeclaredType) v.getValue();
//        if (!processingEnv.getTypeUtils()
//                          .isSubtype(injectedView,
//                                     view)) {
//          messagerUtils.error(annotatedElement,
//                              Messages.INVALID_VIEW,
//                              injectedView,
//                              view,
//                              injectedView,
//                              view);
//        }
//      }
//    }
//  }
}
