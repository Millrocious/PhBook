package com.phonebook;

import com.phonebook.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        JdbcUtil.init();

        try (Connection connection = JdbcUtil.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                String sql = "select * from users";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " | " + rs.getString(2));
                }
            }

            try (Statement stmt = connection.createStatement()) {
                String sql = "select * from companies";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " | " + rs.getString(2));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JdbcUtil.clearData();
    }
}