package com.chessengine.player;

import com.chessengine.Alliance;
import com.chessengine.board.Board;
import com.chessengine.board.Move;
import com.chessengine.board.Move.KingSideCastleMove;
import com.chessengine.board.Move.QueenSideCastleMove;
import com.chessengine.board.Tile;
import com.chessengine.pieces.Piece;
import com.chessengine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {

        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            if (!this.board.getTile(61).isTileOccupied() &&
                !this.board.getTile(62).isTileOccupied()) {
                // White's King side castle
                final Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board,
                                                               this.playerKing,
                                                               62,
                                                               (Rook)rookTile.getPiece(),
                                                               rookTile.getTileCoordinate(),
                                                               61));
                    }
                }
            }
            // White's Queen side castle
            if (!this.board.getTile(59).isTileOccupied() &&
                !this.board.getTile(58).isTileOccupied() &&
                !this.board.getTile(57).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    kingCastles.add(new QueenSideCastleMove(this.board,
                                                            this.playerKing,
                                                            58,
                                                            (Rook)rookTile.getPiece(),
                                                            rookTile.getTileCoordinate(),
                                                            59));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
