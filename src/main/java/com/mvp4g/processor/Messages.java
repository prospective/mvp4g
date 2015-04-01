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

package com.mvp4g.processor;


public class Messages {

	public static final String CLASS_SHOULD_EXTEND_BASE_PRESENTER = "mvp4g: a presenter annotated with @%s must extend BasePresenter or LazyPresenter";

	public static final String CLASS_SHOULD_NOT_BE_ABSTRACT = "mvp4g: a class annotated with @%s can not be abstract";

	public static final String NOT_A_CLASS = "mvp4g: Only classes can be annotated with @%s";

	public static final String INVALID_VIEW = "mvp4g: Invalid View: %s can't be injected to %s. Can not convert %s to %s";

//	public static final String MISSING_METHOD = "%s is missing public method %s: %s";
//
//	public static final String MISSING_ANNOTATION = "%s should be annotated with @%s";
//
//	public static final String MISSING_ANNOTATION_OR = MISSING_ANNOTATION + " or @%s";
//
//	public static final String INVALID_EVENT_BUS = "Invalid Event bus: %s can't be injected to %s. Can not convert %s to %s";


//	public static final String MODULE_ASSOCIATED_TWICE = "Module %s: a module can't be associated to 2 event bus, %s and %s.";
//
//	public static final String MODULE_NOT_A_CHILD = "EventBus %s: module %s is not a child module.";
//
//	public static final String MODULE_NO_EVENT_BUS = "Module %s: this module is not associated to any event bus.";
//
//	public static final String MODULE_NO_PARENT = "EventBus %s: this event bus has no parent.";
//
//	public static final String MODULE_TWO_PARENT_EVENT_BUS = "Module %s: a module can't have 2 parent event bus, %s and %s.";

}