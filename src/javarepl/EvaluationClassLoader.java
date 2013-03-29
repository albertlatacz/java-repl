package javarepl;

import java.net.URL;
import java.net.URLClassLoader;

public class EvaluationClassLoader extends URLClassLoader{
    public EvaluationClassLoader(URL[] urls) {
        super(urls);
    }

    public boolean isClassLoaded(String name) {
        return findLoadedClass(name) != null;
    }
}
