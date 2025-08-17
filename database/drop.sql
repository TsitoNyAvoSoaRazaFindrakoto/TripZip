-- Drop triggers first
DROP TRIGGER IF EXISTS vol_dates_before_insert ON Vol;
DROP TRIGGER IF EXISTS vol_dates_before_update ON Vol;

-- Drop the function used by the triggers
DROP FUNCTION IF EXISTS update_vol_dates();

-- Drop views
DROP VIEW IF EXISTS details_place cascade;

-- Drop tables in reverse order of creation to avoid foreign key constraints
DROP TABLE IF EXISTS rule_config cascade;
DROP TABLE IF EXISTS Reservation cascade;
DROP TABLE IF EXISTS Sieges_Avions cascade;
DROP TABLE IF EXISTS Siege_Vol cascade;
DROP TABLE IF EXISTS Vol cascade;
DROP TABLE IF EXISTS Ville cascade;
DROP TABLE IF EXISTS Siege cascade;
DROP TABLE IF EXISTS Utilisateur cascade;
DROP TABLE IF EXISTS Avion cascade;