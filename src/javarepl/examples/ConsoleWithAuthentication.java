package javarepl.examples;

import com.googlecode.totallylazy.Option;
import com.googlecode.totallylazy.Pair;
import javarepl.console.*;
import javarepl.console.rest.RestConsole;

import static com.googlecode.totallylazy.Sequences.sequence;
import static javarepl.console.ConsoleConfig.consoleConfig;
import static javarepl.console.ConsoleLog.Type.ERROR;
import static javarepl.console.ConsoleLog.Type.SUCCESS;
import static javarepl.console.commands.Command.parseStringCommand;

/**
 * Example shows how to write console wrapper that restricts JavaREPL access until user logs in with correct password.
 * More sophisticated (e.g. db or oauth backed) services can be created if needed.
 */
public class ConsoleWithAuthentication {

    public static void main(String... args) throws Exception {
        new RestConsole(new AuthConsole(new SimpleConsole(consoleConfig()), "password"), 8001);
    }

    public static class AuthConsole extends DelegatingConsole {
        private final String password;
        private boolean authenticated = false;

        protected AuthConsole(Console delegate, String password) {
            super(delegate);
            this.password = password;
        }

        @Override
        public ConsoleResult execute(String expression) {
            if (!authenticated) {
                if (expression.startsWith(":login")) {
                    Pair<String, Option<String>> command = parseStringCommand(expression);
                    if (!command.second().isEmpty()) {
                        if (command.second().get().equals(password)) {
                            authenticated = true;
                            return new ConsoleResult(expression, sequence(new ConsoleLog(SUCCESS, "Logged in")));
                        } else {
                            return new ConsoleResult(expression, sequence(new ConsoleLog(ERROR, "Invalid password")));
                        }
                    } else {
                        return new ConsoleResult(expression, sequence(new ConsoleLog(ERROR, "Please specify password")));
                    }
                } else {
                    return new ConsoleResult(expression, sequence(new ConsoleLog(ERROR, "Please authenticate first.\n    :login <password> to authenticate.\n    :logout at the end of the session to finish.")));
                }
            } else {
                if (expression.startsWith(":logout")) {
                    authenticated = false;
                    return new ConsoleResult(expression, sequence(new ConsoleLog(SUCCESS, "Logged out")));
                } else {
                    return super.execute(expression);
                }
            }
        }
    }
}
