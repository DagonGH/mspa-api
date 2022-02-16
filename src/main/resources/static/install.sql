-------------------------------------------
---          CREATE DATABASE            ---
-------------------------------------------
CREATE USER mspa WITH PASSWORD 'MSPA#2022ADM';
CREATE DATABASE mspadb;
GRANT ALL ON DATABASE mspadb TO mspa;
-------------------------------------------
---          MOUNT POINTS               ---
-------------------------------------------
CREATE TABLE dma_mount_points
(
    mpo_id       SERIAL PRIMARY KEY,
    mpo_code     CHARACTER VARYING(20),
    mpo_path     CHARACTER VARYING(500),
    mpo_max_size NUMERIC(20),
    mpo_status   CHARACTER VARYING(1) DEFAULT 'A'
);
ALTER TABLE dma_mount_points ADD CONSTRAINT uq_mpo_name UNIQUE (mpo_code);
-------------------------------------------
---          ARCHIVES                   ---
-------------------------------------------
CREATE TABLE dma_archives
(
    arc_id            SERIAL PRIMARY KEY,
    mpo_id            INTEGER NOT NULL,
    arc_name          CHARACTER VARYING(100),
    arc_extension     CHARACTER VARYING(10),
    arc_hash          CHARACTER VARYING(500),
    arc_mime_type     CHARACTER VARYING(100),
    arc_size          NUMERIC,
    arc_internal_path CHARACTER VARYING(500),
    arc_date_uploaded TIMESTAMP
);
ALTER TABLE dma_archives ADD CONSTRAINT fk_arc_mount_points FOREIGN KEY (mpo_id) REFERENCES dma_mount_points (mpo_id);
-------------------------------------------
---          SERVICES TYPES             ---
-------------------------------------------
CREATE TABLE spa_services_types
(
    sty_id       SERIAL PRIMARY KEY,
    arc_id       INTEGER,
    sty_code     VARCHAR(10),
    sty_name     VARCHAR(50),
    sty_status   VARCHAR(1)
);
-------------------------------------------
---          SERVICES                   ---
-------------------------------------------
CREATE TABLE spa_services
(
    ser_id            SERIAL PRIMARY KEY,
    sty_id            INTEGER,
    arc_id            INTEGER,
    ser_code          VARCHAR(10),
    ser_name          VARCHAR(50),
    ser_description   VARCHAR(1000),
    ser_value         NUMERIC,
    ser_status        VARCHAR(1)
);
ALTER TABLE spa_services ADD CONSTRAINT fk_ser_type FOREIGN KEY (sty_id) REFERENCES spa_services_types(sty_id);
ALTER TABLE spa_services ADD CONSTRAINT fk_ser_archive FOREIGN KEY (arc_id) REFERENCES dma_archives(arc_id);
-------------------------------------------
---          PACKAGES                   ---
-------------------------------------------
CREATE TABLE spa_packages
(
    pac_id        SERIAL PRIMARY KEY,
    pac_name      VARCHAR(50),
    pac_value     NUMERIC,
    pac_status    VARCHAR(1)
);
-------------------------------------------
---         PACKAGES SERVICES           ---
-------------------------------------------
CREATE TABLE spa_packages_services
(
    pse_id   SERIAL PRIMARY KEY,
    pac_id   INTEGER,
    ser_id   INTEGER
);
ALTER TABLE spa_packages_services ADD CONSTRAINT fk_pse_package FOREIGN KEY (pac_id) REFERENCES spa_packages(pac_id);
ALTER TABLE spa_packages_services ADD CONSTRAINT fk_pse_service FOREIGN KEY (ser_id) REFERENCES spa_services(ser_id);

-------------------------------------------
---         MAIN INSERTS                ---
-------------------------------------------
INSERT INTO dma_mount_points (mpo_code, mpo_path, mpo_max_size)
                      VALUES ('main', '/mspa/files/', '500000');


--
SELECT * FROM spa_services_types;
--
SELECT * FROM dma_archives;