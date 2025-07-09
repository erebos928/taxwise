-- database user: behrooz pass: qualityassurance395
-- url: jdbc:mysql://mysql-behrooz.alwaysdata.net:3306/behrooz_taxwise
DROP TABLE IF EXISTS TaxAuthority;
DROP TABLE IF EXISTS TaxBracket;
DROP TABLE IF EXISTS TaxReport;
DROP TABLE IF EXISTS TaxPayer;

CREATE TABLE TaxAuthority
(
id INT PRIMARY KEY AUTO_INCREMENT,
label VARCHAR(75),
taxFreeThreshold DOUBLE
);
CREATE TABLE TaxBracket
(
id INT PRIMARY KEY AUTO_INCREMENT,
authority_id INT,
minIncome DOUBLE,
maxIncome DOUBLE,
taxRate DOUBLE,
FOREIGN KEY (authority_id) REFERENCES TaxAuthority(id)
);
CREATE TABLE TaxPayer
(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(125),
sin INT
);
CREATE TABLE TaxReport
(
id INT PRIMARY KEY AUTO_INCREMENT,
payer_id INT,
authority_id INT,
year INT,
income DOUBLE,
tax DOUBLE,
FOREIGN KEY (payer_id) REFERENCES TaxPayer(id),
FOREIGN KEY (authority_id) REFERENCES TaxAuthority(id)
);
