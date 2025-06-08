
@test2
Feature: Test Scenario Outline

Scenario Outline: Launch instagram

Given the web application "instagram" is launched in a NewWindow

And the user enters UserName "<username1>" into the "Username1" textbox at the "LoginPage" page
And the user enters Password "<password1>" into the "Password2" textbox at the "LoginPage" page
And the user clicks the "login_button1" element at the "LoginPage" page

Examples:
 | username1 | password1 |
 | subb      | pass_a    |
 | arise     | pass_b    |