
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Main.Main;
import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import raven.glasspanepopup.GlassPanePopup;
import java.text.SimpleDateFormat;


public class EmployeesForm extends javax.swing.JPanel {
    private Main main;
    private DateChooser dc = new DateChooser();
    private EmployeesController employeesController = new EmployeesController();
    private ModelEmployees modelEmployees = new ModelEmployees();
    
    public EmployeesForm(Main main) {
        initComponents();
        this.main = main;
        dc.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dc.setTextField(dateAssumed);
    }
   
    private boolean Confirmation(){
        int reply = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    return reply == JOptionPane.YES_OPTION;
        
    }
   private boolean TxtFieldData() {
    if (employeeID.getText().isEmpty() || firstName.getText().isEmpty() || FamilyName.getText().isEmpty() ||
        position.getText().isEmpty()|| dateAssumed.getText().isEmpty() || 
        plantillaItem.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all fields with *!");
        return false;
    } else {
        return true;
    }
}
      private  ModelEmployees employeesData() throws ParseException{
         int employeeId = Integer.parseInt(employeeID.getText());
                int plantillaNumber = Integer.parseInt(plantillaItem.getText());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateData = dateFormat.parse(dateAssumed.getText());
                java.sql.Date sqlDate = new java.sql.Date(dateData.getTime());
                int sqlId = Integer.parseInt(id.getText());

                // Add employee
                ModelEmployees UpdateEmployees = new ModelEmployees(employeeId, firstName.getText(), middleName.getText(),FamilyName.getText(), position.getText(), (String) department.getSelectedItem(),sqlDate,plantillaNumber,
                    employeesImage.getImage());
                UpdateEmployees.setIdData(sqlId);
        return UpdateEmployees;
             
   }
   private void AddEmployees() throws IOException, ParseException {
         if (TxtFieldData()) {
            // Show confirmation dialog
            if (Confirmation()) {
              
                // Add employee               
                employeesController.addEmployee(employeesData());
                GlassPanePopup.closePopupAll();
                main.testData();
            }
        }   
   }

    private void UpdateEmployees() throws IOException, ParseException {
        int sqlId = Integer.parseInt(id.getText());
         if (TxtFieldData()) {
            // Show confirmation dialog
            if (Confirmation()) {
             
                employeesController.updateEmployeesData(employeesData());
                GlassPanePopup.closePopupAll();
                main.testData();
            }
        }
      
   }
        private void DeleteEmployees(){
        int sqlId = Integer.parseInt(id.getText());
        modelEmployees.setIdData(sqlId);
        if (TxtFieldData()) {
            // Show confirmation dialog
            if (Confirmation()) {
                employeesController.deleteEmployeesData(modelEmployees);
                GlassPanePopup.closePopupAll();
                main.testData();
            }      
            }        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        employeesImage = new AttendanceManagement.Components.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        employeeID = new AttendanceManagement.Components.TextField();
        firstName = new AttendanceManagement.Components.TextField();
        middleName = new AttendanceManagement.Components.TextField();
        position = new AttendanceManagement.Components.TextField();
        FamilyName = new AttendanceManagement.Components.TextField();
        dateAssumed = new AttendanceManagement.Components.TextField();
        plantillaItem = new AttendanceManagement.Components.TextField();
        jPanel3 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        department = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        employeesImage.setBackground(new java.awt.Color(255, 255, 255));
        employeesImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile-png.jpg"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Drag Here ↑");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeesImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(employeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        employeeID.setForeground(new java.awt.Color(102, 102, 102));
        employeeID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        employeeID.setLabelText("Employees ID#");

        firstName.setForeground(new java.awt.Color(102, 102, 102));
        firstName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        firstName.setLabelText("First Name");

        middleName.setForeground(new java.awt.Color(102, 102, 102));
        middleName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        middleName.setLabelText("Middle Name");

        position.setForeground(new java.awt.Color(102, 102, 102));
        position.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        position.setLabelText("Position/Designation");

        FamilyName.setForeground(new java.awt.Color(102, 102, 102));
        FamilyName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        FamilyName.setLabelText("Family Name");

        dateAssumed.setEditable(false);
        dateAssumed.setBackground(new java.awt.Color(255, 255, 255));
        dateAssumed.setForeground(new java.awt.Color(102, 102, 102));
        dateAssumed.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        dateAssumed.setLabelText("Date Assumed To Office");

        plantillaItem.setForeground(new java.awt.Color(102, 102, 102));
        plantillaItem.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        plantillaItem.setLabelText(" Plantilla Item #");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/icons8_add_35px.png"))); // NOI18N
        addBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/icons8_update_35px.png"))); // NOI18N
        updateBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        updateBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/icons8_delete_35px.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        id.setText("0");

        department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JHS", "SHS", "NTP" }));
        department.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DepartMent", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(middleName, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(position, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(dateAssumed, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(plantillaItem, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(department, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateAssumed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(middleName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(FamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(plantillaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
       
        try {
            AddEmployees();
            } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID or Plantilla Item. Please enter a valid number.");
        
        } catch (IOException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
      DeleteEmployees();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            UpdateEmployees();
            } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID or Plantilla Item. Please enter a valid number.");
        
        } catch (IOException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public AttendanceManagement.Components.TextField FamilyName;
    public javax.swing.JButton addBtn;
    public AttendanceManagement.Components.TextField dateAssumed;
    public javax.swing.JButton deleteBtn;
    public javax.swing.JComboBox<String> department;
    public AttendanceManagement.Components.TextField employeeID;
    public AttendanceManagement.Components.PictureBox employeesImage;
    public AttendanceManagement.Components.TextField firstName;
    public javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public AttendanceManagement.Components.TextField middleName;
    public AttendanceManagement.Components.TextField plantillaItem;
    public AttendanceManagement.Components.TextField position;
    public javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}