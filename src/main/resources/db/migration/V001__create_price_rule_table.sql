CREATE TABLE pricing_rule
(
    id            BIGINT NOT NULL,
    code          VARCHAR(255),
    name          VARCHAR(255),
    price         DECIMAL,
    discount_type VARCHAR(255),
    minimum_items       INTEGER          NULL,
    free_item_total     INTEGER          NULL,
    discount_percentage DOUBLE PRECISION NULL,
    CONSTRAINT pk_pricingrule PRIMARY KEY (id)
);
