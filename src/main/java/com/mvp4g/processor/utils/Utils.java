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

import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.module.ChildModules;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
  //
  //	public final static String CLEAR_HISTORY = "com.mvp4g.client.history.ClearHistory";
  //	public final static String STRING = String.class.getCanonicalName();
  //	public final static String EVENT = "com.mvp4g.client.annotation.Event";
  public final static String EVENTS = Events.class.getCanonicalName();
  //  public final static String PRESENTER = Presenter.class.getCanonicalName();

  //	public final static String EVENT_HANDLER = "com.mvp4g.client.annotation.EventHandler";
  //	public final static String HISTORY = "com.mvp4g.client.annotation.History";
  public final static String CHILD_MODULES = ChildModules.class.getCanonicalName();

  //	public final static String HISTORY_CONVERTER_TYPE_NONE = "NONE";
  //	public final static String HISTORY_CONVERTER_TYPE_SIMPLE = "SIMPLE";
  //	public final static String HISTORY_CONVERTER_TYPE_DEFAULT = "DEFAULT";
  //
  public final static String ATTRIBUTE_ASYNC                 = "async";
  public final static String ATTRIBUTE_AUTO_DISPLAY          = "autoDisplay";
  public final static String ATTRIBUTE_HISTORY_ON_START      = "historyOnStart";
  public final static String ATTRIBUTE_MODULE                = "module";
  public final static String ATTRIBUTE_MODULE_CLASS          = "moduleClass";
  public final static String ATTRIBUTE_START_PRESENTER       = "startPresenter";
  public final static String ATTRIBUTE_START_PRESENTER_NAME  = "startPresenterName";
  public final static String ATTRIBUTE_GIN_MODULES           = "ginModules";
  public final static String ATTRIBUTE_GIN_MODULE_PROPERTIES = "ginModuleProperties";

  //	public final static String ATTRIBUTE_MODULE_CLASS = "moduleClass";
  //	public final static String ATTRIBUTE_HANDLERS = "handlers";
  //	public final static String ATTRIBUTE_CALLED_METHOD = "calledMethod";
  //	public final static String ATTRIBUTE_HISTORY_CONVERTER = "historyConverter";
  //	public final static String ATTRIBUTE_MODULES_TO_LOAD = "modulesToLoad";y
  //	public final static String ATTRIBUTE_FORWARD_TO_PARENT = "forwardToParent";
  public final static String ATTRIBUTE_VALUE = "value";

  //------------------------------------------------------------------------------

  private static final AnnotationValueVisitor<Object, Void> VALUE_EXTRACTOR =
    new SimpleAnnotationValueVisitor6<Object, Void>() {
      @Override
      protected Object defaultAction(Object o,
                                     Void v) {
        return o;
      }

      @Override
      public Object visitString(String s,
                                Void p) {
        //        if ("<error>".equals(s)) {
        //          throw new CodeGenerationIncompleteException("Unknown type returned as <error>.");
        //        } else if ("<any>".equals(s)) {
        //          throw new CodeGenerationIncompleteException("Unknown type returned as <any>.");
        //        }
        return s;
      }

      @Override
      public Object visitType(TypeMirror t,
                              Void p) {
        return t;
      }

      @Override
      public Object visitArray(List<? extends AnnotationValue> values,
                               Void v) {
        Object[] result = new Object[values.size()];
        for (int i = 0; i < values.size(); i++) {
          result[i] = values.get(i)
            .accept(this,
                    null);
        }
        return result;
      }
    };

  //------------------------------------------------------------------------------


  //
  //	public static boolean sameParameters( List<? extends Element> expected, List<? extends Element> given, Element e ) {
  //		boolean same = ( expected.size() == given.size() );
  //		if ( same ) {
  //			for ( int i = 0; ( i < expected.size() ) && same; i++ ) {
  //				same = expected.get( i ).asType().toString().equals( given.get( i ).asType().toString() );
  //			}
  //		}
  //		return same;
  //	}
  //
  //	public static String getMethodName( String methodName, List<? extends Element> parameters ) {
  //		int parameterSize = parameters.size();
  //		StringBuilder builder = new StringBuilder( parameterSize * 20 + 50 );
  //		builder.append( methodName );
  //		builder.append( "(" );
  //		if ( parameterSize > 0 ) {
  //			builder.append( parameters.get( 0 ).asType().toString() );
  //			for ( int i = 1; i < parameterSize; i++ ) {
  //				builder.append( "," );
  //				builder.append( parameters.get( i ).asType().toString() );
  //			}
  //		}
  //		builder.append( ")" );
  //		return builder.toString();
  //	}

  private Utils() {
  }

  public static AnnotationMirror getAnnotationMirror(String annotationName,
                                                     TypeElement element) {
    if (null == element || null == annotationName || annotationName.length() == 0) {
      return null;
    }
    for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
      if (annotationName.equals(annotationMirror.getAnnotationType()
                                  .toString())) {
        return annotationMirror;
      }
    }
    return null;
  }

  public static AnnotationValue getAnnotationValue(String annotationName,
                                                   String elementName,
                                                   TypeElement elemnent) {
    AnnotationMirror am = Utils.getAnnotationMirror(annotationName,
                                                    elemnent);
    if (am == null) {
      return null;
    }
    return Utils.getAnnotationValue(elementName,
                                    am);
  }

  public static AnnotationValue getAnnotationValue(String elementName,
                                                   AnnotationMirror annotation) {
    if (null == elementName || elementName.length() == 0 || null == annotation) {
      return null;
    }
    String keyName;
    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotation.getElementValues()
      .entrySet()) {
      keyName = entry.getKey()
        .getSimpleName()
        .toString();
      if (elementName.equals(keyName)) {
        return entry.getValue();
      }
    }
    return null;
  }


  //  // Binding<?>.
  //  public static final TypeName BINDING_OF_ANY = ParameterizedTypeName.get(
  //                                                                           ClassName.get(Binding.class), WildcardTypeName.subtypeOf(Object.class));
  //  // Set<Binding<?>>.
  //  public static final TypeName SET_OF_BINDINGS = ParameterizedTypeName.get(
  //                                                                            ClassName.get(Set.class), BINDING_OF_ANY);
  //  // Class<?>[].
  //  public static final TypeName ARRAY_OF_CLASS = ArrayTypeName.of(ParameterizedTypeName.get(
  //                                                                                            ClassName.get(Class.class), WildcardTypeName.subtypeOf(Object.class)));
  //  // @SuppressWarnings("unchecked")
  //  public static final AnnotationSpec UNCHECKED = AnnotationSpec.builder(SuppressWarnings.class)
  //                                                               .addMember("value", "$S", "unchecked")
  //                                                               .build();
  //
  //  private Util() {
  //  }

  public static boolean isSubType(ProcessingEnvironment processingEnv,
                                  Element element,
                                  Class clazz) {
    TypeElement      el     = (TypeElement) element;
    final TypeMirror parent = el.getSuperclass();

    if (! parent.getKind()
      .equals(TypeKind.DECLARED)) {
    } else {
      final DeclaredType parentType = (DeclaredType) parent;
      final Element parentEl = parentType.asElement();
      if (! parentEl.getKind()
        .equals(ElementKind.CLASS)) {
        return false;
      }
    }
    if (processingEnv.getTypeUtils()
      .isSameType(parent,
                  processingEnv.getElementUtils()
                    .getTypeElement(clazz.getCanonicalName())
                    .asType())) {
      return true;
    } else if (parent.toString()
      .contains(clazz.getCanonicalName())) {
      return true;
    } else if (processingEnv.getTypeUtils()
      .isSameType(parent,
                  processingEnv.getElementUtils()
                    .getTypeElement("java.lang.Object")
                    .asType())) {
      return false;
    } else {
      return Utils.isSubType(processingEnv,
                             ((DeclaredType) parent).asElement(),
                             clazz);
    }
  }

  //  /**
  //   * Returns the supertype, or {@code null} if the supertype is a platform
  //   * class. This is intended for annotation processors that assume platform
  //   * classes will never be annotated with application annotations.
  //   */
  //  public static TypeMirror getApplicationSupertype(TypeElement type) {
  //    TypeMirror supertype = type.getSuperclass();
  //    return Keys.isPlatformType(supertype.toString()) ? null : supertype;
  //  }
  //
  //  /** Returns a class name to complement {@code type}. */
  //  public static ClassName adapterName(ClassName type, String suffix) {
  //    return ClassName.get(type.packageName(),
  //                         Joiner.on('$').join(type.simpleNames()) + suffix);
  //  }
  //
  //  /** Returns a string for {@code type}. Primitive types are always boxed. */
  //  public static String typeToString(TypeMirror type) {
  //    StringBuilder result = new StringBuilder();
  //    typeToString(type, result, '.');
  //    return result.toString();
  //  }

  /**
   * Returns a string for the raw type of {@code type}. Primitive types are always boxed.
   */
  public static String rawTypeToString(TypeMirror type,
                                       char innerClassSeparator) {
    if (! (type instanceof DeclaredType)) {
      throw new IllegalArgumentException("Unexpected type: " + type);
    }
    StringBuilder result       = new StringBuilder();
    DeclaredType  declaredType = (DeclaredType) type;
    rawTypeToString(result,
                    (TypeElement) declaredType.asElement(),
                    innerClassSeparator);
    return result.toString();
  }

  /**
   * Appends a string for {@code type} to {@code result}. Primitive types are
   * always boxed.
   *
   * @param innerClassSeparator either '.' or '$', which will appear in a
   *                            class name like "java.lang.Map.Entry" or "java.lang.Map$Entry".
   *                            Use '.' for references to existing types in code. Use '$' to define new
   *                            class names and for strings that will be used by runtime reflection.
   */
  public static void typeToString(final TypeMirror type,
                                  final StringBuilder result,
                                  final char innerClassSeparator) {
    type.accept(new SimpleTypeVisitor6<Void, Void>() {
                  @Override
                  protected Void defaultAction(TypeMirror typeMirror,
                                               Void v) {
                    throw new UnsupportedOperationException(
                      "Unexpected TypeKind " + typeMirror.getKind() + " for " + typeMirror);
                  }

                  @Override
                  public Void visitDeclared(DeclaredType declaredType,
                                            Void v) {
                    TypeElement typeElement = (TypeElement) declaredType.asElement();
                    rawTypeToString(result,
                                    typeElement,
                                    innerClassSeparator);
                    List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
                    if (! typeArguments.isEmpty()) {
                      result.append("<");
                      for (int i = 0; i < typeArguments.size(); i++) {
                        if (i != 0) {
                          result.append(", ");
                        }
                        typeToString(typeArguments.get(i),
                                     result,
                                     innerClassSeparator);
                      }
                      result.append(">");
                    }
                    return null;
                  }

                  @Override
                  public Void visitPrimitive(PrimitiveType primitiveType,
                                             Void v) {
                    result.append(box((PrimitiveType) type));
                    return null;
                  }

                  @Override
                  public Void visitArray(ArrayType arrayType,
                                         Void v) {
                    TypeMirror type = arrayType.getComponentType();
                    if (type instanceof PrimitiveType) {
                      result.append(type.toString()); // Don't box, since this is an array.
                    } else {
                      typeToString(arrayType.getComponentType(),
                                   result,
                                   innerClassSeparator);
                    }
                    result.append("[]");
                    return null;
                  }

                  @Override
                  public Void visitTypeVariable(TypeVariable typeVariable,
                                                Void v) {
                    result.append(typeVariable.asElement()
                                    .getSimpleName());
                    return null;
                  }

                  @Override
                  public Void visitError(ErrorType errorType,
                                         Void v) {
                    // Error type found, a type may not yet have been generated, but we need the type
                    // so we can generate the correct code in anticipation of the type being available
                    // to the compiler.

                    // Paramterized types which don't exist are returned as an error type whose name is "<any>"
                    //                    if ("<any>".equals(errorType.toString())) {
                    //                      throw new CodeGenerationIncompleteException("Type reported as <any> is likely a not-yet generated parameterized type.");
                    //                    }
                    // TODO(cgruber): Figure out a strategy for non-FQCN cases.
                    result.append(errorType.toString());
                    return null;
                  }
                },
                null);
  }

  /**
   * Returns a string for {@code type}. Primitive types are always boxed.
   */
  public static TypeName injectableType(TypeMirror type) {
    return type.accept(new SimpleTypeVisitor6<TypeName, Void>() {
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

                         @Override
                         protected TypeName defaultAction(TypeMirror typeMirror,
                                                          Void v) {
                           return TypeName.get(typeMirror);
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

  //
  //  // TODO(sgoldfed): better format for other types of elements?
  //  static String elementToString(Element element) {
  //    switch (element.getKind()) {
  //      case FIELD:
  //        // fall through
  //      case CONSTRUCTOR:
  //        // fall through
  //      case METHOD:
  //        return element.getEnclosingElement() + "." + element;
  //      default:
  //        return element.toString();
  //    }
  //  }

  /**
   * Returns the annotation on {@code element} formatted as a Map. This returns
   * a Map rather than an instance of the annotation interface to work-around
   * the fact that Class and Class[] fields won't work at code generation time.
   * See http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5089128
   */
  public static Map<String, Object> getAnnotation(Class<?> annotationType,
                                                  Element element) {
    for (AnnotationMirror annotation : element.getAnnotationMirrors()) {
      if (! rawTypeToString(annotation.getAnnotationType(),
                            '$')
        .equals(annotationType.getName())) {
        continue;
      }

      Map<String, Object> result = new LinkedHashMap<String, Object>();
      for (Method m : annotationType.getMethods()) {
        result.put(m.getName(),
                   m.getDefaultValue());
      }
      for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> e : annotation.getElementValues()
        .entrySet()) {
        String name = e.getKey()
          .getSimpleName()
          .toString();
        Object value = e.getValue()
          .accept(VALUE_EXTRACTOR,
                  null);
        Object defaultValue = result.get(name);
        if (defaultValue != null) {
          if (! lenientIsInstance(defaultValue.getClass(),
                                  value)) {
            throw new IllegalStateException(String.format("Value of %s.%s is a %s but expected a %s\n    value: %s",
                                                          annotationType,
                                                          name,
                                                          value.getClass()
                                                            .getName(),
                                                          defaultValue.getClass()
                                                            .getName(),
                                                          value instanceof Object[] ? Arrays.toString((Object[]) value) : value));
          }
        }
        result.put(name,
                   value);
      }
      return result;
    }
    return null; // Annotation not found.
  }

  //  /**
  //   * Returns the no-args constructor for {@code type}, or null if no such
  //   * constructor exists.
  //   */
  //  public static ExecutableElement getNoArgsConstructor(TypeElement type) {
  //    for (Element enclosed : type.getEnclosedElements()) {
  //      if (enclosed.getKind() != ElementKind.CONSTRUCTOR) {
  //        continue;
  //      }
  //      ExecutableElement constructor = (ExecutableElement) enclosed;
  //      if (constructor.getParameters().isEmpty()) {
  //        return constructor;
  //      }
  //    }
  //    return null;
  //  }
  //
  //  /**
  //   * Returns true if generated code can invoke {@code constructor}. That is, if
  //   * the constructor is non-private and its enclosing class is either a
  //   * top-level class or a static nested class.
  //   */
  //  public static boolean isCallableConstructor(ExecutableElement constructor) {
  //    if (constructor.getModifiers().contains(Modifier.PRIVATE)) {
  //      return false;
  //    }
  //    TypeElement type = (TypeElement) constructor.getEnclosingElement();
  //    return type.getEnclosingElement().getKind() == ElementKind.PACKAGE
  //           || type.getModifiers().contains(Modifier.STATIC);
  //  }
  //
  //
  //  /**
  //   * Returns a user-presentable string like {@code coffee.CoffeeModule}.
  //   */
  //  public static String className(ExecutableElement method) {
  //    return ((TypeElement) method.getEnclosingElement()).getQualifiedName().toString();
  //  }
  //
  //  public static boolean isInterface(TypeMirror typeMirror) {
  //    return typeMirror instanceof DeclaredType
  //           && ((DeclaredType) typeMirror).asElement().getKind() == ElementKind.INTERFACE;
  //  }
  //
  //  static boolean isStatic(Element element) {
  //    for (Modifier modifier : element.getModifiers()) {
  //      if (modifier.equals(Modifier.STATIC)) {
  //        return true;
  //      }
  //    }
  //    return false;
  //  }
  //
  //  static ParameterizedTypeName bindingOf(TypeMirror type) {
  //    return ParameterizedTypeName.get(ClassName.get(Binding.class), injectableType(type));
  //  }
  //
  //  /**
  //   * An exception thrown when a type is not extant (returns as an error type),
  //   * usually as a result of another processor not having yet generated its types upon
  //   * which a dagger-annotated type depends.
  //   */
  //  final static class CodeGenerationIncompleteException extends IllegalStateException {
  //    public CodeGenerationIncompleteException(String s) {
  //      super(s);
  //    }
  //  }

  //==============================================================================

  static void rawTypeToString(StringBuilder result,
                              TypeElement type,
                              char innerClassSeparator) {
    String packageName = getPackage(type).getQualifiedName()
      .toString();
    String qualifiedName = type.getQualifiedName()
      .toString();
    if (packageName.isEmpty()) {
      result.append(qualifiedName.replace('.',
                                          innerClassSeparator));
    } else {
      result.append(packageName);
      result.append('.');
      result.append(qualifiedName.substring(packageName.length() + 1)
                      .replace('.',
                               innerClassSeparator));
    }
  }


  //==============================================================================

  public static PackageElement getPackage(Element type) {
    while (type.getKind() != ElementKind.PACKAGE) {
      type = type.getEnclosingElement();
    }
    return (PackageElement) type;
  }

  /**
   * Returns true if {@code value} can be assigned to {@code expectedClass}.
   * Like {@link Class#isInstance} but more lenient for {@code Class<?>} values.
   */
  private static boolean lenientIsInstance(Class<?> expectedClass,
                                           Object value) {
    if (expectedClass.isArray()) {
      Class<?> componentType = expectedClass.getComponentType();
      if (! (value instanceof Object[])) {
        return false;
      }
      for (Object element : (Object[]) value) {
        if (! lenientIsInstance(componentType,
                                element)) {
          return false;
        }
      }
      return true;
    } else if (expectedClass == Class.class) {
      return value instanceof TypeMirror;
    } else {
      return expectedClass == value.getClass();
    }
  }

}
