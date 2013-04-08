package javarepl.commands;

import com.googlecode.totallylazy.Function1;
import com.googlecode.totallylazy.Function2;
import com.googlecode.totallylazy.Predicate;
import javarepl.Evaluator;
import jline.console.completer.Completer;

public abstract class Command extends Function2<Evaluator, String, Void> {
    private final String description;
    private final Predicate<String> predicate;
    private final Completer completer;

    protected Command(String description, Predicate<String> predicate, Completer completer) {
        this.description = description;
        this.predicate = predicate;
        this.completer = completer;
    }

    public final Completer completer() {
        return completer;
    }

    public final String description() {
        return description;
    }

    public final Predicate<String> predicate() {
        return predicate;
    }

    public static enum functions {
        ;

        public static Function1<Command, String> description() {
            return new Function1<Command, String>() {
                public String call(Command command) throws Exception {
                    return command.description();
                }
            };
        }

        public static Function1<Command, Completer> completer() {
            return new Function1<Command, Completer>() {
                public Completer call(Command command) throws Exception {
                    return command.completer();
                }
            };
        }
    }


}
