CREATE OR REPLACE VIEW V_AIRPORT_DISTANCES AS
  SELECT
    a1.id                                                        AS start_airport_id,
    a1.ident                                                     AS start_airport_ident,
    a2.id                                                        AS target_airport_id,
    a2.ident                                                     AS target_airport_ident,
    a2.type                                                      AS target_airport_type,
    a2.scheduled_service                                         AS target_airport_scheduled_service,
    ST_DISTANCE_SPHERE(POINT(a1.longitude_deg, a1.latitude_deg),
                       POINT(a2.longitude_deg, a2.latitude_deg)) AS distance_in_meter
  FROM airports a1, airports a2
  WHERE a1.id != a2.id;
