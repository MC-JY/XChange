package org.knowm.xchange.bitfinex.v2;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.knowm.xchange.bitfinex.v2.dto.BitfinexExceptionV2;
import org.knowm.xchange.bitfinex.v2.dto.marketdata.BitfinexCandle;
import org.knowm.xchange.bitfinex.v2.dto.marketdata.BitfinexPublicFundingTrade;
import org.knowm.xchange.bitfinex.v2.dto.marketdata.BitfinexPublicTrade;
import org.knowm.xchange.bitfinex.v2.dto.marketdata.Status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("v2")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitfinex {

  @GET
  @Path("tickers")
  List<ArrayNode> getTickers(@QueryParam("symbols") String symbols)
      throws IOException, BitfinexExceptionV2;

  @GET
  @Path("status/{type}")
  List<Status> getStatus(@PathParam("type") String type, @QueryParam("keys") String symbols)
      throws IOException, BitfinexExceptionV2;

  @GET
  @Path("/trades/{symbol}/hist")
  BitfinexPublicFundingTrade[] getPublicFundingTrades(
      @PathParam("symbol") String fundingSymbol,
      @QueryParam("limit") int limit,
      @QueryParam("start") long startTimestamp,
      @QueryParam("end") long endTimestamp,
      @QueryParam("sort") int sort)
      throws IOException, BitfinexExceptionV2;

  @GET
  @Path("/trades/{symbol}/hist")
  BitfinexPublicTrade[] getPublicTrades(
      @PathParam("symbol") String fundingSymbol,
      @QueryParam("limit") int limit,
      @QueryParam("start") long startTimestamp,
      @QueryParam("end") long endTimestamp,
      @QueryParam("sort") int sort)
      throws IOException, BitfinexExceptionV2;

  @GET
  @Path("candles/trade:{candlePeriod}:{symbol}:{fundingPeriod}/hist")
  List<BitfinexCandle> getHistoricFundingCandles(
          @PathParam("candlePeriod") String candlePeriod,
          @PathParam("symbol") String currency,
          @PathParam("fundingPeriod") String fundingPeriod,
          @QueryParam("limit") int limit)
          throws IOException, BitfinexExceptionV2;
}
