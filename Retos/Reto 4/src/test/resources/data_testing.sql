DELETE FROM customer;

INSERT INTO customer (id, name, email) VALUES
(1L, 'Pepe', 'email1@gmail.com'),
(2L,'Juan', 'email34@gmail.com'),
(3L, 'Jose', 'email0000@gmail.com');

DELETE FROM accounts;

INSERT INTO accounts (type, opening_date, balance, owner_id) VALUES
('Personal', '2024-04-10', 1000, 1L),
('Company', '2024-04-16', 2000, 2L),
('Company', '2024-04-12', 80, 3L),
('Personal', '2024-04-13', 120, 3L),
('Company', '2024-04-11', 2, 3L);