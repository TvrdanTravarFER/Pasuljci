#!/usr/bin/env bash

# chmod +x seed-database.sh 
# usage: ./scripts/seed-database.sh

echo "Seeding local database with test data..."

sudo systemctl start postgresql
sudo -u postgres dropdb --if-exists krvnaveza
sudo -u postgres createdb krvnaveza
sudo -u postgres psql -v ON_ERROR_STOP=1 -d krvnaveza -f ../db/schema.sql -f ../db/seed.sql -f ../db/roles.sql
