-- -----------------------------------------------------
-- Setting DB paymybuddydb_test
-- -----------------------------------------------------
DROP DATABASE if exists paymybuddydb_test;
CREATE DATABASE paymybuddydb_test;
USE paymybuddydb_test ;
-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`user`
-- -----------------------------------------------------
CREATE TABLE user (
  id_user INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  active BIT(1) NULL DEFAULT b'1',
  email VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  roles VARCHAR(20) NOT NULL,
  user_name VARCHAR(50) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  creation_date DATE NOT NULL,
  modif_date DATE NULL DEFAULT NULL,
  wallet DOUBLE NULL DEFAULT NULL);


-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`bank_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS paymybuddydb_test.bank_account (
  id_bank_account INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  rib VARCHAR(255) NOT NULL,
  user_id INT NOT NULL,
		CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES user(id_user) );

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS paymybuddydb_test.friend (
  id_friend INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  creation_date DATE NOT NULL,
  friend_id INT NOT NULL,
  owner_id INT NOT NULL,
		CONSTRAINT fk_owner
        FOREIGN KEY (owner_id)
        REFERENCES user(id_user),
        CONSTRAINT fk_friend
        FOREIGN KEY (friend_id)
        REFERENCES user(id_user) );

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS paymybuddydb_test.transaction (
  id_transaction INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  date DATETIME NULL DEFAULT NULL,
  description VARCHAR(200) NOT NULL,
  fee DOUBLE NOT NULL,
  beneficiary_id INT NOT NULL,
  payer_id INT NOT NULL,
		CONSTRAINT fk_beneficiary
        FOREIGN KEY (beneficiary_id)
        REFERENCES user(id_user),
        CONSTRAINT fk_payer
        FOREIGN KEY (payer_id)
        REFERENCES user(id_user) );


-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS paymybuddydb_test.transfer (
  id_transfer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  amount DOUBLE NOT NULL,
  date DATE NOT NULL,
  rib VARCHAR(255) NOT NULL,
  type ENUM('CREDIT_WALLET', 'DEBIT_WALLET') NOT NULL,
  user_id INT NOT NULL,
		CONSTRAINT fk_user_transfer
        FOREIGN KEY (user_id)
        REFERENCES user(id_user) );

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS paymybuddydb_test.hibernate_sequence (
	next_val BIGINT NULL);


-- MySQL Script Data
-- for  paymybuddydb_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`user`
-- -----------------------------------------------------
INSERT INTO `paymybuddydb_test`.`user` (`active`, `email`, `password`, `roles`, `user_name`, `firstname`, `creation_date`, `modif_date`, `wallet`) VALUES
(b'1', 'lolo@email.com', '$2a$10$VTa4iffylpLoUTmiGX5Xn.hSzWI83ggJN257w54hFe6Q0CO9kBtUe', 'ROLE_ADMIN', 'Do', 'Laurent', '2021-06-03', '2021-06-16', '200.0'),
(b'1', 'fanny@email.com', '$2a$10$VTa4iffylpLoUTmiGX5Xn.hSzWI83ggJN257w54hFe6Q0CO9kBtUe', 'ROLE_USER', 'Durant', 'Fanny', '2021-06-03', '2021-06-16', '200.0'),
(b'1', 'flora@email.com', '$2a$10$VTa4iffylpLoUTmiGX5Xn.hSzWI83ggJN257w54hFe6Q0CO9kBtUe', 'ROLE_USER', 'Dupont', 'Flora', '2021-06-03', '2021-06-16', '200.0'),
(b'1', 'mathieu@email.com', '$2a$10$VTa4iffylpLoUTmiGX5Xn.hSzWI83ggJN257w54hFe6Q0CO9kBtUe', 'ROLE_USER', 'Tutor', 'Mathieu', '2021-06-03', '2021-06-16', '200.0');
commit;

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`bank_account`
-- -----------------------------------------------------
INSERT INTO `paymybuddydb_test`.`bank_account` (`rib`, `user_id`) VALUES
('fr 4587 4578 45578 5478 N', '1'),
('fr 1245 5414 5877 4588 N', '1'),
('fr 8854 1245 7852 9542 N', '2'),
('fr 8454 8445 2445 8547 N', '2'),
('fr 4963 4578 8614 5478 N', '3'),
('fr 6747 3541 9645 8957 N', '4');
commit;

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`friend`
-- -----------------------------------------------------
INSERT INTO `paymybuddydb_test`.`friend` (`creation_date`, `friend_id`, `owner_id`) VALUES
('2021-06-23', '3', '4'),
('2021-06-23', '1', '4'),
('2021-06-23', '3', '1'),
('2021-06-23', '4', '2'),
('2021-06-23', '1', '2'),
('2021-06-23', '2', '3'),
('2021-06-23', '4', '3');
commit;
-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`transaction`
-- -----------------------------------------------------
INSERT INTO `paymybuddydb_test`.`transaction` (`amount`, `date`, `description`, `fee`, `beneficiary_id`, `payer_id`) VALUES
('50', '2021-06-22 13:12:35', 'cinéma', '0.25', '2', '1'),
('50', '2021-06-23 13:12:35', 'restaurant', '0.25', '2', '1'),
('50', '2021-06-22 13:10:35', 'Cadeau d anniv Mathieu', '0.25', '3', '1'),
('50', '2021-06-23 13:12:35', 'restaurant', '0.25', '1', '2'),
('50', '2021-06-22 13:09:35', 'cinéma', '0.25', '3', '2'),
('50', '2021-06-22 13:11:35', 'Cadeau d anniv Mathieu', '0.25', '4', '2'),
('50', '2021-06-23 13:10:35', 'restaurant', '0.25', '1', '2'),
('50', '2021-06-22 13:12:35', 'cinéma', '0.25', '3', '2'),
('50', '2021-06-19 13:12:35', 'théatre', '0.25', '3', '1');
commit;

-- -----------------------------------------------------
-- Table `paymybuddydb_test`.`transfer`
-- -----------------------------------------------------
INSERT INTO `paymybuddydb_test`.`transfer` (`amount`, `date`, `rib`, `type`, `user_id`) VALUES
('500', '2021-06-23', 'fr 4445 88989 898989 8989', 'CREDIT_WALLET', '1'),
('100', '2021-06-23', 'fr 4445 88989 898989 8989', 'DEBIT_WALLET', '1'),
('500', '2021-06-23', 'fr 4445 88989 898989 8989', 'CREDIT_WALLET', '2'),
('100', '2021-06-23', 'fr 4445 88989 898989 8989', 'DEBIT_WALLET', '2'),
('500', '2021-06-23', 'fr 4445 88989 898989 8989', 'CREDIT_WALLET', '3'),
('100', '2021-06-23', 'fr 4445 88989 898989 8989', 'DEBIT_WALLET', '3'),
('500', '2021-06-23', 'fr 4445 88989 898989 8989', 'CREDIT_WALLET', '4'),
('100', '2021-06-23', 'fr 4445 88989 898989 8989', 'DEBIT_WALLET', '4');
commit;

INSERT INTO paymybuddydb_test.hibernate_sequence (next_val) VALUES
(60);