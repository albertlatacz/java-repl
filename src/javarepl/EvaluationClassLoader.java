package javarepl;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.URLs.toURL;

public class EvaluationClassLoader extends URLClassLoader {
    private EvaluationClassLoader(URL[] urls) {
        super(urls);
    }

    public static EvaluationClassLoader evaluationClassLoader(File outputDirectory) {
        return new EvaluationClassLoader(new URL[]{toURL().apply(outputDirectory)});
    }

    @Override
    public void addURL(URL url) {
        if (!sequence(getURLs()).contains(url))
            super.addURL(url);
    }

    public boolean isClassLoaded(String name) {
        return findLoadedClass(name) != null;
    }
}
