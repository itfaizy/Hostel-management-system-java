/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User_Hostel_System;

/**
 *
 * @author seren
 */
public class Admin extends User{
    private String AdminUser;
    
    public Admin(String id,
                String user,
                String password,
                String name,
                String phone_number,
                String email,
                String course,
                String role){
        super(id, user, password, name, phone_number, email, course, role);
    }
    
    public String getAdminUser(){
        return AdminUser;
    }
    
    public void setAdminUser(String AdminUser){
        this.AdminUser = AdminUser;
    }
    
    @Override
    public String toString(){
        return "Admin" + '(' + super.toString() + "AdminUser" + AdminUser + ')';
    }
    
    
}
