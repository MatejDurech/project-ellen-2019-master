package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

        private int increase;
        public PerpetualReactorHeating(int increase){
            super();
            this.increase = increase;
    }

    public int getIncrease() {
        return increase;
    }

    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            getActor().increaseTemperature(getIncrease());
        }
    }
}
