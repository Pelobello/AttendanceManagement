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
//        setOpaque(false);
       
        init();
        testReport();
         year_monthDATA();
       
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
           
           SearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Employee's ID");
           Principal.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Principal's Full Name");
           SearchField.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("AttendanceManagement/Images_Icons/search.svg"));
         
}
private void year_monthDATA(){
     Calendar c = Calendar.getInstance();
    int current_year = c.get(Calendar.YEAR);
    int current_month = c.get(Calendar.MONTH); // Note: Months are 0-based (January is 0)

    // Clear existing items
    Year.removeAllItems();
    Month.removeAllItems();

    // Add years from 2015 to the current year
    for (int year = 2015; year <= current_year; year++) {
        String yearStr = Integer.toString(year);
        Year.addItem(yearStr);
    }

    // Set the selected item to the current year
    Year.setSelectedItem(Integer.toString(current_year));

    // Add months to the Month combobox
    String[] months = {
        "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December"
    };

    for (String month : months) {
        Month.addItem(month);
    }

    // Set the selected item to the current month
    Month.setSelectedItem(months[current_month]);
   
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
        Month = new javax.swing.JComboBox<>();
        Year = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        EmployeesImage = new AttendanceManagement.Components.ImageBox();
        FullName = new javax.swing.JLabel();
        employeesID = new javax.swing.JLabel();
        DepartMent = new javax.swing.JLabel();
        pdfBtn = new javax.swing.JButton();
        excelBtn = new javax.swing.JButton();
        SearchField = new javax.swing.JTextField();
        Principal = new javax.swing.JTextField();

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

        Month.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthActionPerformed(evt);
            }
        });

        Year.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
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

        employeesID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        employeesID.setForeground(new java.awt.Color(102, 102, 102));
        employeesID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        DepartMent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        DepartMent.setForeground(new java.awt.Color(102, 102, 102));
        DepartMent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FullName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeesID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(EmployeesImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(FullName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employeesID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DepartMent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        pdfBtn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pdfBtn.setText("Pdf");
        pdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfBtnActionPerformed(evt);
            }
        });

        excelBtn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        excelBtn.setText("Excel");
        excelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelBtnActionPerformed(evt);
            }
        });

        SearchField.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        SearchField.setForeground(new java.awt.Color(102, 102, 102));
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        Principal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Principal.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(excelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pdfBtn))
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelTableLayout.createSequentialGroup()
                                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelTableLayout.createSequentialGroup()
                                        .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTableLayout.createSequentialGroup()
                                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Principal)))
                                .addGap(212, 212, 212)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(16, 16, 16))
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableLayout.createSequentialGroup()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addComponent(SearchField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Month, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pdfBtn)
                    .addComponent(excelBtn))
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

    private void pdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfBtnActionPerformed
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
    ParamenterAttendance dataprint = new ParamenterAttendance(FullName.getText(), (String) Month.getSelectedItem(), cmbtoStr, list,Principal.getText()
    );
    
    ReportManager.getInstance().printAttendanceReport(dataprint);
   
    
} catch (Exception e) {
    e.printStackTrace();
}
    }
        
     
    }//GEN-LAST:event_pdfBtnActionPerformed

    private void excelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelBtnActionPerformed
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
        
      
    }//GEN-LAST:event_excelBtnActionPerformed

    private void YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YearActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
    srchFieldScn();
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonthActionPerformed

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
    private javax.swing.JTextField Principal;
    private javax.swing.JTextField SearchField;
    private javax.swing.JComboBox<String> Year;
    private javax.swing.JTable dtrTable;
    private javax.swing.JLabel employeesID;
    private javax.swing.JButton excelBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTable;
    private javax.swing.JButton pdfBtn;
    // End of variables declaration//GEN-END:variables
}
