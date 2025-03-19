CREATE TABLE payment_entity
(
    id uuid PRIMARY KEY,
    payment_id               VARCHAR(255)     NOT NULL UNIQUE,
    unique_request_id        VARCHAR(255)     NOT NULL UNIQUE,
    requested_execution_date DATE             NOT NULL,
    status                   VARCHAR(50)      NOT NULL, -- PendingProcessing, Processed, Rejected
    charge_bearer            VARCHAR(50)      NOT NULL,
    clearing_network         VARCHAR(50),
    amount                   DOUBLE PRECISION NOT NULL,
    currency                 VARCHAR(10)      NOT NULL,
    currency_of_transfer     VARCHAR(10)      NOT NULL
);

CREATE TABLE account_entity
(
    id uuid PRIMARY KEY,
    iban           VARCHAR(50) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    bic            VARCHAR(20),
    country        VARCHAR(50),
    payment_id uuid ,
    FOREIGN KEY (payment_id) REFERENCES payment_entity (id) ON DELETE CASCADE
);

CREATE TABLE beneficiary_entity
(
    id uuid PRIMARY KEY,
    iban           VARCHAR(50)  NOT NULL,
    name           VARCHAR(255) NOT NULL,
    address        VARCHAR(255),
    bic            VARCHAR(20),
    account_number VARCHAR(50),
    country        VARCHAR(50),
    payment_id uuid ,
    FOREIGN KEY (payment_id) REFERENCES payment_entity (id) ON DELETE CASCADE
);
