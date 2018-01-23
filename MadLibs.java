// package for import file, scanner
import java.io.*;
import java.util.*;
import java.io.File;
import static java.lang.System.*;

/* Bug: 
  1. The spec says to replace hyphens in placeholders with spaces. But I am still seeing things like
     "plural-noun" instead of "plural noun"
  
  Things to improve:
  1. Code needs to be cleaned up
  2. At least one place of redundancy
  3. Know when to use "substring" vs. "charAt"
  4. For scanners please remember to close them after you are done with them
  5. Remove unused code
*/

public class MadLibs {
   
   public static void main(String args[]) throws IOException{
      Scanner console = new Scanner(System.in);
      // opening
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();      
      // choose what mode
      // Rita: these two lines are repeated throughout this file. Please reduce this redundancy
      System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
      String mode = console.nextLine();
      // determine which mode to execute
      while(!mode.equalsIgnoreCase("q")) {
         // if chose createFile
         if(mode.equalsIgnoreCase("c")) {
            create(console);
            System.out.println("Your mad-lib has been created!");
            System.out.println();
            // Rita: please try to factor out these two lines.
            System.out.println("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
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
            // Rita: redundancy here
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            mode = console.nextLine();
         }
         else {
        	 // Rita: redundancy here
            System.out.println("(C)recate mad-lib, (V)iew mad-lib, (Q)uit? ");
            mode = console.nextLine();
         }
      }
   }
   
   private static void create(Scanner console) throws FileNotFoundException{
      // ask for file name
      System.out.print("Input file name: ");
      String fileName = console.nextLine();
      File file = new File(fileName);
      
      // Rita: remove unused code
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
         // Rita: I believe PrintStream will create a file for you if it cannot
         // find an existing file at the given file name path
         outPutFile.createNewFile();
         // Rita: please close scanners when you are done with them
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
	  // Rita: remove unused line
      // System.out.println(line);
      Scanner tokens = new Scanner(line);
      String finalLine = "";
      while(tokens.hasNext()) {
         String word = tokens.next();
         int wordLength = word.length();
         // check if the token is placeholder and replace it with what user want
         // Rita: if you only one to check on letter please use "charAt()" method
         if(word.substring(0, 1).equals("<")) {
        	// Rita: this variable is never used
            String replaceWord = word.substring(1, wordLength-1);
            // check if we use a or an
            String aAn = "a";
            String printWord = word.substring(1, word.length()-1).toLowerCase();
            // Rita: same here. "charAt()"
            // Is there a way you can improve this so just in case in the future there are more vowels, you can make minimal changes?
            // Hint: array and for loop
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