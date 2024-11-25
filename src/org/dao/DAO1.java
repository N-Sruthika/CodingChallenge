package org.dao;

import java.sql.*;
import org.util.DBConnUtil;

public class DAO1 {
   
    public void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orders ("
                                + "oid INT PRIMARY KEY, "
                                + "odate DATE)";
        try (Connection con = DBConnUtil.getCon(); Statement stmt = con.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'orders' created successfully.");
        } catch (SQLException e) {
            System.out.println("Error while creating table");
            e.printStackTrace();
        }
    }

    
    public void insert(int oid, String odate) {
        String insertSQL = "INSERT INTO orders (oid, odate) VALUES (" + oid + ",'" + odate + "')";
        System.out.println("Executing SQL: " + insertSQL);
        try (Connection con = DBConnUtil.getCon(); Statement stmt = con.createStatement()) {
            stmt.execute(insertSQL);
            System.out.println("Inserted successfully...");
        } catch (SQLException e) {
            System.out.println("Error while inserting data");
            e.printStackTrace();
        }
    }
}
