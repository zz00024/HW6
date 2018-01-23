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
      System.out.println("(C)reate mad-lib, (V)iew mad-lib, (Q)uit?");
      String mode = console.next();
      // determine which mode to execute
      while(!mode.equalsIgnoreCase("q")) {
         // if chose createFile
         if(mode.equalsIgnoreCase("c")) {
            create(console);
            System.out.println("(C)reate mad-lib, (V)iew mad-lib, (Q)uit?");
            mode = console.next();   
         }
         // if chose view
         else if(mode.equalsIgnoreCase("v")) {
            System.out.println("Input file name");
            String fileName = console.next();
            File file = new File(fileName);
            // check if the file exist
            while(!file.exists()) {
               System.out.println("File not found. Try again:");
               fileName = console.next();
            }
            /*
            while(!fileFound(fileName)) {
               System.out.println("File not found. Try again:");
               fileName = console.next();   
            }
            */
            viewFile(fileName);
            System.out.println("(C)reate mad-lib, (V)iew mad-lib, (Q)uit?");
            mode = console.next();
         }
      }
   }
   
   private static void create(Scanner console) {
      // ask for file name
      System.out.println("Input file name");
      String fileName = console.next();
      File newFile = new File(fileName);
      
      //Scanner file = new Scanner(newFile);
      while(!fileFound(fileName)) {
         System.out.println("File not found. Try again:");
         fileName = console.next();
      }        
      
      // create a output file 
      System.out.println("Output file name: ");
      String outPut = console.next();
      try {
         File outPutFile = new File(outPut);
         outPutFile.createNewFile();
      }
      catch(IOException e){
         e.printStackTrace();
      }
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
   
   // check if file exists or not
   private static boolean fileFound(String fileName) {
      try
      {
         BufferedReader in = new BufferedReader(new FileReader(fileName));
      }
      catch(FileNotFoundException e) {
         return false;
      }
      return true;
   }
}