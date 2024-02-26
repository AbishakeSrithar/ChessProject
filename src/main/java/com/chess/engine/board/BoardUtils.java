package com.chess.engine.board;

import java.util.*;

public enum BoardUtils {

    INSTANCE;

    // File means Column
    public final boolean[] FIRST_FILE = initColumn(1);
    public final boolean[] SECOND_FILE = initColumn(2);
    public final boolean[] THIRD_FILE = initColumn(3);
    public final boolean[] FOURTH_FILE = initColumn(4);
    public final boolean[] FIFTH_FILE = initColumn(5);
    public final boolean[] SIXTH_FILE = initColumn(6);
    public final boolean[] SEVENTH_FILE = initColumn(7);
    public final boolean[] EIGHTH_FILE = initColumn(8);

    // Ran Row
    public final boolean[] EIGHTH_RANK = initRow(1);
    public final boolean[] SEVENTH_RANK = initRow(2);
    public final boolean[] SIXTH_RANK = initRow(3);
    public final boolean[] FIFTH_RANK = initRow(4);
    public final boolean[] FOURTH_RANK = initRow(5);
    public final boolean[] THIRD_RANK = initRow(6);
    public final boolean[] SECOND_RANK = initRow(7);
    public final boolean[] FIRST_RANK = initRow(8);

    public final List<String> ALGEBRAIC_NOTATION = initialiseAlgebraicNotation();

    public final Map<String, Integer> POSITION_TO_COORDINATE = initialisePositionToCoordinateMap();
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;
    private static final int START_TILE_INDEX = 0;

//    private BoardUtils() {
//        throw new RuntimeException("Cannot instantiate!");
//    }

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[64];
        int columnNumberIndex = columnNumber - 1;
        do {
            column[columnNumberIndex] = true;
            columnNumberIndex += 8;
        } while(columnNumberIndex < 64);
        return column;
    }

    private static List<String> initialiseAlgebraicNotation() {
        return Collections.unmodifiableList(Arrays.asList(
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"));
    }

    private Map<String, Integer> initialisePositionToCoordinateMap() {
        final Map<String, Integer> positionToCoordinate = new HashMap<>();
        for (int i = START_TILE_INDEX; i < NUM_TILES; i++) {
            positionToCoordinate.put(ALGEBRAIC_NOTATION.get(i), i);
        }
        return Collections.unmodifiableMap(positionToCoordinate);
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[64];
        final int firstOfRow = (rowNumber - 1) * 8;
        for (int i = 0; i < NUM_TILES_PER_ROW; i++) {
            row[firstOfRow + i] = true;
        }
        return row;
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }

    public int getCoordinateAtPosition(final String position) {

        return POSITION_TO_COORDINATE.get(position);
    }

    public String getPositionAtCoordinate(final int coordinate) {
        return ALGEBRAIC_NOTATION.get(coordinate);
    }
}
