package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {
    @Override
    public List<Player> getPlayers() {
        try (Connection connection = Database.getConnection()) {
            List<Player> list = new ArrayList<>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ID, NAME, MONEY FROM PLAYERS");
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int playerMoney = resultSet.getInt("MONEY");

                list.add(new Player(id, name, playerMoney));
            }

            return list;
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }
    }

    @Override
    public void insert(Player player) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO PLAYERS(NAME, MONEY) VALUES (?, ?)");
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setInt(2, player.getPlayerMoney());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    @Override
    public void update(Player player) {
        try (Connection connection = Database.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PLAYERS SET MONEY = ? WHERE NAME = ?");
            preparedStatement.setInt(1, player.getPlayerMoney());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
