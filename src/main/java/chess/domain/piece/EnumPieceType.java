package chess.domain.piece;

import chess.model.Team;
import chess.model.piece.PieceType;

public enum EnumPieceType implements PieceType {

    PAWN("\u2659", "\u265F"),
    KNIGHT("\u2658", "\u265E"),
    BISHOP("\u2657", "\u265D"),
    ROOK("\u2656", "\u265C"),
    QUEEN("\u2655", "\u265B"),
    KING("\u2654", "\u265A");

    private String whiteFigure;
    private String blackFigure;

    EnumPieceType(String whiteFigure, String blackFigure) {
        this.whiteFigure = whiteFigure;
        this.blackFigure = blackFigure;
    }

    @Override
    public String getFigure(Team team) {
        return team.isWhite() ? whiteFigure : blackFigure;
    }
}
