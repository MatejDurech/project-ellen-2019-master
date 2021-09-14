package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.List;


public class KeeperController implements KeyboardListener {
    private Keeper keeper;
    public KeeperController(Keeper keeper){
        this.keeper = keeper;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if(Input.Key.ENTER == key){
            new Take<>().scheduleFor(keeper);
        }else if(Input.Key.BACKSPACE == key){
            new Drop<>().scheduleFor(keeper);
        }else if(Input.Key.S == key){
            new Shift<>().scheduleFor(keeper);
        }else if(Input.Key.U == key){
            help();
        }else if(Input.Key.B == key && keeper.getBackpack().getSize() > 0 && keeper.getBackpack().peek() instanceof Usable) {
            new Use<>((Usable<?>) keeper.getBackpack().peek()).scheduleForIntersectingWith(keeper);
        }
    }


    private void help(){
        Actor usableActor = null;
        List<Actor> list = keeper.getScene().getActors();
        for(Actor actor : list){
            if(actor.intersects(keeper)&& actor instanceof Usable && actor.intersects(keeper) && actor != keeper){
                usableActor = actor;
            }
        }
        if(usableActor != null){
            new Use<>((Usable<?>) usableActor).scheduleForIntersectingWith(keeper);
        }
    }
}

