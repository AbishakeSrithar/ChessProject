package com.chess.engine.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void initialBoard() {
        final Board board = Board.createStandardBoard();
        assertEquals(board.currentPlayer().getLegalMoves().size(), 20); // White: 8 pawns (1 step and 2 step) and 2 knights with 2 each
        assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20); // Black: 8 pawns (1 step and 2 step) and 2 knights with 2 each
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().isCastled());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isCastled());
        assertFalse(board.currentPlayer().isCastled());
    }

    @Test
    public void testToString() {
    }

    @Test
    public void whitePlayer() {
    }

    @Test
    public void blackPlayer() {
    }

    @Test
    public void getBlackPieces() {
    }

    @Test
    public void getWhitePieces() {
    }

    @Test
    public void getTile() {
    }

    @Test
    public void getTransitionMove() {
    }

    @Test
    public void getEnpassantPawn() {
    }

    @Test
    public void createStandardBoard() {
    }

    @Test
    public void currentPlayer() {
    }

    @Test
    public void getAllLegalMoves() {
    }
}