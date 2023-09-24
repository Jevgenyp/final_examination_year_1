USE HumanFriends;

-- Удаляем существующие таблицы
DROP TABLE IF EXISTS YoungAnimals;
DROP TABLE IF EXISTS HorseDonkeys;
DROP TABLE IF EXISTS Camels;
DROP TABLE IF EXISTS Donkeys;
DROP TABLE IF EXISTS Horses;
DROP TABLE IF EXISTS Hamsters;
DROP TABLE IF EXISTS Cats;
DROP TABLE IF EXISTS Dogs;
DROP TABLE IF EXISTS AllAnimals;

-- Создаем таблицы
CREATE TABLE Dogs (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);
CREATE TABLE Cats (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);
CREATE TABLE Hamsters (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);
CREATE TABLE Horses (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);
CREATE TABLE Camels (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);
CREATE TABLE Donkeys (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), command VARCHAR(50), birthdate DATE);

-- Заполняем таблицы
USE HumanFriends;

-- Добавляем записи в таблицу Dogs
INSERT INTO Dogs (name, command, birthdate) 
VALUES ('Buddy', 'Sit', '2022-01-15'),
       ('Rex', 'Fetch', '2019-06-01'),
       ('Charlie', 'Roll Over', '2018-12-12'),
       ('Max', 'Stay', '2019-03-03'),
       ('Rocky', 'Jump', '2021-01-01');

-- Добавляем записи в таблицу Cats
INSERT INTO Cats (name, command, birthdate) 
VALUES ('Mittens', 'Sleep', '2023-03-10'),
       ('Whiskers', 'Pounce', '2019-10-20'),
       ('Luna', 'Climb', '2017-11-11'),
       ('Oliver', 'Hide', '2020-05-05'),
       ('Kitty', 'Scratch', '2018-06-06');

-- Добавляем записи в таблицу Hamsters
INSERT INTO Hamsters (name, command, birthdate) 
VALUES ('Hammy', 'Run in Wheel', '2020-07-05'),
       ('Nibbles', 'Eat', '2022-01-02'),
       ('Fluffy', 'Hide', '2022-11-12'),
       ('Peanut', 'Climb', '2021-01-15'),
       ('Squeaky', 'Play', '2020-09-09');

-- Добавляем записи в таблицу Horses
INSERT INTO Horses (name, command, birthdate) 
VALUES ('Shadow', 'Trot', '2018-05-07'),
       ('Daisy', 'Gallop', '2017-09-12'),
       ('Flash', 'Run', '2016-07-07'),
       ('Starlight', 'Jump', '2015-08-08'),
       ('Bolt', 'Race', '2019-03-03');

-- Добавляем записи в таблицу Donkeys
INSERT INTO Donkeys (name, command, birthdate) 
VALUES ('Eeyore', 'Carry Load', '2016-11-11'),
       ('Dunky', 'Rest', '2018-10-10'),
       ('Bray', 'Sing', '2019-12-01'),
       ('Jenny', 'Walk', '2017-06-06'),
       ('Bridget', 'Relax', '2016-09-09');


-- Удаляем таблицу Camels
DROP TABLE Camels;

-- Создаем и заполняем объединенную таблицу HorseDonkeys
CREATE TABLE HorseDonkeys (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(50), 
    command VARCHAR(50), 
    birthdate DATE, 
    type ENUM('Horse', 'Donkey')
);

INSERT INTO HorseDonkeys (name, command, birthdate, type) 
SELECT name, command, birthdate, 'Horse' FROM Horses
UNION ALL
SELECT name, command, birthdate, 'Donkey' FROM Donkeys;



-- Создаем таблицу YoungAnimals


-- Создаем таблицу YoungAnimals
CREATE TABLE YoungAnimals 
(
    animal_type ENUM('Dog', 'Cat', 'Hamster', 'HorseDonkey'),
    name VARCHAR(50),
    command VARCHAR(50),
    birthdate DATE,
    age_in_months INT
);

-- Заполняем таблицу YoungAnimals данными
INSERT INTO YoungAnimals (animal_type, name, command, birthdate, age_in_months)
(
    SELECT 'Dog', name, command, birthdate, TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) 
    FROM Dogs
    WHERE TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) BETWEEN 12 AND 36

    UNION ALL

    SELECT 'Cat', name, command, birthdate, TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) 
    FROM Cats
    WHERE TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) BETWEEN 12 AND 36

    UNION ALL

    SELECT 'Hamster', name, command, birthdate, TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) 
    FROM Hamsters
    WHERE TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) BETWEEN 12 AND 36

    UNION ALL

    SELECT 'HorseDonkey', name, command, birthdate, TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) 
    FROM HorseDonkeys
    WHERE TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) BETWEEN 12 AND 36
);

-- ... [Та же часть кода до создания таблицы AllAnimals]

-- Создаем новую таблицу AllAnimals
CREATE TABLE AllAnimals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    command VARCHAR(50),
    birthdate DATE,
    animal_type ENUM('Dog', 'Cat', 'Hamster', 'HorseDonkey', 'Horse', 'Donkey')
);

-- Вставляем данные из каждой таблицы
INSERT INTO AllAnimals (name, command, birthdate, animal_type)
SELECT name, command, birthdate, 'Dog' FROM Dogs
UNION ALL
SELECT name, command, birthdate, 'Cat' FROM Cats
UNION ALL
SELECT name, command, birthdate, 'Hamster' FROM Hamsters
UNION ALL
SELECT name, command, birthdate, 'HorseDonkey' FROM HorseDonkeys
UNION ALL
SELECT name, command, birthdate, 'Horse' FROM Horses
UNION ALL
SELECT name, command, birthdate, 'Donkey' FROM Donkeys;

-- Удаляем таблицы Horses и Donkeys, так как они теперь не нужны
DROP TABLE IF EXISTS Horses;
DROP TABLE IF EXISTS Donkeys;




