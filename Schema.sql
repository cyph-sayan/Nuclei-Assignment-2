-- TABLE STRUCTURE FOR TABLE STUDENT
CREATE TABLE `student`(
`name` varchar(30) NOT NULL,
`age` int NOT NULL,
`roll` int NOT NULL PRIMARY KEY,
`address` varchar(50) NOT NULL
);
--TABLE STRUCTURE FOR TABLE COURSE
CREATE TABLE `course`(
`course_id` varchar(1) NOT NULL PRIMARY KEY
);
--TABLE STRUCTURE FOR TABLE STUDENT_COURSE
CREATE TABLE `student_course`(
`roll` int NOT NULL,
`course_id` varchar(1) NOT NULL,
FOREIGN KEY(`roll`) references student(`roll`) ON DELETE CASCADE,
FOREIGN KEY(`course_id`) references course(`course_id`) ON DELETE SET NULL
);