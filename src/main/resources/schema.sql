DROP TABLE IF EXISTS conference;

CREATE TABLE  conference( con_seq INT PRIMARY KEY AUTO_INCREMENT
, con_name VARCHAR
, con_user VARCHAR
, con_date DATE
, con_time VARCHAR
, is_repeat INT
, create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
, update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO conference (con_name, con_user, con_date, con_time, is_repeat) VALUES ('A','eunho',now(),'1,2,3,',0);
INSERT INTO conference (con_name, con_user, con_date, con_time, is_repeat) VALUES ('B','eunho',now(),'5,6,',0);
INSERT INTO conference (con_name, con_user, con_date, con_time, is_repeat) VALUES ('C','eunho',now(),'9,10',0);