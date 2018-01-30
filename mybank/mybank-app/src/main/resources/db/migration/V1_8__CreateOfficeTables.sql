CREATE TABLE branch (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  description varchar(255) NOT NULL,
  status varchar(15) NOT NULL,
  created_date datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;