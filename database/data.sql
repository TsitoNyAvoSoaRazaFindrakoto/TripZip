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

-- Insert data into Config_reservation
INSERT INTO
	Config_reservation (libelle, duree)
VALUES
	('reservation', '08:00'),
	('annulation', '05:00');

insert INTO
	vol
VALUES
	( default , ),