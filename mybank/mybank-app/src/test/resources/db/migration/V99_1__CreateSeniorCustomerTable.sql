CREATE TABLE senior_customer (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  email varchar(50) NOT NULL,
  password varchar(100) NOT NULL,
  billing_address varchar(255) NOT NULL,
  age INT(4) NOT NULL,
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
