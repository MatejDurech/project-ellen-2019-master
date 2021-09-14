package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.*;



public class FirstSteps implements SceneListener {
    private Ripley ripley = new Ripley();

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Energy energy = new Energy();
        Ammo ammo = new Ammo();
        Energy energy1 = new Energy();
        Wrench wrench1 = new Wrench();
        Wrench wrench = new Wrench();
        Wrench wrench2 = new Wrench();
        Hammer hammer = new Hammer();
        scene.addActor(ripley, 150, 100);
        scene.addActor(energy, 150, 300);
        scene.addActor(ammo, 0, 120);
        scene.addActor(energy1, 15, 356);
        scene.addActor(wrench1, 0, 120);
//        List<Actor> actors = scene.getActors();
//        for(Actor temp : actors){
//            if(temp instanceof Energy){
////                new When<>(
////                    () -> Energy.class.cast(temp).intersects(ripley),
////                    new Use<>((Energy)temp)
////                ).scheduleFor(ripley);
//                if(Energy.class.cast(temp).intersects(ripley)){
//                    new Use<>((Energy)temp).scheduleFor(ripley);
//                }
//                continue;
//            }
//            if(temp instanceof Ammo){
////                new When<>(
////                    () -> Ammo.class.cast(temp).intersects(ripley),
////                    new Use<>((Ammo)temp)
////                ).scheduleFor(ripley);
//                if(Ammo.class.cast(temp).intersects(ripley)){
//                    new Use<>((Ammo)temp).scheduleFor(ripley);
//                }
//            }
//
//        }


        ripley.getBackpack().add(wrench);
        ripley.getBackpack().add(wrench2);
        ripley.getBackpack().add(hammer);
        scene.getGame().pushActorContainer(ripley.getBackpack());
        sceneUpdating(scene);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("|",95, yTextPos);
        scene.getGame().getOverlay().drawText("ENERGY: ",110, yTextPos);
        scene.getGame().getOverlay().drawText(String.valueOf(ripley.getHealth().getValue()),185, yTextPos);
        scene.getGame().getOverlay().drawText("|",220, yTextPos);
        scene.getGame().getOverlay().drawText("AMMO: ",235, yTextPos);
       // scene.getGame().getOverlay().drawText(String.valueOf(ripley.getFirearm().getAmmo()),290, yTextPos);
//        scene.getGame().pushActorContainer(ripley.getBackpack());
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
//
//
//        }
    }
}
//        Cooler cooler = new Cooler(reactor);
//        Hammer hammer = new Hammer();
//        scene.addActor(reactor, 145, 96);
//        scene.addActor(cooler, 45, 45);
//        scene.addActor(hammer, 100, 100);
//        reactor.turnOn();
//        new ActionSequence<>(
//            new Wait<>(5),
//            new Invoke<>(cooler::turnOn)
//        ).scheduleFor(cooler);
//        new When<>(
//            () -> reactor.getTemperature() >= 3000,
//            new Invoke<>(() -> reactor.repair())
//        ).scheduleFor(reactor);
