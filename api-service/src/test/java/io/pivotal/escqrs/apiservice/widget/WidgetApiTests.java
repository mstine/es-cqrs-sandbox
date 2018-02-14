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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        String widgetsJson = asJson(widgets);

        this.mvc.perform(get("/widgets").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(widgetsJson));
    }

    @Test
    public void getWithIdShouldReturnTheCorrectWidget() throws Exception {
        Widget widget = new Widget(1L, "Larry");
        String widgetJson = asJson(widget);

        this.mvc.perform(get("/widgets/{id}", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(widgetJson));
    }

    private String asJson(Object widgets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(widgets);
    }
}
