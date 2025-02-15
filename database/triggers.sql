CREATE OR REPLACE FUNCTION update_vol_dates()
RETURNS TRIGGER AS $$
BEGIN
	-- Subtract reservation duration from date_vol
	IF NEW.reservation IS NULL THEN
		NEW.reservation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'reservation')::interval;
	END IF;

	-- Subtract cancellation duration from date_vol
	IF NEW.annulation IS NULL THEN
		NEW.annulation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'annulation')::interval;
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER vol_dates_before_insert
BEFORE INSERT ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates();

CREATE TRIGGER vol_dates_before_update
BEFORE UPDATE ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates();

