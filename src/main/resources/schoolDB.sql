
CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL PRIMARY KEY,
    course_name varchar(50) NOT NULL,
    course_description varchar(150)
);

CREATE TABLE IF NOT EXISTS groups (
    group_id SERIAL PRIMARY KEY,
    group_name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL PRIMARY KEY,
    group_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS course_student(
    student_id int,
    course_id int,
    foreign key (student_id) references students(student_id),
    foreign key (course_id) references courses(course_id)
 );
