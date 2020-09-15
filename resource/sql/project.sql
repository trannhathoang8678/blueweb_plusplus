-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`PROVIDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PROVIDER` (
  `provider_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `phone` CHAR(10) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`provider_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TYPE` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `maker` VARCHAR(45) NOT NULL,
  `product_line` VARCHAR(45) NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`DISCOUNT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DISCOUNT` (
  `discount_id` INT NOT NULL AUTO_INCREMENT,
  `start_time` TIMESTAMP NULL,
  `end_time` TIMESTAMP NULL,
  `percent` INT(7) NOT NULL,
  PRIMARY KEY (`discount_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = eucjpms;


-- -----------------------------------------------------
-- Table `mydb`.`PRODUCT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PRODUCT` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type_id` INT NOT NULL,
  `specification` TEXT NULL,
  `url_image` VARCHAR(45) NULL,
  `number` VARCHAR(45) NULL,
  `year_create` INT NULL,
  `place_create` VARCHAR(45) NULL,
  `price` DECIMAL(16,3) NOT NULL,
  `instalment` TINYINT(1) NULL,
  `discount_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_PRODUCT_TYPE1_idx` (`type_id` ASC),
  INDEX `fk_PRODUCT_DISCOUNT1_idx` (`discount_id` ASC),
  CONSTRAINT `fk_PRODUCT_TYPE1`
    FOREIGN KEY (`type_id`)
    REFERENCES `mydb`.`TYPE` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_DISCOUNT1`
    FOREIGN KEY (`discount_id`)
    REFERENCES `mydb`.`DISCOUNT` (`discount_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`IMPORT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`IMPORT` (
  `product_id` INT NOT NULL,
  `provider_id` INT NOT NULL,
  `number` INT NULL,
  `price` DECIMAL NOT NULL,
  `note` VARCHAR(45) NULL,
  `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_PROVIDE_PRODUCT_idx` (`product_id` ASC),
  INDEX `fk_PROVIDE_PROVIDER1_idx` (`provider_id` ASC))
ENGINE = MRG_MyISAM
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CUSTOMER` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone_number` CHAR(10) NULL,
  `note` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ORDER` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `address` VARCHAR(45) NULL,
  `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_ORDER_CUSTOMER1_idx` (`customer_id` ASC),
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_ORDER_CUSTOMER1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`CUSTOMER` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`MARKETING_ARTICLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MARKETING_ARTICLE` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `name` VARCHAR(75) NOT NULL,
  `url_image` VARCHAR(45) NULL,
  `content` TEXT NULL,
  `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`),
  INDEX `fk_MARKETING_ARTICLE_PROVIDER1_idx` (`provider_id` ASC),
  CONSTRAINT `fk_MARKETING_ARTICLE_PROVIDER1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `mydb`.`PROVIDER` (`provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`REVIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`REVIEW` (
  `customer_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `RATE` DECIMAL(1,1) NULL,
  `url_image` VARCHAR(45) NULL,
  `content` TEXT NULL,
  `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_REVIEW_PRODUCT1_idx` (`product_id` ASC),
  CONSTRAINT `fk_REVIEW_CUSTOMER1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mydb`.`CUSTOMER` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REVIEW_PRODUCT1`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`PRODUCT` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`ARTICLE_PRODUCT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ARTICLE_PRODUCT` (
  `article_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  INDEX `fk_ARTICLE_PRODUCT_MARKETING_ARTICLE1_idx` (`article_id` ASC),
  INDEX `fk_ARTICLE_PRODUCT_PRODUCT1_idx` (`product_id` ASC),
  CONSTRAINT `fk_ARTICLE_PRODUCT_MARKETING_ARTICLE1`
    FOREIGN KEY (`article_id`)
    REFERENCES `mydb`.`MARKETING_ARTICLE` (`article_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ARTICLE_PRODUCT_PRODUCT1`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`PRODUCT` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`PRODUCT_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PRODUCT_ORDER` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `number_product` INT NULL,
  `payment_mode` VARCHAR(45) NULL,
  `option` VARCHAR(45) NULL,
  INDEX `fk_PRODUCT_ORDER_ORDER1_idx` (`order_id` ASC),
  INDEX `fk_PRODUCT_ORDER_PRODUCT1_idx` (`product_id` ASC),
  CONSTRAINT `fk_PRODUCT_ORDER_ORDER1`
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`ORDER` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCT_ORDER_PRODUCT1`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`PRODUCT` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`HOT_PRODUCT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HOT_PRODUCT` (
  `product_id` INT NOT NULL,
  `is_hot` TINYINT(1) NOT NULL,
  `hot_display_id` INT NULL,
  `is_bigsale` TINYINT(1) NOT NULL,
  `bigsale_id` INT NULL,
  INDEX `fk_BIGSALE_PRODUCT_PRODUCT1_idx` (`product_id` ASC),
  UNIQUE INDEX `hot_display_id_UNIQUE` (`hot_display_id` ASC),
  UNIQUE INDEX `bigsale_id_UNIQUE` (`bigsale_id` ASC),
  CONSTRAINT `fk_BIGSALE_PRODUCT_PRODUCT1`
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`PRODUCT` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
