package se.yrgo.oop.game.things;

import se.yrgo.oop.game.Player;

public class Flower implements Thing {
    private String color;

    public Flower(String color) {
        this.color = color;
    }

    @Override
    public String getDescription() {
        return String.format("a nice %s flower", color);
    }

    @Override
    public void use(Player player) {
        System.out.println("It smells horrible! It must be poisonous.");
        player.decreaseHealth(10);
    }
}
