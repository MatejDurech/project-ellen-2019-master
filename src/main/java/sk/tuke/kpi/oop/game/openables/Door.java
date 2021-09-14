package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;


public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private boolean open;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public enum Orientation {  HORIZONTAL, VERTICAL}
    private Animation animation;
    private Orientation orientation;
    public Door(String name, Orientation orientation){
        super(name);
        this.orientation = orientation;
        if(Orientation.HORIZONTAL == this.orientation){
            animation = new Animation("sprites/hdoor.png", 32, 16, 0.1f);
        }else {
            animation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);
        }
        setAnimation(animation);
        animation.pause();
        open = false;
    }
    @Override
    public void useWith(Actor actor) {
        if(isOpen()){
            close();
        }else{
            open();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        if(Orientation.VERTICAL == orientation) {
            getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(this.getPosX() / 16, (this.getPosY() / 16) + 1).setType(MapTile.Type.CLEAR);
        }
        if(Orientation.HORIZONTAL == orientation){
            getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile((this.getPosX() / 16) + 1, this.getPosY() / 16).setType(MapTile.Type.CLEAR);
        }
        getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        getScene().getMessageBus().publish(DOOR_OPENED, this);
        open = true;
    }

    @Override
    public void close() {
        if(Orientation.HORIZONTAL == orientation) {
            getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile((this.getPosX() / 16) + 1, this.getPosY() / 16).setType(MapTile.Type.WALL);
        }
        if(Orientation.VERTICAL == orientation) {
            getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(this.getPosX() / 16, (this.getPosY() / 16) + 1).setType(MapTile.Type.WALL);
        }
        getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
        open = false;
    }

    @Override
    public boolean isOpen() {
        return open;
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }
}
