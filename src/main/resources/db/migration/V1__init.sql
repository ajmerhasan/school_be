CREATE TABLE student(
  student_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  mobile INT(11) NOT NULL,
  nric BIGINT(12) NOT NULL,
  gender INT(1) NOT NULL,
  PRIMARY KEY (student_id)
);

CREATE TABLE course(
  course_id INT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (course_id)
);

CREATE TABLE student_course(
  student_course_id INT NOT NULL AUTO_INCREMENT,
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  year_taken INT(4) NOT NULL,
  score INT(3) NOT NULL,
  PRIMARY KEY (student_course_id),
  FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);