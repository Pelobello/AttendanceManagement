/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package AttendanceManagement.Forms;

import AttendanceManagement.Controller.AttendanceController;
import AttendanceManagement.Model.ModelAttendance;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.text.SimpleDateFormat;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class AttendanceUpdateForms extends javax.swing.JPanel {
    private EmployeesDataForms dataForms;
    private AttendanceController controller = new AttendanceController();
    public AttendanceUpdateForms(EmployeesDataForms dataForms) {
        initComponents();
        this.dataForms = dataForms;
        amArrivalTP.set24HourView(true);
        pmDepartureTP.set24HourView(true);
         pmArrivalTP.set24HourView(true);
          amDepartureTP.set24HourView(true);
        amArrivalTP.setEditor(amArrival);
        amDepartureTP.setEditor(amDeparture);
        pmArrivalTP.setEditor(pmArrival);
        pmDepartureTP.setEditor(pmDeparture);
        
//        amArrivalTP.setEditorIcon(new FlatSVGIcon("AttendanceManagement/Images_Icons/timePicker.svg"));
       
        
    }
    
      private boolean Confirmation(){
        int reply = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
    return reply == JOptionPane.YES_OPTION;
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        amArrivalTP = new raven.datetime.component.time.TimePicker();
        pmDepartureTP = new raven.datetime.component.time.TimePicker();
        amDepartureTP = new raven.datetime.component.time.TimePicker();
        pmArrivalTP = new raven.datetime.component.time.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        id = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        amArrival = new javax.swing.JFormattedTextField();
        amDeparture = new javax.swing.JFormattedTextField();
        pmDeparture = new javax.swing.JFormattedTextField();
        pmArrival = new javax.swing.JFormattedTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        id.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        id.setText("0");

        fname.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        fname.setText("Full Name");

        department.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        department.setText("DepartMent");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AttendanceManagement/Images_Icons/icons8_update_35px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        amArrival.setEditable(false);
        amArrival.setBackground(new java.awt.Color(255, 255, 255));
        amArrival.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Am Arrival", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        amArrival.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        amArrival.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amArrival.setFocusCycleRoot(true);
        amArrival.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        amDeparture.setEditable(false);
        amDeparture.setBackground(new java.awt.Color(255, 255, 255));
        amDeparture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Am Departure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        amDeparture.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        amDeparture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amDeparture.setFocusCycleRoot(true);
        amDeparture.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        pmDeparture.setEditable(false);
        pmDeparture.setBackground(new java.awt.Color(255, 255, 255));
        pmDeparture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pm Departure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        pmDeparture.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        pmDeparture.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pmDeparture.setFocusCycleRoot(true);
        pmDeparture.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pmDeparture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmDepartureActionPerformed(evt);
            }
        });

        pmArrival.setEditable(false);
        pmArrival.setBackground(new java.awt.Color(255, 255, 255));
        pmArrival.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pm Arrival", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        pmArrival.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        pmArrival.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pmArrival.setFocusCycleRoot(true);
        pmArrival.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(amArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pmDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pmArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pmArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pmDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        if (Confirmation()) {
             try {
            ModelAttendance attendance = new ModelAttendance();
            int idData = Integer.parseInt(id.getText());
            String amIn = amArrival.getText();
            String amOut = amDeparture.getText();
            String pmIn = pmArrival.getText();
            String pmOut = pmDeparture.getText();
            
            if (!amIn.isEmpty()) {
            attendance.setAmTimeIn(LocalTime.parse(amIn));
        } else {
            attendance.setAmTimeIn(null); // or set a default value
        }
        
        if (!amOut.isEmpty()) {
            attendance.setAmTimeOut(LocalTime.parse(amOut));
        } else {
            attendance.setAmTimeOut(null); // or set a default value
        }
        
        if (!pmIn.isEmpty()) {
            attendance.setPmTimeIn(LocalTime.parse(pmIn));
        } else {
            attendance.setPmTimeIn(null); // or set a default value
        }
        
        if (!pmOut.isEmpty()) {
            attendance.setPmTimeOut(LocalTime.parse(pmOut));
        } else {
            attendance.setPmTimeOut(null); // or set a default value
        }
            attendance.setEmployeesID(idData);
            controller.UpdateData(attendance);
            dataForms.loadData();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pmDepartureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmDepartureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pmDepartureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JFormattedTextField amArrival;
    private raven.datetime.component.time.TimePicker amArrivalTP;
    public javax.swing.JFormattedTextField amDeparture;
    private raven.datetime.component.time.TimePicker amDepartureTP;
    public javax.swing.JLabel department;
    public javax.swing.JLabel fname;
    public javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JFormattedTextField pmArrival;
    private raven.datetime.component.time.TimePicker pmArrivalTP;
    public javax.swing.JFormattedTextField pmDeparture;
    private raven.datetime.component.time.TimePicker pmDepartureTP;
    // End of variables declaration//GEN-END:variables
}
