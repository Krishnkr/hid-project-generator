from __future__ import print_function
from cookiecutter.main import cookiecutter

import os
import shutil
import yaml
import logging


TERMINATOR = "\x1b[0m"
INFO = "\x1b[1;33m [INFO]: "
SUCCESS = "\x1b[1;32m [SUCCESS]: "
HINT = "\x1b[3;33m"

def main():

    project_name = "{{- cookiecutter.project_name|trim|lower|replace(' ', '-') -}}"
    _pkg_name = "{{ cookiecutter.project_name|lower|replace(' ', '') }}"
    group_name = "{{ cookiecutter.group_name|lower|replace(' ', '.') }}"
    read_from_file = {{ cookiecutter._read_from_file }}
    resources_name = "{{ cookiecutter.resource_name }}"

    yaml_file = "../hid-project-generator/config.yaml"

    try:
        if read_from_file is True:
            with open(yaml_file, 'r') as stream:
                output = yaml.safe_load(stream)
                resources_name = output['resource_name']
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
                                        "resource_name": resource.title()
                                      }
                    )

    print(SUCCESS +
          "Project initialized successfully! You can now jump to {} folder".
          format(_pkg_name) + TERMINATOR)
    print(INFO +
          "{}/README.md contains instructions on how to proceed.".
          format(_pkg_name) + TERMINATOR)

if __name__ == '__main__':
    main()
