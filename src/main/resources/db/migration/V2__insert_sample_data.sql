-- Insert country
INSERT INTO country(name,code) VALUES ('India','IN');

-- Insert states
INSERT INTO state(name,code,country_id) VALUES ('Telangana','TS',1);
INSERT INTO state(name,code,country_id) VALUES ('Andhra Pradesh','AP',1);

-- Insert districts
INSERT INTO district(name,code,state_id) VALUES ('Ranga Reddy','RR',1);
INSERT INTO district(name,code,state_id) VALUES ('Nalgonda','NAL',1);

INSERT INTO district(name,code,state_id) VALUES ('Guntur','CT',2);
INSERT INTO district(name,code,state_id) VALUES ('Krishna','KR',2);

-- insert cities
INSERT INTO city(name,code,district_id) VALUES ('Hyderabad','HYD',1);
INSERT INTO city(name,code,district_id) VALUES ('Ibrahimpatnam','IBR',1);

INSERT INTO city(name,code,district_id) VALUES ('Suryapet','SRY',2);
INSERT INTO city(name,code,district_id) VALUES ('Kodada','KOD',2);

INSERT INTO city(name,code,district_id) VALUES ('Guntur City','GC',3);
INSERT INTO city(name,code,district_id) VALUES ('Tenali','TEN',3);

INSERT INTO city(name,code,district_id) VALUES ('Vijaywada','VJY',4);
INSERT INTO city(name,code,district_id) VALUES ('Machilpatnam','MAL',4);


