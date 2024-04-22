# Product Service

The purpose of the service is to allow products to be queries using FakestoreAPI and get the results. We are also in process to integrate with database which will be coming soon
The status of this documentation is till 20/04/2024. We are updating as we progress.

## Database Setup
 To setup a database, Ensure that MYSQL is installed.
 
Follow the below steps:
1. Connect to MYSQL database using the root credentials
2. Open the file in the location datascripts.
3. Execute the commands in the script database_init.sql

These steps are onetime only steps.

After executing these stpes, start the application and use the APIs in controller. Also check if the table is created in Database.
