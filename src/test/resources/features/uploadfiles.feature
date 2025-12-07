@upload
Feature: uploadfiles

   Scenario: checking uploadfiles
   Given the user loads test data file "LoginData.xlsx"
  
   And the user fetches the URL for WebPage "rahulshettyacademyUpload" from sheet "URLs"
   And the web application URL is launched in a NewWindow
   And the user waits for "5" seconds
  
   And the user uploads file "\\Downloads\\download.xlsx" into "Choosefile" in "WHPage" page
   
   And the user Get the text from "invisibletext" in "WHPage" page
   And the user waits for "2" seconds
   
   

    
    
    
    
    
    
    
    
    

