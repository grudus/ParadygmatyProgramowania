package com.paradygmaty.lab8;

public class GummiBearFactory extends AbstractEntityFactory {

    @Override
    public Entity create(String name) {
        switch (name) {
            case "Kabi":
            case "Tami":
            case "Sani":
                return new GummiBear(name, 5);
            case "Bunia":
                return new GummiBear(name, 12);
            default:
                return new GummiBear(name);
        }
    }
}
