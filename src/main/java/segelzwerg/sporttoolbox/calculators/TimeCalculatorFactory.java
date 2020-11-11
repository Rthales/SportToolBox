package segelzwerg.sporttoolbox.calculators;

import segelzwerg.sporttoolbox.iunits.distance.Distance;
import segelzwerg.sporttoolbox.iunits.distance.DistanceFactory;
import segelzwerg.sporttoolbox.iunits.pace.Pace;
import segelzwerg.sporttoolbox.iunits.pace.PaceFactory;
import segelzwerg.sporttoolbox.iunits.speed.Speed;
import segelzwerg.sporttoolbox.iunits.speed.SpeedFactory;
import segelzwerg.sporttoolbox.web.speed.SpeedForm;

public final class TimeCalculatorFactory {
    /**
     * prevents instantiating
     */
    private TimeCalculatorFactory() {
    }

    /**
     * builds a {@link TimeCalculator}
     *
     * @param speedForm {@link SpeedForm}
     * @param majorUnit major unit of the distance
     * @param minorUnit minor unit of the distance
     * @return {@link TimeCalculator}
     */
    public static TimeCalculator buildFromSpeed(SpeedForm speedForm, String majorUnit, String minorUnit) {
        int major = speedForm.getMajor();
        int minor = speedForm.getMinor();
        Distance distance = DistanceFactory.createWithOtherThanSIUnits(major, minor, majorUnit, minorUnit);

        float speed = speedForm.getSpeed();
        String speedUnit = speedForm.getSpeedUnit();
        Speed speedObject = SpeedFactory.createSpeedFromUnit(speed, speedUnit);

        return new TimeCalculator(distance, speedObject);
    }

    /**
     * buiilds a {@link TimeCalculator} for pace and distance
     *
     * @param paceForm  {@link SpeedForm} contains pace and distance values
     * @param majorUnit unit of the major distance unit
     * @param minorUnit unit of the minor distance unit
     * @return {@link TimeCalculator}
     */
    public static TimeCalculator buildFromPace(SpeedForm paceForm, String majorUnit, String minorUnit) {
        int major = paceForm.getMajor();
        int minor = paceForm.getMinor();
        Distance distance = DistanceFactory.createWithOtherThanSIUnits(major, minor, majorUnit, minorUnit);

        float pace = paceForm.getPace();
        String paceUnit = paceForm.getPaceUnit();
        Pace paceObject = PaceFactory.createPaceFromUnit(pace, paceUnit);

        return new TimeCalculator(distance, paceObject);
    }
}
