package io.pivotal.escqrs.apiservice.widget;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Matt Stine
 */
@RunWith(SpringRunner.class)
@WebMvcTest(WidgetController.class)
public class WidgetApiTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getShouldReturnAllWidgets() throws Exception {
        List<Widget> widgets = Arrays.asList(new Widget(1L,"Larry"),
                new Widget(2L, "Moe"), new Widget(3L, "Curly"));

        this.mvc.perform(get("/widgets")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(asJson(widgets)));
    }

    @Test
    public void getWithIdShouldReturnTheCorrectWidget() throws Exception {
        Widget widget = new Widget(1L, "Larry");

        this.mvc.perform(get("/widgets/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(asJson(widget)));
    }

    @Test
    public void postWithBodyShouldCreateWidget() throws Exception {
        Widget widget = new Widget(4L, "Walter");

        this.mvc.perform(post("/widgets")
                .accept(MediaType.APPLICATION_JSON).content(asJson(widget))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/widgets/4"))
                .andExpect(content().json(asJson(widget)));
    }

    private String asJson(Object widgets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(widgets);
    }
}
