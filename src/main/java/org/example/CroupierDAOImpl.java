package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CroupierDAOImpl implements CroupierDAO {
    @Override
    public int getTotalMoney() {
        try (Connection connection = Database.getConnection()) {
            int totalMoney = 0;

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOTAL_MONEY " +
                    "FROM CROUPIER");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalMoney = resultSet.getInt("TOTAL_MONEY");
            }

            return totalMoney;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return 0;
        }
    }

    @Override
    public void updateTotalMoney(int totalMoney) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE CROUPIER SET TOTAL_MONEY = ?");
            preparedStatement.setInt(1, totalMoney);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    @Override
    public void setTotalMoney(int totalMoney) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO CROUPIER(TOTAL_MONEY) VALUES (?)");
            preparedStatement.setInt(1, totalMoney);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
