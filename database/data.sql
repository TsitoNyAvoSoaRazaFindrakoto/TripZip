-- filepath: /d:/Studies/L3/MrNaina/TripZip/database/data.sql
-- Insert data into Utilisateur
INSERT INTO
	Utilisateur (role, email, mdp)
VALUES
	('admin', 'admin@example.com', 'password'),
	('client', 'client1@example.com', 'password123'),
	('client', 'client2@example.com', 'securepass');

-- Insert data into Siege
INSERT INTO
	Siege (nom)
VALUES
	('ECO'),
	('BUSINESS'),
	('VIP');

-- Insert data into Avion
INSERT INTO
	Avion (modele, fabrication)
VALUES
	('Boeing 747', '2020-01-01'),
	('Airbus A380', '2021-05-15'),
	('Cessna 172', '2022-11-20');

-- Insert data into Sieges_Avions
INSERT INTO
	Sieges_Avions (Id_Siege, Id_Avion, nombre)
VALUES
	(1, 1, 100),
	(2, 1, 150),
	(3, 1, 50),
	(1, 2, 50),
	(2, 2, 200),
	(3, 2, 75),
	(1, 3, 10),
	(2, 3, 20),
	(3, 3, 5);

-- Insert data into Ville
INSERT INTO
	Ville (nom)
VALUES
	('Paris'),
	('New York'),
	('Tokyo'),
	('London'),
	('Sydney');

-- Insert data into rule_config
INSERT INTO
	rule_config (id, value)
VALUES
	('reservation', '08:00'),
	('annulation', '05:00'),
	('reduc_enfant', '0.9');

-- Insert data into Vol (without reservation and annulation)
INSERT INTO
	Vol (
		date_vol,
		Id_Avion,
		Id_Ville_Depart,
		Id_Ville_Arrivee
	)
VALUES
	('2024-03-15 08:00:00', 1, 1, 2), -- Paris to New York
	('2024-03-16 14:00:00', 2, 2, 3), -- New York to Tokyo
	('2024-03-17 09:30:00', 1, 3, 4), -- Tokyo to London
	('2024-03-18 16:00:00', 3, 4, 5), -- London to Sydney
	('2024-03-19 11:00:00', 2, 5, 1);

-- Sydney to Paris
-- Insert data into Siege_Vol (related to the inserted Vols)
INSERT INTO
	Siege_Vol (montant, prom, siege_prom, Id_Siege, Id_Vol)
VALUES
	(500.00, 0.00, 0, 1, 1),
	(800.00, 0.05, 10, 2, 1),
	(1200.00, 0.00, 0, 3, 1),
	(700.00, 0.00, 0, 1, 2),
	(1000.00, 0.00, 0, 2, 2),
	(1500.00, 0.10, 5, 3, 2),
	(400.00, 0.00, 0, 1, 3),
	(600.00, 0.00, 0, 2, 3),
	(900.00, 0.00, 0, 3, 3),
	(300.00, 0.00, 0, 1, 4),
	(500.00, 0.00, 0, 2, 4),
	(750.00, 0.00, 0, 3, 4),
	(600.00, 0.00, 0, 1, 5),
	(900.00, 0.00, 0, 2, 5),
	(1300.00, 0.00, 0, 3, 5);
	
INSERT INTO
	Reservation (
		date_reservation,
		prix,
		nombre,
		Id_Utilisateur,
		Id_Siege_Vol
	)
VALUES
	('2024-03-14 09:30:00', 1368.00, 2, 3, 2);