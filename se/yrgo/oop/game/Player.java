package se.yrgo.oop.game;

/**
 * This class represents the player
 * 
 */
public class Player {
    private String name;
    private int health;
    private Room currentRoom;

    public Player(String name, Room startRoom) {
        this.name = name;
        this.health = 100;
        this.currentRoom = startRoom;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void increaseHealth(int amount) {
        if (amount > 0) {
            health += amount;
            health = Math.min(health, 100);
        }
    }

    public void decreaseHealth(int amount) {
        if (amount > 0) {
            health -= amount;
            health = Math.max(health, 0);
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Player [currentRoom=" + currentRoom + ", health=" + health + ", name=" + name + "]";
    }
}
