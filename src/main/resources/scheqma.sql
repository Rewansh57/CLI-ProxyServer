

CREATE TABLE IF NOT EXISTS authorities (
                                                  id SERIAL PRIMARY KEY,
                                                  username VARCHAR(45) NOT NULL,
                                                  authority VARCHAR(45) NOT NULL
);


CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(45) NOT NULL,
                                     password VARCHAR(45) NOT NULL,
                                     enabled BOOLEAN NOT NULL
);
INSERT INTO users (username, password, enabled)
VALUES ('john', '12345', true);

INSERT INTO authorities (username, authority)
VALUES ('john', 'write');