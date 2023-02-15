package com.deloitte.{{cookiecutter._pkg_name}}.service.serviceImpl;

import com.deloitte.{{cookiecutter._pkg_name}}.domain.{{cookiecutter.resource_name}};
import com.deloitte.{{cookiecutter._pkg_name}}.repository.{{cookiecutter.resource_name}}Repository;
import com.deloitte.{{cookiecutter._pkg_name}}.service.{{cookiecutter.resource_name}}Service;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.{{cookiecutter._pkg_name}}.exception.ResourceNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class {{cookiecutter.resource_name}}ServiceImpl implements {{cookiecutter.resource_name}}Service {


    @Autowired
    {{cookiecutter.resource_name}}Repository {{cookiecutter.resource_name|lower}}Repository;

    @Override
    public {{cookiecutter.resource_name}} save{{cookiecutter.resource_name}}({{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}) {
        return {{cookiecutter.resource_name|lower}}Repository.save({{cookiecutter.resource_name|lower}});
    }

    @Override
    public {{cookiecutter.resource_name}} update{{cookiecutter.resource_name}}(Long id, {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}) {
        {{cookiecutter.resource_name}} {{cookiecutter.resource_name|lower}}Obj = {{cookiecutter.resource_name|lower}}Repository.findById(id)
                                       .orElseThrow(() -> new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"));
        {{cookiecutter.resource_name|lower}}Obj.set{{cookiecutter.resource_name | title}}Name({{cookiecutter.resource_name|lower}}.get{{cookiecutter.resource_name | title}}Name());
        return {{cookiecutter.resource_name|lower}}Repository.save({{cookiecutter.resource_name|lower}}Obj);
    }

    @Override
    public void delete{{cookiecutter.resource_name}}ById(Long id) {
        {{cookiecutter.resource_name|lower}}Repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"));
        {{cookiecutter.resource_name|lower}}Repository.deleteById(id);
    }

    @Override
    public List<{{cookiecutter.resource_name}}>get{{cookiecutter.resource_name}}() {
        return {{cookiecutter.resource_name|lower}}Repository.findAll();
    }

    @Override
    public {{cookiecutter.resource_name}} get{{cookiecutter.resource_name}}ById(Long id) {
        return {{cookiecutter.resource_name|lower}}Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("{{cookiecutter.resource_name}} Not Found"));
    }
}