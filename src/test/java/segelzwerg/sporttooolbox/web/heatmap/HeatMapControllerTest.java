package segelzwerg.sporttooolbox.web.heatmap;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

import static io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.postForm;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeatMapControllerTest {

    @InjectMocks
    HeatMapController heatMapController;
    @Mock
    private MultipartFile file;
    @Mock
    private HeatMapService heatMapService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void speed_load_english() throws Exception {
        Locale.setDefault(Locale.US);
        mockMvc.perform(get("/heatmap"))
                .andExpect(status().isOk());
    }

    @Test
    void speed_load_german() throws Exception {
        Locale.setDefault(Locale.GERMANY);
        mockMvc.perform(get("/heatmap"))
                .andExpect(status().isOk());
    }

    @Test
    void upload_test() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(heatMapController).build();
        doNothing().when(heatMapService).uploadFile(isA(MultipartFile.class));
        mockMvc.perform(postForm("/heatmap/upload", file))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void generate_test() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(heatMapController).build();
        doNothing().when(heatMapService).generate();
        mockMvc.perform(post("/heatmap/generate"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void generate_exception() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(heatMapController).build();
        doThrow(new UnirestException("")).when(heatMapService).generate();
        assertThrows(Exception.class, () -> mockMvc.perform(post("/heatmap/generate"))
                .andReturn());
    }
}