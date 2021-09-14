package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> list;
    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        list = new ArrayList<>();
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(list);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (capacity == getSize()) {
            throw new IllegalStateException(this.getName() + "is full");
        } else {
            list.add(actor);
        }
    }


    @Override
    public void remove(@NotNull Collectible actor) {
        list.remove(actor);
    }


    @Nullable
    @Override
    public Collectible peek() {
        if(getSize() > 0) {
            return list.get(list.size() - 1);
        }else{
            return null;
        }
    }

    @Override
    public void shift() {
        Collections.rotate(list, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return list.listIterator();
    }
}
