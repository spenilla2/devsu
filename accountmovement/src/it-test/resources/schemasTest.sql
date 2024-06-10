CREATE TABLE client (
    identification VARCHAR(255) NOT NULL,
    name VARCHAR(255) 
);

CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    account_balance DECIMAL(19, 2) NOT NULL,
    status BOOLEAN NOT NULL,
    client_id VARCHAR(255) NOT NULL
);
