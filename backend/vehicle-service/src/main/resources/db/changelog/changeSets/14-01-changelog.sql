-- liquibase formatted sql

-- changeset onish:1763086272548-1
ALTER TABLE vehicle DROP COLUMN fuel_type;

-- changeset onish:1763086272548-2
ALTER TABLE vehicle
    ADD fuel_type SMALLINT;

