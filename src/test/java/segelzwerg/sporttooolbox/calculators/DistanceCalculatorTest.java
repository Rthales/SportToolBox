package segelzwerg.sporttooolbox.calculators;

import org.junit.jupiter.api.Test;
import segelzwerg.sporttooolbox.iunits.Time;
import segelzwerg.sporttooolbox.iunits.distance.Distance;
import segelzwerg.sporttooolbox.iunits.distance.Kilometer;
import segelzwerg.sporttooolbox.iunits.distance.Miles;
import segelzwerg.sporttooolbox.iunits.distance.Nautical;
import segelzwerg.sporttooolbox.iunits.speed.KilometerPerHour;
import segelzwerg.sporttooolbox.iunits.speed.Knot;
import segelzwerg.sporttooolbox.iunits.speed.MilePerHour;
import segelzwerg.sporttooolbox.iunits.speed.Speed;

import static org.assertj.core.api.Assertions.assertThat;

public class DistanceCalculatorTest {
    @Test
    public void thirtyKilometer() {
        Speed speed = new KilometerPerHour(30f);
        Time oneHour = new Time(1, 0, 0);
        DistanceCalculator distanceCalculator = new DistanceCalculator(speed, oneHour);
        Distance expectedDistance = new Kilometer(30);

        Distance kilometer = distanceCalculator.computeDistance();

        assertThat(kilometer).isEqualToComparingFieldByField(expectedDistance);
    }

    @Test
    public void sixtyMiles() {
        Speed speed = new MilePerHour(120f);
        Time thirtyMinutes = new Time(0, 30, 0);
        DistanceCalculator distanceCalculator = new DistanceCalculator(speed, thirtyMinutes);
        Distance expectedDistance = new Miles(60, 0);

        Distance distance = distanceCalculator.computeDistance();

        assertThat(distance).isEqualToComparingFieldByField(expectedDistance);
    }

    @Test
    public void fortyNautical() {
        Speed speed = new Knot(20);
        Time twoHours = new Time(2, 0, 0);
        DistanceCalculator distanceCalculator = new DistanceCalculator(speed, twoHours);
        Nautical expectedDistance = new Nautical(40);

        Distance distance = distanceCalculator.computeDistance();

        assertThat(distance).isEqualToComparingFieldByField(expectedDistance);
    }
}