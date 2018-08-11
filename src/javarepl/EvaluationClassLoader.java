package javarepl;

import com.googlecode.totallylazy.Sequence;

import java.net.URL;
import java.net.URLClassLoader;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.googlecode.totallylazy.io.URLs.toURL;

public class EvaluationClassLoader extends URLClassLoader {
    private Sequence<URL> registeredUrls = sequence();

    private EvaluationClassLoader(EvaluationContext context, ClassLoader parentCL) {
        super(new URL[]{toURL().apply(context.outputDirectory())}, parentCL);
    }

    public static EvaluationClassLoader evaluationClassLoader(EvaluationContext context, ClassLoader parentCL) {
        return new EvaluationClassLoader(context, parentCL);
    }

    public static EvaluationClassLoader evaluationClassLoader(EvaluationContext context) {
        return new EvaluationClassLoader(context, context.getClass().getClassLoader());
    }

    public void registerURL(URL url) {
        if (!sequence(getURLs()).contains(url)) {
            addURL(url);
            registeredUrls = registeredUrls.append(url);
        }
    }

    public Sequence<URL> registeredUrls() {
        return registeredUrls;
    }



    public boolean isClassLoaded(String name) {
        return findLoadedClass(name) != null;
    }
}
