package com.deloitte.{{cookiecutter._pkg_name}}.controller;

import com.deloitte.{{cookiecutter._pkg_name}}.domain.{{cookiecutter.resource_name}};
import com.deloitte.{{cookiecutter._pkg_name}}.service.{{cookiecutter.resource_name}}Service;
import com.deloitte.{{cookiecutter._pkg_name}}.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.deloitte.{{cookiecutter._pkg_name}}.util.{{cookiecutter.resource_name}}TestUtil.create{{cookiecutter.resource_name}};
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({{cookiecutter.resource_name}}Controller.class)
class {{cookiecutter.resource_name}}ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    {{cookiecutter.resource_name}}Service {{cookiecutter.resource_name|lower}}Service;

    @Autowired
    private ObjectMapper objectMapper;

    private {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = new {{cookiecutter.resource_name}}();

    private List<{{cookiecutter.resource_name}}> {{cookiecutter.resource_name|lower}}List = new ArrayList<>();

    @BeforeEach
    public void init() {
        {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name1");
        {{cookiecutter.resource_name|lower}}List.add(create{{cookiecutter.resource_name}}(2L,"name2"));
        {{cookiecutter.resource_name|lower}}List.add(create{{cookiecutter.resource_name}}(3L,"name3"));
    }


    @Test
    public void get{{cookiecutter.resource_name}}Test() throws Exception {

        when({{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}())
        .thenReturn({{cookiecutter.resource_name|lower}}List);

        mockMvc.perform(get("/{{cookiecutter.resource_name|lower}}"))
                .andExpect(status().isOk());

        Assertions.assertEquals(2,{{cookiecutter.resource_name|lower}}List.size());
    }

    @Test
    public void get{{cookiecutter.resource_name}}ByIdTest() throws Exception {

        when({{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}ById(any(Long.class)))
                .thenReturn({{cookiecutter.resource_name|lower}});

        this.mockMvc.perform(get("/{{cookiecutter.resource_name|lower}}/{id}",1L)).andExpectAll(
                status().isOk()
        );

        Assertions.assertNotNull({{cookiecutter.resource_name|lower}});
    }

    @Test
    void save{{cookiecutter.resource_name}}TestSuccess() throws Exception {
        when({{cookiecutter.resource_name|lower}}Service.save{{cookiecutter.resource_name}}(any({{cookiecutter.resource_name}}.class)))
        .thenReturn({{cookiecutter.resource_name|lower}});

        mockMvc.perform(post("/{{cookiecutter.resource_name|lower}}")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString({{cookiecutter.resource_name|lower}})))
        .andExpectAll(status().isOk());

        Assertions.assertNotNull({{cookiecutter.resource_name|lower}});
        }

    @Test
    public void update{{cookiecutter.resource_name}}Test() throws Exception {

        when({{cookiecutter.resource_name|lower}}Service.update{{cookiecutter.resource_name}}(any(Long.class), any({{cookiecutter.resource_name}}.class)))
                .thenReturn({{cookiecutter.resource_name|lower}});

        mockMvc.perform(get("/{{cookiecutter.resource_name|lower}}/{id}",1L)
            .content(objectMapper.writeValueAsString({{cookiecutter.resource_name|lower}})))
            .andExpectAll(status().isOk());

        Assertions.assertNotNull({{cookiecutter.resource_name|lower}});
    }

    @Test
    public void delete{{cookiecutter.resource_name}}Test() throws Exception {
        doNothing()
                .when({{cookiecutter.resource_name|lower}}Service).delete{{cookiecutter.resource_name}}ById(any(Long.class));
        mockMvc.perform(get("/{{cookiecutter.resource_name|lower}}/{id}",1L))
        .andExpectAll(status().isOk());
        }
    @Test
    public void delete{{cookiecutter.resource_name}}TestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"))
        .when({{cookiecutter.resource_name|lower}}Service).delete{{cookiecutter.resource_name}}ById(any(Long.class));
        mockMvc.perform(delete("/{{cookiecutter.resource_name|lower}}/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

    @Test
    public void update{{cookiecutter.resource_name}}TestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"))
        .when({{cookiecutter.resource_name|lower}}Service).update{{cookiecutter.resource_name}}(1L,{{cookiecutter.resource_name|lower}});
        mockMvc.perform(put("/{{cookiecutter.resource_name|lower}}/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

    @Test
    public void get{{cookiecutter.resource_name}}ByIdTestFailure()throws Exception{

        doThrow(new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"))
        .when({{cookiecutter.resource_name|lower}}Service).get{{cookiecutter.resource_name}}ById(1L);
        mockMvc.perform(get("/{{cookiecutter.resource_name|lower}}/{id}",1L))
        .andExpectAll(status().is4xxClientError());
        }

}