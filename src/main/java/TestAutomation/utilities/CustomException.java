package TestAutomation.utilities;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

public class CustomException extends RuntimeException{
	public CustomException(WebDriver driver, Exception e) throws Exception{		
		super("Exception occured during UI flow");
		throw e;
	}	
		public CustomException(String message){
			super(message );
		}
		public CustomException(String message, SQLException e) {
			super(message , e);
		}

}
