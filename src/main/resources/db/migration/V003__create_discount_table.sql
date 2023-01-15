CREATE TABLE discount
(
    id                  BIGINT NOT NULL,
    discount_type       VARCHAR(255),
    minimum_items       INTEGER          NOT NULL,
    discount_percentage DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

