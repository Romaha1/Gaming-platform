package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final PlayerDAO playerDAO = new PlayerDAOImpl();
    private final CroupierDAO croupierDAO = new CroupierDAOImpl();
    private static final Croupier croupier = new Croupier();
    private Player currentPlayer = null;

    public Menu() {
        try {
            Database.init();
            int totalMoney = croupierDAO.getTotalMoney();

            if (totalMoney < 0 || totalMoney == 0) {
                croupier.setTotalMoney(1000000);
                croupierDAO.setTotalMoney(1000000);
            } else {
                croupier.setTotalMoney(croupierDAO.getTotalMoney());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Вибрати іншого гравця");
            System.out.println("2. Додати гравця");
            System.out.println("3. Зіграти за обраного гравця");
            System.out.println("0. Вийти");

            String action = scanner.next();

            switch (action) {
                case "1":
                    getListOfPlayers();
                    break;
                case "2":
                    addNewPlayer();
                    break;
                case "3":
                    makeBet();
                    break;
                case "0":
                    flag = false;
                    break;
                default:
                    System.out.println("Невідома команда, повторіть ще раз\n");
                    break;
            }
        }
    }

    private void getListOfPlayers() {
        List<Player> listOfPlayers = playerDAO.getPlayers();

        if (listOfPlayers.isEmpty()) {
            System.out.println("Список гравців пустий, добавте нового\n");
        }

        for (Player player : listOfPlayers) {
            System.out.printf("%d. Імя: %s, Гроші: %d\n", player.getId(), player.getPlayerName(), player.getPlayerMoney());
        }

        int selectedPlayer = scanner.nextInt();

        try {
            currentPlayer = listOfPlayers.get(selectedPlayer - 1);
        } catch (Exception ignored) {
            System.out.println("Помилка, вибраного гравця нема\n");
        }
    }

    private void addNewPlayer() {
        System.out.println("Введіть імя нового гравця: ");
        String playerName = scanner.next();
        System.out.println("Введіть скільки грошей буде мати гравець: ");
        int playMoney = scanner.nextInt();

        Player player = new Player(playerName, playMoney);

        playerDAO.insert(player);
        System.out.println("Новий гравець успішно доданий!\n");
    }

    private void makeBet() {
        if (currentPlayer == null) {
            System.out.println("Помилка, не вибрано гравця\n");
            return;
        }

        currentPlayer.makeBet(scanner);

        System.out.print(currentPlayer.getPlayerName() + ", введіть число на яке ви хочете зробити ставку: ");

        int numberPlayer;
        try {
            numberPlayer = scanner.nextInt();
        } catch (Exception ignored) {
            System.out.println("Помилка, введіть число\n");
            return;
        }

            currentPlayer.setBetNumber(numberPlayer);
            int winningNumber = croupier.spinWheel();

            handleWinning(currentPlayer, winningNumber);

            System.out.println("Баланс казино: " + croupier.getTotalMoney() + " $.");
            System.out.println(currentPlayer.getPlayerName() + " має " + currentPlayer.getPlayerMoney() + " $.\n");

            croupierDAO.setTotalMoney(croupier.getTotalMoney());
            playerDAO.update(currentPlayer);
    }

    private static void handleWinning(Player player, int winningNumber) {
        if (winningNumber == player.getBetNumber()) {
            int payout = player.getBetAmount() * 36;
            player.setPlayerMoney(player.getPlayerMoney() + payout);
            croupier.setTotalMoney(croupier.getTotalMoney() - payout);
            System.out.println(player.getPlayerName() + " виграв " + payout + " $!");
        } else {
            croupier.setTotalMoney(croupier.getTotalMoney() + player.getBetAmount());
            System.out.println(player.getPlayerName() + " програв " + player.getBetAmount() + " $.\n");
        }
    }




}
