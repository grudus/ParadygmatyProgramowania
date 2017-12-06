package com.paradygmaty.lab8;

import java.util.Objects;

public class GummiBear extends Entity implements Talkable{

    private int juiceCups;

    public GummiBear(String name) {
        this(name, 3);
    }
    public GummiBear(String name, int juiceCups) {
        super(name);
        this.juiceCups = juiceCups;
    }

    @Override
    public void interact(Entity other) {
        if (!(other instanceof GummiBear))
            hide(other);
        else
            System.out.println("Cześc, " + other.name);
    }

    @Override
    public void talk() {
        System.out.println("Czesc jestem gumisiem! " + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GummiBear gummiBear = (GummiBear) o;
        return juiceCups == gummiBear.juiceCups && gummiBear.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(juiceCups, name);
    }

    private void hide(Entity other) {
        juiceCups--;
        if (juiceCups < 0)
            System.out.println("O NIE! Skończył mi się sok z gumijagód!");
        System.out.println("UCIEKAM PRZED " + other.getName());
    }
}
