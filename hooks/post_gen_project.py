from __future__ import print_function
from cookiecutter.main import cookiecutter
from collections import OrderedDict

import os
import yaml
import logging


TERMINATOR = "\x1b[0m"
INFO = "\x1b[1;33m [INFO]: "
SUCCESS = "\x1b[1;32m [SUCCESS]: "
HINT = "\x1b[3;33m"

def setapplicationproperties(db, repo, app_folder):
    with open(app_folder,"a") as appFile:
        appFile.truncate(0)

        if db == 'mysql':
            with open(repo+"/mysqlDb.txt", "r") as sql:
                for line in sql:
                    appFile.write(line)
            sql.close()
        elif db == 'mongoDb':
            with open(repo+"/mongoDb.txt", "r") as mongo:
                for line in mongo:
                    appFile.write(line)
            mongo.close()
        elif db == 'postgreSql':
            with open(repo+"/postgresDb.txt", "r") as postgres:
                for line in postgres:
                    appFile.write(line)
            postgres.close()
        elif db == 'oracleDb':
            with open(repo+"/oracleDb.txt", "r") as oracle:
                for line in oracle:
                    appFile.write(line)
            oracle.close()
        else:
            with open(repo+"/h2Db.txt", "r") as h2Db:
                for line in h2Db:
                    appFile.write(line)
            h2Db.close()
        appFile.close()

def main():
    yaml_file = "../hid-project-generator/config.yaml"
    dependency_yaml_file = "../hid-project-generator/dependency.yaml"

    project_name = "{{- cookiecutter.project_name|trim|lower|replace(' ', '-') -}}"
    _pkg_name = "{{ cookiecutter.project_name|lower|replace(' ', '') }}"
    group_name = "{{ cookiecutter.group_name|lower|replace(' ', '.') }}"
    app_folder = "{{cookiecutter._templates_repo}}"+"{{cookiecutter._copy_without_render}}"+"/src/main/resources/application.properties"
    setapplicationproperties("{{cookiecutter.db}}", "{{cookiecutter._templates_repo}}", app_folder)
    read_from_file = {{ cookiecutter._read_from_file }}
    resources_name = "{{ cookiecutter.resource_name }}"

    dependency = []
    spring_version = "{{ cookiecutter.spring_version }}"
    java_version = {{ cookiecutter.java_version }}
    dependency_repo = {{ cookiecutter._dependency_repo }}

    try:
        with open(dependency_yaml_file, 'r') as dependency_stream:
            output = yaml.safe_load(dependency_stream)
            dependency_repo = output['dependency_repo']

        if read_from_file is True:
            resources_name = {{ cookiecutter._resources }}
            dependency = {{ cookiecutter._dependency }}
        else:
            resources_name = resources_name.split(" ")
    except FileNotFoundError as ex:
        logging.exception("File not found: %s", ex)
        raise ex
    except yaml.YAMLError as ex:
        logging.exception("Error Reading file: %s", yaml_file)
        raise ex
    except Exception as ex:
        logging.exception("Exception occurred while reading the file: %s", ex)
        raise ex


    templates_repo = "{{ cookiecutter._templates_repo }}"
    template_dir = os.path.join("templates")

    for resource in resources_name :
        cookiecutter(   templates_repo,
                        directory=template_dir,
                        no_input=True,
                        output_dir="..",
                        overwrite_if_exists=True,
                        extra_context={
                                        "project_name": project_name,
                                        "_pkg_name": _pkg_name,
                                        "group_name": group_name,
                                        "resource_name": resource.title(),
                                        "resource_name": resource.capitalize(),
                                        "_dependency": dependency,
                                        "spring_version": spring_version,
                                        "java_version": java_version,
                                        "_dependency_repo": dependency_repo,
                                        "_read_from_file": read_from_file
                                      }
                    )

    print(SUCCESS +
          "Project initialized successfully! You can now jump to {} folder".
          format(_pkg_name) + TERMINATOR)
    print(INFO +
          "{}/README.md contains instructions on how to proceed.".
          format(_pkg_name) + TERMINATOR)

    # remove everything from application.properties file
    with open(app_folder,"a") as appFile:
        appFile.truncate(0)
        appFile.close()

if __name__ == '__main__':
    main()
