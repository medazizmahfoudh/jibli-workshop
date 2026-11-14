package tn.jibli.model;

public class Restaurant {

    private final long id;
    private final String name;
    private final boolean open;

    public Restaurant(long id, String name, boolean open) {
        this.id = id;
        this.name = name;
        this.open = open;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return open;
    }
}
