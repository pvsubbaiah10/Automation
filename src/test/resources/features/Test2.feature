
@test2
Feature: My first test


   
    Scenario: Launch instagram

    Given the web application "instagram" is launched in a NewWindow

    And the user enters UserName "username1" into the "Username1" textbox at the "LoginPage" page
    And the user enters Password "password1" into the "Password2" textbox at the "LoginPage" page
    And the user clicks the "login_button1" element at the "LoginPage" page