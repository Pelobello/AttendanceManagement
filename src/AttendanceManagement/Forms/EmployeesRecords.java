
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.EmployeesData;
import AttendanceManagement.Model.ModelEmployeesData;
import AttendanceManagement.ModelRecords.FieldEmployee;
import AttendanceManagement.ModelRecords.FieldReportAttendance;
import AttendanceManagement.ModelRecords.ParamenterAttendance;
import AttendanceManagement.ModelRecords.ParameterEmployee;
import AttendanceManagement.print.ReportManager;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class EmployeesRecords extends javax.swing.JPanel {

    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private EmployeesData employeesData = new EmployeesData();
    public EmployeesRecords() {
        initComponents();
        loadData();
        init();
        testReport();
    }
private void init() {
      centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
              for (int i = 0; i < employeesTable.getColumnCount(); i++) {
            employeesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
           employeesTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "font:bold;"
                + "hoverBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "");
        employeesTable.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");

}
   public void loadData(){
       try {
           DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
           model.setRowCount(0);
           
           List<ModelEmployeesData>list = employeesData.getallData();
           for (ModelEmployeesData d : list) {
               model.addRow(d.tableRow(employeesTable.getRowCount()+1));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   private void searchData(){
       try {
           DefaultTableModel model = (DefaultTableModel)employeesTable.getModel();
           model.setRowCount(0);
           
           List<ModelEmployeesData>list = employeesData.searchlData((String)searchDepartment.getSelectedItem());
           for (ModelEmployeesData d : list) {
               model.addRow(d.tableRow(employeesTable.getRowCount()+1));
           }
       } catch (Exception e) {
           e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeesTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        searchDepartment = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        employeesTable.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        employeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Name", "Middle Name", "Last Name", "Position", "Department", "Plantilla #", "DateAssumed"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(employeesTable);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Print Records");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        searchDepartment.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        searchDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL TEACHERS", "JHS", "SHS", "NTP" }));
        searchDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 928, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(searchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDepartmentActionPerformed
      searchData();
    }//GEN-LAST:event_searchDepartmentActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          try {
        List<FieldEmployee> list = new ArrayList<>();
        DefaultTableModel data = (DefaultTableModel) employeesTable.getModel();
        for (int i = 0; i < data.getRowCount(); i++) {
            Object col1 = data.getValueAt(i, 0);
            Object col2 = data.getValueAt(i, 1);
            Object col3 = data.getValueAt(i, 2);
            Object col4 = data.getValueAt(i, 3);
            Object col5 = data.getValueAt(i, 4);
            Object col6 = data.getValueAt(i, 5);
            Object col7 = data.getValueAt(i, 6);
            Object col8 = data.getValueAt(i, 7);
            
            // Check if the cell value is an Integer, and convert it to a String if necessary
            String strCol1 = (col1 instanceof Integer) ? String.valueOf(col1) : (String) col1;
            String strCol2 = (col2 instanceof Integer) ? String.valueOf(col2) : (String) col2;
            String strCol3 = (col3 instanceof Integer) ? String.valueOf(col3) : (String) col3;
            String strCol4 = (col4 instanceof Integer) ? String.valueOf(col4) : (String) col4;
            String strCol5 = (col5 instanceof Integer) ? String.valueOf(col5) : (String) col5;
            String strCol6 = (col6 instanceof Integer) ? String.valueOf(col6) : (String) col6;
            String strCol7 = (col7 instanceof Integer) ? String.valueOf(col7) : (String) col7;
            String strCol8 = (col8 instanceof Integer) ? String.valueOf(col8) : (String) col8;
            
            list.add(new FieldEmployee(strCol1, strCol2, strCol3, strCol4, strCol5, strCol6, strCol7, strCol8));
        }
            
              URL imageURL = getClass().getResource("/AttendanceManagement/Images_Icons/schoollogo.png");
            // Create an InputStream to read the image data
               InputStream inputStream = imageURL.openStream();
        ParameterEmployee printEmployee = new ParameterEmployee("LUPON VOCATIONAL HIGH SCHOOL", inputStream, list);
        ReportManager.getInstance().printTeachersReport(printEmployee);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JTable employeesTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> searchDepartment;
    // End of variables declaration//GEN-END:variables

    
}
