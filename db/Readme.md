Pokretanje baze na Linuxu:

```bash
sudo systemctl start postgresql
sudo -u postgres dropdb --if-exists krvnaveza
sudo -u postgres createdb krvnaveza
sudo -u postgres psql -v ON_ERROR_STOP=1 -d krvnaveza -f schema.sql -f seed.sql
```

Ulazak u bazu podataka:

```bash
sudo -u postgres psql -d krvnaveza
```
