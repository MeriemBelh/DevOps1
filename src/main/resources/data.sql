CREATE TABLE IF NOT EXISTS PRODUCT (
                                       ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       NAME VARCHAR(255),
                                       DESCRIPTION VARCHAR(255),
                                       PRICE DOUBLE
);

INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE) VALUES ('Morocco Football Home Kit', 'Morocco 2022 World Cup Home Kit with Red and Green Colors', 200.0);

INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE) VALUES ('Morocco Football Away Kit', 'Morocco 2022 World Cup Away Kit with White Color', 200.0);

INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE) VALUES ('Morocco Football Pullover', 'Morocco 2022 World Cup Pullover with Red and Green Colors', 500.0);