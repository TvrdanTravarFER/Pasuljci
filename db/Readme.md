Pokretanje baze na Linuxu:

```bash
sudo systemctl start postgresql
sudo -u postgres dropdb --if-exists krvnaveza
sudo -u postgres createdb krvnaveza
sudo -u postgres psql -v ON_ERROR_STOP=1 -d krvnaveza -f schema.sql -f seed.sql -f roles.sql
```

Ulazak u bazu podataka:

```bash
sudo -u postgres psql -d krvnaveza
```

Ulazak s ulogom:

```bash
psql -h localhost -U krvnaveza_app -d krvnaveza
```