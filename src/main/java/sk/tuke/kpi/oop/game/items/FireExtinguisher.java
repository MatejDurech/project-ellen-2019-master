package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{
    public FireExtinguisher() {
        super(1);
        Animation normalAnimation = new Animation("sprites/extinguisher.png");
        setAnimation(normalAnimation);
    }

    @Override
    public void useWith(Reactor reactor) {
        if (reactor != null && reactor.extinguish()) {
            super.useWith(reactor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}

