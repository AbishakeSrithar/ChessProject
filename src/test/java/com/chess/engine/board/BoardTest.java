package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board.Builder;
import com.chess.engine.board.Move.MoveFactory;
import com.chess.engine.pieces.*;
import com.chess.engine.player.MoveTransition;
import com.google.common.collect.Iterables;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void initialBoard() {

        final Board board = Board.createStandardBoard();
        assertEquals(board.currentPlayer().getLegalMoves().size(), 20);
        assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().isCastled());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isCastled());
        assertEquals(board.whitePlayer().toString(), "White");
        assertEquals(board.blackPlayer().toString(), "Black");

        final Iterable<Move> allMoves = Iterables.concat(board.whitePlayer().getLegalMoves(), board.blackPlayer().getLegalMoves());
        for(final Move move : allMoves) {
            assertFalse(move.isAttack());
            assertFalse(move.isCastlingMove());
        }

        assertEquals(Iterables.size(allMoves), 40);
    }

    @Test
    public void testPlainKingMove() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(4, Alliance.BLACK,false));
        builder.setPiece(new Pawn(12, Alliance.BLACK));
        // White Layout
        builder.setPiece(new Pawn(52, Alliance.WHITE));
        builder.setPiece(new King(60, Alliance.WHITE, false));
        builder.setMoveMaker(Alliance.WHITE);
        // Set the current player
        final Board board = builder.build();
        System.out.println(board);

        assertEquals(board.whitePlayer().getLegalMoves().size(), 6);
        assertEquals(board.blackPlayer().getLegalMoves().size(), 6);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());

        final Move move = MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e1"),
                BoardUtils.INSTANCE.getCoordinateAtPosition("f1"));

        final MoveTransition moveTransition = board.currentPlayer()
                .makeMove(move);

        assert moveTransition.getMoveStatus().isDone();

    }

    @Test(expected=RuntimeException.class)
    public void testInvalidBoard() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Rook(0, Alliance.BLACK));
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK));
        builder.setPiece(new Queen(3, Alliance.BLACK));
        builder.setPiece(new Bishop(5, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK));
        builder.setPiece(new Rook(7, Alliance.BLACK));
        builder.setPiece(new Pawn(8, Alliance.BLACK));
        builder.setPiece(new Pawn(9, Alliance.BLACK));
        builder.setPiece(new Pawn(10, Alliance.BLACK));
        builder.setPiece(new Pawn(11, Alliance.BLACK));
        builder.setPiece(new Pawn(12, Alliance.BLACK));
        builder.setPiece(new Pawn(13, Alliance.BLACK));
        builder.setPiece(new Pawn(14, Alliance.BLACK));
        builder.setPiece(new Pawn(15, Alliance.BLACK));
        // White Layout
        builder.setPiece(new Pawn(48, Alliance.BLACK));
        builder.setPiece(new Pawn(49, Alliance.BLACK));
        builder.setPiece(new Pawn(50, Alliance.BLACK));
        builder.setPiece(new Pawn(51, Alliance.BLACK));
        builder.setPiece(new Pawn(52, Alliance.BLACK));
        builder.setPiece(new Pawn(53, Alliance.BLACK));
        builder.setPiece(new Pawn(54, Alliance.BLACK));
        builder.setPiece(new Pawn(55, Alliance.BLACK));
        builder.setPiece(new Rook(56, Alliance.BLACK));
        builder.setPiece(new Knight(57, Alliance.BLACK));
        builder.setPiece(new Bishop(58, Alliance.BLACK));
        builder.setPiece(new Queen(59, Alliance.BLACK));
        builder.setPiece(new Bishop(61, Alliance.BLACK));
        builder.setPiece(new Knight(62, Alliance.BLACK));
        builder.setPiece(new Rook(63, Alliance.BLACK));
        //white to move
        builder.setMoveMaker(Alliance.WHITE);
        //build the board
        builder.build();
    }

    @Test
    public void testAlgebreicNotation() {
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(0), "a8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(1), "b8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(2), "c8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(3), "d8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(4), "e8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(5), "f8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(6), "g8");
        assertEquals(BoardUtils.INSTANCE.getPositionAtCoordinate(7), "h8");
    }

    @Test
    public void mem() {
        final Runtime runtime = Runtime.getRuntime();
        long start, end;
        runtime.gc();
        start = runtime.freeMemory();
        Board board = Board.createStandardBoard();
        end =  runtime.freeMemory();
        System.out.println("That took " + (start-end) + " bytes.");

    }

}