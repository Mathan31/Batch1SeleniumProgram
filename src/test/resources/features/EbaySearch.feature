@Complete
Feature: Validate the Ebay Search Result Functionality

Background: Pre-Condition for all the scenario
	Given Launch Chrome Browser
  And Navigate to Ebay URL

  @Smoke
  Scenario: Ebay Search with Hardcoded value from coding
  
  When Search the product and Select the Catagory
  And Click on Search Button
  Then User should see the search result
  And Close Browser
  #
  #Scenario: Ebay Search with Hardcoded value from feature file
  #Given Launch Browser based on 2
  #And Navigate to Ebay URL
  #When Search the product as "Samsung" and Select the Catagory as "Cell Phones & Accessories"
  #And Click on Search Button
  #Then User should see the search result
  #And Close Browser
  #
  #Scenario Outline: Ebay Search with Hardcoded multiple value from feature file
  #Given Launch Browser based on multiple value <Browser>
  #And Navigate to Ebay URL
  #When Search the multiple product as <ProductName> and Select the multiple Catagory as <ProductCatagory>
  #And Click on Search Button
  #Then User should see the search result
  #And Close Browser
  #
  #Examples:
  #| Browser | ProductName | ProductCatagory               |
  #|       1 | Selenium    | Books                         |
  #|       2 | TShirt      | Clothing, Shoes & Accessories |
  
  @Sanity @Regression
  Scenario: Ebay Search with Hardcoded multiple value from feature file data table as List
    
    When Search the product from the below data table as List
      | Lenovo | Cell Phones & Accessories |
      | Java   | Books & Magazines         |
    And Click on Search Button
    Then User should see the search result
    And Close Browser

  @Sanity @Regression
  Scenario: Ebay Search with Hardcoded multiple value from feature file data table as Map
   
    When Search the product from the below data table as Map
      | ProductName | ProductCatagory           |
      | Lenovo      | Cell Phones & Accessories |
      | Java        | Books & Magazines         |
    #And Click on Search Button
    Then User should see the search result
    And Close Browser
