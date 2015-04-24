## Summary ##

This project is an awareness tool and nutritional assistant for its users while linking them according to their food social network.

## Background ##

“Acıktım” project offers its users the opportunity to access nutrition information and create a personal tracking tool. The main goal of the system is to raise awareness about what we eat, and establish a relationship between food providers and consumers as well. In the system, data taken from nutrition databases are implemented to meals to calculate overall amount of calorie, fat, etc. by looking at the ingredients of that meal. As a result, users will have a chance to reach healthy food and organize their eating habits. The system also has a social aspect as in twitter, i.e. the users can follow each other and see others’ preferences. Moreover, they can share their comments under others’ posts and food providers’ pages.

## Glossary ##

  * For Individuals
    * **Individual User** : People who use “Acıktım” system in order to search for a recipe, or a menu, share a new recipe, or follow some other users.
    * **Consumer** : Users who consume food that is provided by Food Provider.
    * **Post** : A recipe shared by users.
    * **Comment** : Comments given to shared recipes or menus by users.
    * **Contribution** : Sharing posts or giving comments in the system
    * **Semantic Tag** : A description used for foods to group them based on the class they are belonging to. e.g tagging chickpeas as chickpea::legume on the system
    * **Rating institutions** : An assessment type that users assess institutional users in different aspects which are cleanliness, taste, price.
    * **Recipe** : A recipe is an item of which name, ingredients, category, photo, and semantic tags are shared by an individual or institutional user.
    * **Allergic component** : An ingredient that one may have a biological reaction to it when that is consumed.
    * **Ingredient** : Ingredients are organic molecules which consists food like protein, carbohydrate and fat.
    * **Diet** : Diet is the set of foods one may allowed to eat in a period of time.
    * **Micronutrients** : Nutrients that humans consume in small quantities throughout life. They can be categorized as follows: Vitamins, Trace Materials, Organic Acids
    * **Macronutrients** : Chemical elements that humans consume in the largest quantities. Three primary macronutrients are protein, fat, and carbohydrate.
    * **Protein** : Protein is an ingredient that is consumed mostly by sportive people who cares about muscle building.
    * **Fat** : Fat is an ingredient that is mostly found delicious by people but unhealthy when consumed a lot.
    * **Carbohydrate** : Carbohydrate is an ingredient which is consumed for energy because it is burned first for a human being.
    * **Food Tracker** : A service which tracks the food that is consumed by individual users.
  * For Institutions
    * **Institutional User** : People who use “Acıktım” system in order to share a recipe or menu, follow other users, receive reports associated with the food they provide.
    * **Food Provider** : Company or institution that provides food.
    * **Menu** : A menu consists of different foods shared by an institutional user in the system.
    * **Institution subtypes** : Canteens, cafeterias, restaurants.
    * **Rating raters** : An assessment for the comments given to the posts by liking or disliking manner so that any fake evaluation is prevented.
    * **Post** : A recipe or menu shared by users.
    * **Comment** : Comments given to shared recipes or menus by users.
    * **Contribution** : Sharing posts or giving comments in the system
    * **Semantic Tag** : A description used for foods to group them based on the class they are belonging to. e.g tagging chickpeas as chickpea::legume on the system.
    * **Recipe** : A recipe is an item of which name, ingredients, category, photo, and semantic tags are shared by an individual or institutional user.

