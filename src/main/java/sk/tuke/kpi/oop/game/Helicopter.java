package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import java.util.Objects;

public class Helicopter extends AbstractActor {
    private boolean turn;
    public Helicopter() {
        Animation helicopter = new Animation("sprites/heli.png", 64, 64);
        setAnimation(helicopter);
        turn = false;
    }

    public void searchAndDestroy(){
        if(!turn){
            turn = true;
            help();
        }

    }
    private void help(){
        new Loop<>(new Invoke<>(this::help2)).scheduleFor(this);
    }

    private void help2() {
        Player player = (Player) Objects.requireNonNull(getScene()).getFirstActorByName("Ellen");
        int width = this.getPosX();
        int hight = this.getPosY();

        assert player != null;
        if (player.getPosX() > this.getPosX() ){
            width += 1;
        }
        else {
            width += -1;
        }
        if (player.getPosY() > this.getPosY()){
            hight += 1;
        }
        else {
            hight += -1;
        }
        if (this.intersects(player)) {
            player.setEnergy(player.getEnergy() - 1);
        }

        this.setPosition(width,hight);

    }

}

