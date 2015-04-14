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

import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.history.ClearHistory;
import com.mvp4g.processor.controls.info.ApplicationInfo;
import com.mvp4g.processor.controls.info.EventInfo;
import com.mvp4g.processor.exceptions.ConfigurationException;
import com.mvp4g.processor.utils.MessagerUtils;
import com.mvp4g.processor.utils.Messages;
import com.mvp4g.processor.utils.Mvp4gUtils;
import com.mvp4g.processor.utils.Utils;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import java.util.List;
import java.util.Map;

public class EventControl {

  /* application info */
  private ApplicationInfo applicationInfo;

  /* processing envirement */
  private ProcessingEnvironment processingEnv;
  /* messager utils */
  private MessagerUtils         messagerUtils;

//------------------------------------------------------------------------------

  public EventControl(ApplicationInfo applicationInfo,
                      MessagerUtils messagerUtils,
                      ProcessingEnvironment processingEnv) {
    this.applicationInfo = applicationInfo;
    this.messagerUtils = messagerUtils;
    this.processingEnv = processingEnv;
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
   * @param element the annotated presenter to validate
   * @return true, if it is valid class
   */
  public boolean process(TypeElement element)
    throws ConfigurationException {
    // fill info
    createInfo(element);
    // validate
    return isValid(element);
  }

//------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  private void createInfo(TypeElement element)
    throws ConfigurationException {
    for (ExecutableElement executable : ElementFilter.methodsIn(processingEnv.getElementUtils()
                                                                             .getAllMembers(element))) {
      for (AnnotationMirror mirror : executable.getAnnotationMirrors()) {
        if (Mvp4gUtils.EVENT.equals(mirror.getAnnotationType()
                                          .toString())) {
          EventInfo info = new EventInfo(executable.getSimpleName()
                                                   .toString());
          info.setEvent(executable);

          String keyName;

          for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues()
                                                                                               .entrySet()) {
            keyName = entry.getKey()
                           .getSimpleName()
                           .toString();
            if (Mvp4gUtils.ATTRIBUTE_HANDLERS.equals(keyName)) {
              info.setHandlers(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                    .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_HANDLER_NAMES.equals(keyName)) {
              info.setHandlerNames(Utils.convertNames((List<? extends AnnotationValue>) entry.getValue()
                                                                                             .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_BIND.equals(keyName)) {
              info.setBind(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_BIND_NAMES.equals(keyName)) {
              info.setBindNames(Utils.convertNames((List<? extends AnnotationValue>) entry.getValue()
                                                                                          .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_FORWARD_TO_MODULES.equals(keyName)) {
              info.setForwardToModules(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                            .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_FORWARD_TO_PARENT.equals(keyName)) {
              info.setForwardToParent((Boolean) entry.getValue()
                                                     .getValue());
            } else if (Mvp4gUtils.ATTRIBUTE_HISTORY_CONVERTER.equals(keyName)) {
              TypeElement historyElement = Utils.convert(entry.getValue());
              if (!historyElement.getQualifiedName()
                                 .toString()
                                 .equals(InitHistory.class.getCanonicalName()) &&
                  !historyElement.getQualifiedName()
                                 .toString()
                                 .equals(ClearHistory.class.getCanonicalName())) {
                info.setHistoryConverter(historyElement);
              }
            } else if (Mvp4gUtils.ATTRIBUTE_ACTIVATE.equals(keyName)) {
              info.setActivate(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                    .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_ACTIVATE_NAMES.equals(keyName)) {
              info.setActivateNames(Utils.convertNames((List<? extends AnnotationValue>) entry.getValue()
                                                                                              .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_DEACTIVATE.equals(keyName)) {
              info.setDeactivate(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                      .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_DEACTIVATE_NAMES.equals(keyName)) {
              info.setDeactivateNames(Utils.convertNames((List<? extends AnnotationValue>) entry.getValue()
                                                                                                .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_NAVIGATION_EVENT.equals(keyName)) {
              info.setNavigationEvent((Boolean) entry.getValue()
                                                     .getValue());
            } else if (Mvp4gUtils.ATTRIBUTE_PASSIVE.equals(keyName)) {
              info.setPassive((Boolean) entry.getValue()
                                             .getValue());
            } else if (Mvp4gUtils.ATTRIBUTE_BROADCAST_TO.equals(keyName)) {
              info.setBroadcastTo(Utils.convert(entry.getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_GENERATE.equals(keyName)) {
              info.setGenerate(Utils.convert((List<? extends AnnotationValue>) entry.getValue()
                                                                                    .getValue()));
            } else if (Mvp4gUtils.ATTRIBUTE_GENERATE_NAMES.equals(keyName)) {
              info.setGenerateNames(Utils.convertNames((List<? extends AnnotationValue>) entry.getValue()
                                                                                              .getValue()));
            }
          }

          // No module info found. this can happen, if a module has more than eventbus defined.
          if (applicationInfo.getModuleInfoForEventBus(element.toString()) == null) {
            messagerUtils.warning(element,
                                  Messages.MUTLIPLE_EVENT_BUS_DEFINITIONS,
                                  element.toString());
            throw new ConfigurationException("No module found for eventBus >>" + element.toString() + "<<! Processing terminated!");
          }

          applicationInfo.getModuleInfoForEventBus(element.toString())
                         .getEventBusInfo()
                         .addEvent(info);
        }
      }
    }
  }

//------------------------------------------------------------------------------

  private boolean isValid(TypeElement element) {
    return true;
  }
}