## Requirements ##

  * 1. User Requirements
    * 1.1. Unregistered Users
      * 1.1.1. Individuals shall be able to do search with advanced search options with tags including location of the restaurant, meals, and ingredients (e.g. carbohydrate level, fat level, protein level, vitamin level, mineral level etc.).
      * 1.1.2 Individuals shall be able to search for consumers, providers, recipes and menus.
    * 1.2. Registered Users
      * 1.2.1. Individuals
        * 1.2.1.1. Registered individuals shall be able to do the functionalities of unregistered users stated with 1.1.1 and 1.1.2 items.
        * 1.2.1.2. Individuals shall be provided with filtered results according to their preferences when they make a search.
        * 1.2.1.3. Individuals shall be able to follow other users.
        * 1.2.1.4. Individuals shall be able to comment each others posts.
        * 1.2.1.5. Individuals shall be able to specify their food preferences, such as allergies, dietary restrictions, likes, dislikes.
        * 1.2.1.6. Individuals shall see their preferred food.
        * 1.2.1.7. Individuals shall be able to disable their preferences to make specific searches.
        * 1.2.1.8. Individuals shall be able to add items of interest such as ingredients, ready food and recipes to their favorite recipes
        * 1.2.1.9. Individuals shall be able to provide recipes.
        * 1.2.1.10. Individuals shall be able to track their nutritional intake by providing a list of what they have eaten.
        * 1.2.1.11. Individuals shall be able to provide semantic tags for recipes.
      * 1.2.2. Institutions
        * 1.2.2.1. The Institutions account shall need a verification.
        * 1.2.2.2. Institutions shall have subtypes as canteens, cafeterias, restaurants
        * 1.2.2.3. The Institutions shall give nutrition information about the food that they are serving.
        * 1.2.2.4. Institutions shall be able to provide recipes.
        * 1.2.2.5. Institutions  shall be able to publish menus linked to the nutritional information.
        * 1.2.2.6. Institutions shall be able to follow other users.
        * 1.2.2.7. Institutions shall be able to comment on other posts.
  * 2. System Requirements
    * 2.1. Functional Requirements
      * 2.1.1. The System shall respond according to user preferences which it learns in time.
      * 2.1.2. The system shall have a filtering system for food restrictions working.
      * 2.1.3. The system shall recommend ingredients, recipes, and locations to eat to users based on their food preferences and food social network.
      * 2.1.4. The system shall give location-based suggestions.
      * 2.1.5. The system shall only enable a user to make a contribution after he or she is logged in.
      * 2.1.6. The system shall show nutrients both in macro and micro levels.
      * 2.1.7. The system shall allow a user to follow another user without a permission.
      * 2.1.8. The system shall rank the results of the users queries in accordance with their preferences and filter them according to the users restrictions.
      * 2.1.9. The rating system shall enable users to rate the institution from different aspects.
      * 2.1.10. The system shall prevent fake evaluations by enabling its users to rate the raters.
      * 2.1.11. The system shall warn the users about their restricted food preferences by highlighting the corresponding tag in the recipe detail page
      * 2.1.12. The system shall retrieve the nutritional value of raw and packaged food from the open data sources.
      * 2.1.13. The system shall provide institutions with reports that provide user feedback associated with the food they provide.
    * 2.2. Non-Functional Requirements
      * 2.2.1. Security
        * 2.2.1.1. The system shall enable its user to make decision whether to isolate food-related informations, recipes etc. provided by themselves from another user upon their decisions.
        * 2.2.1.2. The system shall assure the safety of the credentials of the users, provided that even developers or administrators of the project are not able to access credentials’ data.
      * 2.2.2. Usability
        * 2.2.2.1. The system shall have a tutorial tour to new users.
        * 2.2.2.2. The system shall be easily used by users who are older than 16 years old.
      * 2.2.3 Portability
        * 2.2.3.1. The system shall have a web front-end and should work on the following web browsers
          * Google Chrome - Versions later than or equal to 41.0.2272.101
          * Mozilla Firefox - Versions later than or equal to 36.0.4
          * Internet Explorer - Versions later than or equal to 11.0.17
          * Yandex Browser - Versions later than or equal to 14.12
        * 2.2.3.2. The system shall have an Android client, it should work on the versions later than or equal to 5.0 Lolipop.
      * 2.2.4. Reliability
        * 2.2.4.1. The system shall crash once for a month at most.
        * 2.2.4.2. The system shall handle the down time maximum in two hours.
        * 2.2.4.3. The system shall recovers its last state that it was at the moment of crash.
      * 2.2.5. Flexibility
        * 2.2.5.1. The system shall be designed in a modular manner so that new features could be introduced easily.
        * 2.2.5.2. The system shall be designed in a readable manner so that modifications can be done easily.
      * 2.2.6. Accessibility
        * 2.2.6.1. The system shall appear on the top most when a related query is made on web.
        * 2.2.6.2. The system shall appear on the top most when a related query is made on android market.
      * 2.2.7. Efficiency
        * 2.2.7.1. The system shall respond to users maximum in 5 seconds.
        * 2.2.7.2. The system shall be designed in a smart manner so that storage of maximum 100 TB is going to be used.
      * 2.2.8. Robustness
        * 2.2.8.1. The system shall have an appropriate response for every possible error.
      * 2.2.9. Maintainability
        * 2.2.9.1. The system shall support the old versions of it for one year.
        * 2.2.9.2. The system shall let the users know about any update to system in 24 hours in Google Play Store.
      * 2.2.10. Reusability
        * 2.2.10.1. The system shall document the libraries used in it properly.
        * 2.2.10.2. The system shall indicate the reusable components so that the libraries can be used in other applications.