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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;


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
          Map<String, Object> para = new HashMap<>();

        para.put("Name", data.getName());
        para.put("Month", data.getMonth());
        para.put("Year", data.getYear());
        para.put("Principal", data.getPrincipal());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(jReport, para, dataSource);

        viewReport(print);
       
       
         
    }
    private void viewReport(JasperPrint print)throws JRException{
        JasperViewer.viewReport(print,false);
        
    }
    
    public void exportReportToPdf(ParamenterAttendance data, String outputPath) throws JRException {
        
        Map<String, Object> para = new HashMap<>();
        para.put("Name", data.getName());
        para.put("Month", data.getMonth());
        para.put("Year", data.getYear());
        
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        para.put("Principal", data.getPrincipal());
        JasperPrint print = JasperFillManager.fillReport(jReport, para, dataSource);
        
        JasperExportManager.exportReportToPdfFile(print, outputPath);
    }

    public void exportReportToExcel(ParamenterAttendance data, String outputPath) throws JRException {
        Map<String, Object> para = new HashMap<>();
        para.put("Name", data.getName());
        para.put("Month", data.getMonth());
        para.put("Year", data.getYear());
         para.put("Principal", data.getPrincipal());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(jReport, para, dataSource);
        
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputPath));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        exporter.setConfiguration(configuration);
        
        exporter.exportReport();
    }
   
}
