package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private Backpack backpack = new Backpack("Ripley's backpack", 10);
    private Health health;
    private Firearm gun;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("Ripley died", Ripley.class);

    public Ripley() {
        super("Ellen");
        Animation player = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(player);
        stoppedMoving();
        health = new Health(100);
        gun = new Gun(10,100);
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().pause();
    }


    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.gun = weapon;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        Disposable movController = scene.getInput().registerListener(new MovableController(this));
        Disposable kepController = scene.getInput().registerListener(new KeeperController(this));
        Disposable shoController = scene.getInput().registerListener(new ShooterController(this));
        health.onExhaustion(
            () -> {
                setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
                getScene().getMessageBus().publish(RIPLEY_DIED, this);

            }
        );
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> kepController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shoController.dispose());
    }
}

