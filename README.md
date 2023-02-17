# HidCookieCutter

Update the `_templates_repo` in `cookiecutter.json` file with the relevant project repo path while using cloned local repo to generate the project, or else use **config file** to define the repo path.

### Running Project through user defined config file

Use the below command inorder to run:

1. Through Cloned local repo : -

`cookiecutter --no-input --config-file <config file path> <project-name>`

2. Through github directly : -

`cookiecutter --no-input --config-file <config file path> <github project url>`

### Running Project interactively through terminal/CLI

Use the below command inorder to run:

1. Through cloned local repo : -

`cookiecutter <project-name> OR cookiecutter <project-name> -v (for debug mode)`

2. Through github directly :-

`cookiecutter --config-file <config file path> <github project url>`

## Configuration file specs:

Use this to define necessary fields while creating project through user-defined config file.
``` 
    default_context:
        #  Provide the Project name which you wanted to create
        project_name: "project broadcast"
        
        #  Please don't change this
        _pkg_name: "{{ cookiecutter.project_name|lower|replace(' ', '') }}"
        
        #  Please don't change this
        _read_from_file: true
        
        #  Provide the Spring version which you wanted to use for your project from ["2.7.8", "3.0.2"]
        spring_version: "3.0.2"
        
        #  Provide the Java version which you wanted to use for your project from [8,11,17,19]
        java_version: 19
        
        #  Provide the DB name which you are going to use for your project from ["h2Db", "mysql", "mongoDb", "postgreSql", "oracleDb"]
        db: h2Db
        
        #  Provide all the required dependency for your project
        _dependency:
            - jdbc
        
        #  Provide all the resource Name which you are going to use in your project
        _resources:
            - Users
            - Customers
            - Orders
        _templates_repo: "<update with your project repo path>"
```

Or else use this configuration while trying to run project interactively through CLI.

```
    default_context:
        _templates_repo: "<update with your project repo path>"
```

### To access Swagger UI Use below URL
http://localhost:8080/swagger-ui/index.html

### To access h2 console use below URL
http://localhost:8080/h2-console/
