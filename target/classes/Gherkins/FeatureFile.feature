#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Web Testing Cart products
   
   Background: 
   Given Launching the application

  @tag2
  Scenario Outline: Login Test
    Given Login with username <name> and password <pass> and I verify the <status> in step

    Examples: 
      | name  								| 	    pass 				|status |
      | sandeep1@gmail.com  	|      Sandeep1@ 		|pass		|
      | sandeep1@gmail.com  	|      Sandeep11 		|fail		|
      | sandeep@gmail.com  		|      Sandeep1# 		|pass		|
