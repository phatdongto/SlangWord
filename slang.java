/**
 * slang
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList; 
import java.io.PrintWriter;



import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// import java.lang.String;

public class slang {

    public static Scanner keyboard = new Scanner(System.in);
    public static HashMap<String, String> slangMap = new HashMap<String, String>();
    public static ArrayList<String> history = new ArrayList<String>();
    // String "testdata.txt" = "testdata.txt";

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void PauseTest(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    
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
    
    public static void Append_to_file(String s, String url) {
        try {
            File file = new File(url);
            FileWriter fr = new FileWriter(file, true);

            try {
                fr.write(s);
            } catch (Exception e) {
                System.out.println("cant write file");
            }finally{
                fr.close();
            }
        }catch(Exception e){
            System.out.println("cant open file");
        }
    }

    public static void Truncate_to_file(String url) {
        File file = new File(url);
        try (PrintWriter pw = new PrintWriter(file)) {
            try {
                for(String a : slangMap.keySet()){
                    pw.println(a + "`" + slangMap.get(a));
                }
            } catch (Exception e) {
                System.out.println("Can't truncate to file");
            } finally{
                pw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ShowHistory() {
        try{
            File file = new File("history.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            String tmp = "error";
            int inc = 0;
            
            System.out.println("!!!History!!!");

            try {
                tmp = reader.readLine();
                while (tmp != null) {
                    System.out.println( ++inc + ". " + tmp);
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
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public static void Add_slang_word(String slang_key, String def_value) {
       String choose;
        if(slangMap.containsKey(slang_key)){
            System.out.println("Slang word duplicate !!!");
            System.out.println("Choose 1 to overwrite");
            System.out.println("Choose 2 to duplicate to new slang word");
            System.out.print("Your choose: ");
            choose = keyboard.nextLine();
            if(choose.equals("1")){
                slangMap.put(slang_key, def_value);
                Truncate_to_file("testdata.txt");
                System.out.println("Overwrite successfully");
            }else{
                slangMap.put(slang_key, slangMap.get(slang_key) + "| " + def_value );
                Truncate_to_file("testdata.txt");
                System.out.println("Duplicate successfully");
            }

        }else{
            slangMap.put(slang_key, def_value);
            Append_to_file(slang_key + "`" + def_value + "\n", "testdata.txt");
            System.out.println("Add new slangword successfully");
        }
    }
    
    public static void Edit_slang_word(String edit_key, String edit_value) {
        if(slangMap.containsKey(edit_key)){
            slangMap.put(edit_key, edit_value);
            Truncate_to_file("testdata.txt");
            System.out.println("Edit completed");
        }else{
            System.out.println("Slang word not found !!!");
        }
    }
    
    public static void GUI(){
        String out = "";
        while(!"e".equals(out)){
            // System.out.print("\033[H\033[2J");
            // System.out.flush();

            System.out.println("1. Slang word");
            System.out.println("2. Find with definition");
            System.out.println("3. Show history");
            System.out.println("4. Add new slang word");
            System.out.println("5. Edit slang word");
            System.out.println("6. Delete slang word");
            System.out.println("7. Back up origin");
            System.out.println("8. Random slang word");
            System.out.println("9. Game of slang word");
            // System.out.println("");
            System.out.print("Enter your choose: ");
            int choose = keyboard.nextInt();
            String c = keyboard.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("Your slang word: ");
                    String key = keyboard.nextLine();
                    String value = Find_with_slang(key);
                    if(!(value == null)){
                        Append_to_file(key + "   :   " + value + "\n", "history.txt");
                        System.out.println(key + "'s definition is: " + value);
                    }
                    else{
                        System.out.println("No definition founded !!");
                    }
                    break;
                case 2:
                    System.out.print("Your definition: ");
                    String def = keyboard.nextLine();
                    ArrayList<String> keySet = Find_with_value(def);
                    System.out.println("Slang word of " + def + " is: ");
                    for(String a : keySet){
                        Append_to_file(a + "   :   " + slangMap.get(a) + "\n", "history.txt");
                        System.out.println(a + "   :   " + slangMap.get(a));
                    }
                    break;
                case 3:
                    ShowHistory();
                    break;
                case 4:
                    System.out.print("Enter slang word: ");
                    String slang_key = keyboard.nextLine();
                    System.out.print("Enter definition: ");
                    String def_value = keyboard.nextLine();
                    Add_slang_word(slang_key, def_value);
                    break;
                case 5:
                    System.out.print("Enter slang word: ");
                    String edit_key = keyboard.nextLine();
                    System.out.print("Enter definition: ");
                    String edit_value = keyboard.nextLine();
                    Edit_slang_word(edit_key, edit_value);
                    break;
                case 6:
                    System.out.print("Slang word you want to delete: ");
                    String delete_slang = keyboard.nextLine();
                    Delete_slang_word(delete_slang);
                    break;
                case 7:
                    Back_up_origin();
                    break;
                case 8:
                    System.out.println("Your slang word today is: ");
                    String random_key = Random_slang_word();
                    System.out.println(random_key + "   :   " + slangMap.get(random_key));
                    break;
                case 9:
                    Game_of_slang();
                    break;
            }
            PauseTest();
        }
    }

    public static void Game_of_slang() {
        ArrayList<String> ansList = new ArrayList<String>();
        String tmp = Random_slang_word();
        for(int i = 0; i < 4; i++){
            while(ansList.contains(tmp))
                tmp = Random_slang_word();
            ansList.add(tmp);
        }
        int true_key_index = getRandomNumber(0, 3);
        System.out.println("The mysterious slang word is: " + ansList.get(true_key_index));
        System.out.println("Answer: ");
        System.out.println("1. " + slangMap.get(ansList.get(0)));
        System.out.println("2. " + slangMap.get(ansList.get(1)));
        System.out.println("3. " + slangMap.get(ansList.get(2)));
        System.out.println("4. " + slangMap.get(ansList.get(3)));
        System.out.print("\nYour choose is: "); 
        int choose = keyboard.nextInt(); 
        //String c = keyboard.nextLine();
        if (choose - 1 == true_key_index){
            System.out.println("Congrat!!! correct answer !!!");
        }else{
            System.out.println("Oops!!! Wrong answer");
        }
    }

    public static String Random_slang_word() { // return index
        Random generator = new Random();
        Object[] values = slangMap.values().toArray();
        Object randomValue = values[generator.nextInt(values.length)];
        String key = Find_with_value(String.valueOf(randomValue)).get(0);
        return key;
    }

    public static void Back_up_origin() {
        System.out.println("\n1. Set this slang word list as origin.");
        System.out.println("2. Reset to oringin.");
        String choose = keyboard.nextLine();
        if(choose.equals("1")){
            Truncate_to_file("origin.txt");
            System.out.println("Set origin successfully");
        }else{
            slangMap.clear();
            slangMap = GetSlangMap("origin.txt");
            Truncate_to_file("testdata.txt");
            System.out.println("Set origin successfully");
        }
    }

    public static void Delete_slang_word(String delete_slang) {
        if(slangMap.containsKey(delete_slang)){
            System.out.println("Are you sure? (y/n)");
            String choose = keyboard.nextLine();
            if (choose.equals("y")){
                slangMap.remove(delete_slang);
                Truncate_to_file("testdata.txt");
                System.out.println("Delete successfully");
            }
        }
        else{
            System.out.println("Slang word not found !!!");
        }
    }

    public static void main(String[] args) {
        slangMap = GetSlangMap("testdata.txt");
        GUI();
    }
}

