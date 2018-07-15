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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idusuario` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `login` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`propriedade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`propriedade` (
  `idpropriedade` INT NOT NULL,
  `nfc` VARCHAR(45) NULL,
  `peso` DOUBLE NULL,
  `raca` VARCHAR(45) NULL,
  `pelo` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `pai` VARCHAR(45) NULL,
  `mae` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `usuario_idusuario` INT NOT NULL,
  `municipio` VARCHAR(45) NULL,
  `localidade` VARCHAR(45) NULL,
  PRIMARY KEY (`idpropriedade`, `usuario_idusuario`),
  INDEX `fk_propriedade_usuario_idx` (`usuario_idusuario` ASC),
  CONSTRAINT `fk_propriedade_usuario`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `mydb`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`insumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`insumo` (
  `idinsumo` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `qtd` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `utilizacao` VARCHAR(20) NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idinsumo`, `usuario_idusuario`),
  INDEX `fk_insumo_usuario1_idx` (`usuario_idusuario` ASC),
  CONSTRAINT `fk_insumo_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `mydb`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`animal` (
  `idanimal` INT NOT NULL,
  `nfc` VARCHAR(45) NULL,
  `peso` DOUBLE NULL,
  `raca` VARCHAR(45) NULL,
  `pelagem` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `pai` VARCHAR(45) NULL,
  `mae` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  `propriedade_idpropriedade` INT NOT NULL,
  `propriedade_usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idanimal`, `propriedade_idpropriedade`, `propriedade_usuario_idusuario`),
  INDEX `fk_animal_propriedade1_idx` (`propriedade_idpropriedade` ASC, `propriedade_usuario_idusuario` ASC),
  CONSTRAINT `fk_animal_propriedade1`
    FOREIGN KEY (`propriedade_idpropriedade` , `propriedade_usuario_idusuario`)
    REFERENCES `mydb`.`propriedade` (`idpropriedade` , `usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sanidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sanidade` (
  `idsanidade` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `data` VARCHAR(45) NULL,
  `produto_utilizado` VARCHAR(45) NULL,
  `qtd_utilizada` VARCHAR(45) NULL,
  `animal_idanimal` INT NOT NULL,
  `animal_propriedade_idpropriedade` INT NOT NULL,
  `animal_propriedade_usuario_idusuario` INT NOT NULL,
  `insumo_idinsumo` INT NOT NULL,
  `insumo_usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idsanidade`, `animal_idanimal`, `animal_propriedade_idpropriedade`, `animal_propriedade_usuario_idusuario`, `insumo_idinsumo`, `insumo_usuario_idusuario`),
  INDEX `fk_sanidade_animal1_idx` (`animal_idanimal` ASC, `animal_propriedade_idpropriedade` ASC, `animal_propriedade_usuario_idusuario` ASC),
  INDEX `fk_sanidade_insumo1_idx` (`insumo_idinsumo` ASC, `insumo_usuario_idusuario` ASC),
  CONSTRAINT `fk_sanidade_animal1`
    FOREIGN KEY (`animal_idanimal` , `animal_propriedade_idpropriedade` , `animal_propriedade_usuario_idusuario`)
    REFERENCES `mydb`.`animal` (`idanimal` , `propriedade_idpropriedade` , `propriedade_usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sanidade_insumo1`
    FOREIGN KEY (`insumo_idinsumo` , `insumo_usuario_idusuario`)
    REFERENCES `mydb`.`insumo` (`idinsumo` , `usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pesagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pesagem` (
  `idpesagem` INT NOT NULL,
  `data` VARCHAR(45) NULL,
  `nfc` VARCHAR(45) NULL,
  `animal_idanimal` INT NOT NULL,
  `animal_propriedade_idpropriedade` INT NOT NULL,
  `animal_propriedade_usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idpesagem`),
  INDEX `fk_pesagem_animal1_idx` (`animal_idanimal` ASC, `animal_propriedade_idpropriedade` ASC, `animal_propriedade_usuario_idusuario` ASC),
  CONSTRAINT `fk_pesagem_animal1`
    FOREIGN KEY (`animal_idanimal` , `animal_propriedade_idpropriedade` , `animal_propriedade_usuario_idusuario`)
    REFERENCES `mydb`.`animal` (`idanimal` , `propriedade_idpropriedade` , `propriedade_usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pesagem_animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pesagem_animal` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pesagem_has_animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pesagem_has_animal` (
  `pesagem_idpesagem` INT NOT NULL,
  `animal_idanimal` INT NOT NULL,
  `animal_propriedade_idpropriedade` INT NOT NULL,
  `animal_propriedade_usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`pesagem_idpesagem`, `animal_idanimal`, `animal_propriedade_idpropriedade`, `animal_propriedade_usuario_idusuario`),
  INDEX `fk_pesagem_has_animal_animal1_idx` (`animal_idanimal` ASC, `animal_propriedade_idpropriedade` ASC, `animal_propriedade_usuario_idusuario` ASC),
  INDEX `fk_pesagem_has_animal_pesagem1_idx` (`pesagem_idpesagem` ASC),
  CONSTRAINT `fk_pesagem_has_animal_pesagem1`
    FOREIGN KEY (`pesagem_idpesagem`)
    REFERENCES `mydb`.`pesagem` (`idpesagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pesagem_has_animal_animal1`
    FOREIGN KEY (`animal_idanimal` , `animal_propriedade_idpropriedade` , `animal_propriedade_usuario_idusuario`)
    REFERENCES `mydb`.`animal` (`idanimal` , `propriedade_idpropriedade` , `propriedade_usuario_idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
