drop view if exists details_place cascade;

CREATE
OR REPLACE VIEW details_place AS
SELECT
	SV.Id_Siege_Vol,
	V.Id_Vol,
	V.date_vol,
	V.reservation,
	V.annulation,
	V.Id_Avion,
	V.Id_Ville_Depart,
	V.Id_Ville_Arrivee,
	SA.Id_Siege,
	SA.nombre AS places,
	(SA.nombre - COALESCE(SUM(R.nombre), 0)) AS disponible,
	SV.montant AS prix,
	V.etat as etat,
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
	V.date_vol,
	V.reservation,
	V.annulation,
	V.Id_Avion,
	V.Id_Ville_Depart,
	V.Id_Ville_Arrivee,
	SA.Id_Siege,
	SA.nombre,
	SV.montant,
	SV.siege_prom,
	SV.prom,
	SV.Id_Siege_Vol,
	V.etat
HAVING
	(SA.nombre - COALESCE(SUM(R.nombre), 0)) > 0
order by
	v.id_vol,
	sv.id_siege_vol;

create
or replace view details_vols as
select
	Id_Vol,
	date_vol,
	reservation,
	annulation,
	Id_Avion,
	Id_Ville_Depart,
	Id_Ville_Arrivee,
	etat,
	sum(places) as places,
	sum(disponible) as disponible,
	sum(sieges_promo) as sieges_promo,
	0 as Id_Siege_Vol,
	0 as prix,
	0 as Id_Siege
from
	details_place
group by
	id_vol,
	Id_Vol,
	date_vol,
	reservation,
	annulation,
	Id_Avion,
	Id_Ville_Depart,
	Id_Ville_Arrivee,
	etat;

create
or replace view vols_dispo as
select
	*
from
	details_vols
where
	etat is false;