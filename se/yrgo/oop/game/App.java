package se.yrgo.oop.game;

import java.util.Scanner;
import se.yrgo.oop.game.things.BoringThing;
import se.yrgo.oop.game.things.CoffePot;
import se.yrgo.oop.game.things.DustyThing;
import se.yrgo.oop.game.things.Flower;
import se.yrgo.oop.game.things.Thing;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Room startRoom = createMap();

            System.out.println("Welcome to Dungeon Explorer!");
            System.out.println("What is your name?");
            String name = scanner.nextLine();

            Player player = new Player(name, startRoom);

            System.out.printf("Welcome %s! We hope you survive.%n", player.getName());

            while (player.getHealth() > 0) {
                printRoomInfo(player);

                if (!doAction(scanner, player)) {
                    break;
                }

                player.decreaseHealth(1);
            }

            if (player.getHealth() < 1) {
                System.out.println("I'm sorry but you're dead...");
            }
            else {
                System.out.println("Thanks for playing. You survived.");
            }
        }
    }

    /**
     * Create the dungeon layout.
     * 
     * @return the starting room
     */
    private static Room createMap() {
        Room room1 = new Room("Grandma's nice kitchen");

        Thing coffePot1 = new CoffePot("grandma's");
        Thing coffePot2 = new CoffePot("grandpa's");
        room1.addThing(coffePot1);
        room1.addThing(coffePot2);

        Room room2 = new Room("The nasty cellar");

        Thing devilStatue = new DustyThing(new BoringThing("horrible statue of an imp"));
        room2.addThing(devilStatue);

        Room room3 = new Room("The lovely garden");

        Thing flower1 = new Flower("red");
        Thing flower2 = new Flower("white");

        room3.addThing(flower1);
        room3.addThing(flower2);

        room1.addConnectedRoom(room2);
        room1.addConnectedRoom(room3);

        return room1;
    }

    /**
     * Read the next action from the given scanner and perform that
     * action.
     * 
     * @param scanner the scanner to read from
     * @param player the player performing the action
     * @return true if the player wants to continue playing
     */
    private static boolean doAction(Scanner scanner, Player player) {
        System.out.println("What do you want to do?");
        String action = scanner.nextLine();

        try {
            if (action.startsWith("go to")) {
                goToRoom(action, player);
            }
            else if (action.startsWith("use")) {
                useThing(action, player);
            }
            else if (action.equals("quit") || action.equals("exit")) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("I'm sorry, you can't do that.");
            player.decreaseHealth(2);
        }

        // add a newline to make things easier to read
        System.out.println();

        return true;
    }

    /**
     * Print information about the player's current room.
     * 
     * @param player the player who's room we should print out
     */
    private static void printRoomInfo(Player player) {
        Room currentRoom = player.getCurrentRoom();
        System.out.printf("You are now in %s.%n", currentRoom.getDescription());

        System.out.printf("Your health is at level %d.%n", player.getHealth());

        if (currentRoom.getThings().isEmpty()) {
            System.out.println("The room is empty.");
        } else {
            System.out.println("Here you can see:");
            int count = 1;
            for (Thing thing : currentRoom.getThings()) {
                System.out.printf("\t%d. %s%n", count, thing.getDescription());
                count++;
            }
        }

        System.out.println("You can go to:");
        int count = 1;
        for (Room room : currentRoom.getConnectedRooms()) {
            System.out.printf("\t%d. %s%n", count, room.getDescription());
            count++;
        }
    }

    private static void goToRoom(String action, Player player) {
        Room currentRoom = player.getCurrentRoom();
        String choice = action.replaceFirst("^\\D*", "");
        int roomIndex = Integer.parseInt(choice) - 1;
        Room goTo = currentRoom.getConnectedRooms().get(roomIndex);
        player.setCurrentRoom(goTo);
    }

    private static void useThing(String action, Player player) {
        Room currentRoom = player.getCurrentRoom();
        String choice = action.replaceFirst("^\\D*", "");
        int thingIndex = Integer.parseInt(choice) - 1;
        Thing thing = currentRoom.getThings().get(thingIndex);
        thing.use(player);
    }
}
