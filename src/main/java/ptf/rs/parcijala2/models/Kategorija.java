package ptf.rs.parcijala2.models;

public enum Kategorija {
    TEHNIKA("Tehnika"),
    KOZMETIKA("Kozmetika"),
    SPORT("Sport"),
    GARDEROBA("Garderoba"),

    HRANA_PICE("Hrana i piće");

    private final String name;

    Kategorija(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
