package org.semyonan;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.semyonan.controllers.PersonController;
import org.semyonan.dto.PersonDto;
import org.semyonan.service.PersonService;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    private MockMvc mvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private JacksonTester<PersonDto> personJacksonTester;
    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(personController)
                .build();
    }

    @Test
    public void getAll() throws Exception {
        var person1 = new PersonDto(1L, "Anna", 20, 0);
        var person2 = new PersonDto(2L, "Maxim", 40, 0);

        given(personService.getAll())
                .willReturn(Stream.of(person1, person2).toList());

        MockHttpServletResponse response = mvc.perform(
                        get("/persons")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals("["+personJacksonTester.write(person1).getJson()+"," + personJacksonTester.write(person2).getJson() + "]",
                response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void getMaxAged() throws Exception {
        var person1 = new PersonDto(1L, "Anna", 20, 0);
        var person2 = new PersonDto(2L, "Maxim", 40, 0);

        given(personService.getMaxAged())
                .willReturn(person2);

        MockHttpServletResponse response = mvc.perform(
                        get("/getmaxaged")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(personJacksonTester.write(person2).getJson(),
                response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getAgeByName() throws Exception {
        var person1 = new PersonDto(1L, "Anna", 20, 0);
        var person2 = new PersonDto(2L, "Maxim", 40, 0);

        given(personService.getAgeByName("Anna"))
                .willReturn(20);

        MockHttpServletResponse response = mvc.perform(
                        get("/getage").param("name","Anna")
                                .accept(MediaType.ALL))
                .andReturn().getResponse();

        assertEquals("20",
                response.getContentAsString());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
