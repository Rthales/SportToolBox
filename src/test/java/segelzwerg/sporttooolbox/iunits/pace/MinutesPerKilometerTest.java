package segelzwerg.sporttooolbox.iunits.pace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import segelzwerg.sporttooolbox.iunits.speed.KilometerPerHour;
import segelzwerg.sporttooolbox.iunits.speed.Speed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MinutesPerKilometerTest {

    private MinutesPerKilometer threeMinutesfifthteenPerKM;

    @BeforeEach
    public void setUp() {
        threeMinutesfifthteenPerKM = new MinutesPerKilometer(3.25f);
    }

    @Test
    public void convert_to_minutes_per_kilometer() {
        assertThat(threeMinutesfifthteenPerKM, equalTo(threeMinutesfifthteenPerKM.toMinutesPerKilometer()));
    }

    @Test
    public void convert_to_minutes_per_100_meters() {
        MinutesPerHundredMeters pace = (MinutesPerHundredMeters) threeMinutesfifthteenPerKM.toMinutesPerHundredMeters();
        MinutesPerHundredMeters expectedPace = new MinutesPerHundredMeters(0.325f);
        assertThat(pace, equalTo(expectedPace));
    }

    @Test
    public void convert_to_kph() {
        Speed speed = threeMinutesfifthteenPerKM.getSpeed();

        KilometerPerHour kilometerPerHour = new KilometerPerHour(18.4615f);

        assertThat(speed, equalTo(kilometerPerHour));
    }

    @Test
    public void convert_4_30_to_kph() {
        MinutesPerKilometer minutesPerKilometer = new MinutesPerKilometer(4.5f);

        Speed speed = minutesPerKilometer.getSpeed();

        KilometerPerHour expectedSpeed = new KilometerPerHour(13.3333f);

        assertThat(speed, equalTo(expectedSpeed));
    }
}