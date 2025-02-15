DROP VIEW IF EXISTS place_details;
CREATE OR REPLACE VIEW place_details AS
SELECT
	v.Id_Vol,
	s.Id_Siege,
	(sa.nombre - COALESCE(SUM(pr.nombre), 0)) AS available_seats,
	psv.montant AS normal_price,
	CASE
		WHEN (psv.siege_prom - COALESCE(SUM(pr.nombre), 0)) > 0 
			THEN (psv.montant - (psv.montant * psv.prom / 100))
		ELSE 0
	END AS promotion_price,
	CASE
		WHEN (psv.siege_prom - COALESCE(SUM(pr.nombre), 0)) > 0 
			THEN (psv.siege_prom - COALESCE(SUM(pr.nombre), 0))
		ELSE 0
	END AS promotion_seats
FROM Prix_Siege_Vol psv
JOIN Vol v ON v.Id_Vol = psv.Id_Vol
JOIN Siege s ON s.Id_Siege = psv.Id_Siege
JOIN Sieges_Avions sa ON sa.Id_Siege = s.Id_Siege
LEFT JOIN Places_Reservation pr 
	ON pr.Id_Siege = s.Id_Siege
	AND pr.Id_Reservation IN (
		SELECT Id_Reservation
		FROM Reservation
		WHERE Id_Vol = v.Id_Vol
	)
GROUP BY v.Id_Vol, s.Id_Siege, sa.nombre, psv.montant, psv.prom, psv.siege_prom;