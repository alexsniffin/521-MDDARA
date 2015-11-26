-- Procedure creates a Patient User
-- Creates a User and then a Patient using the FK from User
CREATE PROCEDURE NewPatient
	@name CHAR(32),
	@address CHAR(32),
	@ssn INT,
	@phone INT,
	@dob DATE,
	@prov CHAR(32),
	@gender CHAR(5),
	@height INT,
	@weight INT,
	@race CHAR(16),
	@blood CHAR(3),
	@insur CHAR(16),
	@recent DATE
AS
BEGIN
	INSERT INTO Users VALUES (@name, @address, @ssn, @phone, @dob);
	INSERT INTO Patients VALUES (SCOPE_IDENTITY(), @prov, @gender, @height, @weight, @race, @blood, @insur, @recent);
END;

-- Procedure creates a HCProf User
-- Creates a User and then a HCProf using the FK from User
CREATE PROCEDURE NewHCProf
	@name CHAR(32),
	@address CHAR(32),
	@ssn INT,
	@phone INT,
	@dob DATE,
	@pass CHAR(32),
	@username CHAR(32),
	@title CHAR(8),
	@priv BIT
AS
BEGIN
	INSERT INTO Users VALUES (@name, @address, @ssn, @phone, @dob);
	INSERT INTO HCProf VALUES (SCOPE_IDENTITY(), @pass, @username, @title, @priv);
END;

-- Load in a document and its results
-- Should join the documents and results tables based on a document id
-- Need to check which doc first
CREATE PROCEDURE loadDoc 
	@id INT
AS 
BEGIN
SELECT u.Name, d.DateCreated, c.CompoundName, r.Result, c.MeasurementType, c.N_Range_Low, c.N_Range_High
FROM Documents d, Results r, Compound c, Users u
WHERE d.Document_ID = @id AND r.Document_ID = @id AND u.User_ID = d.Patient_ID AND r.Com_ID = c.Com_ID;
END;

-- Check if username and password are right
-- Should be a function and return a 1 if true or 0 if false
CREATE FUNCTION login 
	(@user char(32),@pass char(32))
RETURNS INT
AS
BEGIN
	DECLARE @id INT;

	SET @id = (SELECT User_ID
	FROM HCProf u
	WHERE u.Username = @user AND u.Password = @pass) 

	RETURN @id;
END;

-- Create blank doc
CREATE PROCEDURE newDoc
	@documentID INT output, @doctorID INT, @SSN INT
AS 
BEGIN
		DECLARE @patientID INT; 
		DECLARE @date DATE; -- From db
		
		SET @patientID = (SELECT u.User_ID
			FROM Users u
			WHERE u.SSN = @SSN);

		INSERT INTO Documents VALUES (@doctorID, @patientID, @date);

		SELECT @documentID = SCOPE_IDENTITY();
END;

-- Save a result for a document
CREATE PROCEDURE saveResult
	@resultID INT,
	@resultValue REAL
AS 
BEGIN
	UPDATE Results 
	SET Result = @resultValue
	WHERE Result_ID = @resultID;
END;

-- Create new result using a specific compound
CREATE PROCEDURE newResult
	@documentID INT,
	@userID INT,
	@doctorID INT,
	@result REAL,
	@compoundName VARCHAR(255)
AS
BEGIN
	DECLARE @compoundID INT;

	SET @compoundID = (SELECT COM_ID
		FROM Compound
		WHERE CompoundName = @compoundName)

	IF (NOT EXISTS(SELECT CompoundName
			FROM Compound
			WHERE CompoundName = @compoundName))
		RAISERROR ('Compound not found!', 10, 1)
	ELSE
		INSERT INTO Results Values (@documentID, @doctorID, @userID, @compoundID, @result);
END;
