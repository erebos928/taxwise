-- TaxAuthority
INSERT INTO TaxAuthority (label, taxFreeThreshold) VALUES
('Federal', 15000.00),
('Quebec', 14000.00),
('Ontario', 13500.00),
('Alberta', 13000.00),
('British Columbia', 14500.00);

-- TaxBracket
INSERT INTO TaxBracket (minIncome, maxIncome, taxRate, authority_id) VALUES
(0, 48535, 15.0, 1),
(48536, 97069, 20.5, 1),
(0, 43790, 15.0, 2),
(43791, 87575, 20.0, 2),
(0, 44740, 10.0, 3);
(44741, 55000, 12.0, 3);

-- TaxPayer
INSERT INTO TaxPayer (name, sin) VALUES
('Alice Tremblay', 123456789),
('Bob Martin', 987654321),
('Chantal Gagnon', 456789123),
('David Nguyen', 321654987),
('Émilie Roy', 654321789);

-- PAYER_AUTHORITY
INSERT INTO PAYER_AUTHORITY (payer_id, authority_id, report_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 1, 4),
(5, 2, 5);

-- TaxReport
INSERT INTO TaxReport (payer_authority_id, year, income, tax) VALUES
(1, 2023, 52000.00, 7800.00),
(2, 2023, 42000.00, 6300.00),
(3, 2023, 60000.00, 9000.00),
(4, 2022, 48000.00, 7200.00),
(5, 2022, 51000.00, 7650.00);