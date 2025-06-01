@test1
Feature: My first test

    Scenario: Launch Automation_Practice_Url

    Given the web application "Automation_Practice_Url" is launched in a NewWindow

    And the user enters UserName "username" into the "Username" textbox at the "LoginPage" page
    And the user enters Password "password" into the "Password" textbox at the "LoginPage" page
    And the user clicks the "login_button" element at the "LoginPage" page
    
    #And the user close the browser
    
   
   