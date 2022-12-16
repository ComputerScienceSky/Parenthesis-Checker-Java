package ParenChecker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class ParenCheckLab {

  public static void main(String[] args) {
    String[] files = {"invalid1.in","invalid2.in","invalid3.in",
            "invalid4.in","invalid5.in", "valid1.in","valid2.in"};

    for (String filename: files) {
      System.out.println("\nTesting file: " + filename);
      try {
          checkParens(filename);
        }
      catch (FileNotFoundException ex) {
        System.err.println("File not found");
      }
      catch (Exception ex) {
        System.out.println(ex);
      }
    }
  }


	
  public static void checkParens(String fileName) throws FileNotFoundException {
    Scanner in = new Scanner(new FileReader(fileName));
    ArrayList<Character> openSymbols = new ArrayList<>(Arrays.asList('[', '{', '('));
    ArrayList<Character> closeSymbols = new ArrayList<>(Arrays.asList(']', '}', ')'));
    Stack<Character> parenStack = new Stack<>();
    char topChar;
    int lineCounter = 1;

    //
    // Declare any other variables you need here.
    //

    // Read through the file one line at a time.
    while (in.hasNextLine()) {
      String line = in.nextLine();

      for(int i = 0; i < line.length(); i++){
        if(openSymbols.contains(line.charAt(i))){
          switch(line.charAt(i)){
            case '(':
              parenStack.push(')');
              break;
            case '[':
              parenStack.push(']');
              break;
            case '{':
              parenStack.push('}');
              break;
          }
        } else if (closeSymbols.contains(line.charAt(i))){
          if(parenStack.size() == 0){
            throw new InputMismatchException("On line " + lineCounter + " at pos " + (i + 1) + " found " + line.charAt(i) + ", there is no matching symbol.");
          }
          topChar = parenStack.pop();
          if(!(topChar == line.charAt(i))){
            throw new InputMismatchException("On line " + lineCounter + " at pos " + (i + 1) + " found " + line.charAt(i) + " expected " + topChar);
          } 
        }
      }
      
      lineCounter++;
    }
    if(!(parenStack.isEmpty())){
      throw new InputMismatchException("At end of input -- expecting " + parenStack.pop());
    }

    System.out.println("The parenthesis in the file are valid!");
  }
}