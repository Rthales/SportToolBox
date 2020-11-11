package segelzwerg.sporttoolbox.web.pace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import segelzwerg.sporttoolbox.web.speed.SpeedForm;

import java.util.Locale;

import static io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.postForm;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private SpeedForm paceForm;

    public static Locale[] locations() {
        return new Locale[]{Locale.US, Locale.GERMANY};
    }

    @BeforeEach
    void setUp() {
        paceForm = new SpeedForm();
        paceForm.setMajor(10);
        paceForm.setHour(1);
        paceForm.setDistanceMajorUnit("kilometer");
        paceForm.setPaceUnit("minutesPerKilometer");
    }

    @ParameterizedTest
    @MethodSource("locations")
    void paceContextLoad(Locale locale) throws Exception {
        Locale.setDefault(locale);
        mockMvc.perform(get("/pace"))
                .andExpect(status().isOk());
    }


    @ParameterizedTest
    @MethodSource("locations")
    void paceCalculatingTest(Locale locale) throws Exception {
        Locale.setDefault(locale);
        MockHttpServletRequestBuilder builder = postForm("/pace", paceForm);
        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("locations")
    void autoDistanceTest(Locale locale) throws Exception {
        Locale.setDefault(locale);
        mockMvc.perform(post("/pace/autodistance")
                .param("distance", "50km"))
                .andExpect(status().is3xxRedirection());
    }

    @ParameterizedTest
    @MethodSource("locations")
    public void timeCalculationTest(Locale locale) throws Exception {
        Locale.setDefault(locale);

        SpeedForm speedForm = new SpeedForm();
        speedForm.setMajor(100);
        speedForm.setDistanceMajorUnit("kilometer");
        speedForm.setDistanceMinorUnit("meter");
        speedForm.setHour(0);
        speedForm.setMinute(0);
        speedForm.setSecond(0);
        speedForm.setPace(4.3f);
        speedForm.setPaceUnit("minutesPerKilometer");

        MockHttpServletRequestBuilder builder = postForm("/pace", speedForm);

        mockMvc.perform(builder).andExpect(status().isOk());
    }
}