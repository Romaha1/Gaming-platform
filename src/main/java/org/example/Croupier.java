package org.example;

class Croupier {
    private int totalMoney;

    public int spinWheel() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Помилка при затримці потоку: " + e.getMessage());
        }
        int currentWinningNumber = (int) (Math.random() * 36);
        System.out.println("На рулетці випало число " + currentWinningNumber);
        return currentWinningNumber;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
