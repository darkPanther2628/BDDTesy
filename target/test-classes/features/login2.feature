Feature: Address book Login Feature 2

@smokeTests
Scenario Outline: TC00003_verify User is able to login to Address book with valid Login details
Given User is on signIn Page
When Title of SignIn Page is AddressBook SignIn
Then User enters <emailAddress> and <password>
And User clicks on SignIn button
Then User is on home Page

Examples:

	|emailAddress			 |password|
	|lalithapadala3@gmail.com|test@123|
	
@LoginTests
Scenario Outline: TC00004_verify User is able to login to Address book with valid Login details
Given User is on signIn Page
When Title of SignIn Page is AddressBook SignIn
Then User enters <emailAddress> and <password>
And User clicks on SignIn button
Then User is on home Page

Examples:

	|emailAddress			 |password|
	|lalithapadala3@gmail.com|test@123|