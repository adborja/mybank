CREATE TABLE card (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  status varchar(15) NOT NULL,
  created_date datetime NOT NULL,
  card_number varchar(16) NOT NULL,
  card_image_url varchar(50) NOT NULL,
  due_date datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;