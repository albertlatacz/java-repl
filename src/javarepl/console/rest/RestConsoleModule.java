package javarepl.console.rest;

import com.googlecode.utterlyidle.Resources;
import com.googlecode.utterlyidle.modules.ResourcesModule;

import static com.googlecode.totallylazy.URLs.packageUrl;
import static com.googlecode.utterlyidle.dsl.DslBindings.bindings;
import static com.googlecode.utterlyidle.dsl.StaticBindingBuilder.in;

public class RestConsoleModule implements ResourcesModule {
    @Override
    public Resources addResources(Resources resources) throws Exception {
        return resources.add(bindings(in(packageUrl(RestConsoleResource.class)).path("")));
    }
}
