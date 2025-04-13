CREATE OR REPLACE FUNCTION update_vol_dates()
RETURNS TRIGGER AS $$
DECLARE
    res_time time;
    ann_time time;
BEGIN
    -- Get the configured times
    SELECT value::time INTO res_time FROM rule_config WHERE id = 'reservation';
    SELECT value::time INTO ann_time FROM rule_config WHERE id = 'annulation';
    
    -- Subtract reservation time from flight time
    NEW.reservation := NEW.date_vol - (res_time::interval);
    
    -- Subtract annulation time from flight time
    NEW.annulation := NEW.date_vol - (ann_time::interval);
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER vol_dates_before_insert
BEFORE INSERT ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates();

CREATE OR REPLACE FUNCTION update_vol_dates_on_update()
RETURNS TRIGGER AS $$
DECLARE
    res_time time;
    ann_time time;
BEGIN
    IF NEW.date_vol != OLD.date_vol THEN
        -- Get the configured times
        SELECT value::time INTO res_time FROM rule_config WHERE id = 'reservation';
        SELECT value::time INTO ann_time FROM rule_config WHERE id = 'annulation';
        
        -- Subtract reservation time from flight time
        NEW.reservation := NEW.date_vol - (res_time::interval);
        
        -- Subtract annulation time from flight time
        NEW.annulation := NEW.date_vol - (ann_time::interval);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_vol_dates_on_update
BEFORE UPDATE ON Vol
FOR EACH ROW
EXECUTE FUNCTION update_vol_dates_on_update();