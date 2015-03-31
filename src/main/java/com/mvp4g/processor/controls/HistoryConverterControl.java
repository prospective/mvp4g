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

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;

@Deprecated
public class HistoryConverterControl {

	public void control( ProcessingEnvironment processingEnv, ExecutableElement e, String methodName, AnnotationValue historyConverter ) {

//		if ( historyConverter != null ) {
//			DeclaredType type = (DeclaredType)historyConverter.getValue();
//			if ( !Utils.CLEAR_HISTORY.equals( type.toString() ) ) {
//
//				TypeElement element = (TypeElement)( (DeclaredType)historyConverter.getValue() ).asElement();
//				AnnotationMirror history = Utils.getAnnotationMirror(Utils.HISTORY,
//																														 element);
//				if ( history == null ) {
//					processingEnv.getMessager().printMessage( Kind.ERROR,
//							String.format( Messages.MISSING_ANNOTATION, element.getSimpleName(), Utils.HISTORY ), e );
//				} else {
//					AnnotationValue value = Utils.getAnnotationValue("type",
//																													 history);
//
//					boolean found = false;
//					String convertMetodName = null;
//					String valueStr = ( ( value == null ) || ( value.getValue() == null ) ) ? Utils.HISTORY_CONVERTER_TYPE_DEFAULT : value
//							.getValue().toString();
//
//					List<Element> parameters = new ArrayList<Element>();
//					if ( Utils.HISTORY_CONVERTER_TYPE_NONE.equals( valueStr ) ) {
//						found = true;
//					} else if ( Utils.HISTORY_CONVERTER_TYPE_SIMPLE.equals( valueStr ) ) {
//						convertMetodName = "convertToToken";
//						Element v = processingEnv.getElementUtils().getTypeElement( Utils.STRING );
//						parameters.add( v );
//						parameters.addAll( e.getParameters() );
//					} else {
//						convertMetodName = methodName;
//						if ( convertMetodName == null ) {
//							convertMetodName = "on" + e.getSimpleName().toString().substring( 0, 1 ).toUpperCase()
//									+ e.getSimpleName().toString().substring( 1 );
//						}
//						parameters.addAll( e.getParameters() );
//					}
//
//					Types types = processingEnv.getTypeUtils();
//					String mName;
//					TypeMirror eventBus = e.getEnclosingElement().asType();
//					TypeMirror handlerEventBus;
//
//					for ( ExecutableElement method : ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( element ) ) ) {
//						mName = method.getSimpleName().toString();
//						if ( !found && mName.toString().equals( convertMetodName ) ) {
//							if ( method.getModifiers().contains( Modifier.PUBLIC ) && Utils.STRING.equals( method.getReturnType().toString() ) ) {
//								found = Utils.sameParameters(parameters,
//																						 method.getParameters(),
//																						 e);
//							}
//						} else if ( "convertFromToken".equals( mName ) ) {
//							handlerEventBus = method.getParameters().get( 2 ).asType();
//							if ( !types.isSubtype( eventBus, handlerEventBus ) ) {
//								processingEnv.getMessager().printMessage( Kind.ERROR,
//										String.format( Messages.INVALID_EVENT_BUS, eventBus, type, eventBus, handlerEventBus ), e );
//							}
//						}
//					}
//
//					if ( !found ) {
//						processingEnv.getMessager().printMessage(
//								Kind.ERROR,
//								String.format( Messages.MISSING_METHOD, element.getSimpleName(), Utils.getMethodName(convertMetodName,
//																																																		 parameters), "String" ), e );
//					}
//
//				}
//
//			}
//
//		}

	}
}
