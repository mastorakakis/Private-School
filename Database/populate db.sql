INSERT INTO private_school.student (st_id, first_name, last_name, tuition_fees, date_of_birth)
VALUES	(DEFAULT, "Panagiotis", "Mastorakakis", 2500, "1983-01-16"),
		(DEFAULT, "Nikos", "Vassis", 2500, "1982-04-13"),
		(DEFAULT, "Angelos", "Papakostas", 2500, "1985-03-17"),
        (DEFAULT, "Nikos", "Paliouras", 2500, "1990-05-21"),
        (DEFAULT, "Kostas", "Athanasoulias", 2500, "1982-10-12"),
        (DEFAULT, "Petros", "Theodorou", 2500, "1985-03-11"),
        (DEFAULT, "Nikos", "Rousias", 2500, "1984-08-01"),
        (DEFAULT, "Giorgos", "Makrakis", 2500, "1983-10-04"),
        (DEFAULT, "Aris", "Kitsinis", 2500, "1983-01-26"),
        (DEFAULT, "Christos", "Panopoulos", 2500, "1977-04-19");
  
  
INSERT INTO private_school.trainer (t_id, first_name, last_name, t_subject)
VALUES	(DEFAULT, "Marialena", "Spiraki", "Software Engineer"),
        (DEFAULT, "Stefanos", "Mpoglou", "Software engineer"),
        (DEFAULT, "Petros", "Antoniou", "Database Engineer"),
        (DEFAULT, "Marios", "Perakis","Software Enginner");
        
        
INSERT INTO private_school.assignment (a_id, title, t_description, oral_mark, total_mark, submission_date)
VALUES	(DEFAULT, "CB7_c#_Ex1", "You are required to produce a solution that implements a private school structure.", 20, 100, "2018-02-08"),
        (DEFAULT, "CB7_java_full_Ex1", "You must create a CardGame class to use as a base class for different card games.", 20, 100, "2019-02-08"),
        (DEFAULT, "CB7_java_part_Ex1", "Create a class called Bunny with an int bunnyNumber initialized in the constructor.", 20, 100, "2019-02-15");
        

INSERT INTO private_school.stream
VALUES	(DEFAULT, "java"),
		(DEFAULT, "c#");
        
        

INSERT INTO private_school.c_type
VALUES	(DEFAULT, "full"),
		(DEFAULT, "part");
   
   
   
INSERT INTO private_school.course (c_id, title, stream_id, type_id, start_date, end_date)
VALUES	(DEFAULT, "CB7", 2, 1, "2019-02-04", "2019-02-08"),
        (DEFAULT, "CB7", 1, 1, "2019-02-04", "2019-02-08"),
        (DEFAULT, "CB7", 1, 2, "2019-02-04", "2019-02-15");
        
        
INSERT INTO private_school.trainer_course
VALUES	(1, 1),
		(2, 2),
        (3, 3),
        (4, 3);
        
        
INSERT INTO private_school.assignment_course
VALUES	(1, 1),
		(2, 2),
        (3, 3);
        
        
INSERT INTO private_school.student_course
VALUES	(1,1),
		(2,1),
        (3,1),
        (4,1),
        (5,2),
        (6,2),
        (7,2),
        (8,2),
        (7,3),
        (8,3),
        (9,3),
        (10,3);
  

INSERT INTO private_school.java_objectives (j_ob_id, title)
VALUES	(1, "ECDL Computing"),
		(2, "Introduction to Java"),
        (3, "Understand and Use Interfaces"),
        (4, "MySQL Basics"),
        (5, "HTML/CSS"),
        (6, "Javascript"),
        (7, "GIT"),
        (8, "Design patterns"),
        (9, "Algorithms"),
        (10, "Scrum");
        
        
INSERT INTO private_school.c_objectives (c_ob_id, title)
VALUES	(1, "ECDL Computing"),
		(2, "Introduction to C#"),
		(3, "Understand classes"),
        (4, "SQL Server Basics"),
        (5, "HTML/CSS"),
        (6, "Javascript"),
        (7, "GIT"),
        (8, "Design patterns"),
        (9, "Algorithms"),
        (10, "Scrum");
  
  
