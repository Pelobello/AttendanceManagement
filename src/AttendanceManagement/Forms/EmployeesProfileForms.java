
package AttendanceManagement.Forms;

import AttendanceManagement.EventItem.EventItem;
import AttendanceManagement.Items.EmployeesItem;
import AttendanceManagement.Model.ModelEmployees;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;




public class EmployeesProfileForms extends javax.swing.JPanel {

    
    public EventItem getEventItem() {
        return eventItem;
    }

   
    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }

  private EventItem eventItem; 
    public EmployeesProfileForms() {
        initComponents();
        setOpaque(false);
         jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:10;"
                + "");
        
    }
    
    public void addEmployees(ModelEmployees data){
        EmployeesItem employeesItem = new EmployeesItem();
        employeesItem.setData(data);
         employeesItem.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
                          if (SwingUtilities.isLeftMouseButton(e)) {
                    eventItem.itemClick(employeesItem, data);
                }
           }
       });
        panelItem.add(employeesItem);
        repaint();
        revalidate();
        
    }
      public void setSelected(Component item) {
        for (Component com : panelItem.getComponents()) {
            EmployeesItem i = (EmployeesItem) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((EmployeesItem) item).setSelected(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelItem = new AttendanceManagement.Components.PanelItem();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employees Profile", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 102, 102))); // NOI18N
        jScrollPane1.setViewportView(panelItem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public AttendanceManagement.Components.PanelItem panelItem;
    // End of variables declaration//GEN-END:variables
}
