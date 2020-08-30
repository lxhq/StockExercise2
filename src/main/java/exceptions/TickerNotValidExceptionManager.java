package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TickerNotValidExceptionManager implements ExceptionMapper<TickerNotValidException> {
    @Override
    public Response toResponse(TickerNotValidException exception) {
        return Response.status(200).entity("This ticker is not valid").build();
    }
}
