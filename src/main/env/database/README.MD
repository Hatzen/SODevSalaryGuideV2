https://sherryhsu.medium.com/set-up-postgresql-database-using-with-production-data-using-docker-f164694341f1

Have a look at the schemas

psql -U postgres
SELECT datname FROM pg_catalog.pg_database;