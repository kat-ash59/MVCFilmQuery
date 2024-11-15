# FilmQueryProject

### Description
This project is about placing a web based front end on top of our current Film Query Project

### Technologies Used
All of the previous jdbc is now going to be used as the API backend for our new SpringMVC code.  

### Lessons Learned
copy and paste from previous projects is a huge help.

using the 13 steps also provides a strong foundation for the Spring MVC

be very very careful with names from index.do to filmcontroller, they must! match


### Lab portion of work
#### User Story #1
Completed in class

A user can enter a Film's ID and see the details of the film in a web page. If the film is not found, they see an appropriate message.

#### User Story #2
completed 11/13/24

A user can choose to add a new film. They can enter all the properties of the film. Their input will be used to create Film object, which the DAO implementation will save in the database. If the insert fails, the user is informed of this.

#### User Story #3


When a user retrieves a film, they have the option of deleting it. If they delete the film, it is removed from the database. If the delete fails (such as, due to child records), the user is informed of this.

	Note: It is not necessary to be able to delete existing films, which have child records in various tables. Test your delete functionality using new films you've created via User Story 2.

#### User Story #4
When a user retrieves a film, they have the option of editing it. If they choose this, all the film's current properties are displayed in a form, allowing them to change any property except the film's ID. When they submit the form, that film's record is updated in the database. If the update fails, the user is informed of this.


#### User Story #5
completed in class 11/11/24

A user can search for films by keyword/pattern in title or description. From the resulting list of films, the user can choose to update or delete a record.

#### User Story #6
When a film's details are displayed, its actors and categories are also listed.