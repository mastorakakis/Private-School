-- List of all students------------
SELECT	st_id 'Student ID',
		concat(first_name, '  ', last_name) 'Full Name',
        date_of_birth 'Birth Date',
        tuition_fees 'Tuition Fees'
FROM student;


-- Lists of all trainers-------------
SELECT	t_id 'Trainer ID',
		concat(first_name, '  ', last_name) 'Full Name',
        t_subject Subject
FROM trainer;


-- List of all assignments------------
SELECT	a_id 'Assignment ID',
		title Title,
        t_description Description,
        total_mark 'Total Mark',
        oral_mark 'Oral Mark',
        submission_date 'Submission Date'
FROM assignment;


-- List of all courses------------------
SELECT	c_id 'Course ID',
		title Title,
        stream_name Stream,
        type_name Type,
        start_date 'Start Date',
        end_date 'End Date'
FROM course
	NATURAL JOIN stream
		NATURAL JOIN c_type
ORDER BY c_id;
	

-- All students per course------------
SELECT	title 'Course Title',
        stream_name Stream,
        type_name Type,
		concat(first_name, '  ', last_name) 'Full Name'
FROM student
	INNER JOIN student_course USING (st_id)
		INNER JOIN course USING (c_id)
			INNER JOIN stream USING (stream_id)
				INNER JOIN c_type USING (type_id)
ORDER BY course.title, stream, type, first_name;


-- All trainers per course----------------
SELECT	title 'Course Title',
        stream_name Stream,
        type_name Type,
        concat(first_name, '  ', last_name) 'Full Name',
        t_subject 'Subject'
FROM trainer
	INNER JOIN trainer_course USING (t_id)
		INNER JOIN course USING (c_id)
			INNER JOIN stream USING (stream_id)
				INNER JOIN c_type USING (type_id)
ORDER BY course.title, stream, type, first_name;


-- All assignments per course---------------
SELECT	course.title 'Course Title',
        stream_name Stream,
        type_name Type,
		assignment.title Title,
		t_description Description
FROM assignment
	INNER JOIN assignment_course USING (a_id)
		INNER JOIN course USING (c_id)
			INNER JOIN stream USING (stream_id)
				INNER JOIN c_type USING (type_id)
ORDER BY course.title, stream, type;

                
-- All assignments per course per student---------
SELECT	course.title 'Course Title',
        stream_name Stream,
        type_name Type,
        assignment.title Assignment,
		last_name 'Last Name',
		first_name 'First Name'
FROM assignment
	INNER JOIN assignment_course USING (a_id)
		INNER JOIN course USING (c_id)
			INNER JOIN stream USING (stream_id)
				INNER JOIN c_type USING (type_id)
					INNER JOIN student_course USING (c_id)
						INNER JOIN student USING (st_id)
ORDER BY course.title, stream, type, assignment.title;


-- All students that belong to more than one courses--------
SELECT	concat(first_name, '  ', last_name) 'Full Name',
		title 'Course Title',
		stream_name Stream,
		type_name Type
FROM student
	INNER JOIN student_course USING (st_id)
		INNER JOIN course USING (c_id)
			INNER JOIN stream USING (stream_id)
				INNER JOIN c_type USING (type_id)
where student.st_id in	(SELECT st_id 
						 FROM student_course
						 GROUP BY st_id 
						 HAVING COUNT(st_id) > 1)
ORDER BY first_name;

-- ----Java Objectives------------------------------------
SELECT ob_id, title FROM common_objectives
	LEFT JOIN stream USING (stream_id)
UNION    
SELECT j_ob_id, title FROM java_objectives
	LEFT JOIN stream USING (stream_id)
ORDER BY ob_id;


-- ---C# Objectives----------------------------------------
SELECT ob_id, title FROM common_objectives
	LEFT JOIN stream USING (stream_id)
UNION    
SELECT c_ob_id, title FROM c_objectives
	LEFT JOIN stream USING (stream_id)
ORDER BY ob_id;