# CSC1004-ChatRoom_122090121

Main features: Multi-client chat, login & register , java GUI, MySQL database, animation.

I first set a pure socket chat that runs on the terminal, and then I create a simple chatroom with Javafx GUI that allow only one-to-one chat, then I made combination between these two trials. Then, I set up the login and register system with local files to store the information. After that, MySQL database was connected to store the information.

To run my project, you need first to start a MySQL database, import my .sql file, which is the database that stores the information. Then, run the Server.class, and then you can run the Launch.class multiple times to start the chat (Start two to chat and then add the third is recommended). If the username does not exist in the database, it will not login successfully; if the password is entered wrong, it will not login successfully; and if the user is already online, it will not login successfully. A same username can’t be registered twice, and when register, the two passwords must be identical. Both successful login and register will update your status to Online After login/register, into the chat view you have to send a blank message to connect to the socket. Then you can chat freely. After chat, clicking the Logout button on the top of the right will send you back to the login&register system, and meanwhile update your status as Offline.


