CREATE TABLE pricing_rule
(
    id            BIGINT NOT NULL,
    code          VARCHAR(255),
    name          VARCHAR(255),
    price         DECIMAL,
    discount_type VARCHAR(255),
    CONSTRAINT pk_pricingrule PRIMARY KEY (id)
);


