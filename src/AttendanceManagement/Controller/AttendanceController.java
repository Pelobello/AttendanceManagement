/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendanceManagement.Controller;

import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelAttendance;
import AttendanceManagement.Model.ModelEmployees;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Timestamp;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class AttendanceController {
    private PreparedStatement ps;
    private ResultSet rs;
    public AttendanceController() {
    }

 public void populateTodayAttendance(JTable table) {
        String sql = "SELECT EmployeesImage, EmployeesID, EmployeesFullName, DepartMent, " +
                "DATE_FORMAT(AmTimeIn, '%l:%i %p') AS AmTimeIn, " +
                "DATE_FORMAT(AmTimeOut, '%l:%i %p') AS AmTimeOut, " +
                "DATE_FORMAT(PmTimeIn, '%l:%i %p') AS PmTimeIn, " +
                "DATE_FORMAT(PmTimeOut, '%l:%i %p') AS PmTimeOut, " +
                "CASE WHEN AmTimeIn IS NULL THEN 'Absent' " +
                "WHEN (DepartMent = 'SHS' AND TIME(AmTimeIn) > '07:30:00') THEN 'Late' " +
                "WHEN (DepartMent = 'JHS' AND TIME(AmTimeIn) > '07:30:00') THEN 'Late' " +
                "WHEN (DepartMent = 'NTP' AND TIME(AmTimeIn) > '08:00:00') THEN 'Late' " +
                "ELSE 'On Time' END AS AmTimeInStatus, " +
                "CASE WHEN PmTimeIn IS NULL THEN 'Absent' " +
                "WHEN (DepartMent = 'SHS' AND TIME(PmTimeIn) > '13:01:00') THEN 'Late' " +
                "WHEN (DepartMent = 'JHS' AND TIME(PmTimeIn) > '13:01:00') THEN 'Late' " +
                "WHEN (DepartMent = 'NTP' AND TIME(PmTimeIn) > '13:01:00') THEN 'Late' " +
                "ELSE 'On Time' END AS PmTimeInStatus " +
                "FROM attendance_data WHERE DATE(DateCreated) = CURDATE();";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try (PreparedStatement ps =prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                String base64Image = rs.getString("EmployeesImage");

                // Decode Base64 string to ImageIcon
                ImageIcon imageicon = base64ToImageIcon(base64Image, 45, 45);
                if (imageicon == null) {
                    System.out.println("Image conversion failed for EmployeesID: " + rs.getInt("EmployeesID"));
                }

                // Add data to the vector
                v.add(imageicon);
                v.add(rs.getInt("EmployeesID"));
                v.add(rs.getString("EmployeesFullName"));
                v.add(rs.getString("DepartMent"));
                v.add(rs.getString("AmTimeIn"));
                v.add(rs.getString("AmTimeOut"));
                v.add(rs.getString("PmTimeIn"));
                v.add(rs.getString("PmTimeOut"));
                v.add(rs.getString("AmTimeInStatus"));
                v.add(rs.getString("PmTimeInStatus"));

                // Add the vector as a row to the table model
                model.addRow(v);
            }

            // Set the image renderer for the first column
            table.getColumnModel().getColumn(0).setCellRenderer(new ImageIconCellRenderer());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ImageIcon base64ToImageIcon(String base64String, int width, int height) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    class ImageIconCellRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText("");
            } else {
                setText((value == null) ? "" : value.toString());
                setIcon(null);
            }
        }
    }


