package com.paradygmaty.lab8;

public class AbstractEntityFactoryFactory {

    public static AbstractEntityFactory create(EntityType type) {
        switch (type) {
            case OGRE: return new OgreFactory();
            case KNIGHT: return new KnightFactory();
            case GUMMI_BEAR: return new GummiBearFactory();
            default: throw new IllegalArgumentException("Co to to " + type);
        }
    }
}
