package segelzwerg.sporttoolbox.web.speed;

import org.junit.jupiter.api.Test;
import segelzwerg.sporttoolbox.iunits.speed.KilometerPerHour;
import segelzwerg.sporttoolbox.iunits.speed.Knot;
import segelzwerg.sporttoolbox.iunits.speed.MilePerHour;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SpeedPresenterTest {
    @Test
    void constructorTest() {
        KilometerPerHour kilometerPerHour = new KilometerPerHour((float) 30.567);
        SpeedPresenter presenter = new SpeedPresenter(kilometerPerHour);

        KilometerPerHour expectedKilometerPerHour = new KilometerPerHour((float) 30.57);
        MilePerHour expectedMilePerHour = new MilePerHour((float) 18.99);
        Knot expectedKnots = new Knot((float) 16.50);

        assertThat(presenter.getKilometerPerHour(), equalTo(expectedKilometerPerHour));
        assertThat(presenter.getMilesPerHour(), equalTo(expectedMilePerHour));
        assertThat(presenter.getKnots(), equalTo(expectedKnots));
    }
}