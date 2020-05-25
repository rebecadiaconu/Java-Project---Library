CREATE TABLE `librarie` (
    `id_lib` INTEGER NOT NULL,
    `nume` VARCHAR(255) NOT NULL,
	`adresa` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id_lib`)
);
CREATE TABLE `rubrica` (
    `id_lib` INTEGER NOT NULL,
    `id_rub` VARCHAR(255) NOT NULL,
    `nume` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id_rub`)
);
CREATE TABLE `comanda` (
    `id_lib` INTEGER NOT NULL,
    `id_client` VARCHAR(255) NOT NULL,
    `nr_comanda` VARCHAR(255) NOT NULL,
    `metoda_plata` VARCHAR(255) NOT NULL,
    `pret_comanda` DOUBLE NOT NULL,
    PRIMARY KEY (`nr_comanda`)
);
CREATE TABLE `client` (
    `id_lib` INTEGER NOT NULL,
    `id_client` VARCHAR(255) NOT NULL,
    `nume` VARCHAR(255) NOT NULL,
    `prenume` VARCHAR(255) NOT NULL,
    `cnp` VARCHAR(255) NOT NULL,
    `card` INTEGER NOT NULL,
    `comenzi_date` INTEGER NOT NULL,
    PRIMARY KEY (`cnp`)
);
CREATE TABLE `carte_comandata` (
    `id_lib` INTEGER NOT NULL,
    `id_client` VARCHAR(255) NOT NULL,
    `id_carte` VARCHAR(255) NOT NULL,
    `nr_comanda` VARCHAR(255) NOT NULL,
    `bucati_comanda` INTEGER NOT NULL,
    PRIMARY KEY (`id_carte`)
);
CREATE TABLE `literar` (
    `id_lib` INTEGER NOT NULL,
    `id_rub` VARCHAR(255) NOT NULL,
    `id_carte` VARCHAR(255) NOT NULL,
    `titlu` VARCHAR(255) NOT NULL,
    `nume_autor` VARCHAR(255) NOT NULL,
    `prenume_autor` VARCHAR(255) NOT NULL,
    `carti_scrise` INTEGER NOT NULL,
    `nr_pagini` INTEGER NOT NULL,
    `tip_text` INTEGER NOT NULL,
    `nr_exempl` INTEGER NOT NULL,
    `exempl_cump` INTEGER NOT NULL,
    `pret_digital` DOUBLE NOT NULL,
    `pret_fizic` DOUBLE NOT NULL,
    `limb_disp` VARCHAR(255) NOT NULL,
    `cuv_cheie` VARCHAR(255) NOT NULL,
    `specie` VARCHAR(255) NOT NULL,
    `gen` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id_carte`)
);
CREATE TABLE `nonliterar` (
    `id_lib` INTEGER NOT NULL,
    `id_rub` VARCHAR(255) NOT NULL,
    `id_carte` VARCHAR(255) NOT NULL,
    `titlu` VARCHAR(255) NOT NULL,
    `nume_autor` VARCHAR(255) NOT NULL,
    `prenume_autor` VARCHAR(255) NOT NULL,
    `carti_scrise` INTEGER NOT NULL,
    `nr_pagini` INTEGER NOT NULL,
    `tip_text` INTEGER NOT NULL,
    `nr_exempl` INTEGER NOT NULL,
    `exempl_cump` INTEGER NOT NULL,
    `pret_digital` DOUBLE NOT NULL,
    `pret_fizic` DOUBLE NOT NULL,
    `limb_disp` VARCHAR(255) NOT NULL,
    `cuv_cheie` VARCHAR(255) NOT NULL,
    `specie` VARCHAR(255) NOT NULL,
    `gen` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id_carte`)
);
