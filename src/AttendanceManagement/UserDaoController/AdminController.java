/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.UserDaoController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @author USER
 */
public class AdminController {

    public AdminController() {
    }
    
    
     public boolean createAdmin(ModelAdminData data){
         try {
             
         } catch (Exception e) {
             e.printStackTrace();
         }
        return true;
    }
     public ModelAdminData loginAdmin(ModelAdminData data){
         try {
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }
    public boolean changeAdminPassword(ModelAdminData data){
        return true;
    }
       public String encryptPass(String password){
        try {
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            byte [] encodeHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodeHash.length);
            
             for (int i = 0; i < encodeHash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodeHash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
             return encryptionValue.toString();
        } catch (Exception e) {
             return e.getMessage();
        }
    }
}
