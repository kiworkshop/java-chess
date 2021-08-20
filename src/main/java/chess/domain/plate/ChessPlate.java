package chess.domain.plate;

import chess.domain.RankComparator;
import chess.domain.piece.*;
import chess.domain.team.Camp;
import lombok.Getter;

import java.util.*;

@Getter
public class ChessPlate {
    private static final String BLANK = ".";
    private Map<PiecePosition, Piece> plate = new HashMap<>();
    private List<Piece> allPieces = new LinkedList<>();

    public ChessPlate() {
        Camp black = new Camp(Team.BLACK);
        Camp white = new Camp(Team.WHITE);
        allPieces.addAll(black.getPieces());
        allPieces.addAll(white.getPieces());
        arrangePieces();
    }

    public Map<PiecePosition, Piece> arrangePieces() {
        RankComparator rankComparator = new RankComparator();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                plate.put(new PiecePosition(file, rank), null);
            }
        }
        allPieces.forEach(piece -> {
            plate.put(piece.getPiecePosition(), piece);
        });

        return plate;
    }

    public boolean move(PiecePosition sourcePosition, PiecePosition targetPosition) {
        Direction direction = getDirection(sourcePosition, targetPosition);
        //TO-DO 기물별 허용 움직임 범위인지 체크
        Piece sourcePiece = plate.get(sourcePosition);
        if (!movable(sourcePosition, targetPosition, sourcePiece)) {
            return false;
        }

        //이동 위치에 상대 말인지 /내말이 아닐 경우에만 이동한다
        Piece targetPiece = plate.get(targetPosition);
        if (targetPiece != null && targetPiece.getTeam().equals(sourcePiece.getTeam())) {
            return false;
        }

        if (sourcePiece instanceof Pawn) {
            // 직선 이동
            if ((direction.equals(Direction.UP) ||  direction.equals(Direction.DOWN)) && targetPiece == null) {
                executeMove(sourcePosition, targetPosition, sourcePiece);
                return true;
            }

            // 대각선 이동
            if (isDiagonalMoving(sourcePosition, targetPosition, sourcePiece, targetPiece)) {
                executeMove(sourcePosition, targetPosition, sourcePiece);
                return true;
            } // end of if
            return false;
        }
        executeMove(sourcePosition, targetPosition, sourcePiece);
        return true;
    }

    private Direction getDirection(PiecePosition sourcePosition, PiecePosition targetPosition) {
        int originFile = sourcePosition.getFile().getFilePosition();
        int originRank = sourcePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);

        if(fileGap == 0 && originRank > targetRank){
            return Direction.DOWN;
        }
        if(fileGap == 0 && originRank < targetRank) {
            return Direction.UP;
        }
        //좌상
        if (fileGap == rankGap && (targetFile < originFile && targetRank > originRank)) {
            return Direction.LEFTUP;
        }

        //우상
        if (fileGap == rankGap && (targetFile > originFile && targetRank > originRank)) {
            return Direction.RIGHTUP;
        }

        //우하
        if (fileGap == rankGap && (targetFile > originFile && targetRank < originRank)) {
            return Direction.RIGHTDOWUN;
        }

        //좌하
        if (fileGap == rankGap && (targetFile < originFile && targetRank < originRank)) {
            return Direction.LEFTDOWUN;
        }
        return Direction.OTHERS;
    }

    private void executeMove(PiecePosition sourcePosition, PiecePosition targetPosition, Piece sourcePiece) {
        plate.remove(sourcePosition);
        sourcePiece.move(targetPosition);
        plate.put(sourcePiece.getPiecePosition(), sourcePiece);
    }

    private boolean isDiagonalMoving(PiecePosition sourcePosition, PiecePosition targetPosition, Piece sourcePiece, Piece targetPiece) {
        int fileGapAbs = Math.abs(sourcePosition.getFile().getFilePosition() - targetPosition.getFile().getFilePosition());
        int rankGapAbs = Math.abs(sourcePosition.getRank().getRankPosition() - targetPosition.getRank().getRankPosition());
        boolean isTargetEnemy = targetPiece != null && (sourcePiece.getTeam() != targetPiece.getTeam());
        return (fileGapAbs == rankGapAbs) && (isTargetEnemy);
    }

    private boolean movable(PiecePosition sourcePosition, PiecePosition targetPosition, Piece piece) {
        return piece.movable(targetPosition) && havePieceOnPath(sourcePosition, targetPosition);
    }

    public boolean havePieceOnPath(PiecePosition sourcePosition, PiecePosition targetPosition) {
        if (plate.get(sourcePosition) instanceof Knight) {
            return false;
        }

        Direction direction = getDirection(sourcePosition, targetPosition);
        int fileGap = Math.abs(sourcePosition.getFile().getFilePosition() - targetPosition.getFile().getFilePosition());
        int rankGap = Math.abs(sourcePosition.getRank().getRankPosition() - targetPosition.getRank().getRankPosition());

        if(direction.equals(Direction.UP) || direction.equals(Direction.DOWN)){
            for (int i = 1; i < rankGap; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition()+direction.getFileMoveCount()), Rank.findBy(sourcePosition.getRank().getRankPosition() + direction.getRankMoveCount())));
                if (piece != null) {
                    return true;
                }
            }
        }
        if(direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)){
            for (int i = 1; i < fileGap; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition()+direction.getFileMoveCount()), Rank.findBy(sourcePosition.getRank().getRankPosition() + direction.getRankMoveCount())));
                if (piece != null) {
                    return true;
                }
            }
        }

        for (int i = 1; i < rankGap; i++) {
            Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition() + direction.getFileMoveCount()), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() + direction.getRankMoveCount()))));
            if (piece != null) {
                return true;
            }
        }
        return false;
    }
}
