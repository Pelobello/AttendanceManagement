/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.DtrController;
import AttendanceManagement.Controller.EmployeesController;
import AttendanceManagement.Main.Main;
import AttendanceManagement.Model.ModelDtr;
import AttendanceManagement.Model.ModelEmployees;
import AttendanceManagement.ModelRecords.FieldReportAttendance;
import AttendanceManagement.ModelRecords.ParamenterAttendance;
import AttendanceManagement.print.ReportManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author USER
 */
public class DtrForms extends javax.swing.JPanel {

    private EmployeesController employeesController = new EmployeesController();
    private DtrController dtrController = new DtrController();
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
   
    public DtrForms() {
        
        initComponents();
        setOpaque(false);
        year_monthDATA();
        init();
        testReport();
       
    }
private void init(){
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < dtrTable.getColumnCount(); i++) {
            dtrTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    
        panelTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "");
       
        dtrTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "font:bold;"
                + "hoverBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "");
        dtrTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
            SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "");
           SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
         
}
private void year_monthDATA(){
    Calendar c = Calendar.getInstance();
//    Year.addItem("2014");
    
   int current_year = c.get(Calendar.YEAR);
   Year.setSelectedItem(current_year);
    for (int year = 2015; year <=   current_year; year++) {
        String yearStr = Integer.toString(year);
        
        Year.addItem(yearStr);
        
    }
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
             srcDtr();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid EmployeesID format. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching for the employee.");
        }
    }
}
private void srcDtr(){
    int empIDstr = Integer.parseInt(SearchField.getText());
    int yearStr = Integer.parseInt((String) Year.getSelectedItem());
    
      ModelDtr modelDtr = new ModelDtr(empIDstr, (String) Month.getSelectedItem(), yearStr);
    try {
        dtrController.populateDtr(dtrTable, modelDtr);
        
        
    } catch (Exception e) {
    }
    
}

