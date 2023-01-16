package info.novatec.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ControllerServletRequest.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class ControllerServletRequestMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void oneParameter() throws Exception {
        mvc.perform(get("/servletRequestArrays?array=1,2,3,4,5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]", is("1,2,3,4,5")));
    }

    @Test
    void parameterTwoTimes() throws Exception {
        mvc.perform(get("/servletRequestArrays?array=1,2&array=3,4,5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("1,2")))
                .andExpect(jsonPath("$[1]", is("3,4,5")));
    }

    @Test
    void parameterThreeTimes() throws Exception {
        mvc.perform(get("/servletRequestArrays?array=1,2&array=3&array=4,5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("1,2")))
                .andExpect(jsonPath("$[1]", is("3")))
                .andExpect(jsonPath("$[2]", is("4,5")));
    }
}
