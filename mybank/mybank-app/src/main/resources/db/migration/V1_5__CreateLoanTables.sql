CREATE TABLE loan (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  status varchar(15) NOT NULL,
  rate decimal(3,2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE car_loan (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  status varchar(15) NOT NULL,
  rate decimal(3,2) NOT NULL,
  life_insurance_company varchar(50) NOT NULL,
  life_insurance_id varchar(5) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE home_loan (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  status varchar(15) NOT NULL,
  rate decimal(3,2) NOT NULL,
  fire_insurance_company varchar(50) NOT NULL,
  fire_insurance_id varchar(5) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;