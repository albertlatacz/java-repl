package javarepl;

import javarepl.client.EvaluationLog;
import javarepl.client.EvaluationResult;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class ResultPrinter {
    private final boolean printColored;

    public ResultPrinter(boolean printColored) {
        this.printColored = printColored;
    }

    public void printError(String message) {
        AnsiConsole.err.println(ansiColored(message));
    }

    public void printInfo(String message) {
        AnsiConsole.out.println(ansiColored(message));
    }

    public void printEvaluationResult(EvaluationResult result) {
        for (EvaluationLog log : result.logs()) {
            printEvaluationLog(log);
        }
    }

    public void printEvaluationLog(EvaluationLog log) {
        switch (log.type()) {
            case INFO:
                printInfo("\u001B[0m" + log.message() + "\u001B[0m");
                break;
            case SUCCESS:
                printInfo("\u001B[32m" + log.message() + "\u001B[0m");
                break;
            case ERROR:
                printError("\u001B[31m" + log.message() + "\u001B[0m");
                break;
        }
    }

    public String ansiColored(String message) {
        if (!printColored) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            AnsiOutputStream ansiStream = new AnsiOutputStream(byteArrayOutputStream);
            try {
                ansiStream.write(message.getBytes());
            } catch (IOException e) {
            }
            return byteArrayOutputStream.toString();
        }

        return message;
    }

}
