
/*Jacob Jorgensen
*DATE HERE
*This validates user input 
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/* uncomment this for javaFx apps
*import javafx.scene.control.Alert;
*/
public class Validation 
{

	private final String lineEnd;
	
	public Validation() 
	{
		this.lineEnd = "\n";
	}

	//checks if text field is empty
	public String isPresent(String value, String name) 
	{
		String msg = "";
		if (value.isEmpty()) 
		{
			msg = name + " is required." + lineEnd;
		}
		return msg;
	}
	
	//checks if the input is a double.
	public String isDouble(String value, String name) 
	{
		String msg = "";
		try 
		{
			Double.parseDouble(value);
		}
		catch(NumberFormatException e) 
		{
			msg = name + " Must be a valid number." + lineEnd;
		}
		return msg;
	}
	
	//checks if the input is a date.
	public String isDate(String input, String name) 
	{
		String msg = "";
		try 
		{
			LocalDate.parse(input);
		}
		catch(Exception e)
		{
			msg = name + " must be a valid date (yyyy-mm-dd)." + lineEnd;
		}
		return msg;
	}
	
	//checks if a date is in a range. 
	public String dateRange(LocalDate inputDate, String name, LocalDate minDate) 
	{
		String msg = "";
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		if(inputDate.isAfter(currentDate)) 
		{
			msg = name +" must not be before " + formatter.format(currentDate);
		}
		if(inputDate.isBefore(minDate)) 
		{
			msg = name + " must not be before " + formatter.format(minDate);
		}
		return msg;
	}
	
	//checks if a value is a valid integer
	public String isInteger(String value, String name) 
	{
		String msg = "";
		
		try 
		{
			Integer.parseInt(value);
		}
		catch(NumberFormatException e) 
		{
			msg = name + " must be an integer." + lineEnd;
		}
		return msg;
	}
	
//checks if an int is in range.
	public String getIntRange(String input, int min, int max, String name)
	{
		String message = "";
		message += isInteger(input, name);
		
		if(message.isEmpty())
		{
					while(true)
			{
					int value = Integer.parseInt(input);
					
					if(value >= min && value <= max) 
					{
						return message;
					}
					else 
					{
						message = name + " must be between " + min + " and " + max + lineEnd;
			        }
	        } 		
			
		}
		return message;
	}


//checks if an input is a double and if its in a range.
	public String getDoubleRange(String input, double min, double max, String name) 
	{
		String message = "";
		message += isDouble(input, name);
		if(message.isEmpty()) 
		{
			while (true) 
			{
				double value = Double.parseDouble(input);
				
				if(value >= min && value <= max) 
				{
					return message;
				}
				else 
				{
					message = name + " must be between " + min + " and " + max + lineEnd;
		        }
			}

		}
		return message;
	}
	//Checks if a password is valid.
    public String passwordCheck(String password, int lengthIn, boolean needLength, boolean needCaps, boolean needNum, boolean needSpecial) 
    {
    	String errorMsg = "";
    	/*the input booleans determine if something is needed.  passwordCheck(password, 10, false, false, false, false)
    	 * Tells it the minimum length is 10 the minimum check is enabled and that it must contain a capital letter, number, and special character
    	 */
    	boolean capsCheck = needCaps;
    	boolean numCheck = needNum;
    	boolean specialCharCheck = needSpecial;
    	boolean lengthCheck = needLength;
    	
    	String validSpecial = "-.,<>$%!?@";
    	
    	if (password.length() >= lengthIn) 
    	{
    		lengthCheck = true;
    	}
    	
    	for (int i = 0; i < password.length(); i++) 
    	{
    		char c = password.charAt(i);
    		if(Character.isUpperCase(c)) 
    		{
    			capsCheck = true;
    		}
    		if(Character.isDigit(c)) 
    		{
    			numCheck = true;
    		}
    		for (char s: validSpecial.toCharArray()) 
    		{
    			if(c == s) 
    			{
    				specialCharCheck = true;
    			}
    		}	
    	} 
    	
    	if(!lengthCheck) 
    	{
    		errorMsg += "Password must be " + lengthIn +  " characters or longer" + lineEnd;
    	}
    	
    	if(!capsCheck) 
    	{
    		errorMsg += "Password must contain a capital letter" + lineEnd;
    	}
    	if(!numCheck) 
    	{
    		errorMsg += "Password must contain a digit" + lineEnd;
    	}
    	if(!specialCharCheck) 
    	{
    		errorMsg += "Password must contain one special character (- . , < > $ % ! ? @)" + lineEnd;
    	}
    	
    	return errorMsg;

    }
	
	//Uncomment for frame alerts.  This just outputs the error message thats input.
	//Set up here so I don't have to make an alert in every new app 
/*
*	public void ValidAlert(String errorMsg)
*	{
*			Alert alert = new Alert(Alert.AlertType.ERROR);
*			alert.setHeaderText("Invalid Data");
*			alert.setContentText(errorMsg);
*			alert.showAndWait();
*	} 
*/
}
