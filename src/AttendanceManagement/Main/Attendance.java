
package AttendanceManagement.Main;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


public class Attendance extends javax.swing.JFrame {

  private EmployeesController employeesController = new EmployeesController();
  private AttendanceController attendanceController = new AttendanceController();
    public Attendance() {
        initComponents();
    
               
        DepartMent.setVisible(false);
//         setExtendedState(Attendance.MAXIMIZED_BOTH);
  SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
  userName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
 SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
 setupKeyBindings();
    }
    //control A D for admin log in to show up
    private void setupKeyBindings() {
        // Get the root pane's input map and action map
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        // Define the key strokes for Alt + F1 and Alt + F2
        KeyStroke altF1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.ALT_DOWN_MASK);
        KeyStroke altF2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_DOWN_MASK);

        // Map the key strokes to action keys
        inputMap.put(altF1, "altF1");
        inputMap.put(altF2, "altF2");

        // Define the actions
        actionMap.put("altF1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if index 0 exists to avoid ArrayIndexOutOfBoundsException
                if (tabpane.getTabCount() > 0) {
                    tabpane.setSelectedIndex(0); // Select the first tab
                }
            }
        });

        actionMap.put("altF2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if index 1 exists to avoid ArrayIndexOutOfBoundsException
                if (tabpane.getTabCount() > 1) {
                    tabpane.setSelectedIndex(1); // Select the second tab
                }
            }
        });
    }
private void SearchEmployees(){
     int searchID = Integer.parseInt(SearchField.getText());
        
        ModelEmployees employees = employeesController.SearchEmployees(searchID);        
        if (employees!=null) {           
            String idStr = Integer.toString(employees.getId());
             FullName.setText(employees.getFirstName()+" "+employees.getLastName());
             employeesID.setText(idStr);
             EmployeesImage.setImage(employees.getEmployeesImage());
             DepartMent.setText(employees.getDepartment());
            repaint();
            revalidate();            
        }
}
private void srchFieldScn(){
       if (SearchField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please input EmployeesID");
    } else {
        try {
            SearchEmployees();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID format. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching for the employee.");
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabpane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        employeesID = new javax.swing.JLabel();
        FullName = new javax.swing.JLabel();
        AmTimeIn = new javax.swing.JButton();
        AmTimeOut = new javax.swing.JButton();
        DepartMent = new javax.swing.JLabel();
        SearchField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        userName = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(48, 52, 129));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabpane.setBackground(new java.awt.Color(48, 52, 129));
        tabpane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabpane.setEnabled(false);
        tabpane.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        EmployeesImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/profile-png.jpg"))); // NOI18N

        employeesID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        employeesID.setForeground(new java.awt.Color(102, 102, 102));
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeesID.setText("0");
        employeesID.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        FullName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FullName.setForeground(new java.awt.Color(102, 102, 102));
        FullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FullName.setText("Na");

        AmTimeIn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AmTimeIn.setText("Time In");
        AmTimeIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmTimeInActionPerformed(evt);
            }
        });

        AmTimeOut.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AmTimeOut.setText("Time Out");
        AmTimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmTimeOutActionPerformed(evt);
            }
        });

        DepartMent.setDisplayedMnemonic('\u013a');
        DepartMent.setText("DepartMent");

        SearchField.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        SearchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EmployeesImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(AmTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AmTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 518, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmTimeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AmTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        tabpane.addTab("Logs", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setText("Sign in");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        userName.setFocusCycleRoot(true);

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(515, 515, 515)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(userName, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(659, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpane.addTab("Admin", jPanel3);

        jPanel1.add(tabpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, -43, 1360, 810));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     SwingUtilities.invokeLater(() -> {
        Main main = new Main();
      main.setVisible(true); // Show the new window
        dispose(); // Dispose the current JFrame
    });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void AmTimeInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmTimeInActionPerformed
       try {
        if (employeesID.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        } else {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
            int currentHour = currentTime.getHour();
            int empID = Integer.parseInt(employeesID.getText());
            Icon employeesImageProfile = EmployeesImage.getImage();
            String department = DepartMent.getText();
            String fullName = FullName.getText();
            
            ModelAttendance attendanceRecord = new ModelAttendance();
            attendanceRecord.setEmployeesImage(employeesImageProfile);
            attendanceRecord.setDepartment(department);
            attendanceRecord.setEmployeesID(empID);
            attendanceRecord.setEmployeesFullName(fullName);
            LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);

            String timeLabel = (currentHour < 13) ? "AM Arrival" : "PM Arrival";
            int confirmation = JOptionPane.showConfirmDialog(null, "Do you agree to record the " + timeLabel + " at " + formattedTime + "?", "Confirm Time In", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                if (currentHour < 13) {          
                    attendanceRecord.setAmTimeIn(formattedLocalTime);
                    attendanceController.amtimeIn(attendanceRecord);
                } else {  
                    attendanceRecord.setPmTimeIn(formattedLocalTime);
                    attendanceController.pmtimeIn(attendanceRecord);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time In was not recorded.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_AmTimeInActionPerformed

    private void AmTimeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmTimeOutActionPerformed
           try {
        if (employeesID.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
        } else {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(dtf);
            int currentHour = currentTime.getHour();
            int empID = Integer.parseInt(employeesID.getText());
            Icon employeesImageProfile = EmployeesImage.getImage();
            String department = DepartMent.getText();
            String fullName = FullName.getText();
            
            ModelAttendance attendanceRecord = new ModelAttendance();
            attendanceRecord.setEmployeesImage(employeesImageProfile);
            attendanceRecord.setDepartment(department);
            attendanceRecord.setEmployeesID(empID);
            attendanceRecord.setEmployeesFullName(fullName);
            LocalTime formattedLocalTime = LocalTime.parse(formattedTime, dtf);

            String timeLabel = (currentHour < 13) ? "AM Departure" : "PM Departure";
            int confirmation = JOptionPane.showConfirmDialog(null, "Do you agree to record the " + timeLabel + " at " + formattedTime + "?", "Confirm Time In", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                if (currentHour < 13) {          
                    attendanceRecord.setAmTimeOut(formattedLocalTime);
                    attendanceController.amtimeOut(attendanceRecord);
                } else {  
                    attendanceRecord.setPmTimeOut(formattedLocalTime);
                    attendanceController.pmtimeOut(attendanceRecord);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Time In was not recorded.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_AmTimeOutActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
      srchFieldScn();
    }//GEN-LAST:event_SearchFieldActionPerformed

   
    public static void main(String args[]) {
        FlatIntelliJLaf.registerCustomDefaultsSource("AttendanceManagement/StyleProperties");
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Attendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AmTimeIn;
    private javax.swing.JButton AmTimeOut;
    private javax.swing.JLabel DepartMent;
    private AttendanceManagement.Components.ImageBox EmployeesImage;
    private javax.swing.JLabel FullName;
    private javax.swing.JTextField SearchField;
    private javax.swing.JLabel employeesID;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTabbedPane tabpane;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
