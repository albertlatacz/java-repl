import repler.java.REPL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Repler {

    public static void main(String[] args) throws IOException {
        REPL javaREPL = new REPL();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        do {
            String line = console.readLine();
            if (line == null || line.equals("exit")) {
                exit(0);
            }

            if (!line.isEmpty()) {
                javaREPL.evaluate(line);
            }

        } while (true);

    }
}
