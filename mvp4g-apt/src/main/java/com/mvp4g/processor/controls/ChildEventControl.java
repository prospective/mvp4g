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

import com.mvp4g.processor.controls.info.ApplicationInfo;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.List;

@Deprecated
public class ChildEventControl {

	public void control( ProcessingEnvironment processingEnv, ExecutableElement e, TypeElement parentEventBus,
			List<? extends AnnotationValue> modulesToLoad, ApplicationInfo appInfo ) {
//
//		if ( modulesToLoad != null ) {
//
//			String mName;
//			ModuleInfo info;
//			TypeElement type;
//			String module;
//			String methodName = e.getSimpleName().toString();
//			Map<String, ModuleInfo> modules = appInfo.getModule();
//			for ( AnnotationValue value : modulesToLoad ) {
//				module = ( (DeclaredType)value.getValue() ).toString();
//				info = modules.get( module );
//				if ( ( info == null ) || ( info.getCurrentEventBus() == null ) ) {
//					processingEnv.getMessager().printMessage( Kind.ERROR, String.format( Messages.MODULE_NO_EVENT_BUS, module ), e );
//				} else {
//					type = info.getParentEventBus();
//					if ( ( type == null ) || ( !type.getQualifiedName().toString().equals( parentEventBus.getQualifiedName().toString() ) ) ) {
//						processingEnv.getMessager().printMessage( Kind.ERROR, String.format( Messages.MODULE_NOT_A_CHILD, parentEventBus.getQualifiedName(), module ), e );
//					} else {
//						type = info.getCurrentEventBus();
//						boolean found = false;
//						for ( ExecutableElement method : ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( type ) ) ) {
//							mName = method.getSimpleName().toString();
//							if ( mName.toString().equals( methodName ) ) {
//								if ( method.getModifiers().contains( Modifier.PUBLIC ) ) {
//									found = Utils.sameParameters(e.getParameters(),
//																							 method.getParameters(),
//																							 e);
//									if ( found ) {
//										break;
//									}
//								}
//							}
//						}
//						if ( !found ) {
//							processingEnv.getMessager().printMessage(
//									Kind.ERROR,
//									String.format( Messages.MISSING_METHOD, type.getSimpleName(), Utils.getMethodName(methodName,
//																																																		e
//																																																			.getParameters()), e.getReturnType() ), e );
//						}
//					}
//				}
//			}
//		}
	}
}
