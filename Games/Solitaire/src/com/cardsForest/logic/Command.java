package com.cardsForest.logic;

public enum Command {

    START("start"), DEAL("deal"), PAINT("paint");

    private String command;

    Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
