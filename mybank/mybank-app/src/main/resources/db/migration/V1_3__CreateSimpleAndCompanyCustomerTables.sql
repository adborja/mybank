CREATE TABLE employee_customer (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  employee_id varchar(15) NOT NULL,
  email varchar(50) NOT NULL,
  mobile varchar(15) NOT NULL,
  password varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX emp_customer_employee_id_uniq ON employee_customer (employee_id);

CREATE TABLE company_customer (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  company_id varchar(15) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(15) NOT NULL,
  password varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX com_customer_company_id_uniq ON company_customer (company_id);

