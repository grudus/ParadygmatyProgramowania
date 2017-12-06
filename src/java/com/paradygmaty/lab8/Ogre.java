package com.paradygmaty.lab8;

public class Ogre extends Entity implements Attackable{

    public Ogre(String name) {
        super(name);
    }


    @Override
    public void interact(Entity other) {
        System.out.println("WALCZ ZE MNO");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ogre ogre = (Ogre) o;
        return ogre.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public void attack(Entity other) {
        System.out.println("UI UI UUUUUI");
    }
}
