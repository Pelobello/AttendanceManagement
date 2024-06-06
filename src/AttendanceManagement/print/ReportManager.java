/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.print;

import AttendanceManagement.ModelRecords.ParamenterAttendance;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class ReportManager {
private static ReportManager instance;
private JasperReport jReport;
public static ReportManager getInstance(){
    if (instance == null) {
        instance = new ReportManager();
        
    }
        return instance;
    
}


    private ReportManager() {
        
        
    }
    public void compileReport()throws JRException{
        jReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/AttendanceManagement/print/AttendanceReport.jrxml"));
    }
    public void printAttendanceReport(ParamenterAttendance data)throws JRException{
        Map para = new HashMap();
        para.put("", data.getName());
        para.put("", data.getMonth());
         para.put("", data.getYear());
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
         JasperPrint print = JasperFillManager.fillReport(jReport, para,dataSource);
    }
}
