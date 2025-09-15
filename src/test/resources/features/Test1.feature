@smoke
Feature: My first test

    Scenario: Launch Automation_Practice_Url

    Given the web application "Automation_Practice_Url" is launched in a NewWindow

    And the user enters UserName "username" into the "Username" textbox at the "LoginPage" page
    And the user enters Password "password" into the "Password" textbox at the "LoginPage" page
    #And the user waits for "10" seconds
    
    And the user waits for the "login_button" element to be "CLICKABLE" on the "LoginPage" page
    And the user clicks the "login_button" element at the "LoginPage" page
    
    
    
    #@smoke
    Scenario: Launch Automation_Practice_Url123

    Given the web application "rahulshettyacademy" is launched in a NewWindow

    And the user enters UserName "rahul_username" into the "r_username" textbox at the "LoginPage" page
    And the user enters Password "rahul_pass" into the "r_password" textbox at the "LoginPage" page
    #And the user waits for "10" seconds
    #Then the user completes the rest of the test manually
    And the user waits for the "r_sigin" element to be "VISIBLE" on the "LoginPage" page
    And the user clicks the "r_sigin" element at the "LoginPage" page