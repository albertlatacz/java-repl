package javarepl.console.rest;

import com.googlecode.totallylazy.json.Json;
import com.googlecode.utterlyidle.Response;
import com.googlecode.utterlyidle.ResponseHandler;

public class MapToJsonResponseHandler implements ResponseHandler {
    public Response handle(Response response) throws Exception {
        return response.entity(Json.json(response.entity().value()));
    }
}
