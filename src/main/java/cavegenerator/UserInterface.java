package cavegenerator;

import org.hexworks.zircon.api.CP437TilesetResources;
import org.hexworks.zircon.api.DrawSurfaces;
import org.hexworks.zircon.api.Shapes;
import org.hexworks.zircon.api.SwingApplications;
import org.hexworks.zircon.api.application.AppConfig;
import org.hexworks.zircon.api.color.*;
import org.hexworks.zircon.api.data.*;
import org.hexworks.zircon.api.graphics.*;
import org.hexworks.zircon.api.grid.TileGrid;

class UserInterface {
    final int MAP_WIDTH;
    final int MAP_HEIGHT;
    final TileGrid tileGrid;
    final AppConfig config;

    UserInterface(int w, int h) {
        MAP_WIDTH = w;
        MAP_HEIGHT = h;

        config = AppConfig.newBuilder()
                .withTitle("Luolasto")
                .withSize(MAP_WIDTH, MAP_HEIGHT)
                .withDefaultTileset(CP437TilesetResources.rexPaint16x16())
                .build();

        tileGrid = SwingApplications.startTileGrid(config);

        final TileGraphics background = DrawSurfaces.tileGraphicsBuilder()
                .withSize(tileGrid.getSize())
                .withFiller(Tile.newBuilder()
                        .withCharacter('#')
                        .withBackgroundColor(TileColor.transparent())
                        .withForegroundColor(ANSITileColor.GRAY)
                        .build())
                .build();

        final TileGraphics rectangle = Shapes.buildRectangle(
                Position.zero(),
                tileGrid.getSize())
                .toTileGraphics(Tile.newBuilder()
                                .withCharacter(Symbols.BLOCK_SOLID)
                                .withBackgroundColor(TileColor.transparent())
                                .withForegroundColor(ANSITileColor.GREEN)
                                .build(),
                        config.getDefaultTileset());

        background.draw(rectangle, Position.zero());

        tileGrid.draw(background, Position.zero());
    }

    void addRoom(int w, int h, int x, int y) {
        Layer layer0 = Layer.newBuilder()
                .withTileGraphics(DrawSurfaces.tileGraphicsBuilder()
                        .withSize(w, h)
                        .withFiller(Tile.newBuilder()
                                .withForegroundColor(ANSITileColor.WHITE)
                                .withBackgroundColor(ANSITileColor.BLACK)
                                .withCharacter(Symbols.BULLET_SMALL)
                                .build())
                        .build())
                .withOffset(x, y)
                .build();

        tileGrid.addLayer(layer0);
    }
}
