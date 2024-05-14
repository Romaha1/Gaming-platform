package org.example;

import java.util.Scanner;
public class Player {
    private int id;
    private String playerName;
    private int playerMoney;
    private int betAmount;
    private int betNumber;


    Player(int id, String playerName, int playerMoney) {
        this.id = id;
        this.playerName = playerName;
        this.playerMoney = playerMoney;
    }

    Player(String playerName, int playerMoney) {
        this.playerName = playerName;
        this.playerMoney = playerMoney;
    }

    void makeBet(Scanner scanner) {
        System.out.print(playerName + ", введіть суму вашої ставки: ");
        int amount = scanner.nextInt();

        if (amount <= playerMoney && amount > 0) {
            this.betAmount = amount;
            playerMoney -= amount;
            System.out.println(playerName + " зробив ставку на " + amount + " $.");
        } else {
            System.out.println(playerName + " не має достатньо грошей або введено неправильну суму для ставки.");
            makeBet(scanner); // Рекурсивний виклик для повторення введення ставки
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(int betNumber) {
        this.betNumber = betNumber;
    }
}
