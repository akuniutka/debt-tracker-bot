ALTER TABLE incomes ADD COLUMN value_date DATE;
ALTER TABLE incomes ADD COLUMN entry_date TIMESTAMP;
UPDATE incomes SET value_date = DATE '2023-05-05', entry_date = localtimestamp WHERE value_date IS NULL;
ALTER TABLE incomes ALTER COLUMN value_date SET NOT NULL;
ALTER TABLE incomes ALTER COLUMN value_date SET DEFAULT current_date;
ALTER TABLE incomes ALTER COLUMN entry_date SET NOT NULL;
ALTER TABLE incomes ALTER COLUMN entry_date SET DEFAULT localtimestamp;

ALTER TABLE expenses ADD COLUMN value_date DATE;
ALTER TABLE expenses ADD COLUMN entry_date TIMESTAMP;
UPDATE expenses SET value_date = DATE '2023-05-05', entry_date = localtimestamp WHERE value_date IS NULL;
ALTER TABLE expenses ALTER COLUMN value_date SET NOT NULL;
ALTER TABLE expenses ALTER COLUMN value_date SET DEFAULT current_date;
ALTER TABLE expenses ALTER COLUMN entry_date SET NOT NULL;
ALTER TABLE expenses ALTER COLUMN entry_date SET DEFAULT localtimestamp;

