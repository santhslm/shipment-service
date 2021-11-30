package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;

@Component
public class ShipmentServiceSplitter {

	private static final Logger SPLITTER_LOGGER = LoggerFactory.getLogger(ShipmentServiceSplitter.class);

	public List<CancelShipmentOrderRequest> splitForCancelOrder(
			@Header(name = "trackingId", required = false) String trackingId,
			CancelShipmentOrderRequest cancelShipmentOrderRequest) {
		SPLITTER_LOGGER.info("Start of ShipmentServiceSplitter::splitForCancelOrder method >> ");
		List<CancelShipmentOrderRequest> list = new ArrayList<>();
		list.add(cancelShipmentOrderRequest);
		list.add(cancelShipmentOrderRequest);
		SPLITTER_LOGGER.info("End of ShipmentServiceSplitter::splitForCancelOrder method >> ");
		return list;
	}

}
