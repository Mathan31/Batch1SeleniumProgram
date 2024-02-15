@Complete
Feature: Validate the Amazon Search Result Functionality

@Smoke
Scenario: Amazon Search with Hardcoded value from coding

Given Launch Chrome Browser for Amazon
And Navigate to Amazon URL
When Search the amazon product and Select the Catagory
And Click on amaon Search Button
Then User should see the amazon search result
And Close amazon Browser