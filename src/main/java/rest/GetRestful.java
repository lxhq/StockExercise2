package rest;

import json.BuyShare;
import model.repository.Repository;
import model.stock.Stock;

import javax.inject.Inject;
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
    public Response getStock(@PathParam("ticker") String ticker) {
        Stock stock = repository.getStock(ticker);
        return Response
                .status(Response.Status.OK)
                .entity(stock)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    @Path("/createStock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStock(final String ticker) {

        if (repository.createStock(ticker)) {
            return Response
                    .status(Response.Status.OK)
                    .entity(repository.getStock(ticker))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }

    @DELETE
    @Path("/deleteStock")
    public Response deleteStock(final String ticker) {
        if (repository.deleteStock(ticker)) {
            return Response
                    .ok("Delete " + ticker + " successfully")
                    .build();
        } else {
            return Response
                    .status(500)
                    .build();
        }
    }

    @PUT
    @Path("/buyShares")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyShares(BuyShare buyShare) {
        Stock stock = repository.getStock(buyShare.getTicker());
        stock.addShare(buyShare.getShares(), LocalDate.parse(buyShare.getDate()));
        return Response
                .status(Response.Status.OK)
                .entity(stock)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
