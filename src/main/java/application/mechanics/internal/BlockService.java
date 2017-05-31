package application.mechanics.internal;

import application.mechanics.Config;
import application.mechanics.base.Block;
import application.mechanics.base.geometry.Coordinates;
import application.mechanics.base.Map;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@SuppressWarnings("InstanceVariableNamingConvention")
@Service
public class BlockService {
    @NotNull
    private static final Logger LOGGER = LoggerFactory.getLogger(BlockService.class.getSimpleName());

    private final int m;
    private final int n;
    private final Block[][] blocks;

    public BlockService() {
        m = Map.m;
        n = Map.n;
        blocks = new Block[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (Map.map[i][j] > 0) {
                    final Coordinates corner = new Coordinates(
                            i * Config.BLOCK_SIZE,
                            Config.BLOCK_HEIGHT,
                            j * Config.BLOCK_SIZE
                    );
                    final Block block = new Block(corner, Config.BLOCK_SIZE, Config.BLOCK_HEIGHT, Config.BLOCK_SIZE);
                    blocks[i][j] = block;
                }
            }
        }
    }

    public boolean isWallsBetween(@NotNull Coordinates from, @NotNull Coordinates to) {
        final Index fromIndex = getIndexOnMap(from);
        final Index toIndex = getIndexOnMap(to);
        LOGGER.info("from: {}, index: {}. to: {}, index: {}", from.toString(), "i " + fromIndex.i + ", j " + fromIndex.j,
                to.toString(), "i " + toIndex.i + ", j " + toIndex.j);
        final int fromI = Math.min(fromIndex.i, toIndex.i);
        final int toI = Math.max(fromIndex.i, toIndex.i);
        final int fromJ = Math.min(fromIndex.j, toIndex.j);
        final int toJ = Math.max(fromIndex.j, toIndex.j);
        for (int i = fromI; i <= toI; i++) {
            for (int j = fromJ; j <= toJ; j++) {
                if (blocks[i][j] == null) {
                    continue;
                }
                if (blocks[i][j].isBetween(from, to)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    private Index getIndexOnMap(@NotNull Coordinates point) {
        final Index index = new Index();
        index.i = (int) ((point.x + Config.BLOCK_SIZE / 2) / Config.BLOCK_SIZE) + n / 2;
        index.j = (int) ((point.z + Config.BLOCK_SIZE / 2) / Config.BLOCK_SIZE) + m / 2;
        return index;
    }

    @SuppressWarnings("PublicField")
    private static class Index {
        public int i;
        public int j;
    }
}
