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
import java.util.List;

@Deprecated
public class HandlerControl {

	public void control( ProcessingEnvironment processingEnv, ExecutableElement e, String methodName, List<? extends AnnotationValue> handlers ) {

//		if ( handlers != null ) {
//
//			if ( methodName == null ) {
//				methodName = "on" + e.getSimpleName().toString().substring( 0, 1 ).toUpperCase() + e.getSimpleName().toString().substring( 1 );
//			}
//
//			TypeElement eventBusElement = (TypeElement)e.getEnclosingElement();
//			TypeMirror eventBus = e.getEnclosingElement().asType();
//			boolean withEvents = ( Utils.getAnnotationMirror(Utils.EVENTS,
//																											 eventBusElement) != null );
//			TypeMirror handlerEventBus;
//
//			String mName;
//			Types types = processingEnv.getTypeUtils();
//			Elements elements = processingEnv.getElementUtils();
//			for ( AnnotationValue value : handlers ) {
//				TypeElement type = (TypeElement)( (DeclaredType)value.getValue() ).asElement();
//
//				boolean found = false;
//				for ( ExecutableElement method : ElementFilter.methodsIn( elements.getAllMembers( type ) ) ) {
//					mName = method.getSimpleName().toString();
//					if ( !found && mName.toString().equals( methodName ) ) {
//						if ( method.getModifiers().contains( Modifier.PUBLIC ) ) {
//							found = Utils.sameParameters(e.getParameters(),
//																					 method.getParameters(),
//																					 e);
//						}
//					} else if ( "getEventBus".equals( mName ) ) {
//						handlerEventBus = method.getReturnType();
//						if ( !types.isSubtype( eventBus, handlerEventBus ) ) {
//							//in this event part of the parent event bus
//							if ( withEvents || !types.isSubtype( handlerEventBus, eventBus ) ) {
//								processingEnv.getMessager().printMessage( Kind.ERROR,
//										String.format( Messages.INVALID_EVENT_BUS, eventBus, type.getSimpleName(), eventBus, handlerEventBus ), e );
//							}
//						}
//					}
//				}
//				if ( Utils.getAnnotationMirror(Utils.PRESENTER,
//																			 type) == null ) {
//					if ( Utils.getAnnotationMirror(Utils.EVENT_HANDLER,
//																				 type) == null ) {
//						processingEnv.getMessager().printMessage(
//								Kind.ERROR,
//								String.format( Messages.MISSING_ANNOTATION_OR, type.getSimpleName(), Utils.PRESENTER,
//										Utils.EVENT_HANDLER ), e );
//					}
//				}
//				if ( !found ) {
//					processingEnv.getMessager().printMessage(
//							Kind.ERROR,
//							String.format( Messages.MISSING_METHOD, type.getSimpleName(),
//									Utils.getMethodName(methodName,
//																			e.getParameters()), "void" ), e );
//				}
//			}
//		}
	}

}
