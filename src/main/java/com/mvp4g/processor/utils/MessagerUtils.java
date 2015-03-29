package com.mvp4g.processor.utils;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * Created by hoss on 07.02.15.
 */
public class MessagerUtils {

  private Messager messager;

//------------------------------------------------------------------------------

  public MessagerUtils(Messager messager) {
    this.messager = messager;
  }

//------------------------------------------------------------------------------

  /**
   * Prints an error message
   *
   * @param e    The element which has caused the error. Can be null
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void error(Element e,
                    String msg,
                    Object... args) {
    printMessage(e,
                 Diagnostic.Kind.ERROR,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param e    The element which has caused the error. Can be null
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void mandatoryWarning(Element e,
                               String msg,
                               Object... args) {
    printMessage(e,
                 Diagnostic.Kind.MANDATORY_WARNING,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param e    The element which has caused the error. Can be null
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void note(Element e,
                   String msg,
                   Object... args) {
    printMessage(e,
                 Diagnostic.Kind.NOTE,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param e    The element which has caused the error. Can be null
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void other(Element e,
                    String msg,
                    Object... args) {
    printMessage(e,
                 Diagnostic.Kind.OTHER,
                 msg,
                 args);
  }

  /**
   * Prints an warning message
   *
   * @param e    The element which has caused the error. Can be null
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void warning(Element e,
                      String msg,
                      Object... args) {
    printMessage(e,
                 Diagnostic.Kind.WARNING,
                 msg,
                 args);
  }

//------------------------------------------------------------------------------

  /**
   * Prints an error message
   *
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void error(String msg,
                    Object... args) {
    printMessage(Diagnostic.Kind.ERROR,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void mandatoryWarning(String msg,
                               Object... args) {
    printMessage(Diagnostic.Kind.MANDATORY_WARNING,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void note(String msg,
                   Object... args) {
    printMessage(Diagnostic.Kind.NOTE,
                 msg,
                 args);
  }

  /**
   * Prints an error message
   *
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void other(String msg,
                    Object... args) {
    printMessage(Diagnostic.Kind.OTHER,
                 msg,
                 args);
  }

  /**
   * Prints an warning message
   *
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  public void warning(String msg,
                      Object... args) {
    printMessage(Diagnostic.Kind.WARNING,
                 msg,
                 args);
  }

//------------------------------------------------------------------------------

  /**
   * Prints an message
   *
   * @param e    The element which has caused the error. Can be null
   * @param kind Kinds of diagnostics, for example, error or warning.
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  private void printMessage(Element e,
                            Diagnostic.Kind kind,
                            String msg,
                            Object... args) {
    messager.printMessage(kind,
                          String.format(msg,
                                        args),
                          e);
  }

  /**
   * Prints an message
   *
   * @param kind Kinds of diagnostics, for example, error or warning.
   * @param msg  The error message
   * @param args if the error messge cotains %s, %d etc. placeholders this arguments will be used
   *             to
   *             replace them
   */
  private void printMessage(Diagnostic.Kind kind,
                            String msg,
                            Object... args) {
    messager.printMessage(kind,
                          String.format(msg,
                                        args));
  }
}
