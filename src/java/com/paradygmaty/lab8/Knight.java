package com.paradygmaty.lab8;

import java.util.Objects;

public class Knight extends Entity implements Talkable, Attackable {

    private final int respectLevel;
    
    public Knight(String name, int respectLevel) {
        super(name);
        this.respectLevel = respectLevel;
    }

    public Knight(String name) {
        this(name, 0);
    }

    @Override
    public void interact(Entity other) {
        if (other instanceof Ogre)
            System.out.println("ZABIJE CIE WSTRETNY TY POTWORZE!");
        else {
            System.out.print("WITAJ, PRZYJACIELU! ");
            talk();
        }
    }
    
    public boolean isRespectable() {
        return respectLevel > 10;
    }

    @Override
    public void talk() {
        System.out.printf("JAM JEST DZIELNY RYCERZ %s!\n", name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return knight.getName().equals(name) && knight.respectLevel == respectLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(respectLevel, name);
    }

    @Override
    public void attack(Entity other) {
        System.out.println("ZABIJAM CIE " + other.getName());
    }
}
