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
  "name": "user is redirected to \"MY ACCOUNT!\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "com.step_definitions.CommonSteps.myAccountPageLoaded(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: expected [MY ACCOUNT!] but found [MY ACCOUNT]\r\n\tat org.testng.Assert.fail(Assert.java:94)\r\n\tat com.step_definitions.CommonSteps.myAccountPageLoaded(CommonSteps.java:81)\r\n\tat ✽.user is redirected to \"MY ACCOUNT!\" page(file:///C:/Users/User/IdeaProjects/FirstTask/src/test/resources/features/SignIn.feature:8)\r\n",
  "status": "failed"
});
formatter.write("Current Page URL is http://automationpractice.com/index.php?controller\u003dmy-account");
formatter.embedding("image/png", "embedded0.png", "Screenshot1587226219662_Registered user login with valid credentials.png");
formatter.after({
  "status": "passed"
});
});