package User_Hostel_System;

public enum Roles {
    ADMIN("ADMIN", "ADMIN HOSTEL"),
    STUDENT("STUDENT", "STUDENT HOSTEL"),
    UNREGISTER("UNREGISTER", "UNRESGISTER USER");
    
    private final String role;
    private final String rolefullname;
    
    private Roles(String role, String rolefullname){
        this.role = role;
        this.rolefullname = rolefullname;
    }
    
    public String getRole(){
        return role;
    }
    
    public String getRolefullname(){
        return rolefullname;
    }
    
    public static Roles stringToRole(String role){
        return switch(role){
            case "ADMIN" -> 
                ADMIN;
            case "STUDENT" -> 
                STUDENT;
            default -> 
                UNREGISTER;
        };
    }
    
    
}

