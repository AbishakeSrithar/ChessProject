package com.chessengine.player;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    },
    ILLEGALMOVE {
        @Override
        boolean isDone() {
            return false;
        }
    }, LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }
    };
    abstract boolean isDone();
}