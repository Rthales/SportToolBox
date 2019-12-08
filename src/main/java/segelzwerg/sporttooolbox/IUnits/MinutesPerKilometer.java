package segelzwerg.sporttooolbox.IUnits;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Pace of minutes per kilometers
 */
@Getter
@ToString
@EqualsAndHashCode
public class MinutesPerKilometer implements Pace {
    private final float pace;

    public MinutesPerKilometer(float pace) {
        this.pace = pace;
    }

    /**
     * Converting to minutes per kilometers
     * @return itself, since there's no conversion to be made
     */
    @Override
    public Pace toMinutesPerKilometer() {
        return this;
    }

    /**
     * Converting to minutes per hundred meters
     * @return Pace instance with a converted numeric value
     */
    @Override
    public Pace toMinutesPerHundredMeters() {
        return new MinutesPerHundredMeters(pace * PER_KILOMETER_TO_PER_HUNDRED_METER);
    }
}