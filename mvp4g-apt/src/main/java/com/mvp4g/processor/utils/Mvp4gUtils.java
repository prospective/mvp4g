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

package com.mvp4g.processor.utils;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.History;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.SimpleTypeVisitor6;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Mvp4gUtils {

  public final static String EVENT     = Event.class.getCanonicalName();
  public final static String EVENTS    = Events.class.getCanonicalName();
  public final static String HISTORY   = History.class.getCanonicalName();
//  public final static String PRESENTER = Presenter.class.getCanonicalName();

  public final static String ATTRIBUTE_ACTIVATE               = "activate";
  public final static String ATTRIBUTE_ACTIVATE_NAMES         = "activateNames";
  public final static String ATTRIBUTE_ASYNC                  = "async";
  public final static String ATTRIBUTE_AUTO_DISPLAY           = "autoDisplay";
  public final static String ATTRIBUTE_BIND                   = "bind";
  public final static String ATTRIBUTE_BIND_NAMES             = "bindNames";
  public final static String ATTRIBUTE_BROADCAST_TO           = "broadcastTo";
  public final static String ATTRIBUTE_DEACTIVATE             = "deactivate";
  public final static String ATTRIBUTE_DEACTIVATE_NAMES       = "deactivateNames";
  public final static String ATTRIBUTE_FORWARD_TO_MODULES     = "forwardToModules";
  public final static String ATTRIBUTE_FORWARD_TO_PARENT      = "forwardToParent";
  public final static String ATTRIBUTE_GENERATE               = "generate";
  public final static String ATTRIBUTE_GENERATE_NAMES         = "generateNames";
  public final static String ATTRIBUTE_GIN_MODULES            = "ginModules";
  public final static String ATTRIBUTE_GIN_MODULE_PROPERTIES  = "ginModuleProperties";
  public final static String ATTRIBUTE_HANDLERS               = "handlers";
  public final static String ATTRIBUTE_HANDLER_NAMES          = "handlerNames";
  public final static String ATTRIBUTE_HISTORY_CONVERTER      = "historyConverter";
//  public final static String ATTRIBUTE_HISTORY_CONVERTER_NAME = "historyConverterName";
  public final static String ATTRIBUTE_HISTORY_ON_START       = "historyOnStart";
  public final static String ATTRIBUTE_MODULE                 = "module";
  public final static String ATTRIBUTE_MODULE_CLASS           = "moduleClass";
//  public final static String ATTRIBUTE_MODULES_TO_LOAD        = "modulesToLoad";
  public final static String ATTRIBUTE_MULTIPLE               = "mutiple";
  public final static String ATTRIBUTE_NAME                   = "name";
  public final static String ATTRIBUTE_NAVIGATION_EVENT       = "navigationEvent";
  public final static String ATTRIBUTE_PASSIVE                = "passive";
  public final static String ATTRIBUTE_START_PRESENTER        = "startPresenter";
  public final static String ATTRIBUTE_START_PRESENTER_NAME   = "startPresenterName";
  public final static String ATTRIBUTE_TYPE                   = "type";
//  public final static String ATTRIBUTE_VALUE                  = "value";
  public final static String ATTRIBUTE_VIEW                   = "view";
  public final static String ATTRIBUTE_VIEW_NAME              = "viewName";

  public final static String METHOD_BIND  = "bind";
  public final static String METHOD_EVENT = "on";

//------------------------------------------------------------------------------

  public static synchronized boolean isImplementingMethod(ProcessingEnvironment processingEnv,
                                                          TypeElement element,
                                                          ExecutableElement compareMethod,
                                                          String methodName) {
    for (ExecutableElement method : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                         .getAllMembers(element))) {
      if (method.getSimpleName()
                .toString()
                .equals(methodName)) {
        return Mvp4gUtils.hasSameSignature(method.getParameters(),
                                           compareMethod.getParameters());
      }
    }
    return false;
  }

  public static synchronized boolean hasSameSignature(List<? extends VariableElement> parameters01,
                                                      List<? extends VariableElement> parameters02) {
    if (parameters01.size() != parameters02.size()) {
      return false;
    }
    for (int i = 0; i < parameters01.size(); i++) {
      TypeName typeName01 = injectableType(parameters01.get(i)
                                                       .asType());
      TypeName typeName02 = injectableType(parameters02.get(i)
                                                       .asType());
      if (!typeName01.toString()
                     .equals(typeName02.toString())) {
        return false;
      }
    }
    return true;
  }

  public static synchronized List<ExecutableElement> getImplementingMethod(ProcessingEnvironment processingEnv,
                                                                           TypeElement element,
                                                                           String methodName) {
    List<ExecutableElement> methods = new ArrayList<>();
    for (ExecutableElement method : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                         .getAllMembers(element))) {
      if (method.getSimpleName()
                .toString()
                .equals(methodName)) {
        methods.add(method);
      }
    }
    return methods;
  }

  public static synchronized boolean isImplementingType(TypeElement element,
                                                        Class clazz) {
    List<? extends TypeMirror> interfaces = element.getInterfaces();
    if (interfaces.size() == 0) {
      // may be the super class will implement the interface ...
      TypeMirror superClassMirror = element.getSuperclass();
      return !superClassMirror.toString()
                              .equals("<none>") &&
             isImplementingType((TypeElement) ((DeclaredType) superClassMirror).asElement(),
                                                                      clazz);
    } else {
      for (TypeMirror mirror : interfaces) {
        TypeElement el = (TypeElement) ((DeclaredType) mirror).asElement();
        if (el.toString()
              .equals(clazz.getCanonicalName())) {
          return true;
        }
      }
      return false;
    }
  }

//==============================================================================

  /**
   * Returns a string for {@code type}. Primitive types are always boxed.
   */
  private static TypeName injectableType(TypeMirror type) {
    return type.accept(new SimpleTypeVisitor6<TypeName, Void>() {
                         @Override
                         protected TypeName defaultAction(TypeMirror typeMirror,
                                                          Void v) {
                           return TypeName.get(typeMirror);
                         }

                         @Override
                         public TypeName visitPrimitive(PrimitiveType primitiveType,
                                                        Void v) {
                           return box(primitiveType);
                         }

                         @Override
                         public TypeName visitError(ErrorType errorType,
                                                    Void v) {
                           // Error type found, a type may not yet have been generated, but we need the type
                           // so we can generate the correct code in anticipation of the type being available
                           // to the compiler.

                           // Paramterized types which don't exist are returned as an error type whose name is "<any>"
                           //                           if ("<any>".equals(errorType.toString())) {
                           //                             throw new CodeGenerationIncompleteException("Type reported as <any> is likely a not-yet generated parameterized type.");
                           //                           }

                           return ClassName.bestGuess(errorType.toString());
                         }
                       },
                       null);
  }

  private static TypeName box(PrimitiveType primitiveType) {
    switch (primitiveType.getKind()) {
      case BYTE:
        return ClassName.get(Byte.class);
      case SHORT:
        return ClassName.get(Short.class);
      case INT:
        return ClassName.get(Integer.class);
      case LONG:
        return ClassName.get(Long.class);
      case FLOAT:
        return ClassName.get(Float.class);
      case DOUBLE:
        return ClassName.get(Double.class);
      case BOOLEAN:
        return ClassName.get(Boolean.class);
      case CHAR:
        return ClassName.get(Character.class);
      case VOID:
        return ClassName.get(Void.class);
      default:
        throw new AssertionError();
    }
  }
}
