CREATE TABLE IF NOT EXISTS usuario (
  id              INT     NOT NULL PRIMARY KEY,
  login       	  VARCHAR(200) NOT NULL,
  senha          VARCHAR(200) NOT NULL,
);