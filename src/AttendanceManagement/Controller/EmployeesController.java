package AttendanceManagement.Controller;

import AttendanceManagement.Forms.EmployeesProfileForms;
import AttendanceManagement.JDBC.DatabaseConnection;
import AttendanceManagement.Model.ModelEmployees;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EmployeesController {

    private PreparedStatement ps;
    private ResultSet rs;
    private EmployeesProfileForms employeesProfileForms;

    public EmployeesController(EmployeesProfileForms employeesProfileForms) {
        this.employeesProfileForms = employeesProfileForms;
    }

    public EmployeesController() {
    }

    public void addEmployee(ModelEmployees data) throws IOException {
        String sql = "INSERT INTO employees_data (IDnumber, FirstName, MiddleName, LastName, Position, Department, DateAssumed, PlantillaNumber, EmployeesImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = prepareStatement(sql)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(data.getDateAssumed());

            // Convert ImageIcon to BufferedImage with white background
            BufferedImage bufferedImage = createBufferedImageWithWhiteBackground((ImageIcon) data.getEmployeesImage());

            // Convert to base64 string
            String base64Image = encodeImageToBase64(bufferedImage);

            if (existingEmployee(data)) {
                JOptionPane.showMessageDialog(null, "Employee ID already exists!");
            } else {
                if (ps != null) {
                    ps.setInt(1, data.getId());
                    ps.setString(2, data.getFirstName());
                    ps.setString(3, data.getMiddleName());
                    ps.setString(4, data.getLastName());
                    ps.setString(5, data.getPosition());
                    ps.setString(6, data.getDepartment());
                    ps.setDate(7, java.sql.Date.valueOf(formattedDate));
                    ps.setInt(8, data.getPlantillaNumber());
                    ps.setString(9, base64Image);

                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeesData(ModelEmployees data) {
        try {
            String sql = "UPDATE employees_data SET IDnumber = ?, FirstName = ?, MiddleName = ?, LastName = ?, Position = ?, Department = ?, DateAssumed = ?, PlantillaNumber = ?, EmployeesImage = ? WHERE ID = ?";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(data.getDateAssumed());

            BufferedImage bufferedImage = createBufferedImageWithWhiteBackground((ImageIcon) data.getEmployeesImage());
            String base64Image = encodeImageToBase64(bufferedImage);

            
                try (PreparedStatement ps = prepareStatement(sql)) {
                    if (ps != null) {
                        ps.setInt(1, data.getId());
                        ps.setString(2, data.getFirstName());
                        ps.setString(3, data.getMiddleName());
                        ps.setString(4, data.getLastName());
                        ps.setString(5, data.getPosition());
                        ps.setString(6, data.getDepartment());
                        ps.setDate(7, java.sql.Date.valueOf(formattedDate));
                        ps.setInt(8, data.getPlantillaNumber());
                        ps.setString(9, base64Image);
                        ps.setInt(10, data.getIdData());

                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Updated!");
                    
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeesData(ModelEmployees data) {
        try {
            String sql = "UPDATE employees_data SET DateDeleted = CURRENT_DATE, DateUpdated = CURRENT_DATE WHERE ID = ?";
            try (PreparedStatement ps = prepareStatement(sql)) {
                ps.setInt(1, data.getIdData());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existingEmployee(ModelEmployees data) {
        String sql = "SELECT IDnumber FROM employees_data WHERE IDnumber = ? AND DateDeleted IS NULL";
        try (PreparedStatement ps = prepareStatement(sql)) {
            ps.setInt(1, data.getId());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ModelEmployees SearchEmployees(int EmployeesID){
        try {
            String sql = "SELECT * FROM employees_data WHERE IDnumber = ? AND DateDeleted IS NULL";
           ps = getConnection().prepareStatement(sql);
           ps.setInt(1, EmployeesID);
            rs = ps.executeQuery();
    
            if (rs.next()) {
                 String base64Image = rs.getString("EmployeesImage");
                byte[] imageData = Base64.getDecoder().decode(base64Image);

                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
                BufferedImage imageWithWhiteBackground = createBufferedImageWithWhiteBackground(bufferedImage);

                ImageIcon whiteBackgroundIcon = new ImageIcon(imageWithWhiteBackground);         
                ModelEmployees employeesProfile = new ModelEmployees( rs.getInt("IDnumber"),  rs.getString("FirstName"),  rs.getString("MiddleName"), rs.getString("LastName"),rs.getString("Position"),rs.getString("Department"),rs.getDate("DateAssumed"),rs.getInt("PlantillaNumber"),whiteBackgroundIcon);
                employeesProfile.setIdData(rs.getInt("ID"));
                return employeesProfile;
            }else{
                JOptionPane.showMessageDialog(null, "Invalid ID!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void populateEmployeesProfile() {
        String sql = "SELECT * FROM employees_data WHERE DateDeleted IS NULL";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String base64Image = rs.getString("EmployeesImage");
                byte[] imageData = Base64.getDecoder().decode(base64Image);

                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
                BufferedImage imageWithWhiteBackground = createBufferedImageWithWhiteBackground(bufferedImage);

                ImageIcon whiteBackgroundIcon = new ImageIcon(imageWithWhiteBackground);         
                ModelEmployees employeesProfile = new ModelEmployees( rs.getInt("IDnumber"),  rs.getString("FirstName"),  rs.getString("MiddleName"), rs.getString("LastName"),rs.getString("Position"),rs.getString("Department"),rs.getDate("DateAssumed"),rs.getInt("PlantillaNumber"),whiteBackgroundIcon);
                employeesProfile.setIdData(rs.getInt("ID"));
                employeesProfileForms.addEmployees(employeesProfile);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isNumeric(String str) {
    if (str == null) {
        return false;
    }
    try {
        Integer.parseInt(str);
    } catch (NumberFormatException e) {
        return false;
    }
    return true;
}
       public void searchEmployeesProfile(String Search) {
           
        String sql = "SELECT * FROM employees_data WHERE DateDeleted IS NULL  AND (IDnumber LIKE ? or FirstName LIKE ? or LastName LIKE ?)";
        try {
            
      ps = prepareStatement(sql);
       String searchPattern = "%" + Search + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
                rs =  ps.executeQuery();
            while (rs.next()) {
                String base64Image = rs.getString("EmployeesImage");
                byte[] imageData = Base64.getDecoder().decode(base64Image);

                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
                BufferedImage imageWithWhiteBackground = createBufferedImageWithWhiteBackground(bufferedImage);

                ImageIcon whiteBackgroundIcon = new ImageIcon(imageWithWhiteBackground);         
                ModelEmployees employeesProfile = new ModelEmployees( rs.getInt("IDnumber"),  rs.getString("FirstName"),  rs.getString("MiddleName"), rs.getString("LastName"),rs.getString("Position"),rs.getString("Department"),rs.getDate("DateAssumed"),rs.getInt("PlantillaNumber"),whiteBackgroundIcon);
                employeesProfile.setIdData(rs.getInt("ID"));
                employeesProfileForms.addEmployees(employeesProfile);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
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
