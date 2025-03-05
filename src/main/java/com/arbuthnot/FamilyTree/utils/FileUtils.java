package com.arbuthnot.FamilyTree.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtils {

  public ArrayList<String> readFile(String fileName) throws Exception {

    File file = new File(fileName);
    FileInputStream fileInputStream;

    {
      try {
        fileInputStream = new FileInputStream(file);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }

    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    ArrayList<String> fileLine = new ArrayList<String>();
    String nextLine = new String();

    while ((nextLine = bufferedReader.readLine()) != null) {
      // process the line
      fileLine.add(nextLine);
      fileLine.add("\n");
    }

    bufferedReader.close();

    return fileLine;

  }

  public String getProjectRoot() {
    String envRootDir = System.getProperty("user.dir");
    Path rootDir = Paths.get(".").normalize().toAbsolutePath();
    if (rootDir.startsWith(envRootDir)) {
      return rootDir.toString();
    } else {
      throw new RuntimeException("Root dir not found in user directory.");
    }
  }

}
