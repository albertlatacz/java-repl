package javarepl.console;

import com.googlecode.totallylazy.Predicates;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static javarepl.console.ConsoleLog.Type.INFO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class ConsoleLogOutputStreamTest {
    @Test
    public void printLogsAllTypesOfMessage() {
        ConsoleLogger logger = new ConsoleLogger();
        ConsoleLogOutputStream stream = new ConsoleLogOutputStream(INFO, Predicates.<String>never(), logger, System.out);

        stream.print(true);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "true")));

        stream.print('a');
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "a")));

        stream.print(1);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "1")));

        stream.print(2L);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "2")));

        stream.print(3.5f);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "3.5")));

        stream.print(4.5d);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "4.5")));

        stream.print(new char[]{'h', 'e', 'l', 'l', 'o'});
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "hello")));

        stream.print("world");
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "world")));

        stream.print(new BigDecimal("5.5"));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "5.5")));
    }

    @Test
    public void printlnLogsAllTypesOfMessage() {
        ConsoleLogger logger = new ConsoleLogger();
        ConsoleLogOutputStream stream = new ConsoleLogOutputStream(INFO, Predicates.<String>never(), logger, System.out);

        stream.println(true);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "true")));

        stream.println('a');
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "a")));

        stream.println(1);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "1")));

        stream.println(2L);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "2")));

        stream.println(3.5f);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "3.5")));

        stream.println(4.5d);
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "4.5")));

        stream.println(new char[]{'h', 'e', 'l', 'l', 'o'});
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "hello")));

        stream.println("world");
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "world")));

        stream.println(new BigDecimal("5.5"));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "5.5")));
    }


    @Test
    public void appendLogsAllTypesOfMessage() {
        ConsoleLogger logger = new ConsoleLogger();
        ConsoleLogOutputStream stream = new ConsoleLogOutputStream(INFO, Predicates.<String>never(), logger, System.out);

        stream.append('h').append("ello").append("big world", 3, 9);

        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "h")));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "ello")));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, " world")));
    }

    @Test
    public void printfLogsAllTypesOfMessage() {
        ConsoleLogger logger = new ConsoleLogger();
        ConsoleLogOutputStream stream = new ConsoleLogOutputStream(INFO, Predicates.<String>never(), logger, System.out);

        stream.printf("%s is %d.", "universe", 42).printf(Locale.UK, "%s %d..", "hello", 42);

        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "universe is 42.")));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "hello 42..")));
    }


    @Test
    public void formatLogsAllTypesOfMessage() {
        ConsoleLogger logger = new ConsoleLogger();
        ConsoleLogOutputStream stream = new ConsoleLogOutputStream(INFO, Predicates.<String>never(), logger, System.out);

        stream.format("%s is %d.", "universe", 42).format(Locale.UK, "%s %d..", "hello", 42);

        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "universe is 42.")));
        assertThat(logger.logs(), hasItem(new ConsoleLog(INFO, "hello 42..")));
    }
}
