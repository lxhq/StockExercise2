package rest;

import exceptions.TickerNotValidException;
import json.BuyShare;
import model.repository.Repository;
import model.stock.Stock;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/stocks")
public class GetRestful {

    @Inject
    Repository repository;

    @Path("/allStocks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStocks() {
        List<Stock> list = repository.getAllStocks();
        GenericEntity<List<Stock>> listWrapper = new GenericEntity<>(list){};
        return Response
                .status(Response.Status.OK)
                .entity(listWrapper)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @GET
    @Path("/stock/{ticker}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStock(@PathParam("ticker") String ticker) throws TickerNotValidException {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(repository.getStock(ticker))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TickerNotValidException();
        }
    }

    @POST
    @Path("/createStock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStock(final String ticker) throws TickerNotValidException {
        try {
            repository.createStock(ticker);
            return Response
                    .status(Response.Status.OK)
                    .entity(repository.getStock(ticker))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TickerNotValidException();
        }
    }

    @DELETE
    @Path("/deleteStock")
    public Response deleteStock(final String ticker) throws TickerNotValidException {
        try {
            repository.deleteStock(ticker);
            return Response
                    .ok("Delete " + ticker + " successfully")
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TickerNotValidException();
        }
    }

    @PUT
    @Path("/buyShares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyShares(@Valid final BuyShare buyShare) throws TickerNotValidException {
        try {
            Stock stock = repository.getStock(buyShare.getTicker());
            stock.addShare(buyShare.getShares().doubleValue(), LocalDate.parse(buyShare.getDate()));
            return Response
                    .status(Response.Status.OK)
                    .entity(stock)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new TickerNotValidException();
        }
    }
}
