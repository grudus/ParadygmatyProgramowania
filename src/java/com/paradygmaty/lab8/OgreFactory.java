package com.paradygmaty.lab8;

public class OgreFactory extends AbstractEntityFactory {

    @Override
    public Entity create(String name) {
        return new Ogre(name);
    }
}
