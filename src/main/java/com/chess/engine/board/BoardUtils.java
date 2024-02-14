package com.chess.engine.board;

import java.util.Map;

public class BoardUtils {

    // File means Column
    public static final boolean[] FIRST_FILE = initColumn(1);
    public static final boolean[] SECOND_FILE = initColumn(2);
    public static final boolean[] THIRD_FILE = initColumn(3);
    public static final boolean[] FOURTH_FILE = initColumn(4);
    public static final boolean[] FIFTH_FILE = initColumn(5);
    public static final boolean[] SIXTH_FILE = initColumn(6);
    public static final boolean[] SEVENTH_FILE = initColumn(7);
    public static final boolean[] EIGHTH_FILE = initColumn(8);

    // Rank means Row
    public static final boolean[] EIGHTH_RANK = initRow(1);
    public static final boolean[] SEVENTH_RANK = initRow(2);
    public static final boolean[] SIXTH_RANK = initRow(3);
    public static final boolean[] FIFTH_RANK = initRow(4);
    public static final boolean[] FOURTH_RANK = initRow(5);
    public static final boolean[] THIRD_RANK = initRow(6);
    public static final boolean[] SECOND_RANK = initRow(7);
    public static final boolean[] FIRST_RANK = initRow(8);

    public static final String[] ALGEBRAIC_NOTATION = initialiseAlgebraicNotation();
    public static final Map<String, Integer> POSITION_TO_COORDINATE = initialisePositionToCoordinateMap();
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("Cannot instantiate!");
    }

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[64];
        int columnNumberIndex = columnNumber - 1;
        do {
            column[columnNumberIndex] = true;
            columnNumberIndex += 8;
        } while(columnNumberIndex < 64);
        return column;
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

    public static int getCoordinateAtPosition(final String position) {
        return POSITION_TO_COORDINATE.get(position);
    }

    public static int getPositionAtCoordinate(final int coordinate) {
        return ALGEBRAIC_NOTATION[coordinate];
    }
}
