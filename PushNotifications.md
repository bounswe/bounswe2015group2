# **Push Notifications** #

Push notifications let an application notify a user of new messages or events even when the user is not actively using the application. On Android devices, when a device receives a push notification, the application's icon and a message appear in the status bar. When the user taps the notification, they are sent to your application. Notifications can be broadcast to all users, such as for a marketing campaign, or sent to just a subset of users, to give personalized information.

For android applications Google Cloud Messaging is the most widely used Push notifications tool.

The basic process of Push notifications is achieved in the fallowing steps

  * When a event occurs that the user is to be notified. The server for the application sends the a message to the Notifications server containing a short token specifying the user and the application.
  * The server sends the message to the user when the user becomes available

The main reason why such a service is needed is that the availability of the mobile phone are very limited.Hence making a reliable communication channel from the server to the user is nearly impossible. To overcome this obstacle a Notification server keeps a connection with the user alive as long as the connection of the mobile user is active. And when a message is to be sent to the user the notification server delivers the message in the name of the application

For further information on GCM please visit https://developer.android.com/google/gcm/index.html

Reference
  * http://en.wikipedia.org/wiki/Push_technology
  * https://parse.com/tutorials/android-push-notifications