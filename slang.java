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
import java.util.HashMap;

// import java.lang.String;

public class slang {

    public static HashMap<String, String> GetSlangMap(String url) {
        HashMap<String, String> slangMap = new HashMap<String, String>();
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

    public static void main(String[] args) {
        HashMap<String, String> slangMap = GetSlangMap("slang.txt");
        System.out.println("The collection is: " + slangMap.values());
    }
}