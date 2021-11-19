package se.yrgo.oop.game.things;

import se.yrgo.oop.game.Player;

public class DustyThing implements Thing {

    private Thing thing;

    public DustyThing(Thing thing) {
        this.thing = thing;
    }

    @Override
    public String getDescription() {
        return "a very dusty " + thing.getDescription();
    }

    @Override
    public void use(Player player) {
        System.out.println("You remove all the dust from the " + thing.getDescription());
        player.getCurrentRoom().removeThing(this);
        player.getCurrentRoom().addThing(thing);
    }
}
