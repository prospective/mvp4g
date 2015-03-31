package com.mvp4g.processor.utils;

import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Presenter;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import java.util.Map;

public class Utils {
  //
//	final public static String CLEAR_HISTORY = "com.mvp4g.client.history.ClearHistory";
//	final public static String STRING = String.class.getCanonicalName();
//	final public static String EVENT = "com.mvp4g.client.annotation.Event";
  public final static String EVENTS    = Events.class.getCanonicalName();
  public final static String PRESENTER = Presenter.class.getCanonicalName();

//	final public static String EVENT_HANDLER = "com.mvp4g.client.annotation.EventHandler";
//	final public static String HISTORY = "com.mvp4g.client.annotation.History";
//	final public static String CHILD_MODULES = "com.mvp4g.client.annotation.module.ChildModules";
//
//	final public static String HISTORY_CONVERTER_TYPE_NONE = "NONE";
//	final public static String HISTORY_CONVERTER_TYPE_SIMPLE = "SIMPLE";
//	final public static String HISTORY_CONVERTER_TYPE_DEFAULT = "DEFAULT";
//
//	final public static String ATTRIBUTE_MODULE = "module";
//	final public static String ATTRIBUTE_MODULE_CLASS = "moduleClass";
//	final public static String ATTRIBUTE_HANDLERS = "handlers";
//	final public static String ATTRIBUTE_CALLED_METHOD = "calledMethod";
//	final public static String ATTRIBUTE_HISTORY_CONVERTER = "historyConverter";
//	final public static String ATTRIBUTE_MODULES_TO_LOAD = "modulesToLoad";
//	final public static String ATTRIBUTE_FORWARD_TO_PARENT = "forwardToParent";
//	final public static String ATTRIBUTE_VALUE = "value";
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

  public static AnnotationMirror getAnnotationMirror(String annotationName,
                                                     TypeElement element) {
    if (null == element || null == annotationName || annotationName.length() == 0) {
      return null;
    }
    for (AnnotationMirror a : element.getAnnotationMirrors()) {
      if (annotationName.equals(a.getAnnotationType()
                                 .toString())) {
        return a;
      }
    }
    return null;
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

  public static boolean isSubType(ProcessingEnvironment processingEnv,
                                  Element element,
                                  Class clazz) {
    TypeElement el = (TypeElement) element;
    final TypeMirror parent = el.getSuperclass();

    if (!parent.getKind()
               .equals(TypeKind.DECLARED)) {
    } else {
      final DeclaredType parentType = (DeclaredType) parent;
      final Element parentEl = parentType.asElement();
      if (!parentEl.getKind()
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
}
