CREATE TABLE project (
  project_id int(11) NOT NULL,
  department_id int(11) NOT NULL,
  name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  status varchar(15) NOT NULL,
  created_date datetime NOT NULL,
  termination_date datetime,
  budget decimal(10,2) NOT NULL,
  PRIMARY KEY (project_id, department_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO project VALUES (80301, 101, 'foreign_exchange', 'Internation foreign exchange project', 'ACTIVE', '2017-12-01 00:00:00', null, 100000);
