package sk.tuke.kpi.oop.game.items;


public class Mjolnir extends Hammer {
    public Mjolnir() {
        super();
        setRemainingUses(4);
    }
    /*@Override
    public void useWith(Reactor reactor) {
        if(reactor != null ){
            super.useWith(reactor);
            reactor.extinguish(reactor);
        }
    }

     */
}
