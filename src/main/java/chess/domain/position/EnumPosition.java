package chess.domain.position;

import chess.model.postiion.File;
import chess.model.MovingDirection;
import chess.model.postiion.Position;
import chess.model.postiion.Rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EnumPosition implements Position {

    static final Map<String, Position> positionMap;

    File file;
    Rank rank;

    static {
        positionMap = new HashMap<>();

        for (EnumFile file : EnumFile.values()) {
            addPosition(file);
        }
    }

    private EnumPosition(EnumFile file, EnumRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String positionName) {
        Optional<Position> position = Optional.ofNullable(positionMap.get(positionName.toUpperCase()));
        return position.orElseThrow(() -> new IllegalArgumentException("잘못된 위치를 선택했습니다"));
    }

    @Override
    public Position moveByDirection(MovingDirection movingDirection) {
        File movedFile = file.add(movingDirection.getFileDirection());
        Rank movedRank = rank.add(movingDirection.getRankDirection());
        return positionMap.get(key(movedFile, movedRank));
    }

    @Override
    public boolean isBoundary() {
        return file.isMax() || file.isMin() || rank.isMax() || rank.isMin();
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int fileDifference(Position target) {
        return file.fileDifference(target.getFile());
    }

    @Override
    public int rankDifference(Position target) {
        return rank.rankDifference(target.getRank());
    }

    private static void addPosition(EnumFile file) {
        for (EnumRank rank : EnumRank.values()) {
            positionMap.put(key(file, rank), new EnumPosition(file, rank));
        }
    }

    private static String key(File file, Rank rank) {
        return file.getFileName() + rank.value();
    }
}
