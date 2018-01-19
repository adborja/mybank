CREATE TABLE channel (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE product (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(45) NOT NULL,
  description varchar(45) NOT NULL,
  status varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE transaction_type (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  description varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE customer (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  customer_id varchar(15) NOT NULL,
  email varchar(50) NOT NULL,
  mobile varchar(15) NOT NULL,
  phone varchar(15) DEFAULT NULL,
  password varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX customer_customer_id_uniq ON customer (customer_id);

CREATE TABLE customer_product (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  customer_id int(11) NOT NULL,
  product_id int(11) NOT NULL,
  creation_date datetime NOT NULL,
  termination_date datetime DEFAULT NULL,
  balance decimal(10,0) NOT NULL DEFAULT '0',
  status varchar(10) NOT NULL,
  product_number varchar(10) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE INDEX customer_fk_idx ON customer_product (customer_id);
CREATE INDEX product_fk_idx ON customer_product (product_id);

CREATE TABLE transaction (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  customer_product_id int(11) NOT NULL,
  channel_id int(11) NOT NULL,
  amount decimal(10,0) NOT NULL,
  date datetime NOT NULL,
  status varchar(10) NOT NULL,
  type int(11) NOT NULL,
  transaction_number int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE INDEX channel_fk_idx ON transaction (channel_id);
CREATE INDEX tr_channel_fk_idx ON transaction (channel_id);
CREATE INDEX tr_trxtype_idx ON transaction (type);
CREATE INDEX tr_customer_product_fk_idx ON transaction (customer_product_id);

ALTER TABLE customer_product ADD CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE customer_product ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE transaction ADD CONSTRAINT tr_channel_fk FOREIGN KEY (channel_id) REFERENCES channel (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE transaction ADD CONSTRAINT tr_customer_product_fk FOREIGN KEY (customer_product_id) REFERENCES customer_product (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE transaction ADD CONSTRAINT tr_trxtype FOREIGN KEY (type) REFERENCES transaction_type (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO channel VALUES (1,'ATM','Automatic Teller Machine'),(2,'Web','Web site'),(3,'App','Mobile App'),(4,'POS','Point of Sales'),(5,'Mobile Wallet','Mobile Wallet');
INSERT INTO product VALUES (1,'Savings Account','Savings Account','ACTIVE'),(2,'Checking Account','Checking Account','ACTIVE');
INSERT INTO transaction_type VALUES (1,'Withdrawal'),(2,'Deposit'),(3,'POS'),(4,'Online'),(5,'Transfer');
INSERT INTO customer VALUES (1,'John','Lydon','1012345','john.lydon@sexpistols.com','1200300400',NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF'),(2,'Joe','Strummer','2012345','joe.strummer@theclash.com','1300400500',NULL,'*A4B6157319038724E3560894F7F932C8886EBFCF'),(3,'David','Borja','3012345','adborja@gmail.com','3004347502','','$2a$10$O5c0Z9/MmeJjm6FBqUZz4euDRuO0iYluknP1ZZbF1Ap6bj5HOh7fC'),(4,'Mike','Ness','4012345','mike.ness@socialdistortion.com','3119990011','+5749990011','$2a$10$OgGlloVplZ5HYvNcaBb0YOAgGTBs5O3l0F.6CrHQEd4FZ1qsBSex2'),(5,'Tim','Armstrong','5012345','tim.armstrong@rancid.com','3119990022','+5749990022','$2a$10$SCsbydsdmOP1VQWpXnkAGu6Ulx2fCMqpMMQwLTjsKKKNDy5MN7SHG'),(6,'Evaristo','Paramos','6012345','evaristo.paramos@gatillazo.com','3119990033','+5749990033','$2a$10$xdUzf2St4uQLypSY8JLBM.TAGh4rF5vOrBo9LvOkvrad6TYgrKYg.');
INSERT INTO customer_product VALUES (1,1,1,'2017-11-20 09:00:00',NULL,33000,'ACTIVE','1000000001'),(2,2,1,'2017-11-20 10:00:00',NULL,50000,'ACTIVE','1000000002'),(3,3,1,'2017-11-22 12:00:00',NULL,10000,'ACTIVE','1000000003');
INSERT INTO transaction VALUES (1,3,4,20000,'2017-11-22 15:08:08','APPROVED',3,452),(2,3,4,20000,'2017-11-22 15:10:41','APPROVED',3,3783),(3,3,4,20000,'2017-11-22 15:13:28','APPROVED',3,6672),(4,3,4,20000,'2017-11-22 22:33:31','APPROVED',3,1306),(5,3,4,20000,'2017-11-22 22:34:37','APPROVED',3,4898),(6,1,3,10000,'2017-11-22 23:39:06','APPROVED',3,1680),(7,1,2,5000,'2017-11-22 23:39:20','APPROVED',3,6252),(8,1,2,2000,'2017-11-23 21:10:30','APPROVED',3,6778);