private void testReport(){
    try {
        ReportManager.getInstance().compileReport();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dtrTable = new javax.swing.JTable();
        SearchField = new AttendanceManagement.Components.TextField();
        Month = new javax.swing.JComboBox<>();
        Year = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        FullName = new javax.swing.JLabel();
        employeesID = new javax.swing.JLabel();
        DepartMent = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Principal = new AttendanceManagement.Components.TextField();

        setPreferredSize(new java.awt.Dimension(1145, 659));

        panelTable.setBackground(new java.awt.Color(255, 255, 255));

        dtrTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dtrTable.setForeground(new java.awt.Color(51, 51, 51));
        dtrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Day", "Arrival", "Departure", "Arrival", "Departure", "Total Working Hours", "UnderTime", "OverTime"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dtrTable);
        if (dtrTable.getColumnModel().getColumnCount() > 0) {
            dtrTable.getColumnModel().getColumn(0).setResizable(false);
            dtrTable.getColumnModel().getColumn(0).setPreferredWidth(25);
            dtrTable.getColumnModel().getColumn(1).setResizable(false);
            dtrTable.getColumnModel().getColumn(2).setResizable(false);
            dtrTable.getColumnModel().getColumn(3).setResizable(false);
            dtrTable.getColumnModel().getColumn(4).setResizable(false);
            dtrTable.getColumnModel().getColumn(5).setResizable(false);
            dtrTable.getColumnModel().getColumn(6).setResizable(false);
            dtrTable.getColumnModel().getColumn(7).setResizable(false);
        }

        SearchField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchField.setLabelText("Employees ID");
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        Month.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        Year.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2014" }));
        Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        FullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        FullName.setForeground(new java.awt.Color(102, 102, 102));
        FullName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FullName.setText("FullName");

        employeesID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeesID.setForeground(new java.awt.Color(102, 102, 102));
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        employeesID.setText("IDNumber");

        DepartMent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DepartMent.setForeground(new java.awt.Color(102, 102, 102));
        DepartMent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DepartMent.setText("Department");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FullName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeesID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmployeesImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        jButton1.setText("Pdf");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Principal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Principal.setLabelText("Principal");
        Principal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrincipalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelTableLayout.createSequentialGroup()
                                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelTableLayout.createSequentialGroup()
                                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(SearchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Month, javax.swing.GroupLayout.Alignment.LEADING, 0, 203, Short.MAX_VALUE))
                                        .addGap(26, 26, 26)
                                        .addComponent(Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
      srchFieldScn();
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (Principal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill out Principal Field");
        }else{
           try {
    List<FieldReportAttendance> list = new ArrayList<>();
    DefaultTableModel data = (DefaultTableModel) dtrTable.getModel();
    
    // Iterate through each row of the table
    for (int i = 0; i < data.getRowCount(); i++) {
        // Retrieve the values of each column in the current row
        String col1 = (String) data.getValueAt(i, 0);
        String col2 = (String) data.getValueAt(i, 1);
        String col3 = (String) data.getValueAt(i, 2);
        String col4 = (String) data.getValueAt(i, 3);
        String col5 = (String) data.getValueAt(i, 4);
        String col6 = (String) data.getValueAt(i, 5);
        String col7 = (String) data.getValueAt(i, 6);
        String col8 = (String) data.getValueAt(i, 7);
        
        // Create a new FieldReportAttendance object with the values and add it to the list
        list.add(new FieldReportAttendance(col1, col2, col3, col4, col5, col6, col7, col8));
    }
    
    int cmbtoStr = Integer.valueOf((String) Year.getSelectedItem());
    ParamenterAttendance dataprint = new ParamenterAttendance(
        FullName.getText(), 
        (String) Month.getSelectedItem(), 
        cmbtoStr, 
        list,Principal.getText()
    );
    
    ReportManager.getInstance().printAttendanceReport(dataprint);
   
    
} catch (Exception e) {
    e.printStackTrace();
}
    }
        
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Principal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill out Principal Field");
        }else{
              File file = showFileChooser();
    if (file != null) {
        try {
            List<FieldReportAttendance> list = new ArrayList<>();
            DefaultTableModel data = (DefaultTableModel) dtrTable.getModel();
            
            // Iterate through each row of the table
            for (int i = 0; i < data.getRowCount(); i++) {
                // Retrieve the values of each column in the current row
                String col1 = (String) data.getValueAt(i, 0);
                String col2 = (String) data.getValueAt(i, 1);
                String col3 = (String) data.getValueAt(i, 2);
                String col4 = (String) data.getValueAt(i, 3);
                String col5 = (String) data.getValueAt(i, 4);
                String col6 = (String) data.getValueAt(i, 5);
                String col7 = (String) data.getValueAt(i, 6);
                String col8 = (String) data.getValueAt(i, 7);
                
                // Create a new FieldReportAttendance object with the values and add it to the list
                list.add(new FieldReportAttendance(col1, col2, col3, col4, col5, col6, col7, col8));
            }
            
            int cmbtoStr = Integer.parseInt((String) Year.getSelectedItem());
            ParamenterAttendance dataprint = new ParamenterAttendance(
                FullName.getText(), 
                (String) Month.getSelectedItem(), 
                cmbtoStr, 
                list,Principal.getText()
            );

            String pdfPath = file.getAbsolutePath();
            if (!pdfPath.toLowerCase().endsWith(".xls")) {
                pdfPath += ".xls";
            }

            // Export the report to a PDF file
            ReportManager.getInstance().exportReportToExcel(dataprint, pdfPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
        }
        
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void PrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrincipalActionPerformed

    private void YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YearActionPerformed

 private File showFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Report as exceel");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xls"));
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        return fileChooser.getSelectedFile();
    }
    return null;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DepartMent;
    private AttendanceManagement.Components.ImageBox EmployeesImage;
    private javax.swing.JLabel FullName;
    private javax.swing.JComboBox<String> Month;
    private AttendanceManagement.Components.TextField Principal;
    private AttendanceManagement.Components.TextField SearchField;
    private javax.swing.JComboBox<String> Year;
    private javax.swing.JTable dtrTable;
    private javax.swing.JLabel employeesID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTable;
    // End of variables declaration//GEN-END:variables
}
