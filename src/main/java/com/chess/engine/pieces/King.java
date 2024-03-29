package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorAttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
    private final boolean isCastled;
    public final boolean kingSideCastleCapable;
    public final boolean queenSideCastleCapable;

    public King(final int piecePosition,
                final Alliance pieceAlliance,
                final boolean kingSideCastleCapable,
                final boolean queenSideCastleCapable
                ) {
        super(PieceType.KING, piecePosition, pieceAlliance, true);
        this.isCastled = false;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    public King(final int piecePosition,
                final Alliance pieceAlliance,
                final Boolean isFirstMove,
                final Boolean isCastled,
                final boolean kingSideCastleCapable,
                final boolean queenSideCastleCapable) {
        super(PieceType.KING, piecePosition, pieceAlliance, isFirstMove);
        this.isCastled = isCastled;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    public boolean isCastled() {
        return this.isCastled;
    }

    public boolean kingSideCastleCapable() {
        return this.kingSideCastleCapable;
    }

    public boolean queenSideCastleCapable() {
        return this.queenSideCastleCapable;
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
                        legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public King movePiece(final Move move) {
        return new King(move.getDestinationCoordinate(), move.getMovedPiece().pieceAlliance, false, move.isCastlingMove(), false, false);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.FIRST_FILE[currentPosition] && (candidateOffSet == -9 || candidateOffSet == -1 || candidateOffSet == 7);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffSet) {
        return BoardUtils.INSTANCE.EIGHTH_FILE[currentPosition] && (candidateOffSet == -7 || candidateOffSet == 1 || candidateOffSet == 9);
    }

    private static boolean isFirstRowExclusion(final int currentPosition, final int candidateOffSet) { // Not in video but seems like it should be here?
        return BoardUtils.INSTANCE.EIGHTH_RANK[currentPosition] && (candidateOffSet == -9 || candidateOffSet == -8 || candidateOffSet == -7);
    }

    private static boolean isEightRowExclusion(final int currentPosition, final int candidateOffSet) { // Not in video but seems like it should be here?
        return BoardUtils.INSTANCE.FIRST_RANK[currentPosition] && (candidateOffSet == 7 || candidateOffSet == 8 || candidateOffSet == 9);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    public boolean isKingSideCastleCapable() {
        return false;
    }

    public boolean isQueenSideCastleCapable() {
        return false;
    }
}
