CREATE TABLE employee(
id INT AUTO_INCREMENT PRIMARY KEY ,
name VARCHAR (20) NOT NULL ,
company_id INT ,
create_date TIMESTAMP DEFAULT NOW()
)