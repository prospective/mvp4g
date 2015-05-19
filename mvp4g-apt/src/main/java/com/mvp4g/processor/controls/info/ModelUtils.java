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
import java.util.Arrays;

/**
 * Created by hoss on 18.05.15.
 */
public class ModelUtils {

  private static final String FILE_DELIMITER = "$";
  private static final String FILE_TYPE = "ser";
  private static final String INFO_TYPE_DELIMITER = "_";

//------------------------------------------------------------------------------

  public String createFileName(String packageName,
                        String className,
                        InfoType type) {
    StringBuffer name = new StringBuffer();
    if (packageName != null) {
      name.append(packageName.replaceAll("\\\\",
                                         ModelUtils.FILE_DELIMITER));
      name.append(ModelUtils.FILE_DELIMITER);
    }
    name.append(className);
    name.append(ModelUtils.INFO_TYPE_DELIMITER);
    name.append(type.toString().toLowerCase());
    name.append(".");
    name.append(ModelUtils.FILE_TYPE);
    return name.toString();
  }


  public static String createKeyValue(String key,
                                      String value) {
    StringBuilder sb = new StringBuilder();
    sb.append(key)
      .append("=")
      .append(value);
    return sb.toString();
  }


  public static String toByteArray(Object object) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024)) {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      return new String(baos.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  public static Object toByteArray(Object object) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1024)) {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      return new String(baos.toByteArray());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

//------------------------------------------------------------------------------

  public enum InfoType {
    PRESENTER
  }
}

//public class ExerciseSerializableNew {
//
//  public static void main(String... aArguments) {
//    //create a Serializable List
//    List<String> quarks = Arrays.asList(
//                                         "up",
//                                         "down",
//                                         "strange",
//                                         "charm",
//                                         "top",
//                                         "bottom"
//    );
//
//    //serialize the List
//    try (
//          OutputStream file = new FileOutputStream("quarks.ser");
//          OutputStream buffer = new BufferedOutputStream(file);
//          ObjectOutput output = new ObjectOutputStream(buffer);
//    ){
//      output.writeObject(quarks);
//    }
//    catch(IOException ex){
//      fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
//    }
//
//    //deserialize the quarks.ser file
//    try(
//         InputStream file = new FileInputStream("quarks.ser");
//         InputStream buffer = new BufferedInputStream(file);
//         ObjectInput input = new ObjectInputStream (buffer);
//    ){
//      //deserialize the List
//      List<String> recoveredQuarks = (List<String>)input.readObject();
//      //display its data
//      for(String quark: recoveredQuarks){
//        System.out.println("Recovered Quark: " + quark);
//      }
//    }
//    catch(ClassNotFoundException ex){
//      fLogger.log(Level.SEVERE, "Cannot perform input. Class not found.", ex);
//    }
//    catch(IOException ex){
//      fLogger.log(Level.SEVERE, "Cannot perform input.", ex);
//    }
//  }
//
//  // PRIVATE
//
//  private static final Logger fLogger =
//    Logger.getLogger(ExerciseSerializableNew.class.getPackage().getName())
//    ;
//}