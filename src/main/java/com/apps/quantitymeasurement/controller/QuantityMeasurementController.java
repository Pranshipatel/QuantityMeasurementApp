package com.apps.quantitymeasurement.controller;

import java.util.logging.Logger;

import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementController {
	//Logger for logging information and errors in the controller
	private static final Logger logger = Logger.getLogger(QuantityMeasurementController.class.getName());

	// Private Instance of IQuantityMeasurementService interface
	private IQuantityMeasurementService quantityMeasurementService;

	// Parameterized Constructor
	public QuantityMeasurementController(QuantityMeasurementServiceImpl quantityMeasurementService) {
		this.quantityMeasurementService = quantityMeasurementService;
		logger.info("QauntityMeasurementController initialized with service: " + quantityMeasurementService);
	}

	// Method to perform comparison that call compare service
	public boolean performComparison(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		return quantityMeasurementService.compare(thisQuantityDTO, thatQuantityDTO);
	}

	// Method to perform conversion that calls convert service
	public QuantityDTO performConversion(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		return quantityMeasurementService.convert(thisQuantityDTO, thatQuantityDTO);
	}

	// Method to perform addition that calls add service
	public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO);
	}

	// Method to perform addition with target result unit that calls add services
	public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetUnitDTO) {
		return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO, targetUnitDTO);
	}

	// Method to perform subtraction that calls subtract service
	public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO);
	}

	// Method to perform subtraction with target result unit that calls subtract
	// service
	public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetUnitDTO) {
		return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO, targetUnitDTO);
	}

	// Method to perform division that calls divide service
	public QuantityDTO performDivision(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		return quantityMeasurementService.divide(thisQuantityDTO, thatQuantityDTO);
	}
}