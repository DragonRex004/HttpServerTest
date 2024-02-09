package de.dragonrex.console.command;

public enum CommandResponse {
    SUCCESS(0),
    WARNING(1),
    ERROR(2),
    NONE(3);

    private final int key;

    CommandResponse(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
