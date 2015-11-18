CREATE TABLE Users (
	User_ID INT PRIMARY KEY IDENTITY(1,1),
	Name CHAR(32),
	Address CHAR(32),
	SSN INT CHECK(SSN <= 999999999 AND SSN >= 0),
	Phone INT,
	DOB DATE -- Age should be > 0 (non future date) and < 130
);

CREATE TABLE Patients (
	User_ID INT REFERENCES Users(User_ID),
	Provider CHAR(32),
	Gender CHAR(5) CHECK (Gender = 'Male' OR Gender = 'Female'),
	Height INT CHECK(Height>0), --CM
	Weight INT CHECK(Weight>0), -- LBS
	Race CHAR(16),  CHECK(Race='African American' OR Race='Caucasian' OR Race= 'Native American' OR  Race='Hispanic' OR Race= 'OTHER'), --Add check for fixed selection of races
	BloodType CHAR(3) CHECK (BloodType = 'A' OR BloodType = 'B' OR BloodType =  'AB' OR BloodType = 'O'),
	Insurance CHAR(16), 
	Recent_Visit DATE
);
CREATE TABLE HCProf (
	User_ID INT REFERENCES Users(User_ID),
	Password CHAR(32),
	Username CHAR(32),
	Title CHAR(8) CHECK(Title='Doctor' OR Title='Nurse') , -- Should be a fixed set of titles, e.g. Dr., etc
	Privileges BIT CHECK(Privileges=0 OR Privileges=1) -- Should be only 0 (nurse), 1 (doctor)
);
CREATE TABLE Treats (
	Treat_ID INT PRIMARY KEY IDENTITY(1,1),
	Doctor_ID INT REFERENCES Users(User_ID),
	Patient_ID INT REFERENCES Users(User_ID)
);
CREATE TABLE Conditions (
	Condition_ID INT PRIMARY KEY IDENTITY(1,1),
Doctor_ID INT REFERENCES Users(User_ID),
	Patient_ID INT REFERENCES Users(User_ID),
	ConditionName VARCHAR(255),
	Treatment VARCHAR(255),
	DateDiagnosed DATE -- Should not be a future date
);
CREATE TABLE Documents (
	Document_ID INT PRIMARY KEY IDENTITY(1,1),
Doctor_ID INT REFERENCES Users(User_ID),
	Patient_ID INT REFERENCES Users(User_ID),
	DateCreated DATE -- Should not be a future date
);
CREATE TABLE Compound (
	Com_ID INT PRIMARY KEY IDENTITY(1,1),
	CompoundName VARCHAR(255), -- Name of chemical compound
	MeasurementType CHAR(8), -- Type of measurement.. e.g. /ml, /liter, etc
	N_Range_Low INT, -- Normal lower bound
	N_Range_High INT -- Normal upper bound
);
CREATE TABLE Risks (
	Risk_ID INT PRIMARY KEY IDENTITY(1,1),
	Document_ID INT REFERENCES Documents(Document_ID), 
	Doctor_ID INT REFERENCES Users(User_ID),
	Patient_ID INT REFERENCES Users(User_ID),
	Com_ID INT REFERENCES Compound(Com_ID),
	Severity INT CHECK(Severity=0 OR Severity=1 OR Severity=2) --Limit to 0 (low), 1 (mid), and 2 (high)  
);
CREATE TABLE Results (
	Result_ID INT PRIMARY KEY IDENTITY(1,1),
	Document_ID INT REFERENCES Documents(Document_ID), 
	Doctor_ID INT REFERENCES Users(User_ID),
	Patient_ID INT REFERENCES Users(User_ID),
	Com_ID INT REFERENCES Compound(Com_ID),
	Result REAL CHECK(Result>0), -- Should not be negative
);