CREATE TABLE tbbicicleta (
    id INT NOT NULL AUTO_INCREMENT,
    numero_de_serie INT NOT NULL UNIQUE,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;