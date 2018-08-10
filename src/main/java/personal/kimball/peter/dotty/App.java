package personal.kimball.peter.dotty;

import personal.kimball.peter.dotty.logic.BoardSolver;
import personal.kimball.peter.dotty.util.ANSIColor;
import personal.kimball.peter.dotty.elements.Board;
import personal.kimball.peter.dotty.elements.Point;
import personal.kimball.peter.dotty.elements.Tile;
import personal.kimball.peter.dotty.logic.ValueSolver;

public class App
{

    // TODO - select solver, board dimension, * location using app parameters
    public static int main( String[] args )
    {
        int dimension = 3;
        // Quick hack
        if (args.length > 0) {
            dimension = Integer.parseInt(args[0]);
        }
        int length = (int)Math.pow(2, dimension);
        int successCount = 0;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                Board b = new Board(dimension);
                Tile t = new Tile();
                t.setOccupied('*', ANSIColor.BLACK);
                b.setTile(new Point(x, y), t);

                BoardSolver solver = new ValueSolver(b);
                if (solver.solve()) {
                    successCount++;
                }
            }
        }
        System.out.println("Found valid solution " + successCount + " out of " + length * length + " times");
        if (successCount == (length * length)) {
            return 0;
        }
        return -1;
    }
}
