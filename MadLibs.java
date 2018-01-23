// package for import file, scanner
import java.io.*;
import java.util.*;
import java.io.File;
import static java.lang.System.*;

public class MadLibs {
   
   public static void main(String args[]) throws IOException{
      Scanner console = new Scanner(System.in);
      // opening
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");      
      // choose what mode
      System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
      String mode = console.nextLine();
      // determine which mode to execute
      while(!mode.equalsIgnoreCase("q")) {
         // if chose createFile
         if(mode.equalsIgnoreCase("c")) {
            create(console);
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            mode = console.nextLine();   
         }
         // if chose view
         else if(mode.equalsIgnoreCase("v")) {
            System.out.print("Input file name: ");
            String fileName = console.nextLine();
            File file = new File(fileName);
            // check if the file exist
            while(!file.exists()) {
               System.out.println("File not found. Try again: ");
               fileName = console.nextLine();
               file = new File(fileName);
            }
            // open to read the file
            viewFile(fileName);
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            mode = console.nextLine();
         }
      }
   }
   
   private static void create(Scanner console) throws FileNotFoundException{
      // ask for file name
      System.out.print("Input file name: ");
      String fileName = console.nextLine();
      File file = new File(fileName);
      
      //Scanner file = new Scanner(newFile);
      while(!file.exists()) {
         System.out.print("File not found. Try again: ");
         fileName = console.nextLine();
         file = new File(fileName);
      }        
      
      // create a output file 
      System.out.print("Output file name: ");
      String outPut = console.nextLine();
      try {
         File outPutFile = new File(outPut);
         outPutFile.createNewFile();
         // read input file line by line and and replace each <word> with user choice and write it in output file
         Scanner input = new Scanner(new File(fileName));
         // writing in output file
         PrintStream output = new PrintStream(new File(outPut));
         while(input.hasNextLine()) {
            String line = input.nextLine();
            // check and replace what user prompt into output file content
            line = replace(console, line);            
            // copy input file content into output
            output.println(line);
         }
      }
      catch(IOException e){
         e.printStackTrace();
      }
   }
   
   // method for replacing word in each line of the output file
   private static String replace(Scanner console, String line) {
      // System.out.println(line);
      Scanner tokens = new Scanner(line);
      String finalLine = "";
      while(tokens.hasNext()) {
         String word = tokens.next();
         int wordLength = word.length();
         // check if the token is placeholder and replace it with what user want
         if(word.substring(0, 1).equals("<")) {
            String replaceWord = word.substring(1, wordLength-1);
            // check if we use a or an
            String aAn = "a";
            String printWord = word.substring(1, word.length()-1).toLowerCase();
            if(printWord.substring(0, 1).equals("a") || printWord.substring(0, 1).equals("e") || printWord.substring(0, 1).equals("i") 
            || printWord.substring(0, 1).equals("o") || printWord.substring(0, 1).equals("u")) {
               aAn = "an";
            }
            // ask user to prompt their choice word
            System.out.print("Please type " + aAn + " " + printWord + " : ");
            String userType = console.nextLine();
            // replace the original word with user choice
            word = userType;
         }
         finalLine += word + " ";
      }
      return finalLine;
   }
   
   // method for viewing file
   private static void viewFile(String fileName) throws IOException {
            FileReader in = new FileReader(fileName);
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                  System.out.println(line);
            }
            in.close();                    
   }      
}