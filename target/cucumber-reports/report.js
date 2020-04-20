$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SignIn.feature");
formatter.feature({
  "name": "Sign In",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user navigates to website",
  "keyword": "Given "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userNavigatesToWebsite()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Registered user login with valid credentials",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user is \"registered\" on the website",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userIsRegistered(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user signs in with valid username \"elchupakabra@mailinator.com\" and password \"Test1234!\"",
  "keyword": "When "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userSignIn(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is redirected to \"MY ACCOUNT\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.myAccountPageLoaded(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user navigates to website",
  "keyword": "Given "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userNavigatesToWebsite()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "User login with invalid credentials",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user is \"not registered\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userIsNotRegistered(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user enters invalid username \"ratatui@ne.em\" and password \"badPassword\"",
  "keyword": "When "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.invalidUserSignIn(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "login error \"Authentication failed.\" is displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.loginError(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user navigates to website",
  "keyword": "Given "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userNavigatesToWebsite()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Registered user sign out",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user is logged in",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user clicks sign out button",
  "keyword": "When "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userSignOut()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is logged out",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.verifyUserSignedOut()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.uri("file:src/test/resources/features/SignInWithDatabasePulledUser.feature");
formatter.feature({
  "name": "Sign In with user fetched from Database",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user navigates to website",
  "keyword": "Given "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userNavigatesToWebsite()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Registered user login with valid credentials",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user status is \"registered\"",
  "keyword": "When "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.getRegisteredUserFromDatabase(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user signs in",
  "keyword": "And "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.userSignsIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is redirected to \"MY ACCOUNT\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.myAccountPageLoaded(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});