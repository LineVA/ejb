CREATE TABLE Genre(
    genre VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Supplier(
    supplier VARCHAR(50) PRIMARY KEY
);

CREATE TABLE DVD (
    idDVD int PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    title VARCHAR(50) NOT NULL,
    dateDVD int NOT NULL,
    genre VARCHAR(50),
    stock INT,
    supplier VARCHAR(50),
    CONSTRAINT fk_supplier
        FOREIGN KEY (supplier)
        REFERENCES Supplier(supplier),
    CONSTRAINT fk_genre
        FOREIGN KEY (genre)
        REFERENCES Genre(genre)
);



CREATE TABLE Individual(
    idIndividual INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    firstName VARCHAR(50),
    lastName VARCHAR(50)
);

CREATE TABLE Intervention(
    idIndividual INT,
    idDVD INT,
    PRIMARY KEY(idIndividual, idDVD),
    CONSTRAINT fk_individual
        FOREIGN KEY (idIndividual)
        REFERENCES Individual(idIndividual),
    CONSTRAINT fk_idDVD_intervention
        FOREIGN KEY (idDVD)
        REFERENCES DVD(idDVD)
);

CREATE TABLE Delivery(
    idDelivery int PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    stateDelivery VARCHAR(7)
);

CREATE TABLE Subdelivery(
     idSubdelivery int PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    stateSubdelivery VARCHAR(7),
    idDelivery int,
    idDVD int,
    CONSTRAINT fk_idDelivery
        FOREIGN KEY (idDelivery)
        REFERENCES Delivery(idDelivery),
    CONSTRAINT fk_dvd_subdelivery
        FOREIGN KEY (idDVD)
        REFERENCES DVD(idDVD)
);
