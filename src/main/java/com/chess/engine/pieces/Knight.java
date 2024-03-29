package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, true);
    }
    public Knight(final int piecePosition, final Alliance pieceAlliance, final Boolean isFirstMove) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, isFirstMove);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Knight movePiece(final Move move) {
        return new Knight(move.getDestinationCoordinate(), move.getMovedPiece().pieceAlliance);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.FIRST_FILE[currentPosition] && (candidateOffSet == -17 || candidateOffSet == -10 || candidateOffSet == 6 || candidateOffSet == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.SECOND_FILE[currentPosition] && (candidateOffSet == -10 || candidateOffSet == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.SEVENTH_FILE[currentPosition] && (candidateOffSet == -6 || candidateOffSet == 10);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.EIGHTH_FILE[currentPosition] && ((candidateOffSet == -15) || (candidateOffSet == -6) || (candidateOffSet == 10) || (candidateOffSet == 17));
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }
}
