package chess.domain.plate;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.team.Camp;
import chess.domain.team.Team;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class ChessPlate {
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
        //TO-DO 기물별 허용 움직임 범위인지 체크
        MovingDirection movingDirection = new MovingDirection(sourcePosition, targetPosition);
        Piece sourcePiece = plate.get(sourcePosition);
        if (sourcePiece == null) {
            return false;
        }
        //기물별 움직임 범위 체크
        if (!sourcePiece.canMoveTo(targetPosition)) {
            return false;
        }
        //가는 길에 기물이 있는지 체크
        if (havePieceOnPath(movingDirection)) {
            return false;
        }
        //이동 위치에 상대 말인지 /내말이 아닐 경우에만 이동한다
        Piece targetPiece = plate.get(targetPosition);
        if (targetPiece != null && targetPiece.getTeam().equals(sourcePiece.getTeam())) {
            return false;
        }
        //폰인경우
        if (sourcePiece instanceof Pawn && targetPiece != null && movingDirection.isVertical()) {
            return false;
        }
        if (sourcePiece instanceof Pawn && targetPiece == null && movingDirection.isDiagonal()) {
            return false;
        }

        executeMove(sourcePosition, targetPosition);
        return true;
    }

    private void executeMove(PiecePosition sourcePosition, PiecePosition targetPosition) {
        Piece sourcePiece = plate.get(sourcePosition);
        plate.remove(sourcePosition);
        sourcePiece.move(targetPosition);
        plate.put(sourcePiece.getPiecePosition(), sourcePiece);
    }

    public boolean havePieceOnPath(MovingDirection movingDirection) {
        if (plate.get(movingDirection.getSourcePosition()) instanceof Knight) {
            return false;
        }

        for (int i = 1; i < movingDirection.getGap(); i++) {
            Piece piece = plate.get(
                    new PiecePosition(
                            File.findBy(movingDirection.getSourcePosition().getFile().getFilePosition() + movingDirection.getDirection().getFileMoveCount()),
                            Rank.findBy(movingDirection.getSourcePosition().getRank().getRankPosition() + movingDirection.getDirection().getRankMoveCount())));
            if (piece != null) {
                return true;
            }
        }
        return false;
    }
}
