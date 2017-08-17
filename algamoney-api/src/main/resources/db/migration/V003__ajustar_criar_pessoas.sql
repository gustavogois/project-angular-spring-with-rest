DROP TABLE IF EXISTS pessoa;

CREATE TABLE pessoa (
  codigo            BIGINT(20)  PRIMARY KEY AUTO_INCREMENT,
  nome              VARCHAR(50) NOT NULL,
  ativo             TINYINT(1)  NULL DEFAULT 0,
  logradouro     VARCHAR(60) NULL,
  numero         VARCHAR(5)  NULL,
  complemento    VARCHAR(60) NULL,
  bairro         VARCHAR(30) NULL,
  cep            VARCHAR(9)  NULL,
  cidade         VARCHAR(30) NULL,
  estado         VARCHAR(30) NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

insert into pessoa (ativo, nome, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values (1, 'Gustavo Gois', 'Praceta das Orquideas', 1, '', 'Odivelas', '2675-578', 'Lisboa', 'Lisboa');

insert into pessoa (ativo, nome, logradouro, numero, complemento, bairro, cep, cidade, estado)
			values (1, 'Joao da Silva', 'Edf Piramide', 1, '', 'Águas Claras', '32100-280', 'Brasilia', 'DF');