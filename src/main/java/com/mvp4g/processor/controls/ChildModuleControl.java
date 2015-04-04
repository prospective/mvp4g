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

package com.mvp4g.processor.controls;

import com.mvp4g.processor.utils.MessagerUtils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
@Deprecated
public class ChildModuleControl {

  private MessagerUtils messagerUtils;

//------------------------------------------------------------------------------

  public ChildModuleControl(MessagerUtils messagerUtils) {
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
//    // check presenter
//    if (element != null) {
//      // Check if the annotated file is a class
//      if (element.getKind() != ElementKind.CLASS) {
//        messagerUtils.error(element,
//                            Messages.NOT_A_CLASS,
//                            Presenter.class.getSimpleName());
//        return false; // Exit processing
//      }
//      // Check if the annotated file is a class
//      if (element.getModifiers()
//                          .contains(Modifier.ABSTRACT)) {
//        messagerUtils.error(element,
//                            Messages.CLASS_SHOULD_NOT_BE_ABSTRACT,
//                            Presenter.class.getSimpleName());
//        return false; // Exit processing
//      }
//      // check if the class extends BasePresenter
//      if (!Utils.isSubType(processingEnv,
//                           element,
//                           BasePresenter.class)) {
//        messagerUtils.error(element,
//                            Messages.CLASS_SHOULD_EXTEND_BASE_PRESENTER,
//                            Presenter.class.getSimpleName());
//        return false; // Exit processing
//      }
//      // TODO
////      TypeMirror view = null;
////      for (ExecutableElement method : ElementFilter.methodsIn(processingEnv.getElementUtils()
////                                                                           .getAllMembers(element))) {
////        if ("getView".equals(method.getSimpleName()
////                                   .toString())) {
////          view = method.getReturnType();
////        }
////      }
////      checkView(processingEnv,
////                element,
////                view);
//    }
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
