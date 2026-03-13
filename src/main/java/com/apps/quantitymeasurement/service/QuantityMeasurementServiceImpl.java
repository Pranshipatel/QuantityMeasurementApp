package com.apps.quantitymeasurement.service;



import com.apps.quantitymeasurement.entity.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.entity.QuantityModel;
import com.apps.quantitymeasurement.quantity.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.IMeasurable;
import com.apps.quantitymeasurement.unit.LengthUnit;
import com.apps.quantitymeasurement.unit.TemperatureUnit;
import com.apps.quantitymeasurement.unit.VolumeUnit;
import com.apps.quantitymeasurement.unit.WeightUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;

	// constructor
	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);

		validateModels(model1, model2);

		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		double difference = q1.compare(q1, q2);

		boolean result = difference == 0;

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "COMPARE", difference,
				q1.getUnit().toString(), thisQuantityDTO.getMeasurementType());

		repository.save(entity);

		return result;
	}

	@Override
	public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO targetUnitDTO) {

		QuantityModel<IMeasurable> sourceModel = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> targetModel = mapToModel(targetUnitDTO);

		validateModels(sourceModel, targetModel);

		Quantity<IMeasurable> quantity = new Quantity<>(sourceModel.getValue(), sourceModel.getUnit());

		double convertedValue = quantity.convertTo(targetModel.getUnit());

		IMeasurable targetUnit = targetModel.getUnit();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), 0, targetUnit.toString(),
				thisQuantityDTO.getMeasurementType(), "CONVERT", convertedValue, targetUnit.toString(),
				thisQuantityDTO.getMeasurementType());

		repository.save(entity);

		return new QuantityDTO(convertedValue, targetUnit.toString(), thisQuantityDTO.getMeasurementType());
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		// Convert QuantityDTO instances to QuantityModel instances (Mapping Strings to
		// Objects)
		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);

		// Validate operands (non-null, same category, finite)
		validateModels(model1, model2);

		// Create Quantity<IMeasurable> objects from the QuantityModel instances
		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		// Call q1.add(q2)
		Quantity<IMeasurable> resultQuantity = q1.add(q2);

		// Extract result value and unit
		double resultValue = resultQuantity.getValue();
		String resultUnitName = resultQuantity.getUnit().toString();

		// Store in repository as QuantityMeasurementEntity
		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "ADD", resultValue, resultUnitName,
				thisQuantityDTO.getMeasurementType());
		repository.save(entity);

		System.out.println(
				thisQuantityDTO.getValue() + " " + thisQuantityDTO.getUnit() + " + " + thatQuantityDTO.getValue() + " "
						+ thatQuantityDTO.getUnit() + " = " + resultValue + " " + resultUnitName);
		// Return new QuantityDTO for the response
		return new QuantityDTO(resultValue, resultUnitName, thisQuantityDTO.getMeasurementType());
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {

		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);
		QuantityModel<IMeasurable> targetModel = mapToModel(targetUnitDTO);

		validateModels(model1, model2);

		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		Quantity<IMeasurable> resultValue = q1.add(q2);

		IMeasurable targetUnit = targetModel.getUnit();

		double convertedValue = resultValue.convertTo(targetUnit);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "ADD", convertedValue,
				targetUnit.toString(), thisQuantityDTO.getMeasurementType());

		repository.save(entity);

		return new QuantityDTO(convertedValue, targetUnit.toString(), thisQuantityDTO.getMeasurementType());
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		// Convert QuantityDTO instances to QuantityModel instances (Mapping Strings
		// to Objects)
		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);

		// Validate operands (non-null, same category, finite)
		validateModels(model1, model2);

		// Create Quantity<IMeasurable> objects from the QuantityModel instances
		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		// Call q1.subtract(q2)
		Quantity<IMeasurable> resultQuantity = q1.subtract(q2);

		// Extract result value and unit
		double resultValue = resultQuantity.getValue();
		String resultUnitName = resultQuantity.getUnit().toString();

		// Store in repository as QuantityMeasurementEntity
		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "SUBTRACT", resultValue,
				resultUnitName, thisQuantityDTO.getMeasurementType());
		repository.save(entity);

		// Return new QuantityDTO for the response
		return new QuantityDTO(resultValue, resultUnitName, thisQuantityDTO.getMeasurementType());
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {

		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);
		QuantityModel<IMeasurable> targetModel = mapToModel(targetUnitDTO);

		validateModels(model1, model2);

		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		Quantity<IMeasurable> resultValue = q1.subtract(q2);

		IMeasurable targetUnit = targetModel.getUnit();

		double convertedValue = resultValue.convertTo(targetUnit);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "SUBTRACT", convertedValue,
				targetUnit.toString(), thisQuantityDTO.getMeasurementType());

		repository.save(entity);

		return new QuantityDTO(convertedValue, targetUnit.toString(), thisQuantityDTO.getMeasurementType());
	}

	@Override
	public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {

		QuantityModel<IMeasurable> model1 = mapToModel(thisQuantityDTO);
		QuantityModel<IMeasurable> model2 = mapToModel(thatQuantityDTO);

		validateModels(model1, model2);

		Quantity<IMeasurable> q1 = new Quantity<>(model1.getValue(), model1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(model2.getValue(), model2.getUnit());

		double resultValue = q1.divide(q2);

		IMeasurable resultUnit = model1.getUnit();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisQuantityDTO.getValue(),
				thisQuantityDTO.getUnit(), thisQuantityDTO.getMeasurementType(), thatQuantityDTO.getValue(),
				thatQuantityDTO.getUnit(), thatQuantityDTO.getMeasurementType(), "DIVIDE", resultValue,
				resultUnit.toString(), thisQuantityDTO.getMeasurementType());

		repository.save(entity);

		return new QuantityDTO(resultValue, resultUnit.toString(), thisQuantityDTO.getMeasurementType());
	}

	// Helper to map DTO (Strings) to Model (Actual Unit Enums)

	private QuantityModel<IMeasurable> mapToModel(QuantityDTO dto) {
		String type = dto.getMeasurementType().toUpperCase();
		String unitName = dto.getUnit().toUpperCase();
		IMeasurable unit;

		switch (type) {
		case "LENGTH":
			unit = LengthUnit.valueOf(unitName);
			break;
		case "VOLUME":
			unit = VolumeUnit.valueOf(unitName);
			break;
		case "WEIGHT":
			unit = WeightUnit.valueOf(unitName);
			break;
		case "TEMPERATURE":
			unit = TemperatureUnit.valueOf(unitName);
			break;
		default:
			throw new IllegalArgumentException("Unknown Measurement Type: " + type);
		}
		return new QuantityModel<>(dto.getValue(), unit);
	}

	/**
	 * Validation logic as requested in the flow diagram
	 */
	private void validateModels(QuantityModel<?> m1, QuantityModel<?> m2) {
		if (m1 == null || m2 == null)
			throw new IllegalArgumentException("Operands cannot be null");

		if (m1.getUnit().getClass() != m2.getUnit().getClass())
			throw new IllegalArgumentException("Cannot perform operation on different categories");

		if (!Double.isFinite(m1.getValue()) || !Double.isFinite(m2.getValue()))
			throw new IllegalArgumentException("Values must be finite");
	}
}