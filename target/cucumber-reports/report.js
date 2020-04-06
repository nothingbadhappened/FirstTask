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
  "error_message": "java.lang.AssertionError: expected [MY ACCOUNT!] but found [MY ACCOUNT]\r\n\tat org.testng.Assert.fail(Assert.java:94)\r\n\tat step_definitions.SignIn.myAccountPageLoaded(SignIn.java:77)\r\n\tat ✽.user is redirected to \"MY ACCOUNT!\" page(file:///C:/Users/User/IdeaProjects/FirstTask/src/test/resources/features/SignIn.feature:8)\r\n",
  "status": "failed"
});
formatter.write("Current Page URL is http://automationpractice.com/index.php?controller\u003dmy-account");
formatter.embedding("image/png", "embedded0.png", "Screenshot1586194532072_Registered user login with valid credentials.png");
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
  "error_message": "org.openqa.selenium.NoSuchWindowException: no such window: target window already closed\nfrom unknown error: web view not found\n  (Session info: chrome\u003d80.0.3987.163)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027FABULOUS-PC\u0027, ip: \u0027192.168.0.8\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u002714\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 80.0.3987.163, chrome: {chromedriverVersion: 80.0.3987.106 (f68069574609..., userDataDir: C:\\Users\\User\\AppData\\Local...}, goog:chromeOptions: {debuggerAddress: localhost:56804}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}\nSession ID: 0985b9691f08e88bc0249104b4987bd1\n*** Element info: {Using\u003dxpath, value\u003d//*[@id\u003d\"center_column\"]/div[1]/ol/li}\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)\r\n\tat java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:323)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:428)\r\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:353)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:315)\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy20.getText(Unknown Source)\r\n\tat step_definitions.SignIn.loginError(SignIn.java:112)\r\n\tat ✽.login error \"Authentication failed.\" is displayed(file:///C:/Users/User/IdeaProjects/FirstTask/src/test/resources/features/SignIn.feature:13)\r\n",
  "status": "failed"
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