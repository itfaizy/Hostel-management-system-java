/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author seren
 */
public class File_Function {
    public static void ModifyFile(String content, String filename, boolean append) {
        File file = new File(filename);
        try {
            FileWriter FW = new FileWriter(file, append);
            BufferedWriter BW = new BufferedWriter(FW);
            BW.write(content);
            BW.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String[]> readFile(String filepath) {

        File file = new File(filepath);

        ArrayList<String[]> tempArray = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            //get line from txt file
            Object[] lines = br.lines().toArray();
            br.close();

            for (Object line1 : lines) {
                String line = line1.toString().trim();
                String[] dataRow = line.split(";");
                tempArray.add(dataRow);
            }
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempArray;
    }
    
    public static void updateSingleLineByID(String filepath, List<String> newData) {
        ArrayList<String[]> tempArray = readFile(filepath);
        String content = "";
        int i = 0;
        boolean updated = false;
        
        for (String[] oldData : tempArray) {
            if (!updated && oldData[0].equals(newData.get(0))) {
                content += String.join(";", newData) + "\n";
                updated = true;
                continue;
            }
            content += String.join(";", oldData) + "\n";
        }
        if (updated) {
            ModifyFile(content, filepath, false);
        } 
        else {
            System.out.printf("ID %s is not found, %s cannot be updated", newData.get(0), filepath);
        }
    }

    public static String[] getSpecificData(String filepath, String username, int columnNum) {

        File file = new File(filepath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            //get line from txt file
            Object[] lines = br.lines().toArray();
            br.close();

            for (Object line1 : lines) {
                String line = line1.toString().trim();
                String[] dataRow = line.split(";");
                if (dataRow[columnNum].equals(username)) {
                    return dataRow;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Long getID(String filepath) {

        File file = new File(filepath);
        String lastline = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    lastline = line;
                }
            }
            lastline = lastline.split(";")[0];
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lastline == null) {
            return 0L;
        }
        return Long.parseLong(lastline) + 1;
    }

    public static ArrayList<String[]> getMultipleSpecificData(String filepath, String username, int columnNum) {
        ArrayList<String[]> data = new ArrayList<>();
        File file = new File(filepath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            //get line from txt file
            Object[] lines = br.lines().toArray();
            br.close();

            for (Object line1 : lines) {
                String line = line1.toString().trim();
                String[] dataRow = line.split(";");
                if (dataRow[columnNum].equals(username)) {
                    data.add(dataRow);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    //create save function
    public static void addData(String[] data, String filepath) {
        String content = String.join(";", data) + "\n";
        ModifyFile( content, filepath, true);
    }
}
