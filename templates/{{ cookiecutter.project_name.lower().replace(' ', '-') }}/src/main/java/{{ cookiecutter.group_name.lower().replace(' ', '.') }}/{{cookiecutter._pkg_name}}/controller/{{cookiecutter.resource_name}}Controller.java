package com.deloitte.{{cookiecutter._pkg_name}}.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.deloitte.{{cookiecutter._pkg_name}}.domain.{{cookiecutter.resource_name}};
import com.deloitte.{{cookiecutter._pkg_name}}.service.{{cookiecutter.resource_name}}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class {{cookiecutter.resource_name}}Controller {


    @Autowired
    private {{cookiecutter.resource_name}}Service {{cookiecutter.resource_name|lower}}Service;

    @Operation(summary = "Get the list of {{cookiecutter.resource_name|lower}}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the {{cookiecutter.resource_name|lower}}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = {{cookiecutter.resource_name}}.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No {{cookiecutter.resource_name}}s found",
                    content = @Content) })
    @GetMapping("/{{cookiecutter.resource_name|lower}}")
    public List<{{cookiecutter.resource_name}}> get{{cookiecutter.resource_name}}() {
        return {{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}();
    }

    @Operation(summary = "Get the {{cookiecutter.resource_name|lower}} by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the {{cookiecutter.resource_name|lower}}",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = {{cookiecutter.resource_name}}.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "{{cookiecutter.resource_name}} not found",
                    content = @Content) })
    @GetMapping("/{{cookiecutter.resource_name|lower}}/{id}")
    public {{cookiecutter.resource_name}} get{{cookiecutter.resource_name}}ById(@PathVariable Long id) {
        return {{cookiecutter.resource_name|lower}}Service.get{{cookiecutter.resource_name}}ById(id);
    }

    @Operation(summary = "Save the {{cookiecutter.resource_name|lower}} with the provided request body content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "{{cookiecutter.resource_name|lower}} is saved",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = {{cookiecutter.resource_name}}.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/{{cookiecutter.resource_name|lower}}")
    public {{cookiecutter.resource_name}} save{{cookiecutter.resource_name}}(@RequestBody {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}) {
        return {{cookiecutter.resource_name|lower}}Service.save{{cookiecutter.resource_name}}({{cookiecutter.resource_name|lower}});
    }

    @Operation(summary = "Update the {{cookiecutter.resource_name|lower}} with the provided request body content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "{{cookiecutter.resource_name|lower}} is updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = {{cookiecutter.resource_name}}.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PutMapping("/{{cookiecutter.resource_name|lower}}/{id}")
    public {{cookiecutter.resource_name}} update{{cookiecutter.resource_name}}(@PathVariable Long id, @RequestBody {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}) {
        return {{cookiecutter.resource_name|lower}}Service.update{{cookiecutter.resource_name}}(id, {{cookiecutter.resource_name|lower}});
    }

    @Operation(summary = "Delete the {{cookiecutter.resource_name|lower}} by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "{{cookiecutter.resource_name|lower}} is deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = {{cookiecutter.resource_name}}.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "{{cookiecutter.resource_name}} not found",
                    content = @Content) })
    @DeleteMapping("/{{cookiecutter.resource_name|lower}}/{id}")
    public void delete{{cookiecutter.resource_name}}(@PathVariable Long id) {
        {{cookiecutter.resource_name|lower}}Service.delete{{cookiecutter.resource_name}}ById(id);
    }
}
