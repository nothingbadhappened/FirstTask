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
  "location": "step_definitions.SignIn.userNavigatesToWebsite()"
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
  "location": "step_definitions.SignIn.userIsRegistered(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user signs in with valid username \"elchupakabra@mailinator.com\" and password \"Test1234!\"",
  "keyword": "When "
});
formatter.match({
  "location": "step_definitions.SignIn.userSignIn(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is redirected to \"MY ACCOUNT!\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "step_definitions.SignIn.myAccountPageLoaded(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: expected [MY ACCOUNT!] but found [MY ACCOUNT]\r\n\tat org.testng.Assert.fail(Assert.java:94)\r\n\tat org.testng.Assert.failNotEquals(Assert.java:494)\r\n\tat org.testng.Assert.assertEquals(Assert.java:123)\r\n\tat org.testng.Assert.assertEquals(Assert.java:176)\r\n\tat org.testng.Assert.assertEquals(Assert.java:186)\r\n\tat step_definitions.SignIn.myAccountPageLoaded(SignIn.java:73)\r\n\tat ✽.user is redirected to \"MY ACCOUNT!\" page(file:///C:/Users/User/IdeaProjects/FirstTask/src/test/resources/features/SignIn.feature:8)\r\n",
  "status": "failed"
});
formatter.write("Current Page URL is http://automationpractice.com/index.php?controller\u003dmy-account");
formatter.embedding("image/png", "embedded0.png", "Screen");
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
  "location": "step_definitions.SignIn.userNavigatesToWebsite()"
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
  "location": "step_definitions.SignIn.userIsNotRegistered(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user enters invalid username \"ratatui@ne.em\" and password \"badPassword\"",
  "keyword": "When "
});
formatter.match({
  "location": "step_definitions.SignIn.invalidUserSignIn(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "login error \"Authentication failed.\" is displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "step_definitions.SignIn.loginError(java.lang.String)"
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
  "location": "step_definitions.SignIn.userNavigatesToWebsite()"
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
  "location": "step_definitions.SignIn.userLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user clicks sign out button",
  "keyword": "When "
});
formatter.match({
  "location": "step_definitions.SignIn.userSignOut()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is logged out",
  "keyword": "Then "
});
formatter.match({
  "location": "step_definitions.SignIn.verifyUserSignedOut()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});