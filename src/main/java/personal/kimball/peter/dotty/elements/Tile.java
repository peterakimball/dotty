package personal.kimball.peter.dotty.elements;


import personal.kimball.peter.dotty.util.ANSIColor;

public class Tile {

    private char occupant;
    private ANSIColor color;

    public Tile() {
        occupant = ' ';
        color = ANSIColor.BACKGROUND_GREEN;
    }

    public boolean isOccupied() {
        return occupant != ' ';
    }

    public char getOccupant() {
        return occupant;
    }

    public void setOccupied(char occupant, ANSIColor color) {
        this.occupant = occupant;
        this.color = color;
    }

    public String toString() {
        return color.getAnsiString() + occupant + ANSIColor.RESET.getAnsiString();
    }
}