private ImageIcon blobToImageIcon(Blob blob, int width, int height) throws SQLException {
    if (blob != null) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            // Read the bytes from the blob
            byte[] bytes = inputStream.readAllBytes();
            // Convert bytes to ImageIcon
            ImageIcon originalIcon = new ImageIcon(bytes);
            // Scale down the image
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return null;
}
public void amtimeIn(ModelAttendance data) {
    try {
        String sql = "INSERT INTO attendance_data (EmployeesImage,DepartMent, EmployeesID, EmployeesFullName, AmTimeIn) VALUES (?,?, ?, ?, ?)";
        ps = prepareStatement(sql);

        // Convert ImageIcon to Base64 string
        BufferedImage bufferedImage = createBufferedImageWithWhiteBackground((ImageIcon) data.getEmployeesImage());
        String base64Image = encodeImageToBase64(bufferedImage);

        if (existingEmployee(data)) {
            JOptionPane.showMessageDialog(null, "Already Time in!");
        } else {
            ps.setString(1, base64Image);
            ps.setString(2, data.getDepartment());
            ps.setInt(3, data.getEmployeesID());
            ps.setString(4, data.getEmployeesFullName());

            // Convert LocalDateTime to java.sql.Timestamp
             LocalTime localTime = data.getAmTimeIn();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);

            ps.setTime(5, sqlTime);

            ps.executeUpdate(); // Execute the update
            JOptionPane.showMessageDialog(null, "Success");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void amtimeOut(ModelAttendance data) {
    try {
        if (existingAmTimeIn(data)) {
            if (!AlreadyAmTimeout(data)) {
                LocalTime localDateTime = data.getAmTimeIn();
                java.sql.Time sqlTimestamp = java.sql.Time.valueOf(localDateTime);

                String sql = "UPDATE attendance_data SET AmTimeOut = ? WHERE EmployeesID = ? AND DATE(AmTimeIn) = CURDATE() AND AmTimeOut IS NULL";
                ps = prepareStatement(sql);
                ps.setTime(1, sqlTimestamp);
                ps.setInt(2, data.getEmployeesID());

                int rowsUpdated = ps.executeUpdate(); // Execute the update
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Success");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching record found to update");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Already timed out");
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have AM time in");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void pmtimeIn(ModelAttendance data) {
    try {
        BufferedImage bufferedImage = createBufferedImageWithWhiteBackground((ImageIcon) data.getEmployeesImage());
        String base64Image = encodeImageToBase64(bufferedImage);
        LocalTime localDateTime = LocalTime.now(); // Assuming you want to use current time for PmTimeIn
        java.sql.Time sqlTimestamp = java.sql.Time.valueOf(localDateTime);
 
        
        if (existingPmTimeIn(data)) {
            JOptionPane.showMessageDialog(null, "already time in");
            return;
        }
        if (!existingAmTimeIn(data)) {
            // If AM time in data does not exist, add a new record
            String sql = "INSERT INTO attendance_data (EmployeesImage,DepartMent, EmployeesID, EmployeesFullName, AmTimeIn, PmTimeIn, DateUpdated) VALUES (?,?, ?, ?, NULL, ?, CURDATE()) ";
            ps = prepareStatement(sql);
            ps.setString(1, base64Image);
            ps.setString(2, data.getDepartment());
            ps.setInt(3, data.getEmployeesID());
            ps.setString(4, data.getEmployeesFullName());
            ps.setTime(5, sqlTimestamp);

            int rowsInserted = ps.executeUpdate(); // Execute the insert
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Time in Success");
            } else {
                JOptionPane.showMessageDialog(null, "Insert failed");
            }
            return; // Exit the method after adding the record
        }

       

        if (existingEmployee(data)) {
            String sql = "UPDATE attendance_data SET PmTimeIn = ?, DateUpdated = CURDATE() WHERE EmployeesID = ? AND AmTimeIn  IS NOT NULL AND PmTimeIn IS NULL ";
            ps = prepareStatement(sql);

            ps.setTime(1, sqlTimestamp);
            ps.setInt(2, data.getEmployeesID());

            int rowsUpdated = ps.executeUpdate(); // Execute the update
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Update Success");
            } else {
                JOptionPane.showMessageDialog(null, "No matching record found to update");
            }
        } else {
            String sql = "INSERT INTO attendance_data (EmployeesImage,DepartMent, EmployeesID, EmployeesFullName, PmTimeIn, DateUpdated) VALUES (?,?, ?, ?, ?, CURDATE()) ";
            ps = prepareStatement(sql);
            ps.setString(1, base64Image);
            ps.setString(2, data.getDepartment());
            ps.setInt(3, data.getEmployeesID());
            ps.setString(4, data.getEmployeesFullName());
            ps.setTime(5, sqlTimestamp);

            int rowsInserted = ps.executeUpdate(); // Execute the insert
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Time in Success");
            } else {
                JOptionPane.showMessageDialog(null, "Insert failed");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean existingAmTimeIn(ModelAttendance data) {
    String sql = "SELECT EmployeesID FROM attendance_data WHERE EmployeesID = ? AND DATE(DateCreated) = CURDATE() AND AmTimeIn IS NOT NULL";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getEmployeesID());
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean existingPmTimeIn(ModelAttendance data) {
    String sql = "SELECT EmployeesID FROM attendance_data WHERE EmployeesID = ? AND DATE(DateCreated) = CURDATE() AND PmTimeIn IS NOT NULL";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getEmployeesID());
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public void pmtimeOut(ModelAttendance data) {
    try {
        if (existingPmTimeIn(data)) {
            if (!existingPmTimeOut(data)) {
                LocalTime localDateTime = LocalTime.now(); // Assuming you want to use current time for PmTimeOut
                java.sql.Time sqlTimestamp = java.sql.Time.valueOf(localDateTime);

                String sql = "UPDATE attendance_data SET PmTimeOut = ? WHERE EmployeesID = ? AND PmTimeIn IS NOT NULL AND PmTimeOut IS NULL";
                ps = prepareStatement(sql);
                ps.setTime(1, sqlTimestamp);
                ps.setInt(2, data.getEmployeesID());

                int rowsUpdated = ps.executeUpdate(); // Execute the update
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Success");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching record found to update");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Already timed out");
            }
        } else {
            JOptionPane.showMessageDialog(null, "You do not have PM time in");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean existingPmTimeOut(ModelAttendance data) {
    String sql = "SELECT EmployeesID FROM attendance_data WHERE EmployeesID = ? AND DATE(DateCreated) = CURDATE() AND PmTimeOut IS NOT NULL";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getEmployeesID());
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean AlreadyAmTimeout(ModelAttendance data) {
    String sql = "SELECT EmployeesID FROM attendance_data WHERE EmployeesID = ? AND Date(DateCreated) =CURDATE() AND AmTimeOut IS NOT NULL";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getEmployeesID());
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean AlreadyPmTimeout(ModelAttendance data) {
    String sql = "SELECT EmployeesID FROM attendance_data WHERE EmployeesID = ? PmTimeOut IS NOT NULL AND DateCreated = CURDATE()";
    try (PreparedStatement ps = prepareStatement(sql)) {
        ps.setInt(1, data.getEmployeesID());
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean existingEmployee(ModelAttendance data) {
        String sql = "SELECT * FROM attendance_data WHERE EmployeesID = ? AND DATE(DateCreated) = CURDATE()";
        try (PreparedStatement ps = prepareStatement(sql)) {
            ps.setInt(1, data.getEmployeesID());
           rs = ps.executeQuery() ;
           
                return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
       private BufferedImage createBufferedImageWithWhiteBackground(ImageIcon imageIcon) {
        BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2d.drawImage(imageIcon.getImage(), 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }

    private BufferedImage createBufferedImageWithWhiteBackground(BufferedImage bufferedImage) {
        BufferedImage imageWithWhiteBackground = new BufferedImage(
                bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imageWithWhiteBackground.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageWithWhiteBackground.getWidth(), imageWithWhiteBackground.getHeight());
        g2d.drawImage(bufferedImage, 0, 0, null);
        g2d.dispose();
        return imageWithWhiteBackground;
    }

    private String encodeImageToBase64(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        byte[] imageInByte = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageInByte);
    }
}
