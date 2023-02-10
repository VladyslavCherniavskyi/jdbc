-- выборка
SELECT col1, col2, ...colN
FROM tableName;

SELECT DISTINCT col1, col2, ...colN
FROM tableName;

SELECT col1, col2, ...colN
FROM tableName
WHERE condition;

SELECT col1, col2, ...colN
FROM tableName
WHERE condition1 AND|OR condition2;

SELECT col2, col2, ...colN
FROM tableName
WHERE colName IN (val1, val2, ...valN);

SELECT col1, col2, ...colN
FROM tableName
WHERE colName BETWEEN val1 AND val2;

SELECT col1, col2, ...colN
FROM tableName
WHERE colName LIKE pattern;

SELECT col1, col2, ...colN
FROM tableName
WHERE condition
ORDER BY colName [ASC|DESC];

SELECT SUM(colName)
FROM tableName
WHERE condition
GROUP BY colName;

SELECT COUNT(colName)
FROM tableName
WHERE condition;

SELECT SUM(colName)
FROM tableName
WHERE condition
GROUP BY colName
HAVING (function condition);

-- создание таблицы
CREATE TABLE tableName (
  col1 datatype,
  col2 datatype,
  ...
  colN datatype,
  PRIMARY KEY (одна или более колонка)
);

-- удаление таблицы
DROP TABLE tableName;

-- создание индекса
CREATE UNIQUE INDEX indexName
ON tableName (col1, col2, ...colN);

-- удаление индекса
ALTER TABLE tableName
DROP INDEX indexName;

-- получение описания структуры таблицы
DESC tableName;

-- очистка таблицы
TRUNCATE TABLE tableName;

-- добавление/удаление/модификация колонок
ALTER TABLE tableName ADD|DROP|MODIFY colName [datatype];

-- переименование таблицы
ALTER TABLE tableName RENAME TO newTableName;

-- вставка значений
INSERT INTO tableName (col1, col2, ...colN)
VALUES (val1, val2, ...valN)

-- обновление записей
UPDATE tableName
SET col1 = val1, col2 = val2, ...colN = valN
[WHERE condition];

-- удаление записей
DELETE FROM tableName
WHERE condition;

-- создание БД
CREATE DATABASE [IF NOT EXISTS] dbName;

-- удаление БД
DROP DATABASE [IF EXISTS] dbName;

-- выбор БД
USE dbName;

-- завершения транзакции
COMMIT;

-- отмена изменений
ROLLBACK;