INSERT INTO private_school.course_dates
VALUES	(1, 1, "2019-02-04"),
		(2, 1, "2019-02-05"),
        (3, 1, "2019-02-06"),
        (4, 1, "2019-02-07"),
        (5, 1, "2019-02-08"),
        (6, 2, "2019-02-04"),
        (7, 2, "2019-02-05"),
        (8, 2, "2019-02-06"),
        (9, 2, "2019-02-07"),
		(10, 2, "2019-02-08"),
        (11, 3, "2019-02-04"),
        (12, 3, "2019-02-05"),
        (13, 3, "2019-02-06"),
        (14, 3, "2019-02-07"),
		(15, 3, "2019-02-08"),
        (16, 3, "2019-02-11"),
        (17, 3, "2019-02-12"),
        (18, 3, "2019-02-13"),
        (19, 3, "2019-02-14"),
		(20, 3, "2019-02-15");
        
INSERT INTO private_school.roles (id, role) 
VALUES	('1', 'head master'),
		('2', 'trainer'),
		('3', 'student');

-- --------------------------------------------------------        

CREATE TABLE assignment_student_course
SELECT *
FROM (SELECT c_id, a_id, st_id
	  FROM assignment_course
		INNER JOIN student_course USING (c_id)) AS joinedtables;
 
ALTER TABLE private_school.assignment_student_course 
ADD COLUMN oral_mark INT NULL AFTER st_id,
ADD COLUMN total_mark INT NULL AFTER oral_mark,
ADD PRIMARY KEY (c_id, a_id, st_id);

ALTER TABLE private_school.assignment_student_course 
ADD CONSTRAINT fk_asc_c_id
  FOREIGN KEY (c_id)
  REFERENCES private_school.course (c_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT fk_asc_a_id
  FOREIGN KEY (a_id)
  REFERENCES private_school.assignment (a_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT fk_asc_s_id
  FOREIGN KEY (st_id)
  REFERENCES private_school.student (st_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
UPDATE private_school.assignment_student_course
SET oral_mark = '20', total_mark = '90' 
WHERE (c_id = '1') and (a_id = '1') and (st_id = '1');

UPDATE private_school.assignment_student_course
SET oral_mark = '15', total_mark = '85'
WHERE (c_id = '1') and (a_id = '1') and (st_id = '2');
-- ----------------------------------------------------------------------

INSERT INTO `private_school`.`student_password` (`st_id`, username, `password`, `role_id`)
VALUES  ('1', 'mast', 'ceb74219d144ab5760a228e71440c5ca', '3'),
        ('2', 'vass', '2249ad26be14e474cf425a6f1efaabe6', '3'),
        ('3', 'papa', '0ac6cd34e2fac333bf0ee3cd06bdcf96', '3'),
        ('4', 'pali', 'bb7030f04b041c5d629bd46fbb208277', '3'),
        ('5', 'atha', '94af637759d2b72f1c7bee1832a6f0ad', '3'),
        ('6', 'theo', '7938414aed691e4bf32edcad0d7df0c6', '3'),
        ('7', 'rous', '0936a457f7149a0a7c1c0aa37d2c6ec5', '3'),
        ('8', 'makr', 'ecfe37d052d984a079d68560dbe36cb9', '3'),
        ('9', 'kits', 'e16d4ffa0f9467cd8d2db7d706f0a7a5', '3'),
        ('10', 'pano', '7af88f1d651067943c4aff4d7023ef55', '3');
-- -----------------------------------------------------------------------------

INSERT INTO `private_school`.`trainer_password` (`t_id`, username, `password`, `role_id`)
VALUES  ('1', 'spir', '7b84eb993405d76c871bdf40e5e9cda7', '2'),
        ('2', 'mpog', 'f7f616238d308ccbc82783db50b4af56', '2'),
        ('3', 'anto', '2c946c0178ec72aaefa54f786540d301', '2'),
        ('4', 'pera', 'd8795f0d07280328f80e59b1e8414c49', '2');
-- -------------------------------------------------------------------------------------------
        
INSERT INTO `private_school`.`headmaster_password` (`h_id`, `username`, `password`, `role_id`) 
VALUES ('1', 'head', '96e89a298e0a9f469b9ae458d6afae9f', '1');

