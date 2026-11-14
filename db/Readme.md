pokretanje baze na linux-u


sudo systemctl start postgresql
sudo -u postgres dropdb --if-exists krvnaveza
sudo -u postgres createdb krvnaveza
sudo -u postgres psql -v ON_ERROR_STOP=1 -d krvnaveza -f schema.sql -f seed.sql


to enter data base:
sudo -u postgres psql -d krvnaveza