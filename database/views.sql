drop view if exists details_place cascade;
CREATE VIEW details_place AS
SELECT
	V.Id_Vol,
	SA.Id_Siege,
	SA.nombre AS places,
	(SA.nombre - COALESCE(SUM(R.nombre), 0)) AS disponible,
	SV.montant AS prix,
	CASE
		WHEN SV.siege_prom > COALESCE(SUM(R.nombre), 0) THEN SV.siege_prom - COALESCE(SUM(R.nombre), 0)
		ELSE 0
	END AS sieges_promo,
	SV.montant * (1 - SV.prom) AS prix_promo
FROM
	Vol V
	JOIN Sieges_Avions SA ON V.Id_Avion = SA.Id_Avion
	JOIN Siege_Vol SV ON V.Id_Vol = SV.Id_Vol
	AND SA.Id_Siege = SV.Id_Siege
	LEFT JOIN Reservation R ON SV.Id_Siege_Vol = R.Id_Siege_Vol
GROUP BY
	V.Id_Vol,
	SA.Id_Siege,
	SA.nombre,
	SV.montant,
	SV.siege_prom,
	SV.prom
HAVING
	(SA.nombre - COALESCE(SUM(R.nombre), 0)) > 0;