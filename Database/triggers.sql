CREATE TRIGGER SeverityTrig
	ON Results
	AFTER INSERT, UPDATE
	as
	Declare @theID INT,
			@theResult INT,
		    @lowRange INT,
		    @highRange INT

	select @theResult = Result_ID from inserted;
	select @lowRange = (select N_Range_Low from Compound c, Results r where r.Result_ID = @theResult and r.Com_ID = c.Com_ID);
	select @highRange = (select N_Range_High from Compound c, Results r where r.Result_ID = @theResult and r.Com_ID = c.Com_ID);
	select @theID = Patient_ID from inserted;

	BEGIN
	IF ( (@theResult > @highRange AND (@theResult - @highRange) / @highRange * 100 > 5) OR (@theResult < @lowRange AND (@lowRange - @theResult) / @lowRange * 100 > 5 )) 

				UPDATE Risks SET Severity = 1 where Patient_ID = @theID; 

    ELSE IF((@theResult > @highRange AND (@theResult - @highRange) / @highRange * 100 > 10) OR (@theResult < @lowRange AND (@lowRange - @theResuLt) / @lowRange * 100 > 10))
				UPDATE Risks SET Severity = 2 where Patient_ID = @theID;
			ELSE
				UPDATE Risks SET Severity = 0 where Patient_ID = @theID;


	END