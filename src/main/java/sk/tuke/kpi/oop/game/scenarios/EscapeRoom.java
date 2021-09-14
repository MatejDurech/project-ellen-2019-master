package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;

import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;


import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.LockedDoor;


public class  EscapeRoom implements SceneListener {
    private Ripley ripley;

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@NotNull String type, String name) {
            if (name.equals("ellen")) {
                return new Ripley();
            } else if (name.equals("energy")) {
                return new Energy();
            } else if (name.equals("ammo")) {
                return new Ammo();
            } else if(name.equals("alien")){
                return new Alien(50, new RandomlyMoving());
            }else {
                return null;
            }
        }
    }

    public void sceneInitialized(@NotNull Scene scene) {
        ripley = (Ripley) scene.getFirstActorByName("Ellen");
        LockedDoor door = (LockedDoor) scene.getFirstActorByName("LockedDoor");
        assert door != null;
        scene.follow(ripley);
        scene.getGame().pushActorContainer(ripley.getBackpack());
        sceneUpdating(scene);
    }
    @Override
    public void sceneCreated(@NotNull Scene scene) {

    }


    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("|", 95, yTextPos);
        scene.getGame().getOverlay().drawText("ENERGY:" + ripley.getHealth().getValue(), 110, yTextPos);
        scene.getGame().getOverlay().drawText("|", 220, yTextPos);
        scene.getGame().getOverlay().drawText("AMMO: " + ripley.getFirearm().getAmmo(), 235, yTextPos);
        scene.getGame().pushActorContainer(ripley.getBackpack());
        if ((ripley.getHealth().getValue() == 0)) {
            scene.getGame().getOverlay().drawText("Ripley Died!", 100, 100);
        }
//        //////////////////////////////////////////////////
//        //automaticke branie
//        List<Actor> actors = scene.getActors();
//        for(Actor temp : actors){
//            if(temp instanceof Energy){
//                if(Energy.class.cast(temp).intersects(ripley)){
//                    new Use<>((Energy)temp).scheduleFor(ripley);
//                }
//                continue;
//            }
//            if(temp instanceof Ammo){
//                if(Ammo.class.cast(temp).intersects(ripley)){
//                    new Use<>((Ammo)temp).scheduleFor(ripley);
//                }
//            }
//        }
//        ////////////////////////////////////////////////////
    }
}
