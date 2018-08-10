package personal.kimball.peter.dotty.elements;

import org.junit.Assert;
import org.junit.Test;

import personal.kimball.peter.dotty.util.ANSIColor;

public class TileTest {

    @Test
    public void testNewTileIsUnoccupied() {
        Tile sut = new Tile();
        Assert.assertFalse("Expect that a new tile is unoccupied", sut.isOccupied());
    }

    @Test
    public void testNewTileBackgroundColorIsGreen() {
        Tile sut = new Tile();
        String displayedString = sut.toString();
        Assert.assertTrue("Expect that the string representation of the tile will use a green background",
                displayedString.contains(ANSIColor.BACKGROUND_GREEN.getAnsiString()));
    }

    @Test
    public void testTileIsOccupiedAfterSettingValue() {
        Tile sut = new Tile();
        sut.setOccupied('A', ANSIColor.WHITE);
        Assert.assertTrue("Expect that a tile is occupied after its contents have been set", sut.isOccupied());
    }

    @Test
    public void testTileColorIsCorrectAfterSettingValue() {
        Tile sut = new Tile();
        sut.setOccupied('B', ANSIColor.CYAN);
        String displayedString = sut.toString();
        Assert.assertTrue("Expect that the string representation of the tile uses the color that was set",
                displayedString.contains(ANSIColor.CYAN.getAnsiString()));
    }
}
