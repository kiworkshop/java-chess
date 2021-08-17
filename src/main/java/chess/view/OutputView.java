package chess.view;

import chess.domain.RankComparator;
import chess.domain.piece.File;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;
import chess.domain.plate.ChessPlate;

import java.util.Arrays;
import java.util.Map;

public class OutputView {

    public void printChessPlate(ChessPlate chessPlate) {
        RankComparator rankComparator = new RankComparator();
        Arrays.stream(Rank.values())
                .sorted(rankComparator::compare)
                .forEach(rank -> {
                    for (File file : File.values()) {
                        System.out.print(chessPlate.getPlate().get(new PiecePosition(file, rank)).getDisplayName());
                    }
                    System.out.println();
                });
    }

    public void initPrint() {
        System.out.println(">체스 게임을 시작합니다.");
        System.out.println(">게임 시작 : start");
        System.out.println(">게임 종료 : end");
        System.out.println(">게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }


}
