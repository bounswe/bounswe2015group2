## **Testing Android Applications** ##

We can't say that the development of an Android App is completed or ready for use unless it is tested in all aspects at every level from unit to framework. In order to test the application in all aspects, we need an architecture and powerful tools. And the Android testing framework provides what we need.


Android's build and test tools assume these test projects are organized into a standard structure of tests, test case classes(container of test methods), test packages, and test projects. So, before starting to test the application, it's required to create test projects in which we can create the source code, manifest file, and other files for a test package.


Android testing is based on JUnit. In general, a JUnit test is a method whose statements test a part of the application under test. Each test should be an isolated test of an individual module.

Android provides several test case classes for developers.
These classes enable us to do following component-specific unit tests:


  * Activity Testing
  * Content Provider Testing
  * Service Testing

Besides these component specific unit tests, we should test the behavior of our application’s user interface (UI) when it is running on a device and we should test whether the application accessible to users with varying abilities or not. These are corresponding to the following tests:


  * Accessibility Testing
  * UI Testing


### **Automated testing** ###
Testing is a very costly process in the development of an Android Application. In order to decrease the cost and time we spent while executing a test, and to test things that aren’t manually possible (i.e. what if I processed ten transactions simultaneously), we use Automated Testing Tools.


Here are some automated testing tools:
  * Robotium
  * Monkeyrunner
  * Appthwack


For more details about the tools, you can look at  [here](http://blog.bughuntress.com/automated-testing/useful-tools-for-android-apps-test-automation)


References:
  * http://developer.android.com/tools/testing/testing_android.html
  * http://blog.bughuntress.com/automated-testing/useful-tools-for-android-apps-test-automation
  * http://blog.bughuntress.com/automated-testing/when-test-automation-is-not-good
  * http://watirmelon.com/2013/04/14/which-is-better-manual-or-automated-testing/