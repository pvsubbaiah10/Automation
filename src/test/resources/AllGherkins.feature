   
   
      # *********** launch browser and credntials ***********
        
    Given the web application "Automation_Practice_Url" is launched in a NewWindow
    And the user enters UserName "username" into the "Username" textbox at the "LoginPage" page
    And the user enters Password "password" into the "Password" textbox at the "LoginPage" page
    And the user clicks the "login_button" element at the "LoginPage" page
    
      # *********** waits ***********
    
    And the user waits for "10" seconds
  
    And the user waits for the "login_button" element to be "VISIBLE" on the "LoginPage"
    And the user waits for the "submit_button" element to be "CLICKABLE" on the "HomePage"
    And the user waits for the "logo" element to be "PRESENT" on the "DashboardPage"
    And the user waits for the "login_button" element to be "INVISIBLE" on the "LoginPage" page
    
    Then the user completes the rest of the test manually
    
    # *********** SSL ***********
    When the user clicks the Advanced option and clicks Proceed
    
    # *********** page title validation ***********
    And the user validate the page title with "expired.badssl.com"
    Then the user validate the page URL with "https://expired.badssl.com/"
    
    # *********** SWITCH TO WINDOW & FRAMES ***********
    