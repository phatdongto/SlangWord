/**
 * slang
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList; 
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

// import sun.swing.PrintColorUIResource;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// import java.lang.String;

public class slang {

    public static Scanner keyboard = new Scanner(System.in);
    public static HashMap<String, String> slangMap = new HashMap<String, String>();
    // public static HashMap<String, String> valueMap = new HashMap<String, String>();

    public static HashMap<String, String> GetSlangMap(String url) {
        
        try{
            File file = new File(url);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            String tmp = "error";
            String[] strSplit;
            
            try {
                tmp = reader.readLine();
                while (tmp != null) {
                    strSplit = tmp.split("`");
                    slangMap.put(strSplit[0], strSplit[1]);
                    tmp = reader.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(slang.class.getName())
                                .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(slang.class.getName())
                                .log(Level.SEVERE, null, ex);
            } finally {
                try {
                    reader.close();
                    // file.close();
                } catch (IOException ex) {
                    Logger.getLogger(slang.class.getName())
                                    .log(Level.SEVERE, null, ex);
                }
            }

            return slangMap;
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
        return slangMap;
    }

    public static String Find_with_slang(String key) {
        return slangMap.get(key);
    }

    public static ArrayList<String> Find_with_value(String value) {
        ArrayList<String> keySet = new ArrayList<String>();
        for(String a : slangMap.keySet())
            if(slangMap.get(a).contains(value)){
                keySet.add(a);
            }
        return keySet;
    }
    
    public static void GUI(){
        String out = "";
        while(!"e".equals(out)){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("1. Slang word");
            System.out.println("2. Find with definition");
            // System.out.println("");
            // System.out.println("");
            // System.out.println("");
            // System.out.println("");
            System.out.print("Enter your choose: ");
            int choose = keyboard.nextInt();
            String c = keyboard.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("Your slang word: ");
                    String key = keyboard.nextLine();
                    String value = Find_with_slang(key);
                    System.out.println(key + "'s definition is: " + value);
                    break;
                case 2:
                    System.out.print("Your definition: ");
                    String def = keyboard.nextLine();
                    ArrayList<String> keySet = Find_with_value(def);
                    System.out.println("Slang word of " + def + " is: ");
                    for(String a : keySet){
                        System.out.println(a + " : " + slangMap.get(a));
                    }
                    break;
                case 3:
                System.out.println("Wednesday");
                break;
                case 4:
                System.out.println("Thursday");
                break;
                case 5:
                System.out.println("Friday");
                break;
                case 6:
                System.out.println("Saturday");
                break;
                case 7:
                System.out.println("Sunday");
                break;
            }
            System.out.println("\n\nPress 'Enter' to continue or type 'e' to exit");
            out = keyboard.next();
        }
    }

    public static void main(String[] args) {
        slangMap = GetSlangMap("slang.txt");
        GUI();
        // System.out.println(Find_with_value("Penis"));
    }
}

