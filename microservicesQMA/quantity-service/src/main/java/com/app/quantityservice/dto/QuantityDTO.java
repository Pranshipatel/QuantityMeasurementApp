package com.app.quantityservice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "A quantity with a value and unit")
public class QuantityDTO {
    private static final Logger logger = Logger.getLogger(QuantityDTO.class.getName());

    interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }

    public enum LengthUnit implements IMeasurableUnit {
        FEET("FEET"), INCHES("INCHES"), YARDS("YARDS"), CENTIMETERS("CENTIMETERS");
        private final String unit;
        LengthUnit(String unit) { this.unit = unit; }
        @Override public String getUnitName() { return unit; }
        @Override public String getMeasurementType() { return "LengthUnit"; }
    }

    public enum VolumeUnit implements IMeasurableUnit {
        LITRE("LITRE"), MILLILITER("MILLILITER"), GALLON("GALLON");
        private final String unit;
        VolumeUnit(String unit) { this.unit = unit; }
        @Override public String getUnitName() { return unit; }
        @Override public String getMeasurementType() { return "VolumeUnit"; }
    }

    public enum WeightUnit implements IMeasurableUnit {
        MILLIGRAM("MILLIGRAM"), GRAM("GRAM"), KILOGRAM("KILOGRAM"), POUND("POUND"), TONNE("TONNE");
        private final String unit;
        WeightUnit(String unit) { this.unit = unit; }
        @Override public String getUnitName() { return unit; }
        @Override public String getMeasurementType() { return "WeightUnit"; }
    }

    public enum TemperatureUnit implements IMeasurableUnit {
        CELSIUS("CELSIUS"), FAHRENHEIT("FAHRENHEIT");
        private final String unit;
        TemperatureUnit(String unit) { this.unit = unit; }
        @Override public String getUnitName() { return unit; }
        @Override public String getMeasurementType() { return "TemperatureUnit"; }
    }

    @NotNull(message = "Value cannot be empty")
    @Schema(example = "1.0")
    public double value;

    @NotNull(message = "Unit cannot be null")
    @Schema(example = "FEET", allowableValues = {
            "FEET", "INCHES", "YARDS", "CENTIMETERS",
            "LITRE", "MILLILITER", "GALLON",
            "MILLIGRAM", "GRAM", "KILOGRAM", "POUND", "TONNE",
            "CELSIUS", "FAHRENHEIT"
    })
    public String unit;

    @NotNull(message = "Measurement type cannot be null")
    @Pattern(regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
             message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit")
    @Schema(example = "LengthUnit", allowableValues = {
            "LengthUnit", "VolumeUnit", "WeightUnit", "TemperatureUnit"
    })
    public String measurementType;

    public QuantityDTO(double value, IMeasurableUnit unit) {
        this.value = value;
        this.unit = unit.getUnitName();
        this.measurementType = unit.getMeasurementType();
    }

    @AssertTrue(message = "Unit must be valid for the specified measurement type")
    public boolean isValidUnit() {
        logger.info("Validating unit: " + unit + " for measurement type: " + measurementType);
        try {
            switch (measurementType) {
                case "LengthUnit": LengthUnit.valueOf(unit); break;
                case "VolumeUnit": VolumeUnit.valueOf(unit); break;
                case "WeightUnit": WeightUnit.valueOf(unit); break;
                case "TemperatureUnit": TemperatureUnit.valueOf(unit); break;
                default: return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
