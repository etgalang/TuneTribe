## Title
TuneTribe - TT

## Team Members
- Mauricio Garcia-Paez
- Shauna Ayscue
- Emmanuel Galang
- Kannan Thilak

## Description
TuneTribe is a social platform designed for music enthusiasts and artists alike. Users can share their
favorite songs, create music-related posts, comment on friends' posts, and recommend songs/artists to
each other. Artists have a dedicated space to upload their music, gain a following, and track royalties. The
platform fosters community interaction while providing exposure for emerging artists. Moderators ensure
a positive user experience by managing comments and addressing any issues. Admins oversee the
platform's operations and have the authority to ban users or artists if necessary.

## State of the Project
Currently, the project is up and running for both normal users and admins. A user can create an account, 
and most of the use cases have been implemented, except for those related to friends or followers/following. 
All other user use cases have been implemented. For admins, all use cases have been fully implemented. 
For moderators, to be in a usable state, we just need to implement some backend functionalities to send 
and receive data from the database. For artists, there is currently no implementation.

## Use Case Diagram
![UML case diagram (8)](https://github.com/etgalang/TuneTribe/assets/156261506/0352f287-b6e8-4546-afcd-9e8096163bba)



## Setting up the App
Last minute there was a problem so we had to revert to a previous version of our app, in order to link 
that and also the new apiprotype app which is used to generate the spotify key in one link I downloaded
the file we reverted to and replaced our current repo with that file plus the apiprototype app.

Instructions:
- Clone/Download the "TuneTribe" and "apiPrototype" projects and open them in NetBeans.
- Make sure jdk versions match for both projects.
- Run main method in "apiPrototype"
- It should print an accesskey in the terminal
- Copy the accesskey
- In the "TuneTribe" Project go to Source Packages->TuneTribe.Song->SongRepository.java
- Open that file and near the top there is a variable called accessToken
- paste the accessToken you copied in the accessToken variable

- Database:
- Open xampp controll panel and start up Apache and MySql
- Open the admin panel in mySQL and create a database called "csc340_project"
- Import the csc340_project.sql file into the database you created 

- Run the main method for "TuneTribe"
- A normal user and admin are already setup with the credentials
-   username: user password: user
-   username: admin password: admin
- You do not need to use these and can create your own accounts.




