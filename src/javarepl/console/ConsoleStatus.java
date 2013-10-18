package javarepl.console;

public enum ConsoleStatus {
    Idle, Starting, Running, Terminating, Terminated;

    public Boolean isRunning() {
        return this == Running;
    }
}
