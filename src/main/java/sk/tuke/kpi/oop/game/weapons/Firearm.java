package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int ammo;
    private int maxAmmo;

    public Firearm(int initAmmo, int maxAmmo){
        this.ammo = initAmmo;
        this.maxAmmo = maxAmmo;
    }
    public Firearm(int initAmmo){
        this(initAmmo, initAmmo);
    }

    public int getAmmo() {
        return ammo;
    }

    public void reload(int newAmmo){
        if(ammo + newAmmo > maxAmmo) {
            ammo = maxAmmo;
        }else{
            ammo += newAmmo;
        }
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }
    public Fireable fire() {
        if (ammo == 0) {
            return null;
        } else {
            reload(-1);
           return createBullet();
        }
    }
    public abstract Fireable createBullet();
}

