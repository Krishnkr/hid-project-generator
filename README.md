# HidCookieCutter

Update the `_templates_repo` with the relevant project repo.

### Note: 
The config file which is to be created by user must be named `config.yaml` and the location of the file should be in the parent directory, i.e. `./<project_name>/config.yaml`


### Running Project through user defined config file

Use the below command inorder to run:

`cookiecutter --no-input --config-file <config file path> <project-name>`

### Running Project interactively through terminal/CLI

Use the below command inorder to run:

`cookiecutter <project-name>`

Use the below command to run in debug mode:

`cookiecutter <project-name> -v`

### To access Swagger UI Use below URL
http://localhost:8080/swagger-ui/index.html

### To access h2 console use below URL
http://localhost:8080/h2-console/
