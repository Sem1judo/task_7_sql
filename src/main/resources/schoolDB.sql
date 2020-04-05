
CREATE TABLE IF NOT EXISTS courses (
    course_id int NOT NULL PRIMARY KEY,
    course_name varchar(50) NOT NULL,
    course_description varchar(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS Sgroups (
    group_id int NOT NULL PRIMARY KEY,
    group_name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id int NOT NULL PRIMARY KEY,
    group_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS course_student (
  student_id    int REFERENCES students (student_id) ON UPDATE CASCADE,
 course_id int REFERENCES courses (course_id) ON UPDATE CASCADE,
CONSTRAINT student_course_pkey PRIMARY KEY (student_id, course_id)
);

