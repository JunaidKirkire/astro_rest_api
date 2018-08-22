# astro_rest_api

This project is a REST Service built on Spring framework using Eclipse.  The boilerplate of this project was built using https://start.spring.io

One only needs to import the project into Eclipse and run it.

We would be using MySQL DB for this instead of H2 database.  A DB called `support` would have to be created for this purpose.

The app works in this fashion --
1.  User should create a schedule by specifying the start date and the end date.

2.  As per the number of working days present in the interval, the requisite number of slots are created.  Since our problem statement is considering only two slots, we will keep it like that for now.
    Our code can manage the scenario when we have more than two slots in a day also.

3.  We will have to seed the DB with the list of employees after our app boots up.  Currently, we are going to assume that we have 10 employees.
    INSERT INTO `employees`(employee_name) VALUES ('Captain America');
    INSERT INTO `employees`(employee_name) VALUES ('Iron Man');
    INSERT INTO `employees`(employee_name) VALUES ('Hulk');
    INSERT INTO `employees`(employee_name) VALUES ('Black Widow');
    INSERT INTO `employees`(employee_name) VALUES ('Hawkeye');
    INSERT INTO `employees`(employee_name) VALUES ('Vision');
    INSERT INTO `employees`(employee_name) VALUES ('Wanda');
    INSERT INTO `employees`(employee_name) VALUES ('Thor');
    INSERT INTO `employees`(employee_name) VALUES ('Spider Man');
    INSERT INTO `employees`(employee_name) VALUES ('Ant Man');

4.  We can view all the slots and assign one of the above employees using the "/assign_employee" endpoint.  We've devised the APIs such that we can assign an employee to a particular slot at a time.  The endpoints are described below --

5.  The DB schema is as follows --

** schedules --
    +---------------------+----------+------+-----+---------+----------------+
| Field               | Type     | Null | Key | Default | Extra          |
+---------------------+----------+------+-----+---------+----------------+
| schedule_id         | int(11)  | NO   | PRI | NULL    | auto_increment |
| schedule_end_time   | datetime | YES  |     | NULL    |                |
| schedule_start_time | datetime | YES  |     | NULL    |                |
+---------------------+----------+------+-----+---------+----------------+

** slots --
+----------------------+----------+------+-----+---------+----------------+
| Field                | Type     | Null | Key | Default | Extra          |
+----------------------+----------+------+-----+---------+----------------+
| slot_id              | int(11)  | NO   | PRI | NULL    | auto_increment |
| employee_id          | int(11)  | NO   |     | NULL    |                |
| schedule_date        | datetime | YES  |     | NULL    |                |
| schedule_schedule_id | int(11)  | YES  | MUL | NULL    |                |
+----------------------+----------+------+-----+---------+----------------+

** employees --
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| employee_id   | int(11)      | NO   | PRI | NULL    | auto_increment |
| employee_name | varchar(255) | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+

It has the following endpoints --

1. /schedules - give the list of schedules
   sample JSON --
   {"scheduleStartTime":"2018-07-30T00:00:00.000+0000","scheduleEndTime":"2018-07-30T00:00:00.000+0000","scheduleId":1}
   
2.  /add - Add a schedule
    As one can see in the front-end that we can create a schedule of not just 2 weeks but more than that!  This is to make
    the creation of schedules as flexible as possible.
    
3.  /slots?schedule_id=<ID of the created schedule> - gives the list of slots for that schedule

4.  /assign_employee?slot_id=&schedule_id= - assigns one of the employees from above for the slot


    
