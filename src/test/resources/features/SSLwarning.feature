@expaired
Feature: SSL Certificate Warning Handling

    Scenario: User bypasses SSL warning
    
    Given the web application "koenigsegg_page" is launched in a NewWindow
    
    When the user clicks the Advanced option and clicks Proceed
    And the user validate the page title with "Home - Koenigsegg"
    Then the user validate the page URL with "https://www.koenigsegg.com/home"
   
    

