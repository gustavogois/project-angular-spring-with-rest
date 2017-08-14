CREATE TABLE pessoa (
  codigo            BIGINT(20)  PRIMARY KEY AUTO_INCREMENT,
  nome              VARCHAR(50) NOT NULL,
  ativo             TINYINT(1)  NULL DEFAULT 0,
  endLogradouro     VARCHAR(60) NULL,
  endNumero         VARCHAR(5)  NULL,
  endComplemento    VARCHAR(60) NULL,
  endBairro         VARCHAR(30) NULL,
  endCep            VARCHAR(9)  NULL,
  endCidade         VARCHAR(30) NULL,
  endEstado         VARCHAR(30) NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

insert into pessoa (ativo, nome, endLogradouro, endNumero, endComplemento, endBairro, endCep, endCidade, endEstado)
			values (1, 'Gustavo Gois', 'Praceta das Orquideas', 1, '', 'Odivelas', '2675-578', 'Lisboa', 'Lisboa');

insert into pessoa (ativo, nome, endLogradouro, endNumero, endComplemento, endBairro, endCep, endCidade, endEstado)
			values (1, 'Joao da Silva', 'Edf Piramide', 1, '', 'Águas Claras', '32100-280', 'Brasilia', 'DF');