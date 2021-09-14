//package sk.tuke.kpi.oop.game.scenarios;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import sk.tuke.kpi.gamelib.*;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.oop.game.Locker;
//import sk.tuke.kpi.oop.game.Ventilator;
//import sk.tuke.kpi.oop.game.characters.Ripley;
//import sk.tuke.kpi.oop.game.controllers.KeeperController;
//import sk.tuke.kpi.oop.game.controllers.MovableController;
//import sk.tuke.kpi.oop.game.items.AccessCard;
//import sk.tuke.kpi.oop.game.items.Energy;
//import sk.tuke.kpi.oop.game.openables.Door;
//import sk.tuke.kpi.oop.game.openables.LockedDoor;
//
//public class MissionImpossible implements SceneListener {
//    private Ripley ripley;
//
//    public static class Factory implements ActorFactory {
//        @Override
//        public @Nullable Actor create(@NotNull String type, String name) {
//            if (name.equals("ellen")) {
//                return new Ripley();
//            } else if (name.equals("energy")) {
//                return new Energy();
//            } else if (name.equals("door")) {
//                return new LockedDoor();
//            } else if (name.equals("access card")) {
//                return new AccessCard();
//            } else if (name.equals("locker")) {
//                return new Locker();
//            } else if (name.equals("ventilator")) {
//                return new Ventilator();
//            } else {
//                return null;
//            }
//        }
//    }
//}
////    public void sceneInitialized(@NotNull Scene scene) {
////        ripley = (Ripley) scene.getFirstActorByName("Ellen");
////        LockedDoor door = (LockedDoor) scene.getFirstActorByName("LockedDoor");
////        assert door != null;
////        door.lock();
////        Disposable movController = scene.getInput().registerListener(new MovableController(ripley));
////        Disposable kepController = scene.getInput().registerListener(new KeeperController(ripley));
////        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Door) -> new Loop<>(new ActionSequence<>(new Invoke<Actor>(() -> ripley.setEnergy(ripley.getEnergy() - 1)), new Wait<>(1))).scheduleOn(scene));
////
////        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movController.dispose());
////        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> kepController.dispose());
////
////        scene.follow(ripley);
////        scene.getGame().pushActorContainer(ripley.getBackpack());
////        sceneUpdating(scene);
////     }
////
////    @Override
////    public void sceneUpdating(@NotNull Scene scene) {
////        int windowHeight = scene.getGame().getWindowSetup().getHeight();
////        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
////        scene.getGame().getOverlay().drawText("|", 95, yTextPos);
////        scene.getGame().getOverlay().drawText("ENERGY:" + ripley.getEnergy(), 110, yTextPos);
////        scene.getGame().getOverlay().drawText("|", 220, yTextPos);
////        scene.getGame().getOverlay().drawText("AMMO: " + ripley.getAmmo(), 235, yTextPos);
////        scene.getGame().pushActorContainer(ripley.getBackpack());
////        if ((ripley.getEnergy() == 0)){
////            scene.getGame().getOverlay().drawText("Ripley Died!", 100, 100);
////        }
//////        //////////////////////////////////////////////////
//////        //automaticke branie
//////        List<Actor> actors = scene.getActors();
//////        for(Actor temp : actors){
//////            if(temp instanceof Energy){
//////                if(Energy.class.cast(temp).intersects(ripley)){
//////                    new Use<>((Energy)temp).scheduleFor(ripley);
//////                }
//////                continue;
//////            }
//////            if(temp instanceof Ammo){
//////                if(Ammo.class.cast(temp).intersects(ripley)){
//////                    new Use<>((Ammo)temp).scheduleFor(ripley);
//////                }
//////            }
//////        }
//////        ////////////////////////////////////////////////////
////    }
////}
//
