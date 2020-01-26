-- insert privileges to database
INSERT INTO privilege (name, url) VALUES ('ADD_USER', '/users/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_USER', '/users/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_USER', '/users/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_USER', '/users,/users/*');
INSERT INTO privilege (name, url) VALUES ('ADD_STUDENT', '/students/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_STUDENT', '/students/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_STUDENT', '/students/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_STUDENT', '/students,/students/*');
INSERT INTO privilege (name, url) VALUES ('ADD_CLASS', '/classes/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_CLASS', '/classes/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_CLASS', '/classes/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_CLASS', '/classes,/classes/*');
INSERT INTO privilege (name, url) VALUES ('ADD_EMPLOYEE', '/employees/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_EMPLOYEE', '/employees/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_EMPLOYEE', '/employees/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_EMPLOYEE', '/employees,/employees/*');
INSERT INTO privilege (name, url) VALUES ('ADD_COURSE', '/courses/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_COURSE', '/courses/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_COURSE', '/courses/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_COURSE', '/courses,/courses/*');
INSERT INTO privilege (name, url) VALUES ('ADD_SCHOOL', '/schools/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_SCHOOL', '/schools/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_SCHOOL', '/schools/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_SCHOOL', '/schools,/schools/*');
INSERT INTO privilege (name, url) VALUES ('ADD_ROLLCALL', '/absences/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_ROLLCALL', '/absences/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_ROLLCALL', '/absences/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_ROLLCALL', '/absences,/absences/*');
INSERT INTO privilege (name, url) VALUES ('ADD_GUARDIAN', '/guardians/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_GUARDIAN', '/guardians/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_GUARDIAN', '/guardians/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_GUARDIAN', '/guardians,/guardians/*');
INSERT INTO privilege (name, url) VALUES ('ADD_JOB', '/jobs/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_JOB', '/jobs/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_JOB', '/jobs/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_JOB', '/jobs,/jobs/*');
INSERT INTO privilege (name, url) VALUES ('ADD_LEAVE', '/studentLeaves/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_LEAVE', '/studentLeaves/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_LEAVE', '/studentLeaves/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_LEAVE', '/studentLeaves,/studentLeaves/*');
INSERT INTO privilege (name, url) VALUES ('ADD_ROLE', '/roles/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_ROLE', '/roles/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_ROLE', '/roles/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_ROLE', '/roles,/roles/*');
-- INSERT INTO privilege (name, url) VALUES ('ADD_GRADE', '/');
-- INSERT INTO privilege (name, url) VALUES ('EDIT_GRADE', '/');
-- INSERT INTO privilege (name, url) VALUES ('DELETE_GRADE', '/');
-- INSERT INTO privilege (name, url) VALUES ('SHOW_GRADE', '/');
INSERT INTO privilege (name, url) VALUES ('ADD_TEACHER', '/teachers/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_TEACHER', '/teachers/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_TEACHER', '/teachers/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_TEACHER', '/teachers,/teachers/*');
INSERT INTO privilege (name, url) VALUES ('ADD_YEAR', '/years/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_YEAR', '/years/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_YEAR', '/years/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_YEAR', '/years,/years/*');
INSERT INTO privilege (name, url) VALUES ('ADD_TRANSCRIPT', '/transcripts/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_TRANSCRIPT', '/transcripts/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_TRANSCRIPT', '/transcripts/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_TRANSCRIPT', '/transcripts,/transcripts/*');
INSERT INTO privilege (name, url) VALUES ('ADD_STUDENT_GUARDIAN', '/studentGuardians/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_STUDENT_GUARDIAN', '/studentGuardians/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_STUDENT_GUARDIAN', '/studentGuardians/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_STUDENT_GUARDIAN', '/studentGuardians,/studentGuardians/*');
INSERT INTO privilege (name, url) VALUES ('ADD_LEVEL', '/levels/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_LEVEL', '/levels/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_LEVEL', '/levels/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_LEVEL', '/levels,/levels/*');
INSERT INTO privilege (name, url) VALUES ('ADD_COURSE_LEVEL', '/courseLevels/add');
INSERT INTO privilege (name, url) VALUES ('EDIT_COURSE_LEVEL', '/courseLevels/edit/*');
INSERT INTO privilege (name, url) VALUES ('DELETE_COURSE_LEVEL', '/courseLevels/delete/*');
INSERT INTO privilege (name, url) VALUES ('SHOW_COURSE_LEVEL', '/courseLevels,/courseLevels/*');
INSERT INTO privilege (name, url) VALUES ('HOME', '/');
INSERT INTO privilege (name, url) VALUES ('ALL_PRIVILEGE', '/**');

-- Insert job titles
INSERT INTO job_title (title) VALUES ('معلم')
INSERT INTO job_title (title) VALUES ('مدیر')

-- Insert course
INSERT INTO course (course_name, course_type) VALUES ('فارسی', 'نظری')
INSERT INTO course (course_name, course_type) VALUES ('ریاضی', 'نظری')
INSERT INTO course (course_name, course_type) VALUES ('قرآن', 'نظری')
INSERT INTO course (course_name, course_type) VALUES ('علوم', 'عملی')
INSERT INTO course (course_name, course_type) VALUES ('ورزش', 'عملی')
