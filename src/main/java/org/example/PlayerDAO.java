package org.example;

import java.util.List;

public interface PlayerDAO {
    List<Player> getPlayers();
    void insert(Player player);
    void update(Player player);
}
