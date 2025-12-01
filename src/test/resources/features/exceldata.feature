@excel
Feature: excel

Scenario: Login with Excel based URL and Credentials
  Given the user loads test data file "LoginData.xlsx"
  And the user fetches login details for UserName "subbu" from sheet "credentials"
  And the user fetches the URL for WebPage "instagram" from sheet "URLs"
  And the web application URL is launched in a NewWindow
  
  And the user waits for "1" seconds
  
  And the user enters UserName into the "Username" textbox at the "LoginPage" page
  And the user enters Password into the "Password" textbox at the "LoginPage" page
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

