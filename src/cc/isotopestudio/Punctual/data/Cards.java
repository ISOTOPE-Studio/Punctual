package cc.isotopestudio.Punctual.data;

import java.util.List;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class Cards {

    private final String name;
    private final double price;
    private final List<String> commands;

    public Cards(String name, double price, List<String> commands) {
        this.name = name;
        this.price = price;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getCommands() {
        return commands;
    }
}
