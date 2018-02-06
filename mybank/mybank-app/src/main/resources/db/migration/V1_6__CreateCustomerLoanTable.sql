CREATE TABLE customer_loan (
  id int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  customer_id INT(11) NOT NULL,
  loan_id INT(11) NOT NULL,
  creation_date datetime NOT NULL,
  termination_date datetime DEFAULT NULL,
  balance decimal(10,0) NOT NULL DEFAULT '0',
  status varchar(10) NOT NULL,
  loan_number varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
CREATE UNIQUE INDEX cust_loan_number_uniq ON customer_loan (loan_number);
CREATE INDEX loan_customer_fk_idx ON customer_loan (customer_id);
CREATE INDEX loan_loan_fk_idx ON customer_loan (loan_id);

ALTER TABLE customer_loan ADD CONSTRAINT loan_customer_fk FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE customer_loan ADD CONSTRAINT loan_loan_fk FOREIGN KEY (loan_id) REFERENCES loan (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
