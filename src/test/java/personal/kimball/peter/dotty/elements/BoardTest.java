package personal.kimball.peter.dotty.elements;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import personal.kimball.peter.dotty.util.ANSIColor;

public class BoardTest {

    // character counting taken from
    // https://stackoverflow.com/questions/275944/java-how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string
    private long countCharactersInString(char c, String string) {
        return string.chars().filter(ch -> ch == c).count();
    }

    @Test
    public void testBoardDisplayedDimensions() {
        Board sut = new Board(1);
        String displayedBoard = sut.toString();
        long lines = countCharactersInString('\n', displayedBoard);
        Assert.assertEquals("Expect that for a board with sides of length 2^1, the height of the board is 2",
                2L, lines);
        long tiles = countCharactersInString(' ', displayedBoard);
        Assert.assertEquals("Expect that for a board with sides of length 2^1, there are 2*2 tiles",
                2*2L, tiles);

        sut = new Board(4);
        displayedBoard = sut.toString();
        lines = countCharactersInString('\n', displayedBoard);
        Assert.assertEquals("Expect that for a board with sides of length 2^4, the height of the board is 16",
                16L, lines);
        tiles = countCharactersInString(' ', displayedBoard);
        Assert.assertEquals("Expect that for a board with sides of length 2^4, there are 16*16 tiles",
                16*16L, tiles);
    }

    @Test
    public void testSetAndGetTile() {
        Board sut = new Board(2);
        Point p = new Point(2,2);

        Tile setTile = new Tile();
        setTile.setOccupied('X', ANSIColor.BLACK);
        sut.setTile(p, setTile);

        Tile foundTile = sut.getTile(p);
        Assert.assertSame("Expect that the retrieved tile is the same as the set tile", setTile, foundTile);
    }

    @Ignore
    @Test
    public void testSetTileOutsideBoundaries() {

    }

    @Ignore
    @Test
    public void testGetTileOutsideBoundaries() {

    }

}
