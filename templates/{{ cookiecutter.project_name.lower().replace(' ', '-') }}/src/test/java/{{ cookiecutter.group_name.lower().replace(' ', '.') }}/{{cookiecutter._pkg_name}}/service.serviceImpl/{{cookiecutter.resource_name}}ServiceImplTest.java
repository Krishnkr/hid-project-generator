package com.deloitte.{{cookiecutter._pkg_name}}.service.serviceImpl;

import com.deloitte.{{cookiecutter._pkg_name}}.domain.{{cookiecutter.resource_name}};
import com.deloitte.{{cookiecutter._pkg_name}}.repository.{{cookiecutter.resource_name}}Repository;
import com.deloitte.{{cookiecutter._pkg_name}}.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.deloitte.{{cookiecutter._pkg_name}}.util.{{cookiecutter.resource_name}}TestUtil.create{{cookiecutter.resource_name}};
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class {{cookiecutter.resource_name}}ServiceImplTest {

    @InjectMocks
    {{cookiecutter.resource_name}}ServiceImpl {{cookiecutter.resource_name|lower}}Service;

    @Mock
    {{cookiecutter.resource_name}}Repository {{cookiecutter.resource_name|lower}}Repository;


    @Test
    void save{{cookiecutter.resource_name}}() {

        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name");
        Mockito.when({{cookiecutter.resource_name|lower}}Repository.save(any({{cookiecutter.resource_name}}.class)))
                .thenReturn({{cookiecutter.resource_name|lower}});
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}1 = {{cookiecutter.resource_name|lower}}Service.save{{cookiecutter.resource_name}}({{cookiecutter.resource_name|lower}});
        Assertions.assertNotNull({{cookiecutter.resource_name|lower}}1);
    }

    @Test
    void update{{cookiecutter.resource_name}}() {
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name");
        Mockito.when({{cookiecutter.resource_name|lower}}Repository.findById(any(Long.class)))
                .thenReturn(Optional.of({{cookiecutter.resource_name|lower}}));
        Mockito.when({{cookiecutter.resource_name|lower}}Repository.save(any())).thenReturn({{cookiecutter.resource_name|lower}});
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}1 = {{cookiecutter.resource_name|lower}}Service.update{{cookiecutter.resource_name}}(1L,{{cookiecutter.resource_name|lower}});

        Assertions.assertEquals({{cookiecutter.resource_name|lower}}.get{{cookiecutter.resource_name}}Name(),{{cookiecutter.resource_name|lower}}1.get{{cookiecutter.resource_name}}Name());
    }

    @Test
    void delete{{cookiecutter.resource_name}}ById() {
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name");
        Mockito.when({{cookiecutter.resource_name|lower}}Repository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of({{cookiecutter.resource_name|lower}}));
        Mockito.doNothing()
                .when({{cookiecutter.resource_name|lower}}Repository).deleteById(Mockito.any(Long.class));

        {{cookiecutter.resource_name|lower}}Service.delete{{cookiecutter.resource_name}}ById(1L);

        verify({{cookiecutter.resource_name|lower}}Repository, times(1)).deleteById(1L);

    }

    @Test
    void get{{cookiecutter.resource_name}}() {

        List<{{cookiecutter.resource_name}}> {{cookiecutter.resource_name|lower}}List = new ArrayList<>();
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}1 = create{{cookiecutter.resource_name}}(1L,"name1");
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}2 = create{{cookiecutter.resource_name}}(2L,"name2");
        {{cookiecutter.resource_name|lower}}List.add({{cookiecutter.resource_name|lower}}1);
        {{cookiecutter.resource_name|lower}}List.add({{cookiecutter.resource_name|lower}}2);

        Mockito.when({{cookiecutter.resource_name|lower}}Repository.findAll()).thenReturn({{cookiecutter.resource_name|lower}}List);

        List<{{cookiecutter.resource_name}}> {{cookiecutter.resource_name|lower}} = {{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}();

        Assertions.assertEquals({{cookiecutter.resource_name|lower}}List.size(),{{cookiecutter.resource_name|lower}}.size());

    }

    @Test
    void get{{cookiecutter.resource_name}}ById() {
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name");

        Mockito.when({{cookiecutter.resource_name|lower}}Repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of({{cookiecutter.resource_name|lower}}));

        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}1 = {{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}ById(1L);

        Assertions.assertEquals({{cookiecutter.resource_name|lower}}.get{{cookiecutter.resource_name}}Name(),{{cookiecutter.resource_name|lower}}1.get{{cookiecutter.resource_name}}Name());
    }

    @Test
    void update{{cookiecutter.resource_name}}Failure() {
            {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}} = create{{cookiecutter.resource_name}}(1L,"name");
            {{cookiecutter.resource_name|lower}}Service.save{{cookiecutter.resource_name}}({{cookiecutter.resource_name|lower}});
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> {{cookiecutter.resource_name|lower}}Service.update{{cookiecutter.resource_name}}(2L,{{cookiecutter.resource_name|lower}}));

        assertEquals("{{cookiecutter.resource_name}} Not Found", resourceNotFoundException.getMessage());
        }

    @Test
    void get{{cookiecutter.resource_name}}ByIdFailure() {
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> {{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}ById(anyLong()));

        assertEquals("{{cookiecutter.resource_name}} Not Found", resourceNotFoundException.getMessage());
        }

    @Test
    void delete{{cookiecutter.resource_name}}ByIdFailure() {
            ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
        () -> {{cookiecutter.resource_name|lower}}Service.delete{{cookiecutter.resource_name}}ById(anyLong()));

        assertEquals("{{cookiecutter.resource_name}} Not Found", resourceNotFoundException.getMessage());
        }
}