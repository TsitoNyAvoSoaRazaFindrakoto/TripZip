CREATE TABLE Avion(
   Id_Avion SERIAL,
   modele VARCHAR(50)  NOT NULL,
   fabrication DATE NOT NULL,
   PRIMARY KEY(Id_Avion)
);

CREATE TABLE Utilisateur(
   Id_Utilisateur SERIAL,
   role VARCHAR(50)  NOT NULL,
   email VARCHAR(50) ,
   mdp VARCHAR(70)  NOT NULL,
   PRIMARY KEY(Id_Utilisateur)
);

CREATE TABLE Siege(
   Id_Siege SERIAL,
   nom VARCHAR(2) ,
   PRIMARY KEY(Id_Siege)
);

CREATE TABLE Ville(
   Id_Ville SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(Id_Ville)
);

CREATE TABLE Vol(
   Id_Vol SERIAL,
   date_vol TIMESTAMP NOT NULL,
   reservation TIMESTAMP,
   annulation TIMESTAMP,
   Id_Ville INTEGER NOT NULL,
   Id_Ville_1 INTEGER NOT NULL,
   PRIMARY KEY(Id_Vol),
   FOREIGN KEY(Id_Ville) REFERENCES Ville(Id_Ville),
   FOREIGN KEY(Id_Ville_1) REFERENCES Ville(Id_Ville)
);

CREATE TABLE Prix_Siege_Vol(
   Id_PrixSiegeVol SERIAL,
   montant NUMERIC(17,2)   NOT NULL,
   prom NUMERIC(4,2)   NOT NULL default 0,
   siege_prom INTEGER NOT NULL default 0,
   Id_Siege INTEGER NOT NULL,
   Id_Vol INTEGER NOT NULL,
   PRIMARY KEY(Id_PrixSiegeVol),
   FOREIGN KEY(Id_Siege) REFERENCES Siege(Id_Siege),
   FOREIGN KEY(Id_Vol) REFERENCES Vol(Id_Vol)
);

CREATE TABLE Sieges_Avions(
   Id_Siege INTEGER,
   Id_Avion INTEGER,
   nombre INTEGER NOT NULL,
   PRIMARY KEY(Id_Siege, Id_Avion),
   FOREIGN KEY(Id_Siege) REFERENCES Siege(Id_Siege),
   FOREIGN KEY(Id_Avion) REFERENCES Avion(Id_Avion)
);

CREATE TABLE Reservation(
   Id_Reservation SERIAL,
   contact VARCHAR(50)  NOT NULL,
   date_reservation TIMESTAMP NOT NULL,
   Id_Vol INTEGER NOT NULL,
   PRIMARY KEY(Id_Reservation),
   FOREIGN KEY(Id_Vol) REFERENCES Vol(Id_Vol)
);

CREATE TABLE Places_Reservation(
   Id_Places_Reservation SERIAL,
   nombre INTEGER NOT NULL,
   prix_unitaire NUMERIC(17,2)   NOT NULL,
   Id_Siege INTEGER NOT NULL,
   Id_Reservation INTEGER NOT NULL,
   PRIMARY KEY(Id_Places_Reservation),
   FOREIGN KEY(Id_Siege) REFERENCES Siege(Id_Siege),
   FOREIGN KEY(Id_Reservation) REFERENCES Reservation(Id_Reservation)
);

CREATE TABLE Config_reservation(
   libelle VARCHAR(20) ,
   duree TIME,
   PRIMARY KEY(libelle)
);
