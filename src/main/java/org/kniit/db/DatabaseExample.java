package org.kniit.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "user", "pass");
            String query = "select * from newtable";
            try (Statement stmt = conn.createStatement()) {
                var i = stmt.executeUpdate("insert into newtable(name) values ('kniit')");
                System.out.println("rows updated " + i);

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println(id + " " + name);
                }
            } catch (SQLException e ) {
                throw new Error("Problem", e);
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
