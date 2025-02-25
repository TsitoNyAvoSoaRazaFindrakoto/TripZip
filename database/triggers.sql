CREATE OR REPLACE FUNCTION update_vol_dates()
RETURNS TRIGGER AS $$
BEGIN
	-- Subtract reservation duration from date_vol
		NEW.reservation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'reservation')::interval;

	-- Subtract cancellation duration from date_vol
		NEW.annulation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'annulation')::interval;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER vol_dates_before_insert
BEFORE INSERT ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates();

CREATE OR REPLACE FUNCTION update_vol_dates_on_update()
RETURNS TRIGGER AS $$
BEGIN
	IF NEW.date_vol != OLD.date_vol IS NULL THEN
	-- Subtract reservation duration from date_vol
		NEW.reservation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'reservation')::interval;

	-- Subtract cancellation duration from date_vol
		NEW.annulation := NEW.date_vol - (SELECT duree FROM Config_reservation WHERE libelle = 'annulation')::interval;
	end if;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_vol_dates_on_update
BEFORE UPDATE ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates();