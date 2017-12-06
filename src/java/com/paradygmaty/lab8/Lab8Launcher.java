package com.paradygmaty.lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.paradygmaty.lab8.EntityType.*;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class Lab8Launcher {

    public static void main(String[] args) {
        AbstractEntityFactory factory = AbstractEntityFactoryFactory.create(GUMMI_BEAR);

        List<Entity> gummiBears = Stream.of("Kabi", "Sani", "Zami").map(factory::create)
                .collect(toList());

        factory = AbstractEntityFactoryFactory.create(KNIGHT);

        List<Entity> knights = Stream.of("Ightorn", "Gregor").map(factory::create)
                .collect(toList());

        List<Entity> ogres = singletonList(AbstractEntityFactoryFactory.create(OGRE).create("Toadie"));


        List<Entity> entities = new ArrayList<>(gummiBears);
        entities.addAll(knights);
        entities.addAll(ogres);

        entities.forEach(entity -> {
            if (entity instanceof Talkable)
                ((Talkable) entity).talk();
            else System.out.println("Nie umjem mowic :(( ~" + entity.getName());
        });

        System.out.println("//////////////");

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).interact(entities.get((i+1) % entities.size()));
        }
    }
}
