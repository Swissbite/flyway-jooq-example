CREATE TABLE regions
(
  id             INT          NOT NULL
    PRIMARY KEY,
  code           VARCHAR(10)  NULL,
  local_code     VARCHAR(10)  NULL,
  name           VARCHAR(50)  NULL,
  continent      VARCHAR(2)   NULL,
  iso_country    VARCHAR(2)   NULL,
  wikipedia_link VARCHAR(300) NULL,
  keywords       VARCHAR(300) NULL,
  CONSTRAINT regions_code_uindex
  UNIQUE (code)
);
CREATE TABLE countries
(
  id             INT          NOT NULL
    PRIMARY KEY,
  code           VARCHAR(2)   NULL,
  name           VARCHAR(50)  NULL,
  contintent     VARCHAR(2)   NULL,
  wikipedia_link VARCHAR(300) NULL,
  keywords       VARCHAR(300) NULL,
  CONSTRAINT countries_code_uindex
  UNIQUE (code)
);

CREATE TABLE airports
(
  id                INT          NOT NULL
    PRIMARY KEY,
  ident             VARCHAR(10)  NULL,
  type              VARCHAR(50)  NULL,
  name              VARCHAR(300) NULL,
  latitude_deg      DOUBLE       NOT NULL,
  longitude_deg     DOUBLE       NOT NULL,
  elevation_ft      MEDIUMTEXT   NULL,
  continent         VARCHAR(2)   NULL,
  iso_country       VARCHAR(2)   NULL,
  iso_region        VARCHAR(2)   NULL,
  municipality      VARCHAR(10)  NULL,
  scheduled_service VARCHAR(50)  NULL,
  gps_code          VARCHAR(50)  NULL,
  iata_code         VARCHAR(50)  NULL,
  local_code        VARCHAR(50)  NULL,
  home_link         VARCHAR(300) NULL,
  wikipedia_link    VARCHAR(300) NULL,
  keywords          VARCHAR(300) NULL,
  CONSTRAINT airports_ident_uindex
  UNIQUE (ident)
);

CREATE INDEX airports_name_index
  ON airports (name);

CREATE INDEX airports_type_index
  ON airports (type);





