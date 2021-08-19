package chess.domain.plate;

import chess.domain.RankComparator;
import chess.domain.piece.*;
import chess.domain.team.Camp;
import lombok.Getter;

import java.util.*;

@Getter
public class ChessPlate {
    private static final String BLANK =".";
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

        for (Rank rank: Rank.values()) {
            for (File file : File.values()) {
//              plate.put(new PiecePosition(file, rank), new Piece(".",Team.BLACK, new PiecePosition(file,rank)));
              plate.put(new PiecePosition(file, rank), null);
            }
        }
        allPieces.forEach(piece -> {
            plate.put(piece.getPiecePosition(), piece);
        });

        return plate;
    }
    public boolean move(PiecePosition sourcePosition, PiecePosition targetPosition){
        //TO-DO 기물별 허용 움직임 범위인지 체크
        Piece piece = plate.get(sourcePosition);
        Boolean isPawn = plate.get(sourcePosition) instanceof Pawn;
        if(piece.movable(targetPosition) && !havePieceOnStraightPath(sourcePosition, targetPosition) && !havePieceOnDiagonalPath(sourcePosition, targetPosition)){
            //이동 위치에 상대 말인지 /내말이 아닐 경우에만 이동한다
            Piece targetPiece = plate.get(targetPosition);
            if (targetPiece != null && targetPiece.getTeam().equals(piece.getTeam())){
                return false;
            }

            if(isPawn) {
                // 직선 이동
                if(sourcePosition.getFile().equals(targetPosition.getFile())) {
                    plate.remove(sourcePosition);
                    piece.move(targetPosition);
                    return plate.get(targetPosition) == null;
                }

                // 대각선 이동
                if((Math.abs(sourcePosition.getFile().getFilePosition() - targetPosition.getFile().getFilePosition())
                == Math.abs(sourcePosition.getRank().getRankPosition() - targetPosition.getRank().getRankPosition()))
                && (plate.get(targetPosition) != null && (plate.get(sourcePosition).getTeam() != plate.get(targetPosition).getTeam()))) {
                    plate.remove(sourcePosition);
                    piece.move(targetPosition);
                    return true;
                } // end of if
                return false;
            }
            plate.remove(sourcePosition);
            piece.move(targetPosition);
            return true;
        }
        return false;
    }

    public boolean havePieceOnStraightPath(PiecePosition sourcePosition, PiecePosition targetPosition) {
        if(plate.get(sourcePosition) instanceof Knight) {
            return false;
        }
        int originFile = sourcePosition.getFile().getFilePosition();
        int originRank = sourcePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);

        if(fileGap == 0) {
            if(originRank < targetRank) {
                for (int i = 1; i < rankGap -1; i++) {
                    Piece piece = plate.get(new PiecePosition(sourcePosition.getFile(), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() + i))));
                    return piece !=null;
                }
            }

            if(originRank > targetRank) {
                for (int i = 1; i < rankGap -1; i--) {
                    Piece piece = plate.get(new PiecePosition(sourcePosition.getFile(), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() + i))));
                    return piece !=null;
                }
            }
        }

        if(rankGap == 0) {
            if(originFile < targetFile) {
                for (int i = 1; i < fileGap -1; i++) {
                    Piece piece = plate.get(new PiecePosition(File.findBy(String.valueOf(sourcePosition.getFile().getFilePosition() + i)), sourcePosition.getRank()));
                    return piece != null;
                }
            }

            if(originFile > targetFile) {
                for (int i = 1; i < fileGap -1; i--) {
                    Piece piece = plate.get(new PiecePosition(File.findBy(String.valueOf(sourcePosition.getFile().getFilePosition() + i)), sourcePosition.getRank()));
                    return piece != null;
                }
            }
        }

        return false;
    }

    public boolean havePieceOnDiagonalPath(PiecePosition sourcePosition, PiecePosition targetPosition) {
        if(plate.get(sourcePosition) instanceof Knight) {
            return false;
        }
        int originFile = sourcePosition.getFile().getFilePosition();
        int originRank = sourcePosition.getRank().getRankPosition();
        int targetFile = targetPosition.getFile().getFilePosition();
        int targetRank = targetPosition.getRank().getRankPosition();
        int fileGap = Math.abs(originFile - targetFile);
        int rankGap = Math.abs(originRank - targetRank);

        //좌상
        if(fileGap == rankGap && (targetFile < originFile && targetRank > originRank)) {
            for (int i = 1; i < rankGap; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition() - i), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() + i))));
                return piece !=null;
            }
        }

        //우상
        if(fileGap == rankGap && (targetFile > originFile && targetRank > originRank)) {
            System.out.println("check right up");
            for (int i = 1; i < rankGap; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition() + i), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() + i))));
                return piece !=null;
            }
        }

        //우하
        if(fileGap == rankGap && (targetFile > originFile && targetRank < originRank)) {
            for (int i = 1; i < rankGap ; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition() + i), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() - i))));
                return piece !=null;
            }
        }

        //좌하
        if(fileGap == rankGap && (targetFile < originFile && targetRank < originRank)) {
            for (int i = 1; i < rankGap; i++) {
                Piece piece = plate.get(new PiecePosition(File.findBy(sourcePosition.getFile().getFilePosition() - i), Rank.findBy(String.valueOf(sourcePosition.getRank().getRankPosition() - i))));
                return piece !=null;
            }
        }
        return false;
    }
}
