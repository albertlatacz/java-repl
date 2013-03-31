package javarepl;

import java.net.URL;
import java.net.URLClassLoader;

public class EvaluationClassLoader extends URLClassLoader{
    public EvaluationClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public boolean isClassLoaded(String name) {
        return findLoadedClass(name) != null;
    }
}
