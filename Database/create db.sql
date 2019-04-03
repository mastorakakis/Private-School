CREATE SCHEMA private_school DEFAULT CHARACTER SET utf8;
use private_school;
-- ----------------------------------------------------------

CREATE TABLE private_school.student (
  st_id 			INT 			NOT NULL	AUTO_INCREMENT,
  first_name 		VARCHAR(20)		NOT NULL,
  last_name 		VARCHAR(40) 	NOT NULL,
  date_of_birth		DATE,
  tuition_fees 		INT 			NOT NULL,
  PRIMARY KEY (st_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ------------------------------------------------------------

CREATE TABLE private_school.trainer (
  t_id 			INT				NOT NULL		AUTO_INCREMENT,
  first_name	VARCHAR(20)		NOT NULL,
  last_name 	VARCHAR(40)		NOT NULL,
  t_subject 	VARCHAR(45)		NOT NULL,
  PRIMARY KEY (t_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ---------------------------------------------------------------

CREATE TABLE private_school.assignment (
  a_id 				INT				NOT NULL	AUTO_INCREMENT,
  title 			VARCHAR(45)		NOT NULL,
  t_description		TINYTEXT,
  total_mark 		INT,
  oral_mark 		INT,
  submission_date	DATE,
  PRIMARY KEY (a_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ------------------------------------------------------------------

CREATE TABLE private_school.stream (
  stream_id 			INT 			NOT NULL	AUTO_INCREMENT,
  stream_name 			VARCHAR(10) 	NOT NULL,
  PRIMARY KEY (stream_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.c_type (
  type_id 				INT 			NOT NULL	AUTO_INCREMENT,
  type_name 			VARCHAR(10) 	NOT NULL,
  PRIMARY KEY (type_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------

CREATE TABLE private_school.course (
  c_id 			INT 			NOT NULL		AUTO_INCREMENT,
  title 		VARCHAR(45) 	NOT NULL,
  stream_id 	INT,
  type_id 		INT,
  start_date 	DATE 			NOT NULL,
  end_date 		DATE 			NOT NULL,
  PRIMARY KEY (c_id),
  CONSTRAINT fk_cs_id
    FOREIGN KEY (stream_id) REFERENCES private_school.stream (stream_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_ct_id
    FOREIGN KEY (type_id) REFERENCES private_school.c_type (type_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.student_course (
  st_id INT NOT NULL,
  c_id INT NOT NULL,
  PRIMARY KEY (st_id, c_id),
  CONSTRAINT fk_st_id
	FOREIGN KEY (st_id) REFERENCES private_school.student (st_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_c_id
    FOREIGN KEY (c_id) REFERENCES private_school.course (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.trainer_course (
  t_id INT NOT NULL,
  c_id INT NOT NULL,
  PRIMARY KEY (t_id, c_id),
  CONSTRAINT fk_t_id
	FOREIGN KEY (t_id) REFERENCES private_school.trainer (t_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_tc_id
    FOREIGN KEY (c_id) REFERENCES private_school.course (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.assignment_course (
  a_id INT NOT NULL,
  c_id INT NOT NULL,
  PRIMARY KEY (a_id, c_id),
  CONSTRAINT fk_a_id
	FOREIGN KEY (a_id) REFERENCES private_school.assignment (a_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_ac_id
    FOREIGN KEY (c_id) REFERENCES private_school.course (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ---------------------------------------------------------------------

CREATE TABLE private_school.java_objectives (
  j_ob_id INT NOT NULL,
  title VARCHAR(45) NULL,
  stream_id INT NULL DEFAULT 1,
  PRIMARY KEY (j_ob_id),
  CONSTRAINT fk_sjo_id
	FOREIGN KEY (stream_id) REFERENCES private_school.stream (stream_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------------

CREATE TABLE private_school.c_objectives (
  c_ob_id INT NOT NULL,
  title VARCHAR(45) NULL,
  stream_id INT NULL DEFAULT 2,
  PRIMARY KEY (c_ob_id),
  CONSTRAINT fk_sco_id
	FOREIGN KEY (stream_id) REFERENCES private_school.stream (stream_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------------------------------

CREATE TABLE private_school.course_dates (
  cd_id INT NOT NULL,
  c_id INT NULL,
  day DATE NULL,
  PRIMARY KEY (cd_id),
  CONSTRAINT fk_c2_id
    FOREIGN KEY (c_id) REFERENCES private_school.course (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------------

CREATE TABLE private_school.roles (
  id INT NOT NULL,
  title VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `private_school`.`roles` 
CHANGE COLUMN `title` `role` VARCHAR(45) NOT NULL ;
----------------------------------------------------------------------------

CREATE TABLE `private_school`.`trainer_password` (
  `t_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`t_id`),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  INDEX `fk_tp_r_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_tp_t_id`
    FOREIGN KEY (`t_id`)
    REFERENCES `private_school`.`trainer` (`t_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tp_r_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `private_school`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `private_school`.`trainer_password` 
ADD COLUMN `username` VARCHAR(45) NOT NULL AFTER `t_id`,
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
DROP INDEX `password_UNIQUE` ;
-- ----------------------------------------------------------------------------

CREATE TABLE `private_school`.`student_password` (
  `st_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`st_id`),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  INDEX `fk_sp_r_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_sp_st_id`
    FOREIGN KEY (`st_id`)
    REFERENCES `private_school`.`student` (`st_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_sp_r_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `private_school`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `private_school`.`student_password` 
ADD COLUMN `username` VARCHAR(45) NOT NULL AFTER `st_id`,
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
DROP INDEX `password_UNIQUE`;
-- -----------------------------------------------------------

CREATE TABLE `private_school`.`headmaster_password` (
  `h_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`h_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_h_r_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_h_r_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `private_school`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

