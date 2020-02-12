package segelzwerg.sporttooolbox.calculators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import segelzwerg.sporttooolbox.iunits.Distance;
import segelzwerg.sporttooolbox.iunits.Time;
import segelzwerg.sporttooolbox.iunits.pace.MinutesPerKilometer;
import segelzwerg.sporttooolbox.iunits.speed.KilometerPerHour;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TimeCalculatorTest {

    private Distance sixtyKilometer;

    @BeforeEach
    public void setUp() {
        sixtyKilometer = new Distance(60);
    }

    /**
     * test for 60km with 12kph
     *
     * @result {@link Time} with 5 hours, 0 minutes and 0 seconds
     */
    @Test
    public void sixty_kilometer_with_twelve_kph() {
        KilometerPerHour twelveKPH = new KilometerPerHour(12);
        Time expectedTime = new Time(5, 0, 0);

        TimeCalculator timeCalculator = new TimeCalculator(sixtyKilometer, twelveKPH);
        Time time = timeCalculator.computeTime();

        assertThat(time, equalTo(expectedTime));
    }

    @Test
    public void sixty_kilometer_with_four_min_per_km() {
        MinutesPerKilometer fiveMinutesPerKilometer = new MinutesPerKilometer(4);
        Time expectedTime = new Time(4, 0, 0);

        TimeCalculator timeCalculator = new TimeCalculator(sixtyKilometer, fiveMinutesPerKilometer);
        Time time = timeCalculator.computeTime();

        assertThat(time, equalTo(expectedTime));

    }
}