package segelzwerg.sporttoolbox.calculators;

import segelzwerg.sporttoolbox.iunits.Time;
import segelzwerg.sporttoolbox.iunits.distance.Distance;
import segelzwerg.sporttoolbox.iunits.distance.DistanceFactory;
import segelzwerg.sporttoolbox.web.speed.SpeedForm;

public final class SpeedCalculatorFactory {
    /**
     * prevents instantiating
     */
    private SpeedCalculatorFactory() {
    }

    /**
     * builds a Speed Calculator
     *
     * @param form      {@link SpeedForm} must contain distance and their units as wel as time
     * @param majorUnit should be inside SpeedForm
     * @param minorUnit should be inside SpeedForm
     * @return {@link SpeedCalculator}
     */
    public static SpeedCalculator build(SpeedForm form, @Deprecated String majorUnit, @Deprecated String minorUnit) {
        int major = form.getMajor();
        int minor = form.getMinor();
        Distance distance = DistanceFactory.createWithOtherThanSIUnits(major, minor, majorUnit, minorUnit);

        int hour = form.getHour();
        int minute = form.getMinute();
        int second = form.getSecond();
        Time time = new Time(hour, minute, second);

        return new SpeedCalculator(distance, time);
    }
}
