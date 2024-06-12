/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.UserDaoController;


public class ModelAdminData {

  
    public char[] getNewpassword() {
        return newpassword;
    }

 
    public void setNewpassword(char[] newpassword) {
        this.newpassword = newpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

   
    public void setPassword(char[] password) {
        this.password = password;
    }

    public ModelAdminData() {
    }

    public ModelAdminData(char[] newpassword, String username, char[] password) {
        this.newpassword = newpassword;
        this.username = username;
        this.password = password;
    }
    
    private char[]newpassword;
    private String username;
    private char[]password;
    
}
