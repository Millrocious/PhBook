package com.phonebook.dao;

import com.phonebook.model.Company;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class CompanyDao {
    private final Connection connection;

    public void createCompany(Company user) {
        String sql = "INSERT INTO companies (name, address, phone_number) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getCompanyById(int id) {
        String sql = "SELECT * FROM companies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCompany(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM companies";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                companies.add(mapResultSetToCompany(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void updateCompany(Company company) {
        String sql = "UPDATE companies SET name = ?, address = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setString(3, company.getPhoneNumber());
            statement.setInt(4, company.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(int id) {
        String sql = "DELETE FROM companies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Company mapResultSetToCompany(ResultSet resultSet) throws SQLException {
        return Company.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .address(resultSet.getString("address"))
                .phoneNumber(resultSet.getString("phone_number"))
                .build();
    }
}
