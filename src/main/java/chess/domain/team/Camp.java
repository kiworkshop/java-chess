package chess.domain.team;

import chess.domain.piece.*;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Camp {
    private List<Piece> pieces = new LinkedList<>();
    private Team team;

    public Camp(Team team) {
        this.team = team;
        //킹1/퀸1/비숍2/나이트2/룩2/폰8;
        createKing();
        createQueen();
        createBishop();
        creatKnight();
        createRook();
        createPawn();
    }

    private void createKing() {
        pieces.add(new King(team, new PiecePosition(File.E, team.getPiecesRank())));
    }

    private void createQueen() {
        pieces.add(new Queen(team, new PiecePosition(File.D, team.getPiecesRank())));
    }

    private void createBishop() {
        pieces.add(new Bishop(team, new PiecePosition(File.C, team.getPiecesRank())));
        pieces.add(new Bishop(team, new PiecePosition(File.F, team.getPiecesRank())));
    }

    private void creatKnight() {
        pieces.add(new Knight(team, new PiecePosition(File.B, team.getPiecesRank())));
        pieces.add(new Knight(team, new PiecePosition(File.G, team.getPiecesRank())));
    }

    private void createRook() {
        pieces.add(new Rook(team, new PiecePosition(File.A, team.getPiecesRank())));
        pieces.add(new Rook(team, new PiecePosition(File.H, team.getPiecesRank())));
    }

    private void createPawn() {
        for(File file : File.values()) {
            pieces.add(new Pawn(team, new PiecePosition(file, team.getPawnRank())));
        }
    }
}
