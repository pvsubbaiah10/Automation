@amazon
Feature: Amazon webpage

Scenario: search product in amazon

     # --- using test data ---
	 Given the user loads test data file "LoginData.xlsx"
	 And the user fetches the URL for WebPage "Amazon" from sheet "URLs"
	 And the web application URL is launched in a NewWindow

  
	 #Given the web application "Amazon" is launched in a NewWindow 
	 
	 And the user enters "Apple 17 pro max" into the "search_box" textbox at the "Amazon" page
	 And the user clicks the "search_button" element at the "Amazon" page 
	 
	 And the user clicks the "addtocart" element at the "Amazon" page
	 
	 And the user enters "airpods pro 3" into the "search_box" textbox at the "Amazon" page
	 And the user clicks the "search_button" element at the "Amazon" page
	 And the user clicks the "addtocart" element at the "Amazon" page 
	 
	 
	 And the user clicks the "addtocart_button" element at the "Amazon" page
	 
	 
	 And the user Get the text from "airpods" in "Amazon" page
	 And the user Get the text from "iphone" in "Amazon" page
	 
	 And the user waits for "1" seconds