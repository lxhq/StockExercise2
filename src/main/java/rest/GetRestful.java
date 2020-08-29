package rest;

import model.Repository.Repository;
import model.stock.Stock;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/stocks")
public class GetRestful {

    @Inject
    Repository repository;

    @Path("/allStocks")
    @GET
    public Response getAllStocks() {
        System.out.println("Get All Stocks");
        List<Stock> list = repository.getAllStocks();
        String res = list.stream().map(a -> a.getSymbol()).collect(Collectors.joining(", "));
        return Response.ok(res).build();
    }

    @POST
    @Path("/createStock")
    public Response createStock(final String ticker) {
        System.out.println("Create stock " + ticker);
        if (repository.createStock(ticker)) {
            return Response.ok("Create " + ticker + " successfully").build();
        } else {
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/buyShares")
    public Response buyShares() {
        return null;
    }


    @DELETE
    @Path("/deleteStock")
    public Response deleteStock(final String ticker) {
        System.out.println("delete stock " + ticker);
        if (repository.deleteStock(ticker)) {
            return Response.ok("Delete " + ticker + " successfully").build();
        } else {
            return Response.status(500).build();
        }
    }

    //TODO
    @PUT
    @Path("/buyShare")
    public Response busStock() {
        return null;
    }

    //TODO
    @GET
    @Path("/stock")
    public Response getStock() {
        return null;
    }

}
