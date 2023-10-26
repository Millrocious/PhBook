package com.phonebook.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    private static final String DATABASE_NAME = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static String formatPostgresDbUrl(String databaseName) {
        return String.format("jdbc:postgresql://localhost:5432/%s", databaseName);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(formatPostgresDbUrl(DATABASE_NAME), USERNAME, PASSWORD);
    }

    public static void init() {
        createTables();
        insertDefaultData();
    }

    private static void createTables() {
        try (Connection connection = JdbcUtil.getConnection()) {
            try (Statement createUserTableStatement = connection.createStatement()) {
                String createUserTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                        "id serial PRIMARY KEY," +
                        "first_name VARCHAR(255)," +
                        "last_name VARCHAR(255)," +
                        "phone_number VARCHAR(20)" +
                        ")";
                createUserTableStatement.execute(createUserTableSQL);
            }

            try (Statement createCompanyTableStatement = connection.createStatement()) {
                String createCompanyTableSQL = "CREATE TABLE IF NOT EXISTS companies (" +
                        "id serial PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "address VARCHAR(255)," +
                        "phone_number VARCHAR(20)" +
                        ")";
                createCompanyTableStatement.execute(createCompanyTableSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void insertDefaultData() {
        try (Connection connection = JdbcUtil.getConnection()) {
            try (Statement insertUserDataStatement = connection.createStatement()) {
                String insertUserDataSQL = "INSERT INTO users (first_name, last_name, phone_number) VALUES " +
                        "('John', 'Doe', '123-456-7890')," +
                        "('Jane', 'Smith', '987-654-3210')" +
                        "ON CONFLICT DO NOTHING";
                insertUserDataStatement.execute(insertUserDataSQL);
            }
            try (Statement insertCompanyDataStatement = connection.createStatement()) {
                String insertCompanyDataSQL = "INSERT INTO companies (name, address, phone_number) VALUES " +
                        "('Acme Inc.', '123 Main St', '555-123-4567')," +
                        "('TechCorp', '456 Elm St', '555-987-6543')" +
                        "ON CONFLICT DO NOTHING";
                insertCompanyDataStatement.execute(insertCompanyDataSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void clearData() {
        try (Connection connection = JdbcUtil.getConnection()) {
            try (Statement clearUsersDataStatement = connection.createStatement()) {
                String clearUsersDataSQL = "DROP TABLE users";
                clearUsersDataStatement.execute(clearUsersDataSQL);
            }
            try (Statement clearCompaniesDataStatement = connection.createStatement()) {
                String clearCompaniesDataSQL = "DROP TABLE companies";
                clearCompaniesDataStatement.execute(clearCompaniesDataSQL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
