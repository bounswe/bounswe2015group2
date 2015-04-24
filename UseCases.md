![http://i.imgur.com/7UH0fHF.png](http://i.imgur.com/7UH0fHF.png)

# Use Case #1 #

  * **Name:** Log In
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To log in to an account
  * **Preconditions:**
    * User shall have an active account
  * **Steps:**
    * User will enter email and password into the email and password fields in the home page.
    * User will click to the Sign In button.
    * System will check the email and password provided.
  * **Post conditions:**
    * If email and password are entered correct, user will be logged in to the system, home page will open and he/she will be able to access his/her profile page via home page.
    * If email and password doesn't match, user is going to be notified that email or password he/she entered is incorrect.

# Use Case #2 #

  * **Name:** Forgot Password
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To reset the old password & get a new password
  * **Preconditions:**
    * User shall have an active account & forgot his/her password
  * **Steps:**
    * User will click Forgot Password link.
    * User will enter his/her email address in the forgot password window.
    * User will click "Send a new password" button.
    * System will reset the old password corresponding to the email provided by user.
    * System will send a new automatically created password to the user's email.
    * User will enter his/her username and new password into the username and password fields in the home page.
    * User will click to the Sign In button.
    * System will check the username and password provided.
  * **Post conditions:**
    * If username and password are entered correct, change your password page will open and Use Case #4 Change Password will work.
    * If username and password doesn't match, user is going to be notified that username or password he/she entered is incorrect.

# Use Case #3 #

  * **Name:** Create Account
  * **Actors:** Guests
  * **Goal:** To create a new Account
  * **Preconditions:**
    * User shall have an active email account.
  * **Steps:**
    * User will click Sign Up button in the home page.
    * User will select the type of its account. (individual or institutional)
    * User will enter a username.
    * User will enter his/her name.
    * User will enter his/her surname.
    * User will enter his/her email.
    * User will confirm his/her email.
    * User will enter his/her phone number.
    * If the user selects Institutional account, he/she will enter the address of the institution.
    * User will enter a password.
    * User will confirm the password.
    * User will enter his/her birthdate.
    * User will enter his/her district.
    * User will enter his/her city.
    * User will enter his/her country.
    * User will add his/her photo.
    * User will add his/her preferences as semantic tags.
    * User will click "Create a new account" button.
    * System will check whether the **email** provided by user is not registered before.
    * System will check whether the **username** provided by user is not registered before.
    * System will check whether two passwords match.
    * System will check whether passwords are entered in the correct format.
  * **Post conditions:**
    * If the username is registered before user will be informed that the user name he/she entered is used by another account and he/she is required to enter the passwords again. Account creation cannot be completed.
    * If the email is registered before user will be informed that the email he/she entered is used by another account and he/she is required to enter the passwords again. Account creation cannot be completed.
    * If two passwords don't match to each other, user will be informed that passwords don't match and he/she is required to enter the passwords again. Account creation cannot be completed.
    * If the passwords entered in **incorrect** format, user will be informed that passwords are not in the format and he/she is required to enter the passwords again. Account creation cannot be completed.
    * If each field, provided by user, is OK and user wanted to create an **individual** account, system will create a new account, send a verification email to the user and notify the user to check his/her email account.
    * If each field, provided by user, is OK and user wanted to create an **institutional** account, system will notify the user that he/she is going to be reached in order to verify their institutional profile. Account creation will not be completed until verification is done.

# Use Case #4 #

  * **Name:** Change Password
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To change password
  * **Preconditions:**
    * User shall have an active account.
    * User shall be logged in to his/her account.
  * **Steps:**
    * User will click Settings link.
    * User will click Change Password link in the settings page.
    * User will see new password and retype password fields
    * User will enter a new password.
    * User will retype the new password.
    * User will click Change button.
    * System will check whether two passwords match.
    * System will check whether passwords are entered in the correct format.
  * **Post conditions:**
    * If two passwords don't match to each other, user will be informed that passwords don't match and he/she is required to enter the passwords again. Changing password is not successful.
    * If the passwords entered in **incorrect** format, user will be informed that passwords are not in the format and he/she is required to enter the passwords again. Changing password is not successful.
    * If each field, provided by user, is OK, system will notify the user his/her password is successfully changed.

# Use Case #5 #

  * **Name:** Change Profile Picture
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To change profile picture with an existing one
  * **Preconditions:**
    * User shall have an active account.
    * User shall be logged in to his/her account.
  * **Steps:**
    * User will click My Profile button
    * User will click Edit My Profile button
    * User will click Change my photo link
    * User will be asked to choose a photo from his/her photo library
    * User will pick a photo
    * User will click change button
  * **Post conditions:**
    * Profile page will open
    * System will update the profile picture in the profile page

# Use Case #6 #

  * **Name:** Change Preferences
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To change food preferences
  * **Preconditions:**
    * User shall have an active account.
    * User shall be logged in to his/her account.
  * **Steps:**
    * User will click My Profile button.
    * User will write a name for the new tag in order to add a new one to the preferences list.
    * User click add button.
    * User will click remove button of a specific tag to remove from his/her food preferences list.
  * **Post conditions:**
    * System will add the new tag.
    * System will remove the tag whose remove button is clicked.


# Use Case #7 #

  * **Name:** Add a Recipe
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To uploade your new recipe to the system.
  * **Preconditions:**
    * User shall log in to the system.
  * **Steps:**
    * User will click the “add recipe” section.
    * User will go to the adding recipe page.
    * User will enter a name for the recipe on this page
    * User will choose a category for the recipe.
    * User will add tags for the recipe
    * User will add ingredients in the recipe and enter their amount.
    * User will be able to add  pictures if he/she wants.
  * **Post conditions:**
    * System will calculate the nutrients according to the added ingredients into the recipe.
    * System will store the recipe with the tags provided by users and calculated nutritional info in the database.

# Use Case #8 #

  * **Name:** Search for a Recipe
  * **Actors:** Guests/Registered Users(both individuals & institutions)
  * **Goal:** To find a recipe
  * **Preconditions:**
    * System shall provide a search box that can be accessible from every pages
  * **Steps:**
    * User/guest will enter a text on the search box.
    * User/guest will click search button.
  * **Post conditions:**
    * System will return filtered results based on his/her food preferences and food restrictions by **default** for registered users.
    * System will return filtered results based on ratings for guests.

# Use Case #9 #

  * **Name:** Advanced Search for a Recipe
  * **Actors:** Guests/Registered Users(both individuals & institutions)
  * **Goal:** To search recipes in detail
  * **Preconditions:**
    * System shall provide a search box that can be accessible from every pages
  * **Steps:**
    * User/guest will enter a text on the search box.
    * User/guest will click search button.
    * Registered User will see filtered results based on his/her food preferences and food restrictions
    * Guests will see filtered results based on ratings.
    * User/guest will click advanced search button to open advanced search filtering panel.
    * User/guest will be able to filter the results **more** by selecting tags, or choosing calory, fat, protein, sugar, cost values from the panel.
    * User/guest will be able to remove tags used to filter the results.
  * **Post conditions:**
    * System will return filtered results based on his/her food preferences and food restrictions by **default** (for ex. high protein based results for a user who prefers high protein food)
    * System will return filtered results in a descending order according to the ratings.
    * System will add the search options to user’s historical data to offer him more accurate recipes.

# Use Case #10 #

  * **Name:** View Recipe Details
  * **Actors:** Guests/Registered Users(both individuals & institutions)
  * **Goal:** To see more information about the recipe.
  * **Preconditions:**
    * System shall provide a page that contains all about the recipe
  * **Steps:**
    * User will click one of the recipe on the searching page.
  * **Post conditions:**
    * System will list all the ingredients and nutritional data about that recipe.
    * System will show shared comments to this recipe.
    * System will show similar recipe options based on semantic tags of the recipe itself under the "You might also enjoy" part for guests.
    * System will show other recipe options based on not only semantic tags of the recipe but also user preferences & restrictions under the "You might also enjoy" part for registered users.
    * If the user is a registered user, system will enable user to add a comment, like or share the recipe.

# Use Case #11 #

  * **Name:** Add a comment to a recipe
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To add comments or rate the recipes
  * **Preconditions:**
    * User shall log in to the system.
    * There shall be a rate-able recipe in the system
  * **Steps:**
    * User will open the recipe which he/she wants to add comment.
    * User will add comment and click “add comment” button.
  * **Post conditions:**
    * System will add the new comment under the recipe details provided with the profile of the rater.

# Use Case #12 #

  * **Name:** Follow an individual
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To add a following relation
  * **Preconditions:**
    * User shall log in to the system.
    * There shall be another user rate-able in the system
  * **Steps:**
    * User will search for a user from the search area.
    * User will decide whom to follow from search results.
    * User will click "follow" for the individual he would like to follow.
  * **Post conditions:**
    * System will keep this relationship.
    * System will make each user to be able to see each other's sharing.


# Use Case #13 #

  * **Name:** Follow an institution
  * **Actors:** Registered Users(both individuals & institutions)
  * **Goal:** To add a following relation
  * **Preconditions:**
    * User shall log in to the system.
    * There shall be another institutional user rate-able in the system
  * **Steps:**
    * User will click institutions tab button.
    * User will decide which institution to follow from search results.
    * User will choose "follow" option in the page.
  * **Post conditions:**
    * System will keep this relationship.
    * System will make each user to be able to see each other's sharing.

# Use Case #14 #

  * **Name:** Add to daily consumed list
  * **Actors:** Registered Users(only individuals)
  * **Goal:** To add nutritional values of the food consumed during a day.
  * **Preconditions:**
    * User shall log in to the system.
    * User shall take some food from the recipes in the system
  * **Steps:**
    * User will find an existing recipe from the system or provide a new recipe with his/her own.
    * User will click "Add to daily consumed list" button.
  * **Post conditions:**
    * Based on the nutritional data of the recipe, system will calculate the nutrient distribution of the recipe consumed in that day.
    * System will update user's daily nutrient distribution graph and show to the user
    * System will show a pie chart for the distribution data. Percentages of protein,carbohydrate and fat  will be shown with different colors in the chart.

# Use Case #15 #

  * **Name:** View the Menu
  * **Actors:** Guests/Registered Users(both individuals & institutions)
  * **Goal:** To see the details of a Menu
  * **Preconditions:**
    * System shall provide Institutions tab button.
  * **Steps:**
    * User/guest will click Institutions button.
    * Registered Users will see the Institutions list ordered according to their preferences and location.
    * Guests will see the Institutions list ordered according to the rankings.
    * User/guest will click to view menu button to see menu provided by the institution.
  * **Post conditions:**
    * System will show menu to the user.

# Use Case #16 #

  * **Name:** Rate a Food Provider
  * **Actors:** Registered Users(only individuals)
  * **Goal:** To rate a provider to show people how good this provider is
  * **Preconditions:**
    * System shall provide rating system includes cleanliness,taste, and price
  * **Steps:**
    * User will enter points over 10 to rate these features.
    * User will click rate button.
  * **Post conditions:**
    * System will add these ratings to the provider profile page.

# Use Case #17 #

  * **Name:** Add a Comment to a Food Provider
  * **Actors:** Registered Users(only individuals)
  * **Goal:** To add a comment to provider to show people how good this provider is
  * **Preconditions:**
    * System shall provide add comment part
  * **Steps:**
    * User will enter a comment to that part
    * User will click add comment button.
  * **Post conditions:**
    * System will add these comments to the provider profil page

# Use Case #18 #

  * **Name:** Add a Menu
  * **Actors:** Registered Users(only institutions)
  * **Goal:** To upload your new menu to the system.
  * **Preconditions:**
    * User shall log in to the system.
  * **Steps:**
    * User will click the “add menu” section.
    * User will go to the adding recipe page.
    * User will enter a name for the recipe on this page
    * User will choose a category for the recipe.
    * User will add tags for the recipe
    * User will add ingredients in the recipe and enter their amount.
    * User will be able to add  pictures if he/she wants.
    * User should do these steps all the recipes which belongs to this menu
    * User should also enter a price for each recipe
  * **Post conditions:**
    * System will calculate the nutrients according to the added ingredients into the recipe.
    * System will store the menu with the tags provided by users and calculated nutritional info in the database.