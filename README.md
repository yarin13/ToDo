# ToDo
# SQL Table :
DROP TABLE IF EXISTS task;

CREATE DATABASE CrowdVocate;
USE CrowdVocate;


CREATE TABLE task (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250),
  status VARCHAR(50),
  startDate DATE,
  endDate DATE
  );



  
  INSERT INTO task(name,description,status,startDate,endDate) VALUES
  ("task 1","not really a task","To Do","2020-12-07","2020/12/07"),
  ("task 2","a","To Do","2020-12-07","2020/12/07"),
  ("task 3","b","To Do","2020-12-07","2020/12/07"),
  ("task 4","c","To Do","2020-12-07","2020/12/07");
  
  
  SELECT * FROM task;
