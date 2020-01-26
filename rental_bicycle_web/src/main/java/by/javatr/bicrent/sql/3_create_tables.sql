USE `rental_bicycle_web`;

/*
CREATE TABLE userinfo
*/

CREATE TABLE rental_bicycle_web.userinfo (
  userInfo_id INT(11) UNSIGNED NOT NULL,
  userInfo_surname VARCHAR(255) NOT NULL,
  userInfo_name VARCHAR(255) NOT NULL,
  userInfo_secondName VARCHAR(50) DEFAULT NULL,
  userInfo_birthDate VARCHAR(50) NOT NULL,
  userInfo_passportIssueDate DATE NOT NULL,
  userInfo_passportIssuingAuthority VARCHAR(100) NOT NULL,
  userInfo_passportIdentificationNumber VARCHAR(20) NOT NULL,
  userInfo_passportSerialNumber VARCHAR(10) DEFAULT NULL,
  userInfo_passportAdressRegistration VARCHAR(255) NOT NULL,
  userInfo_passportAdressResidence VARCHAR(255) NOT NULL,
  userInfo_phoneNumber BIGINT(20) NOT NULL,
  userInfo_secondPhoneNumber BIGINT(20) DEFAULT NULL,
  userInfo_email VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (userInfo_id)
)
ENGINE = INNODB,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.userinfo
  ADD CONSTRAINT FK_userinfo_user_user_id FOREIGN KEY (userInfo_id)
    REFERENCES rental_bicycle_web.user(user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*
CREATE TABLE vitualcard
*/

CREATE TABLE rental_bicycle_web.vitualcard (
  vitualCard_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  vitualrCard_name VARCHAR(30) NOT NULL,
  vitualCard_balance DECIMAL(8, 2) UNSIGNED DEFAULT NULL,
  vitualCard_currency VARCHAR(30) NOT NULL,
  PRIMARY KEY (vitualCard_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 3,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

/*
CREATE TABLE user
*/


CREATE TABLE rental_bicycle_web.user (
  user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_login VARCHAR(20) NOT NULL,
  user_password VARCHAR(50) NOT NULL,
  user_virtualcard_id INT(11) UNSIGNED DEFAULT NULL,
  user_role VARCHAR(20) NOT NULL,
  PRIMARY KEY (user_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 3,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.user
  ADD INDEX FK_user_vitualcard_vitualCard_id(user_virtualcard_id);

/*
CREATE TABLE bicycle
*/

CREATE TABLE rental_bicycle_web.bicycle (
  bicycle_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  bicycle_model VARCHAR(100) DEFAULT NULL,
  bicycle_type CHAR(20) DEFAULT NULL,
  bicycle_productionYear YEAR(4) DEFAULT NULL,
  bicycle_producer VARCHAR(50) DEFAULT NULL,
  bicycle_currentLocation_id INT(11) UNSIGNED DEFAULT NULL,
  bicycle_photo BLOB DEFAULT NULL,
  bicycle_price_id INT(11) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (bicycle_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 21,
AVG_ROW_LENGTH = 78643,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.bicycle
  ADD CONSTRAINT FK_bicycle_location_location_id FOREIGN KEY (bicycle_currentLocation_id)
    REFERENCES rental_bicycle_web.location(location_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE rental_bicycle_web.bicycle
  ADD CONSTRAINT FK_bicycle_price_price_id FOREIGN KEY (bicycle_price_id)
    REFERENCES rental_bicycle_web.price(price_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*
CREATE TABLE company
*/

CREATE TABLE rental_bicycle_web.company (
  company_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  company_name VARCHAR(100) NOT NULL,
  company_accountNumberOfPayer INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (company_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 2,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

/*
CREATE TABLE location
*/

CREATE TABLE rental_bicycle_web.location (
  location_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  location_name VARCHAR(100) NOT NULL,
  location_company_id INT(11) UNSIGNED NOT NULL,
  location_country VARCHAR(50) NOT NULL,
  location_city VARCHAR(50) NOT NULL,
  location_street VARCHAR(50) NOT NULL,
  location_house VARCHAR(10) NOT NULL,
  location_office VARCHAR(10) DEFAULT NULL,
  location_index VARCHAR(10) DEFAULT NULL,
  location_phone BIGINT(20) NOT NULL,
  location_secondPhone BIGINT(20) DEFAULT NULL,
  location_photo VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (location_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 10,
AVG_ROW_LENGTH = 8192,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.location
  ADD CONSTRAINT FK_location_company_company_id FOREIGN KEY (location_company_id)
    REFERENCES rental_bicycle_web.company(company_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*
CREATE TABLE payment
*/

CREATE TABLE rental_bicycle_web.payment (
  payment_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  payment_amount DECIMAL(8, 2) UNSIGNED DEFAULT NULL,
  payment_virtualCard_id INT(11) UNSIGNED NOT NULL,
  payment_company_id INT(11) UNSIGNED NOT NULL,
  payment_ifPaid TINYINT(1) DEFAULT NULL,
  PRIMARY KEY (payment_id)
)
ENGINE = INNODB,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.payment
  ADD CONSTRAINT FK_payment_company_company_id FOREIGN KEY (payment_company_id)
    REFERENCES rental_bicycle_web.company(company_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE rental_bicycle_web.payment
  ADD CONSTRAINT FK_payment_vitualcard_vitualCard_id FOREIGN KEY (payment_virtualCard_id)
    REFERENCES rental_bicycle_web.vitualcard(vitualCard_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*
CREATE TABLE price
*/
CREATE TABLE rental_bicycle_web.price (
  price_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  price_currency INT(11) NOT NULL,
  price_unitTime VARCHAR(20) NOT NULL,
  price_rate DECIMAL(8, 2) NOT NULL,
  PRIMARY KEY (price_id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 5,
AVG_ROW_LENGTH = 4096,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

/*
CREATE TABLE rent
*/

CREATE TABLE rental_bicycle_web.rent (
  rent_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  rent_user_id INT(11) UNSIGNED NOT NULL,
  rent_bicycle_id INT(11) UNSIGNED DEFAULT NULL,
  rent_start_time DATETIME DEFAULT NULL,
  rent_finish_time DATETIME DEFAULT NULL,
  rent_startLocation_id INT(11) UNSIGNED NOT NULL,
  rent_finishLocation_id INT(11) UNSIGNED DEFAULT NULL,
  rent_payment_id INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (rent_id)
)
ENGINE = INNODB,
CHARACTER SET latin1,
COLLATE latin1_general_ci;

ALTER TABLE rental_bicycle_web.rent
  ADD INDEX FK_rent_bicycle_bicycle_finishLocation_id(rent_finishLocation_id);

ALTER TABLE rental_bicycle_web.rent
  ADD INDEX FK_rent_location_location_id(rent_startLocation_id);

ALTER TABLE rental_bicycle_web.rent
  ADD CONSTRAINT FK_rent_bicycle_bicycle_id FOREIGN KEY (rent_bicycle_id)
    REFERENCES rental_bicycle_web.bicycle(bicycle_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE rental_bicycle_web.rent
  ADD CONSTRAINT FK_rent_payment_payment_id FOREIGN KEY (rent_payment_id)
    REFERENCES rental_bicycle_web.payment(payment_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE rental_bicycle_web.rent
  ADD CONSTRAINT FK_rent_user_user_id FOREIGN KEY (rent_user_id)
    REFERENCES rental_bicycle_web.user(user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;