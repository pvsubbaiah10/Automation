@drop
Feature: dropdowns

Scenario: checking dropdowns
  Given the user loads test data file "LoginData.xlsx"
  
  And the user fetches the URL for WebPage "rahulshettyacademy" from sheet "URLs"
  And the web application URL is launched in a NewWindow
  
    
   And the user selects valuelndex "3" from the "drop" dropdown at the "LoginPage" page
   
   And the user waits for "2" seconds 
   And the user selects value "AED" from the "drop" dropdown at the "LoginPage" page
   And the user waits for "2" seconds  
   
   And the user clicks the "passengers" element at the "LoginPage" page
   And the user clicks the "adults" element "5" times on the "LoginPage" page
   And the user clicks the "done_button" element at the "LoginPage" page
   And the user waits for "2" seconds
   
   And the user clicks the "from" element at the "LoginPage" page
   And the user clicks the "pune" element at the "LoginPage" page
   And the user clicks the "hyd" element at the "LoginPage" page 
   
   And the user clicks the "currentdate" element at the "LoginPage" page 
   And the user waits for "2" seconds
    
    
    
    
    
    
    
    
    
    
    

