-- Create continent table
CREATE TABLE continents
(
  id   INT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(2) NOT NULL,
  name VARCHAR(255)
);
CREATE UNIQUE INDEX continents_code_uindex
  ON continents (code);

-- Add continent rows
INSERT INTO continents (code, name) VALUES ('AS', 'Asia');
INSERT INTO continents (code, name) VALUES ('OC', 'Oceania');
INSERT INTO continents (code, name) VALUES ('EU', 'Europe');
INSERT INTO continents (code, name) VALUES ('AF', 'Africa');
INSERT INTO continents (code, name) VALUES ('AN', 'Antarctica');
INSERT INTO continents (code, name) VALUES ('SA', 'South America');
INSERT INTO continents (code, name) VALUES ('NA', 'North America');

-- alter regions table to map directly into country and continent
ALTER TABLE regions
  ADD country_id INT NULL;
ALTER TABLE regions
  ADD continent_id INT NULL;

ALTER TABLE regions
  ADD CONSTRAINT regions_countries_id_fk
FOREIGN KEY (country_id) REFERENCES countries (id);

ALTER TABLE regions
  ADD CONSTRAINT regions_continents_id_fk
FOREIGN KEY (continent_id) REFERENCES continents (id);

-- Link from codes to the entries
UPDATE regions r
  JOIN countries c ON c.code = r.iso_country
  JOIN continents con ON con.code = r.continent
SET r.country_id = c.id, r.continent_id = con.id;

-- Set references to not null
ALTER TABLE regions
  MODIFY country_id INT(11) NOT NULL;
ALTER TABLE regions
  MODIFY continent_id INT(11) NOT NULL;

-- delete obsolete columns
ALTER TABLE regions
  DROP continent;
ALTER TABLE regions
  DROP iso_country;


ALTER TABLE airports
  ADD country_id INT NULL;
ALTER TABLE airports
  ADD continent_id INT NULL;
ALTER TABLE airports
  ADD region_id INT NULL;

ALTER TABLE airports
  ADD CONSTRAINT airports_countries_id_fk
FOREIGN KEY (country_id) REFERENCES countries (id);

ALTER TABLE airports
  ADD CONSTRAINT airports_continents_id_fk
FOREIGN KEY (continent_id) REFERENCES continents (id);

ALTER TABLE airports
  ADD CONSTRAINT airports_regions_id_fk
FOREIGN KEY (region_id) REFERENCES regions (id);

UPDATE airports a
  JOIN continents co ON co.code = a.continent
  JOIN regions r ON r.code = a.iso_region
  JOIN countries c ON c.code = a.iso_country
SET a.country_id = c.id, a.continent_id = co.id, a.region_id = r.id;

ALTER TABLE airports
  MODIFY country_id INT NOT NULL;
ALTER TABLE airports
  MODIFY continent_id INT NOT NULL;
ALTER TABLE airports
  MODIFY region_id INT NOT NULL;

ALTER TABLE airports DROP continent, DROP iso_region, DROP iso_country;


