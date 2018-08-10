package personal.kimball.peter.dotty.elements;

import personal.kimball.peter.dotty.util.ANSIColor;

public class Ell {

    public enum Direction {
        NORTHEAST,
        SOUTHEAST,
        SOUTHWEST,
        NORTHWEST;
    }

    private Direction direction;
    private int centerX;
    private int centerY;

    public Ell(Direction direction, int centerX, int centerY) {
        this.direction = direction;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    private boolean isInLeftmostColumn() {
        return centerX == 0;
    }

    private boolean isInRightmostColumn(int boardLength) {
        return centerX == (boardLength - 1);
    }

    private boolean isInTopRow() {
        return centerY == 0;
    }

    private boolean isInBottomRow(int boardLength) {
        return centerY == (boardLength - 1);
    }

    public boolean fitsOnBoard(Board board) {
        boolean fits = true;
        int boardLength = board.getLength();
        switch (direction) {
            case NORTHEAST:
                // Northeast L does not fit if it's in the top row or rightmost column
                if (isInTopRow() || isInRightmostColumn(boardLength)) {
                    fits = false;
                }
                break;
            case SOUTHEAST:
                // Southeast L does not fit if it's in the bottom row or rightmost column
                if (isInBottomRow(boardLength) || isInRightmostColumn(boardLength)) {
                    fits = false;
                }
                break;
            case SOUTHWEST:
                // Southwest L does not fit if it's in the bottom row or leftmost column
                if (isInBottomRow(boardLength) || isInLeftmostColumn()) {
                    fits = false;
                }
                break;
            case NORTHWEST:
                // Northwest L does not fit if it's in the top row or leftmost column
                if (isInTopRow() || isInLeftmostColumn()) {
                    fits = false;
                }
                break;
        }
        return fits;
    }

    public Point getNorthSouthLeg() {
        Point leg;
        switch (direction) {
            case NORTHEAST:
            case NORTHWEST:
                leg = new Point(centerX, centerY - 1);
                break;
            case SOUTHEAST:
            case SOUTHWEST:
                leg = new Point(centerX, centerY + 1);
                break;
            default:
                throw new RuntimeException("Unknown direction: " + direction);
        }
        return leg;
    }

    public Point getEastWestLeg() {
        Point leg;
        switch (direction) {
            case NORTHWEST:
            case SOUTHWEST:
                leg = new Point(centerX - 1, centerY);
                break;
            case NORTHEAST:
            case SOUTHEAST:
                leg = new Point(centerX + 1, centerY);
                break;
            default:
                throw new RuntimeException("Unknown direction: " + direction);
        }
        return leg;
    }

    public Point getCenter() {
        return new Point(centerX, centerY);
    }

    public void addToBoard(Board board) {
        Tile center = board.getTile(new Point(centerX, centerY));
        switch (direction) {
            case NORTHEAST:
                center.setOccupied('\\', ANSIColor.BACKGROUND_CYAN);
                board.getTile(this.getNorthSouthLeg()).setOccupied('|', ANSIColor.BACKGROUND_CYAN);
                board.getTile(this.getEastWestLeg()).setOccupied('_', ANSIColor.BACKGROUND_CYAN);
                break;
            case SOUTHEAST:
                center.setOccupied('/', ANSIColor.BACKGROUND_GREEN);
                board.getTile(this.getEastWestLeg()).setOccupied('¯', ANSIColor.BACKGROUND_GREEN);
                board.getTile(this.getNorthSouthLeg()).setOccupied('|', ANSIColor.BACKGROUND_GREEN);
                break;
            case SOUTHWEST:
                center.setOccupied('\\', ANSIColor.BACKGROUND_YELLOW);
                board.getTile(this.getEastWestLeg()).setOccupied('¯', ANSIColor.BACKGROUND_YELLOW);
                board.getTile(this.getNorthSouthLeg()).setOccupied('|', ANSIColor.BACKGROUND_YELLOW);
                break;
            case NORTHWEST:
                center.setOccupied('/', ANSIColor.BACKGROUND_PURPLE);
                board.getTile(this.getNorthSouthLeg()).setOccupied('|', ANSIColor.BACKGROUND_PURPLE);
                board.getTile(this.getEastWestLeg()).setOccupied('_', ANSIColor.BACKGROUND_PURPLE);
                break;
        }
    }
}
