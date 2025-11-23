@WHP
Feature: WindowHandlePage

    Scenario: check windowhandles
    
    Given the web application "WindowHandlePage" is launched in a NewWindow
    
    And the user clicks the "Window_Button" element at the "WHPage" page
    And the user validate the page title with "Window Handles Practice - H Y R Tutorials"
    And the user waits for "5" seconds
    
    Then the user switches to "firstWindow"
    And the user validate the page title with "Basic Controls - H Y R Tutorials"
    
    Then the user switches to "parentwindow"
    And the user waits for "5" seconds
    And the user validate the page title with "Window Handles Practice - H Y R Tutorials"
    And the user clicks the "Tab_Button" element at the "WHPage" page
    
    
    And the user Get the text from "text" in "WHPage" page
    
    And the user Get the AttributeValue "id" from "inputField" in "WHPage" page
    
    Then the user switches to "firstWindow"
    And the user validate the page title with "Basic Controls - H Y R Tutorials"
    
    
   
    

