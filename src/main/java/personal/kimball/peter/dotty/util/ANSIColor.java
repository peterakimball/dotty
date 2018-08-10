package personal.kimball.peter.dotty.util;

// Coloring method taken from https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

public enum ANSIColor {

    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    BACKGROUND_GREEN("\u001B[42m"),
    BACKGROUND_YELLOW("\u001B[43m"),
    BACKGROUND_CYAN("\u001B[46m"),
    BACKGROUND_PURPLE("\u001B[45m");


    private String ansiString;

    ANSIColor(String color) {
        this.ansiString = color;
    }

    public String getAnsiString() {
        return ansiString;
    }
}
