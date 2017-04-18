package roman.torsten.sample.bankservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankClientServiceTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void checkAccountNice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/account/nice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.id", is("nice")))
                .andExpect(jsonPath("$.bankId.id", is("RAIF")))
                .andExpect(jsonPath("$.balance.amount", is(91000)));
    }

}

