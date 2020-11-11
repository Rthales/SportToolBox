package segelzwerg.sporttoolbox.iunits.pace;

import segelzwerg.sporttoolbox.iunits.speed.Speed;

/**
 * Pace interface
 */
public interface Pace {
    float KILO = 1000.0f;
    float PER_KILOMETER_TO_PER_HUNDRED_METER = 0.1f;
    float PER_HUNDRED_METER_TO_PER_KILOMETER = 10.0f;
    float PER_MILE_TO_PER_KM = 0.621371f;
    float PER_MILE_TO_PER_100_M = 0.0621371f;

    /**
     * Converts to minutes per kilometers
     *
     * @return an instance of Pace
     */
    Pace toMinutesPerKilometer();

    /**
     * Converts to minutes per hundred-meters
     *
     * @return an instance of Pace
     */
    Pace toMinutesPerHundredMeters();

    /**
     * Converts to minutes per mile
     *
     * @return an instance of Pace
     */
    Pace toMinutesPerMile();

    /**
     * converts pace to speed
     *
     * @return {@link segelzwerg.sporttoolbox.iunits.speed.MeterPerSecond} as it is SI
     */
    Speed getSpeed();
}
