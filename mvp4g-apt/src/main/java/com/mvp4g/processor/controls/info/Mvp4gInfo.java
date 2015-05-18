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

package com.mvp4g.processor.controls.info;

import java.io.*;

public class Mvp4gInfo
  implements Serializable {

  private String classMame;
  private String packageClassName;
  private String simpleClassName;

//------------------------------------------------------------------------------

  public Mvp4gInfo() {
  }

//------------------------------------------------------------------------------

  public Mvp4gInfo(String classMame,
                   String packageClassName,
                   String simpleClassName) {
    this.classMame = classMame;
    this.packageClassName = packageClassName;
    this.simpleClassName = simpleClassName;
  }

//------------------------------------------------------------------------------

  public String getClassMame() {
    return classMame;
  }

  public void setClassMame(String classMame) {
    this.classMame = classMame;
  }

  public String getPackageClassName() {
    return packageClassName;
  }

  public void setPackageClassName(String packageClassName) {
    this.packageClassName = packageClassName;
  }

  public String getSimpleClassName() {
    return simpleClassName;
  }

  public void setSimpleClassName(String simpleClassName) {
    this.simpleClassName = simpleClassName;
  }

//------------------------------------------------------------------------------

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(ModelUtils.createKeyValue("className",
                                        classMame));
    sb.append(", ");
    sb.append(ModelUtils.createKeyValue("packageClassName",
                                        packageClassName));
    sb.append(", ");
    sb.append(ModelUtils.createKeyValue("simpleClassName",
                                        simpleClassName));
    return sb.toString();
  }
}


//public class ReadWriteObject implements Serializable {
//
//  /** Serial Version UID */
//  private static final long serialVersionUID = 8008750006656191706L;
//
//  private int age;
//  private String firstName;
//  private String lastName;
//
//  /**
//   * @param age
//   * @param firstName
//   * @param lastName
//   */
//  public ReadWriteObject(int age, String firstName, String lastName) {
//    super();
//    this.age = age;
//    this.firstName = firstName;
//    this.lastName = lastName;
//  }
//
//  /*
//   * (non-Javadoc)
//   *
//   * @see java.lang.Object#toString()
//   */
//  @Override
//  public String toString() {
//    return "ReadWriteObject [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + "]";
//  }
//
//}
//
//
//public class ReadWriteTest {
//
//  public static void main(String[] args) throws IOException, ClassNotFoundException {
//    // Create Object to write and then to read
//    // This object must be Serializable, and all its subobjects as well
//    ReadWriteObject inputObject = new ReadWriteObject(18, "John", "Doe");
//
//    // Read Write Object test
//
//    // Write Object into a Byte Array
//    ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
//    ObjectOutputStream oos = new ObjectOutputStream(baos);
//    oos.writeObject(inputObject);
//    byte[] rawData = baos.toByteArray();
//    String rawString = new String(rawData);
//    System.out.println(rawString);
//
//    // Read Object from the Byte Array
//    byte[] byteArrayFromString = rawString.getBytes();
//    ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayFromString);
//    ObjectInputStream ois = new ObjectInputStream(bais);
//    Object outputObject = ois.readObject();
//    System.out.println(outputObject);
//  }
//}