CREATE TABLE timesheets (
id bigserial NOT NULL PRIMARY KEY,
user_id bigserial NOT NULL,
start_date time,
end_date time
--first_name character varying(30),
--last_name character varying(30),
--role character varying(30),
--email character varying(30) NOT NULL,
--last_login time,
--is_active BOOLEAN DEFAULT FALSE NOT NULL,
--pass character varying(200)
);

--ALTER TABLE timesheets OWNER TO hours;

ALTER TABLE ONLY timesheets
    ADD CONSTRAINT uk_timesheets_start_date UNIQUE (user_id, start_date);

--INSERT INTO users (first_name, last_name, email, role, is_active, pass) VALUES
--('glenn', '', 'gleenn@gmail.com', 'admin', true,
--'bcrypt+sha512$d6d175aaa9c525174d817a74$12$24326124313224314d345444356149457a67516150447967517a67472e717a2e777047565a7071495330625441704f46686a556b5535376849743575');