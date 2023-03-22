#!/bin/bash
// 1. create a dump file from production db
> /usr/local/bin/pg_dump "$PROD_DATABASE_URL" > "$DIR/raw_dump.sql"
// 2. URL of the newly created local database is 
// postgres://{user}:{password}@{server}:{port}/{db_name}
// e.g. 
LOCAL_DATABASE_URL=postgres://postgres@localhost:5432/postgres
// 3. Import production dump file into local db
> psql "$LOCAL_DATABASE_URL" < "$DIR/raw_dump.sql"
// 4. Cleanup the data in local db
> psql "$LOCAL_DATABASE_URL" < "$DIR/clean.sql"
// 5. Create dump file from local db
> /usr/local/bin/pg_dump \    
  --no-acl \    
  --no-owner \    
  "$TO_DATABASE_URL" > "$DIR/../seed.sql"