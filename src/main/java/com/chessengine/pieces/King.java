package com.chessengine.pieces;

import com.chessengine.Alliance;
import com.chessengine.board.Board;
import com.chessengine.board.BoardUtils;
import com.chessengine.board.Move;
import com.chessengine.board.Move.AttackMove;
import com.chessengine.board.Move.MajorMove;
import com.chessengine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEightColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isFirstRowExclusion(this.piecePosition, currentCandidateOffset) || // Not in video but seems like it should be here?
                        isEightRowExclusion(this.piecePosition, currentCandidateOffset)) { // Not in video but seems like it should be here?
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffSet == -9 || candidateOffSet == -1 || candidateOffSet == 7);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffSet == -7 || candidateOffSet == 1 || candidateOffSet == 9);
    }

    private static boolean isFirstRowExclusion(final int currentPosition, final int candidateOffSet) { // Not in video but seems like it should be here?
        return BoardUtils.FIRST_ROW[currentPosition] && (candidateOffSet == -9 || candidateOffSet == -8 || candidateOffSet == -7);
    }

    private static boolean isEightRowExclusion(final int currentPosition, final int candidateOffSet) { // Not in video but seems like it should be here?
        return BoardUtils.EIGHTH_ROW[currentPosition] && (candidateOffSet == 7 || candidateOffSet == 8 || candidateOffSet == 9);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
