CREATE SCHEMA private_school DEFAULT CHARACTER SET utf8;
use private_school;
-- ----------------------------------------------------------

CREATE TABLE private_school.roles (
  r_id 				INT 			NOT NULL,
  `role` 			VARCHAR(45) 	NOT NULL,
  PRIMARY KEY (r_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------

CREATE TABLE `private_school`.`users` (
  `u_id` 			INT 			NOT NULL 	AUTO_INCREMENT,
  `username` 		VARCHAR(45)		NOT NULL,
  `password`		VARCHAR(45)		NOT NULL,
  `r_id` 			INT 			NOT NULL	DEFAULT 1,
  PRIMARY KEY (`u_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_u_r_id`
    FOREIGN KEY (`r_id`) REFERENCES `private_school`.`roles` (`r_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ---------------------------------------------------------

CREATE TABLE private_school.students (
  st_id 			INT 			NOT NULL	AUTO_INCREMENT,
  first_name 		VARCHAR(20)		NOT NULL,
  last_name 		VARCHAR(40) 	NOT NULL,
  date_of_birth		DATE,
  tuition_fees 		INT 			NOT NULL,
  u_id				INT				NOT NULL,
  PRIMARY KEY (st_id),
  CONSTRAINT `fk_s_u_id`
    FOREIGN KEY (`u_id`) REFERENCES `private_school`.`users` (`u_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ------------------------------------------------------------

CREATE TABLE private_school.trainers (
  t_id 			INT				NOT NULL		AUTO_INCREMENT,
  first_name	VARCHAR(20)		NOT NULL,
  last_name 	VARCHAR(40)		NOT NULL,
  t_subject 	VARCHAR(45)		NOT NULL,
  u_id			INT				NOT NULL,
  PRIMARY KEY (t_id),
  CONSTRAINT `fk_t_u_id`
    FOREIGN KEY (`u_id`) REFERENCES `private_school`.`users` (`u_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ---------------------------------------------------------------

CREATE TABLE private_school.assignments (
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

CREATE TABLE private_school.streams (
  stream_id 			INT 			NOT NULL	AUTO_INCREMENT,
  stream_name 			VARCHAR(10) 	NOT NULL,
  PRIMARY KEY (stream_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.c_types (
  type_id 				INT 			NOT NULL	AUTO_INCREMENT,
  type_name 			VARCHAR(10) 	NOT NULL,
  PRIMARY KEY (type_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------

CREATE TABLE private_school.courses (
  c_id 			INT 			NOT NULL		AUTO_INCREMENT,
  title 		VARCHAR(45) 	NOT NULL,
  stream_id 	INT,
  type_id 		INT,
  start_date 	DATE 			NOT NULL,
  end_date 		DATE 			NOT NULL,
  PRIMARY KEY (c_id),
  CONSTRAINT fk_cs_id
    FOREIGN KEY (stream_id) REFERENCES private_school.streams (stream_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT fk_ct_id
    FOREIGN KEY (type_id) REFERENCES private_school.c_types (type_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.students_course (
  st_id 		INT 	NOT NULL,
  c_id 			INT 	NOT NULL,
  PRIMARY KEY (st_id, c_id),
  CONSTRAINT fk_st_id
	FOREIGN KEY (st_id) REFERENCES private_school.students (st_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_c_id
    FOREIGN KEY (c_id) REFERENCES private_school.courses (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.trainers_course (
  t_id 			INT		NOT NULL,
  c_id		 	INT		NOT NULL,
  PRIMARY KEY (t_id, c_id),
  CONSTRAINT fk_t_id
	FOREIGN KEY (t_id) REFERENCES private_school.trainers (t_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_tc_id
    FOREIGN KEY (c_id) REFERENCES private_school.courses (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- --------------------------------------------------------------------

CREATE TABLE private_school.assignments_course (
  a_id 				INT 		NOT NULL,
  c_id 				INT 		NOT NULL,
  PRIMARY KEY (a_id, c_id),
  CONSTRAINT fk_a_id
	FOREIGN KEY (a_id) REFERENCES private_school.assignments (a_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_ac_id
    FOREIGN KEY (c_id) REFERENCES private_school.courses (c_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- ---------------------------------------------------------------------

CREATE TABLE private_school.java_objectives (
  j_ob_id 			INT 			NOT NULL	AUTO_INCREMENT,
  title 			VARCHAR(45) 	NULL,
  stream_id 		INT 			NULL 		DEFAULT 1,
  PRIMARY KEY (j_ob_id),
  CONSTRAINT fk_sjo_id
	FOREIGN KEY (stream_id) REFERENCES private_school.streams (stream_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------------

CREATE TABLE private_school.c_objectives (
  c_ob_id 			INT 			NOT NULL	AUTO_INCREMENT,
  title 			VARCHAR(45) 	NULL,
  stream_id 		INT 			NULL 		DEFAULT 2,
  PRIMARY KEY (c_ob_id),
  CONSTRAINT fk_sco_id
	FOREIGN KEY (stream_id) REFERENCES private_school.streams (stream_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------------------------------

CREATE TABLE `private_school`.`java_schedule_dates` (
  `c_id` INT NOT NULL,
  `j_ob_id` INT NOT NULL,
  `c_date` DATE NULL,
  CONSTRAINT `fk_js_c_id`
    FOREIGN KEY (`c_id`) REFERENCES `private_school`.`courses` (`c_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_js_jo_id`
    FOREIGN KEY (`j_ob_id`) REFERENCES `private_school`.`java_objectives` (`j_ob_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
-- -------------------------------------------------------------------------------------

-- CREATE TABLE `private_school`.`trainer_password` (
--   `t_id` INT NOT NULL,
--   `password` VARCHAR(45) NOT NULL,
--   `role_id` INT NOT NULL,
--   PRIMARY KEY (`t_id`),
--   UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
--   INDEX `fk_tp_r_id_idx` (`role_id` ASC) VISIBLE,
--   CONSTRAINT `fk_tp_t_id`
--     FOREIGN KEY (`t_id`)
--     REFERENCES `private_school`.`trainer` (`t_id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE,
--   CONSTRAINT `fk_tp_r_id`
--     FOREIGN KEY (`role_id`)
--     REFERENCES `private_school`.`roles` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE)
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8;

-- ALTER TABLE `private_school`.`trainer_password` 
-- ADD COLUMN `username` VARCHAR(45) NOT NULL AFTER `t_id`,
-- ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
-- DROP INDEX `password_UNIQUE` ;
-- ----------------------------------------------------------------------------

-- CREATE TABLE `private_school`.`student_password` (
--   `st_id` INT NOT NULL,
--   `password` VARCHAR(45) NOT NULL,
--   `role_id` INT NOT NULL,
--   PRIMARY KEY (`st_id`),
--   UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
--   INDEX `fk_sp_r_id_idx` (`role_id` ASC) VISIBLE,
--   CONSTRAINT `fk_sp_st_id`
--     FOREIGN KEY (`st_id`)
--     REFERENCES `private_school`.`student` (`st_id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE,
--   CONSTRAINT `fk_sp_r_id`
--     FOREIGN KEY (`role_id`)
--     REFERENCES `private_school`.`roles` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE)
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8;

-- ALTER TABLE `private_school`.`student_password` 
-- ADD COLUMN `username` VARCHAR(45) NOT NULL AFTER `st_id`,
-- ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
-- DROP INDEX `password_UNIQUE`;
-- -----------------------------------------------------------

-- CREATE TABLE `private_school`.`headmaster_password` (
--   `h_id` INT NOT NULL,
--   `username` VARCHAR(45) NOT NULL,
--   `password` VARCHAR(45) NOT NULL,
--   `r_id` INT NOT NULL,
--   PRIMARY KEY (`h_id`),
--   UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
--   INDEX `fk_h_r_id_idx` (`r_id` ASC) VISIBLE,
--   CONSTRAINT `fk_h_r_id`
--     FOREIGN KEY (`r_id`)
--     REFERENCES `private_school`.`roles` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE)
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = utf8;
-- -----------------------------------------------------------------
