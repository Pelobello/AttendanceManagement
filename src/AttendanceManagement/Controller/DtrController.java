
package AttendanceManagement.Controller;

import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelDtr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DtrController {
    private PreparedStatement ps;
    private ResultSet rs;
    public DtrController() {
    }
  public void populateDtr(JTable table, ModelDtr data) {
        String sql = "SELECT\n" +
"    ROW_NUMBER() OVER () AS Day,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeIn IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.AmTimeIn, '%l:%i %p')\n" +
"    END AS AmTimeIn,\n" +
"    CASE\n" +
"        WHEN ad.AmTimeOut IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.AmTimeOut, '%l:%i %p')\n" +
"    END AS AmTimeOut,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeIn IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.PmTimeIn, '%l:%i %p')\n" +
"    END AS PmTimeIn,\n" +
"    CASE\n" +
"        WHEN ad.PmTimeOut IS NULL THEN ''\n" +
"        ELSE DATE_FORMAT(ad.PmTimeOut, '%l:%i %p')\n" +
"    END AS PmTimeOut,\n" +
"    IFNULL(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn), '00:00:00') AS TotalAmWorkHours,\n" +
"    IFNULL(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn), '00:00:00') AS TotalPmWorkHours,\n" +
"    SEC_TO_TIME(\n" +
"        IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0) +\n" +
"        IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)\n" +
"    ) AS TotalWorkHours,\n" +
"    SEC_TO_TIME(\n" +
"        TIME_TO_SEC(\n" +
"            IFNULL(\n" +
"                GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('11:45:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                ),\n" +
"                '00:00:00'\n" +
"            )\n" +
"        ) +\n" +
"        TIME_TO_SEC(\n" +
"            IFNULL(\n" +
"                GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('16:30:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                ),\n" +
"                '00:00:00'\n" +
"            )\n" +
"        )\n" +
"    ) AS AM_PM_UnderTime,\n" +
"    IFNULL(\n" +
"        GREATEST(\n" +
"            CASE ad.DepartMent\n" +
"                WHEN 'SHS' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '16:30:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '16:30:00')\n" +
"                        ELSE '00:00:00'\n" +
"                    END\n" +
"                WHEN 'JHS' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00')\n" +
"                        ELSE '00:00:00'\n" +
"                    END\n" +
"                WHEN 'NTP' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00')\n" +
"                        ELSE '00:00:00'\n" +
"                    END\n" +
"                ELSE '00:00:00'\n" +
"            END,\n" +
"            '00:00:00'\n" +
"        ),\n" +
"        '00:00:00'\n" +
"    ) AS OverTime\n" +
"FROM\n" +
"    attendance_data ad\n" +
"WHERE\n" +
"    ad.EmployeesID = ?\n" +
"    AND MONTHNAME(ad.DateCreated) = ?\n" +
"    AND YEAR(ad.DateCreated) = ?\n" +
"    AND ad.DateDeleted IS NULL\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"SELECT\n" +
"    '' AS Day,\n" +
"    '' AS AmTimeIn,\n" +
"    '' AS AmTimeOut,\n" +
"    '' AS PmTimeIn,\n" +
"    'Total' AS PmTimeOut,\n" +
"    SEC_TO_TIME(SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0))) AS TotalAmWorkHours,\n" +
"    SEC_TO_TIME(SUM(IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0))) AS TotalPmWorkHours,\n" +
"    SEC_TO_TIME(\n" +
"        SUM(\n" +
"            IFNULL(TIME_TO_SEC(TIMEDIFF(ad.AmTimeOut, ad.AmTimeIn)), 0) +\n" +
"            IFNULL(TIME_TO_SEC(TIMEDIFF(ad.PmTimeOut, ad.PmTimeIn)), 0)\n" +
"        )\n" +
"    ) AS TotalWorkHours,\n" +
"    SEC_TO_TIME(\n" +
"        SUM(\n" +
"            TIME_TO_SEC(\n" +
"                GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('11:45:00', TIME(ad.AmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('12:00:00', TIME(ad.AmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )\n" +
"            ) +\n" +
"            TIME_TO_SEC(\n" +
"                GREATEST(\n" +
"                    CASE ad.DepartMent\n" +
"                        WHEN 'SHS' THEN TIMEDIFF('16:30:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'JHS' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        WHEN 'NTP' THEN TIMEDIFF('17:00:00', TIME(ad.PmTimeOut))\n" +
"                        ELSE '00:00:00'\n" +
"                    END,\n" +
"                    '00:00:00'\n" +
"                )\n" +
"            )\n" +
"        )\n" +
"    ) AS AM_PM_UnderTime,\n" +
"    SEC_TO_TIME(SUM(\n" +
"        GREATEST(\n" +
"            CASE ad.DepartMent\n" +
"                WHEN 'SHS' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '16:30:00' THEN TIME_TO_SEC(TIMEDIFF(TIME(ad.PmTimeOut), '16:30:00'))\n" +
"                        ELSE 0\n" +
"                    END\n" +
"                WHEN 'JHS' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIME_TO_SEC(TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00'))\n" +
"                        ELSE 0\n" +
"                    END\n" +
"                WHEN 'NTP' THEN\n" +
"                    CASE\n" +
"                        WHEN TIME(ad.PmTimeOut) > '17:00:00' THEN TIME_TO_SEC(TIMEDIFF(TIME(ad.PmTimeOut), '17:00:00'))\n" +
"                        ELSE 0\n" +
"                    END\n" +
"                ELSE 0\n" +
"            END,\n" +
"            0\n" +
"        )\n" +
"    )) AS OverTime\n" +
"FROM\n" +
"    attendance_management_db.attendance_data ad\n" +
"WHERE\n" +
"    ad.EmployeesID = ?\n" +
"    AND MONTHNAME(ad.DateCreated) = ?\n" +
"    AND YEAR(ad.DateCreated) = ?\n" +
"    AND ad.DateDeleted IS NULL;";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            ps = prepareStatement(sql);
            int idx = 1;
            // First SELECT parameters
            ps.setInt(idx++, data.getEmployeesId());
            ps.setString(idx++, data.getMonth());
            ps.setInt(idx++, data.getYear());
            // Second SELECT parameters
            ps.setInt(idx++, data.getEmployeesId());
            ps.setString(idx++, data.getMonth());
            ps.setInt(idx++, data.getYear());

            rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("Day"));
                v.add(rs.getString("AmTimeIn"));
                v.add(rs.getString("AmTimeOut"));
                v.add(rs.getString("PmTimeIn"));
                v.add(rs.getString("PmTimeOut"));
                v.add(rs.getString("TotalWorkHours"));
                v.add(rs.getString("AM_PM_UnderTime"));
                v.add(rs.getString("OverTime"));
                model.addRow(v);
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  public List<ModelAttendance> populateDtrData(){
      try {
          List<ModelAttendance> list = new ArrayList<>();
          ps = prepareStatement("SELECT * FROM attendance_data WHERE DateDeleted IS NULL");
          rs = ps.executeQuery();
          while (rs.next()) {              
              int id = rs.getInt("ID");
              
          }
           return list;
      } catch (Exception e) {
          return null;
      }
  
  }
    
    
     private Connection getConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try {
            return databaseConnection.getCConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PreparedStatement prepareStatement(String sql) {
        try {
            Connection con = getConnection();
            if (con != null) {
                return con.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
