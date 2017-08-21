package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args){
    String[] langs = {"Java", "PHP", "C#", "Python"};

    List<String> languages = Arrays.asList("Java", "PHP", "C#", "Python");

    for (String l : languages){
      System.out.println("Chcę się nauczyć " + l);
    }



  }
}
