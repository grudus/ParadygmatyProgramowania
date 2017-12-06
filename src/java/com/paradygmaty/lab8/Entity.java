package com.paradygmaty.lab8;

public abstract class Entity  {
    protected final String name;

    protected Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void interact(Entity other);
}
