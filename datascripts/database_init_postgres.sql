CREATE DATABASE productservice;

CREATE USER productservice WITH PASSWORD 'password';

-- Use the below commands on postgres user
GRANT ALL PRIVILEGES ON DATABASE productservice TO productservice;
GRANT ALL ON SCHEMA public TO productservice;