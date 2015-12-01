CREATE TRIGGER SeverityTrig
	ON Results
	AFTER INSERT, UPDATE
	AS
	Declare @patientID INT,
	        @resultID INT,
			@lowRange INT,
            @highRange INT,
			@theResult REAL

	select @resultID = Result_ID from inserted;
	select @patientID = Patient_ID from inserted;
	select @lowRange = (select N_Range_Low from Compound c, Results r where r.Result_ID = @resultID and r.Com_ID = c.Com_ID);
	select @highRange = (select N_Range_High from Compound c, Results r where r.Result_ID = @resultID and r.Com_ID = c.Com_ID);
	select @theResult = Result from inserted;

	BEGIN
	IF  ((@theResult > @highRange AND (@theResult - @highRange) / @highRange * 100 > 0 AND (@theResult - @highRange) / @highRange * 100 <5) 
		OR (@theResult < @lowRange AND (@lowRange - @theResult) / @lowRange * 100 > 0  AND  (@lowRange - @theResult) / @lowRange * 100 <5 )) 
			UPDATE Risks SET Severity = 1 where Patient_ID = @patientID; 

	ELSE IF  ((@theResult > @highRange AND (@theResult - @highRange) / @highRange * 100 > 5 AND (@theResult - @highRange) / @highRange * 100 <10) 
		OR (@theResult < @lowRange AND (@lowRange - @theResult) / @lowRange * 100 > 5  AND  (@lowRange - @theResult) / @lowRange * 100 <10 )) 
			UPDATE Risks SET Severity = 2 where Patient_ID = @patientID;

    ELSE IF((@theResult > @highRange AND (@theResult - @highRange) / @highRange * 100 > 10) OR (@theResult < @lowRange AND (@lowRange - @theResuLt) / @lowRange * 100 > 10))
			UPDATE Risks SET Severity = 3 where Patient_ID = @patientID;
	ELSE
			UPDATE Risks SET Severity = 0 where Patient_ID = @patientID;


	END