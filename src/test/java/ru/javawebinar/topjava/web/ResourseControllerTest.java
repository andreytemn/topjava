package ru.javawebinar.topjava.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by atemnikov on 26.10.2016.
 */
public class ResourseControllerTest extends AbstractControllerTest {

    @Test
    public void testStyle() throws Exception
    {
        mockMvc.perform(get("/resource/css/style.css"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_MARKDOWN));
    }
}
