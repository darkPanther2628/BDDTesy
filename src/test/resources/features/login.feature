Feature: Address book Login Feature

@smokeTests
Scenario Outline: TC00001_verify User is able to login to Address book with valid Login details
Given User is on signIn Page
When Title of SignIn Page is AddressBook SignIn
Then User enters <emailAddress> and <password>
And User clicks on SignIn button
Then User is on home Page

Examples:

	|emailAddress			 |password|
	|lalithapadala3@gmail.com|test@123|

@RegressionTests
Scenario Outline: TC00002_verify User is able to login to Address book with valid Login details
Given User is on signIn Page
When Title of SignIn Page is AddressBook SignIn
Then User enters <emailAddress> and <password>
And User clicks on SignIn button
Then User is on home Page

Examples:

	|emailAddress			 |password|
	|lalithapadala3@gmail.com|test@123|