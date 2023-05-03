package org.example.functions;

import java.sql.*;

public class Functions {

    private PreparedStatement stmt;

    public void information(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        int medical_card_number = rs.getInt("medical_card_number");
        String diagnosis = rs.getString("diagnosis");
        System.out.println("Id = " + id + " name = " + name + " address = " + address + " phone = " + phone + " medical_card_number = " + medical_card_number + " diagnosis = " + diagnosis);
    }

    public void searchAndSortMedicalCardNumber(String diagnosis) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT * FROM patients WHERE diagnosis = ? ORDER BY medical_card_number ASC";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, diagnosis);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    information(rs);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchFullNameAndPhone(int startCardNumber, int endCardNumber) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT * FROM patients WHERE medical_card_number BETWEEN " + startCardNumber + " AND " + endCardNumber;
            PreparedStatement stmt = connection.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    information(rs);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void foundPatients(String phonePrefix) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT * FROM patients WHERE phone LIKE '" + phonePrefix + "%'";
            PreparedStatement stmt = connection.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    information(rs);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListOfPatientDiagnoses() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT diagnosis, COUNT(*) AS count FROM patients GROUP BY diagnosis ORDER BY count DESC";
            PreparedStatement stmt = connection.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String diagnosis = rs.getString("diagnosis");
                    System.out.println("diagnosis = " + diagnosis);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAllDiagnosisPatients() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT DISTINCT diagnosis FROM patients";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String diagnosis = rs.getString("diagnosis");
                System.out.println("diagnosis = " + diagnosis);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fullNameSort() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/register", "patient", "123");
            String sql = "SELECT diagnosis, COUNT(*) AS count FROM patients GROUP BY diagnosis";
            PreparedStatement stmt = connection.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String diagnosis = rs.getString("diagnosis");
                    System.out.println("diagnosis = " + diagnosis);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}