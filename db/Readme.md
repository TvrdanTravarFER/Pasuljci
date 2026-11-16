Pokretanje baze na Linuxu:

```bash
sudo systemctl start postgresql
sudo -u postgres dropdb --if-exists krvnaveza
sudo -u postgres createdb krvnaveza
sudo -u postgres psql -v ON_ERROR_STOP=1 -d krvnaveza -f schema.sql -f seed.sql -f roles.sql
```

ili

```bash
chmod +x scripts/seed-database.sh
./scripts/seed-database.sh
```

Ulazak u bazu podataka:

```bash
sudo -u postgres psql -d krvnaveza
```

Ulazak s ulogom:

```bash
psql -h localhost -U krvnaveza_app -d krvnaveza
```
Promjena uloge kao super user:

```bash
sudo -u postgres psql -d krvnaveza

UPDATE AppUser
SET Role = 'ADMIN'
WHERE Email = 'admin@example.com';
```

