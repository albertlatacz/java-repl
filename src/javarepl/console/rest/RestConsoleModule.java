package javarepl.console.rest;

import com.googlecode.utterlyidle.Resources;
import com.googlecode.utterlyidle.handlers.ResponseHandlers;
import com.googlecode.utterlyidle.modules.ResourcesModule;
import com.googlecode.utterlyidle.modules.ResponseHandlersModule;
import com.googlecode.yadic.Container;

import java.util.Map;

import static com.googlecode.totallylazy.io.URLs.packageUrl;
import static com.googlecode.totallylazy.predicates.Predicates.*;
import static com.googlecode.utterlyidle.dsl.DslBindings.bindings;
import static com.googlecode.utterlyidle.dsl.StaticBindingBuilder.in;
import static com.googlecode.utterlyidle.handlers.HandlerRule.entity;

public class RestConsoleModule implements ResourcesModule, ResponseHandlersModule {
    @Override
    public Resources addResources(Resources resources) throws Exception {
        return resources.add(bindings(in(packageUrl(RestConsoleResource.class)).path("")));
    }

    @Override
    public ResponseHandlers addResponseHandlers(ResponseHandlers handlers, Container requestScope) throws Exception {
        return handlers.add(where(entity(), is(instanceOf(Map.class))), MapToJsonResponseHandler.class);
    }

}
