/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User_Hostel_System;

/**
 *
 * @author seren
 */
public class Student extends User{
    private String StudentUser;
    
    public Student(String id,
                String user,
                String password,
                String name,
                String phone_number,
                String email,
                String course,
                String role){
        super(id, user, password, name, phone_number, email,course, role);
    }
    
    public String getStudentUser(){
        return StudentUser;
    }
    
    public void setStudentUser(String StudentUser){
        this.StudentUser = StudentUser;
    }
    
    @Override
    public String toString(){
        return "Student" + '(' + super.toString() + "StudentUser" + StudentUser + ')';
    }
}
