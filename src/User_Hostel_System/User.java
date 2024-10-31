package User_Hostel_System;

import GUI.ConfigControl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

    
public abstract class User {

    private String id;
    private String user;
    private String password;
    private String name;
    private String phone_number;
    private String course;
    private String email;
    private Roles role;

    public User(String id,
                String user,
                String password, 
                String name,
                String phone_number, 
                String email, 
                String course,
                Roles role) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.course = course;
        this.role = role;
    }

    public User(String id,
                String user,
                String password, 
                String name,
                String phone_number, 
                String email, 
                String course,
                String role) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.course = course;
        this.role = Roles.stringToRole(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    

    
    public static String[] ReadCol (int col , File file, String delimiter){
        String data[];
        String currentLine;
        ArrayList<String>colData = new ArrayList<String>();
        
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            while((currentLine = br.readLine())!=null){
                data = currentLine.split(delimiter);
                colData.add(data[col]);
            }
        }catch(Exception e){
            return null;
        }
        return colData.toArray(new String[0]);
    }
    
    public static String incrementNumberIn(String s){
        String result ="";
        String numberStr ="";
        int i = s.length()-1;
        for(;i>0;i--){
            char c = s.charAt(i);
            if(!Character.isDigit(c))
                break;
                numberStr = c + numberStr;
            
        }
        int number = Integer.parseInt(numberStr);
        number++;
        
        result += s.substring(0,i+1);
        result += number <10 ? "0":"";
        result += number;
        
        return result;
    }
    
    public static ArrayList<User> readFile() {

        String filePath = ConfigControl.USER_FILENAME;
        File file = new File(filePath);

        ArrayList<User> tempArray = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            //get line from txt file
            Object[] lines = br.lines().toArray();
            br.close();

            for (Object line1 : lines) {
                String line = line1.toString().trim();
                String[] dataRow = line.split(";");
                tempArray.add(getUser(dataRow[0], 
                                        dataRow[1], 
                                        dataRow[2], 
                                        dataRow[3], 
                                        dataRow[4],
                                        dataRow[5], 
                                        dataRow[6], 
                                        dataRow[7]));
                            }
            //Polymorphism (User user = new admin) 
            
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempArray;
    }
    public static User getUser(String id,
                               String user,
                               String name,
                               String password,
                               String phone_number,
                               String email,
                               String course,
                               String role) {      
       return switch (role) {
            case "ADMIN" ->
                new Admin(id, user, name, password, phone_number, email,course, role);
            case "STUDENT" ->
                new Student(id, user, name, password, phone_number, email, course, role);
            default ->
                null;
            };
        }
    }
