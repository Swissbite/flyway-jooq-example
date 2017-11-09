FLAWAY JOOQ Example
===================

About
-----
This project is just an example project to show how flyway and jooq may work.


Dev Notes
---------

### Fast setup requirements

-  Docker runtime
-  Docker compose
-  JDK 8 and maven

### Compile and setup

-  `docker-compose up` - starts the (empty) database on port 60005
-  `mvn compile` - Set up database and jooq records

### Step by Step

Each "step" has it's own tag, prefixed with `demo_steps/`.

Change between tags needs a new `mvn compile`

| Step  | Description                                                                                   |
| ----- | --------------------------------------------------------------------------------------------- | 
| 00    | Data und DB Setup, load initial dataset with flyway, generate basics objects with jooq        |
| 01    | Has a working REST Endpoint , with first migration of airport table types                     |
| 02    | Migrate table AIRPORTS. Modify some types from String to Enum                                 |
| 03    | Get all Airports by Country. DB is not normalized.                                            |
| 04    | Calculate distances between Airports in a view. Get Airports reachable by distance            |
| 05    | Normalize DB, add a continents table.                                                         |

Credits
-------
The data source is from [ourairports.com](http://ourairports.com/). 
The data source is released under [Public Domain](http://en.wikipedia.org/wiki/Public_domain).
 