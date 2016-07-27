package cc.isotopestudio.Punctual.data;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class Card {

    private final String id;
    private final String name;
    private final int price;
    private final List<String> commands;

    public Card(String id, String name, int price, List<String> commands) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.commands = commands;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return "yueka." + getId();
    }


    @Nullable
    public static Card getCardByName(String name) {
        for (Card card : ConfigData.cards) {
            if (card.getId().equals(name))
                return card;
        }
        return null;
    }
}
