package personal.kimball.peter.dotty.logic;

import java.util.ArrayList;

import personal.kimball.peter.dotty.elements.Board;
import personal.kimball.peter.dotty.elements.Ell;
import personal.kimball.peter.dotty.elements.Point;
import personal.kimball.peter.dotty.elements.Tile;

public class ValueSolver implements BoardSolver {

    private Board board;

    private static final int EDGE_VALUE = 10;
    private static final int DOT_VALUE = 6;
    private static final int NEIGHBOR_VALUE = 7;

    // Set of all ells which fit on the board
    private ArrayList<Ell> allElls;

    public ValueSolver(Board board) {
        this.board = board;
        allElls = new ArrayList<>();
        for (Ell.Direction direction : Ell.Direction.values()) {
            for (int y = 0; y < board.getLength(); y++) {
                for (int x = 0; x < board.getLength(); x++) {
                    Ell candidate = new Ell(direction, x, y);
                    if (candidate.fitsOnBoard(board)) {
                        allElls.add(candidate);
                    }
                }
            }
        }
    }

    private int valueOfTile(Point p) {
        int sum = 0;
        int boardLength = board.getLength();

        Tile t = board.getTile(p);
        if (t.isOccupied()) {
            // if the tile is occupied, return a very negative value (without causing overflow when calculating sums)
            sum = -1024;
        } else {
            if (p.x == boardLength - 1 || p.x == 0) {
                sum += EDGE_VALUE;
            }
            if (p.y == boardLength - 1 || p.y == 0) {
                sum += EDGE_VALUE;
            }
            Tile[] neighbors = {board.getTile(new Point(p.x, p.y - 1)), board.getTile(new Point(p.x, p.y + 1)),
                    board.getTile(new Point(p.x - 1, p.y)), board.getTile(new Point(p.x + 1, p.y))};
            for (Tile neighbor : neighbors) {
                if (neighbor != null && neighbor.getOccupant() == '*') {
                    sum += DOT_VALUE;
                } else if (neighbor != null && neighbor.isOccupied()) {
                    sum += NEIGHBOR_VALUE;
                }
            }
        }
        return sum;
    }

    private int valueOfEll(Ell ell) {
        int sum = 0;
        sum += valueOfTile(ell.getCenter());
        sum += valueOfTile(ell.getNorthSouthLeg());
        sum += valueOfTile(ell.getEastWestLeg());
        return sum;
    }

    public boolean solve() {
        int placedElls = 0;
        int requiredElls = ((board.getLength() * board.getLength()) - 1) / 3;
        boolean success = true;

        while (placedElls < requiredElls) {
            Ell largestValueEll = null;
            int largestValue = -1;
            // Do not iterate over allElls as it will be modified within the loop
            // Greedy search for the best match
            for (Ell candidate : allElls.toArray(new Ell[0])) {
                int currentValue = this.valueOfEll(candidate);
                if (currentValue < 0) {
                    allElls.remove(candidate);
                } else if (currentValue > largestValue) {
                    largestValue = currentValue;
                    largestValueEll = candidate;
                }
            }
            // if we can't place an ell without overlapping an occupied tile, we've failed
            if (largestValue < 0) {
                success = false;
                break;
            } else {
                largestValueEll.addToBoard(board);
                placedElls++;
            }
        }
        System.out.print(board);
        System.out.println();

        return success;
    }
}
