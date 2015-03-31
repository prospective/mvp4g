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

import com.mvp4g.processor.info.ApplicationInfo;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@Deprecated
public class ParentEventControl {

	public void control( ProcessingEnvironment processingEnv, ExecutableElement e, TypeElement eventBus, boolean forwardToParent,
			ApplicationInfo appInfo ) {

//		if ( forwardToParent ) {
//
//			String module = appInfo.getEventBus().get( eventBus.getQualifiedName().toString() );
//			Map<String, ModuleInfo> modules = appInfo.getModule();
//			ModuleInfo info = ( module == null ) ? null : modules.get( module );
//			if ( ( info == null ) || ( info.getParentEventBus() == null ) ) {
//				processingEnv.getMessager().printMessage( Kind.ERROR, String.format( Messages.MODULE_NO_PARENT, eventBus.getQualifiedName() ), e );
//			} else {
//				TypeElement type = info.getParentEventBus();
//				boolean found = false;
//				String mName;
//				String methodName = e.getSimpleName().toString();
//				for ( ExecutableElement method : ElementFilter.methodsIn( processingEnv.getElementUtils().getAllMembers( type ) ) ) {
//					mName = method.getSimpleName().toString();
//					if ( mName.toString().equals( methodName ) ) {
//						if ( method.getModifiers().contains( Modifier.PUBLIC ) ) {
//							found = Utils.sameParameters(e.getParameters(),
//																					 method.getParameters(),
//																					 e);
//							if ( found ) {
//								break;
//							}
//						}
//					}
//				}
//				if ( !found ) {
//					processingEnv.getMessager().printMessage(
//							Kind.ERROR,
//							String.format( Messages.MISSING_METHOD, type.getSimpleName(),
//									Utils.getMethodName(methodName,
//																			e.getParameters()), e.getReturnType() ), e );
//				}
//			}
//		}
	}

}
