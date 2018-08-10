package personal.kimball.peter.dotty.elements;

public class Board {

    private final Tile[] layout;

    private int length;

    public Board(int dimension) {
        if (dimension < 1) {
            throw new RuntimeException("Board dimension must be at least 1!");
        }
        length = (int)Math.pow(2, dimension);
        layout = new Tile[length * length];
        for (int i = 0; i < layout.length; i++) {
            layout[i] = new Tile();
        }
    }

    public int getLength() {
        return length;
    }

    public void setTile(Point p, Tile t) {
        if (p.y > 0 && p.y < length && p.x > 0 && p.x < length) {
            layout[(p.y * length) + p.x] = t;
        }
    }

    public Tile getTile(Point p) {
        Tile foundTile;
        if (p.x > length - 1 || p.x < 0) {
            foundTile = null;
        } else if (p.y > length - 1 || p.y < 0) {
            foundTile = null;
        } else {
            foundTile = layout[(p.y * length) + p.x];
        }
        return foundTile;
    }

    public String toString() {
        StringBuilder boardBuilder = new StringBuilder(layout.length * layout.length);
        for (int i = 0; i < layout.length; i++) {
            boardBuilder.append(layout[i]);
            if (i % length == length - 1) {
                boardBuilder.append('\n');
            }
        }
        return boardBuilder.toString();
    }
}