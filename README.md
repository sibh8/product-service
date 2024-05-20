# Product Service

The purpose of the service is to allow products to be queries using FakestoreAPI and get the results. We are also in process to integrate with database which will be coming soon
The status of this documentation is till 20/04/2024. We are updating as we progress.

# Pre-requisites
Ensure that docker is installed on the system running this application. Refer to https://docs.docker.com/desktop/ for more information. This is needed so that local instance of MYSQL and Redis are started during running the application.


## Install MySql
To start MySQL database execute the below command:

```
docker run --name mysql-db -p 3306:3306 -v $(pwd):/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=password -d mysql:8.3.0
```

## Database Setup
To setup a database, Ensure that MYSQL is installed.
 
Follow the below steps:
1. Connect to MYSQL database using the root credentials
2. Open the file in the location datascripts.
3. Execute the commands in the script database_init.sql

These steps are onetime only steps.

After executing these stpes, start the application and use the APIs in controller. Also check if the table is created in Database.

## Redis Setup
To start redis, Execute the below command.

```
docker run -p 6379:6379 -v $(pwd):/usr/local/etc/redis --name myredis redis redis-server /usr/local/etc/redis/redis.conf
```