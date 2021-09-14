package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;


public class Main {
    public static void main(String[] args) {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 800);

        Game game = new GameApplication(windowSetup);

        Scene scene = new World("world","maps/escape-room.tmx", new EscapeRoom.Factory());

        game.addScene(scene);

        EscapeRoom escapeRoom = new EscapeRoom();

        scene.addListener(escapeRoom);

        scene.getInput().onKeyPressed(key -> {
            if (key == Input.Key.ESCAPE) {
                scene.getGame().stop();
            }
        });


        game.start();
    }
}

