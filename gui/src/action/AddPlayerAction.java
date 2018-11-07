package action;

import player.Character;
import player.Player;
import player.PlayerSystem;

public class AddPlayerAction implements IAction {
    private Character character;
    private String name;

    public AddPlayerAction(Character character, String name) {
        this.character = character;
        this.name = name;
    }

    @Override
    public void trigger() {
        Player player = new Player(name, character);
        PlayerSystem.getInstance().addPlayer(player);
    }
}
