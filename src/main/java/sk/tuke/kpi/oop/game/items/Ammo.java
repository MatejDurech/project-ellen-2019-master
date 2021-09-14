package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;


public class Ammo extends AbstractActor implements Usable<Armed>{
    public Ammo() {
        Animation normalAnimation = new Animation("sprites/ammo.png", 16, 16);
        setAnimation(normalAnimation);
    }

    @Override
    public void useWith(Armed actor) {
        if (actor != null){
            actor.getFirearm().reload(10);
            @Nullable Scene scene = getScene();
            assert scene != null;
            scene.removeActor(this);
        }

    }


    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}

