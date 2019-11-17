DROP TABLE IF EXISTS conference;

CREATE TABLE  conference( conSeq INT PRIMARY KEY AUTO_INCREMENT
, conName VARCHAR
, conUser VARCHAR
, conDate DATE
, conTime VARCHAR
, isRepeat INT
, createAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
, updateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO conference (conName, conUser, conDate, conTime, isRepeat) VALUES ('B','eunho',now(),'1,2,3,',0);
INSERT INTO conference (conName, conUser, conDate, conTime, isRepeat) VALUES ('B','eunho',now(),'5,6,',0);
INSERT INTO conference (conName, conUser, conDate, conTime, isRepeat) VALUES ('C','eunho',now(),'9,10',0);
INSERT INTO conference (conName, conUser, conDate, conTime, isRepeat) VALUES ('B','eunho','2019-11-24','1,2,3,4',0);