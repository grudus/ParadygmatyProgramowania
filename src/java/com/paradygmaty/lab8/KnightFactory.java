package com.paradygmaty.lab8;

public class KnightFactory extends AbstractEntityFactory {


    @Override
    public Entity create(String name) {
        switch (name) {
            case "Gregor":
                return new Knight(name, 100);
            case "Ightorn":
                return new Knight("Ksiaze " + name, -40);
            default:
                return new Knight(name, 2);
        }
    }
}
