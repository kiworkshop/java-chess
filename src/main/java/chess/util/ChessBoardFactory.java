package chess.util;

import chess.domain.*;
import chess.domain.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoardFactory {

    public static ChessBoard makeBoard() {
        List<ChessBoardSquare> chessBoardSquares = new ArrayList<>();
        chessBoardSquares.addAll(makeFirstRow());
        chessBoardSquares.addAll(makeSecondRow());
        chessBoardSquares.addAll(makeThirdRow());
        chessBoardSquares.addAll(makeFourthRow());
        chessBoardSquares.addAll(makeFifthRow());
        chessBoardSquares.addAll(makeSixthRow());
        chessBoardSquares.addAll(makeSeventhRow());
        chessBoardSquares.addAll(makeEightRow());
        return new ChessBoard(chessBoardSquares);
    }

    private static List<ChessBoardSquare> makeFirstRow() {
        return Stream.of(
            Rook.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.A)),
            Knight.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.B)),
            Bishop.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.C)),
            Queen.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.D)),
            King.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.E)),
            Bishop.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.F)),
            Knight.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.G)),
            Rook.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.ONE, Column.H))
        ).map(chessPiece -> new ChessBoardSquare(chessPiece.getChessBoardPosition(), chessPiece))
         .collect(Collectors.toList());
    }

   private static List<ChessBoardSquare> makeSecondRow() {
       return Stream.of(
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.A)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.B)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.C)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.D)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.E)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.F)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.G)),
           Pawn.from(ChessTeam.WHITE, ChessBoardPosition.with(Row.TWO, Column.H))
       ).map(chessPiece -> new ChessBoardSquare(chessPiece.getChessBoardPosition(), chessPiece))
        .collect(Collectors.toList());
   }

    private static List<ChessBoardSquare> makeSeventhRow() {
        return Stream.of(
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.A)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.B)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.C)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.D)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.E)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.F)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.G)),
            Pawn.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.SEVEN, Column.H))
        ).map(chessPiece -> new ChessBoardSquare(chessPiece.getChessBoardPosition(), chessPiece))
        .collect(Collectors.toList());
    }

    private static List<ChessBoardSquare> makeEightRow() {
        return Stream.of(
            Rook.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.A)),
            Knight.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.B)),
            Bishop.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.C)),
            Queen.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.D)),
            King.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.E)),
            Bishop.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.F)),
            Knight.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.G)),
            Rook.from(ChessTeam.BLACK, ChessBoardPosition.with(Row.EIGHT, Column.H))
        ).map(chessPiece -> new ChessBoardSquare(chessPiece.getChessBoardPosition(), chessPiece))
        .collect(Collectors.toList());
    }

    private static List<ChessBoardSquare> makeThirdRow() {
        return Stream.of(
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.A)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.B)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.C)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.D)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.E)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.F)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.G)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.THREE, Column.H))
        ).collect(Collectors.toList());
    }

    private static List<ChessBoardSquare> makeFourthRow() {
        return Stream.of(
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.A)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.B)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.C)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.D)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.E)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.F)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.G)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FOUR, Column.H))
        ).collect(Collectors.toList());
    }

    private static List<ChessBoardSquare> makeFifthRow() {
        return Stream.of(
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.A)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.B)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.C)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.D)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.E)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.F)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.G)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.FIVE, Column.H))
        ).collect(Collectors.toList());
    }

    private static List<ChessBoardSquare> makeSixthRow() {
        return Stream.of(
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.A)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.B)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.C)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.D)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.E)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.F)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.G)),
            new ChessBoardSquare(ChessBoardPosition.with(Row.SIX, Column.H))
        ).collect(Collectors.toList());
    }
}

