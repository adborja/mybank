CREATE TABLE employee (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  employee_id varchar(15) NOT NULL,
  designation varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX emp_employee_id_uniq ON employee (employee_id);
CREATE INDEX emp_employee_id_fk_idx ON employee (employee_id);

CREATE TABLE employee_details (
  id int(11) NOT NULL PRIMARY KEY,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(15) NOT NULL,
  mobile varchar(15),
  address varchar(100) NOT NULL,
  birth_date datetime NOT NULL,
  joining_date datetime NOT NULL,
  salary decimal(15,2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ALTER TABLE employee_details ADD CONSTRAINT employee_fk FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

