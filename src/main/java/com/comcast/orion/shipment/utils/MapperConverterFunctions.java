package com.comcast.orion.shipment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comcast.orion.shipment.omw.request.ShippingOptions;

public class MapperConverterFunctions {

	private static final Logger logger = LoggerFactory.getLogger(MapperConverterFunctions.class);

	public static String enumToStringConverter(ShippingOptions.Method method) {
		logger.info("MapperConverterFunctions::enumToStringConverter::start");
		return method.value();
	}

}
