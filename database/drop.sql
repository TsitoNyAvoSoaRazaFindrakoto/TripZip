-- Drop triggers first
DROP TRIGGER IF EXISTS vol_dates_before_insert ON Vol;
DROP TRIGGER IF EXISTS vol_dates_before_update ON Vol;

-- Drop the function used by the triggers
DROP FUNCTION IF EXISTS update_vol_dates();

-- Drop views
DROP VIEW IF EXISTS details_place;

-- Drop tables in reverse order of creation to avoid foreign key constraints
DROP TABLE IF EXISTS rule_config;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Sieges_Avions;
DROP TABLE IF EXISTS Siege_Vol;
DROP TABLE IF EXISTS Vol;
DROP TABLE IF EXISTS Ville;
DROP TABLE IF EXISTS Siege;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Avion;