# Base

### Description 

### Setup

  Install sbt 0.13.9 or greater,

### Build

    sbt clean compile

### Test

    sbt clean compile test

### Run

    sbt -Dconfig.resource=development.conf clean compile run

### API

  Each api call is documented below as a url and method. The input and output formats are described below. Each
  operation can succeed with a 200 with the documented data object, or fail with either a 404 or 500 with the [error] object


    /version
    GET - gets the version of the project
      Response:
        {
          "version": "1.0.0",
          "name": "base"
        }
      Errors:
        - 0 - no connection  found

    /<ver>/env
    GET - 
      Response:
        - 200 - <environment>
        - 0 - no connection  found

