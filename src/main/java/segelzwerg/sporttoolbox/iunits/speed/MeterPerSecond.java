package segelzwerg.sporttoolbox.iunits.speed;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import segelzwerg.sporttoolbox.iunits.Time;
import segelzwerg.sporttoolbox.iunits.distance.Distance;

/**
 * Speed in meter per second
 */
@Getter
@Setter
@EqualsAndHashCode
public class MeterPerSecond implements Speed {
    public static final String UNIT = "meter per second";
    private final float speed;

    MeterPerSecond(float speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed must not be negative.");
        }
        this.speed = speed;
    }

    /**
     * Convert to kilometer per hour
     *
     * @return speed in kilometer per hour
     */
    @Override
    public Speed toKilometerPerHour() {
        return new KilometerPerHour(speed * Speed.METER_PER_SECOND_TO_KILOMETER_PER_HOUR);
    }

    /**
     * Convert to meter per second
     *
     * @return speed in meter per second
     */
    @Override
    public Speed toMeterPerSecond() {
        return this;
    }

    /**
     * Convert to mile per hour
     *
     * @return speed in mile per hour
     */
    @Override
    public Speed toMilePerHour() {
        return new MilePerHour(speed * Speed.METER_PER_SECOND_TO_KILOMETER_PER_HOUR / Speed.MILE_PER_HOUR_TO_KILOMETER_PER_HOUR);
    }

    /**
     * Convert to knot
     *
     * @return speed in knot
     */
    @Override
    public Speed toKnot() {
        return new Knot(speed * Speed.METER_PER_SECOND_TO_KILOMETER_PER_HOUR / Speed.KNOT_TO_KILOMETER_PER_HOUR);
    }

    /**
     * formats the decimal to 2 digits
     *
     * @return a new MeterPerSecond
     */
    @Override
    public Speed format() {
        return new MeterPerSecond((float) (Math.round(speed * 100.0) / 100.0));
    }

    /**
     * @param kilometer integer of the distance in kilometer
     * @param meter     decimal of distance as integer
     * @return
     */
    @Override
    public Time computeTime(float kilometer, float meter) {
        float distance = kilometer * 1000 + meter;
        float time = (distance / speed);
        return new Time(0, 0, (int) time);
    }

    @Override
    public Distance computeDistance(Time time) {
        return null;
    }
}
