CREATE FUNCTION pg_temp.uid(p_email TEXT) RETURNS BIGINT
LANGUAGE sql AS $$ SELECT UserID FROM AppUser WHERE Email = p_email $$;

CREATE FUNCTION pg_temp.center(p_n INTEGER) RETURNS INTEGER
LANGUAGE sql AS $$ SELECT CenterID FROM Center ORDER BY CenterID OFFSET p_n - 1 LIMIT 1 $$;

INSERT INTO AppUser (Email, OAuthProvider, OAuthSubject, FirstName, LastName, DateOfBirth, Sex, Residence, PhoneNumber, Role) VALUES
('user.zagreb@gmail.com', 'google', 'test-staff-0', 'Ivan', 'Gundulić', DATE '1999-09-11','M','Zagreb','+385950000001','STAFF'),
('user.osijek@gmail.com', 'google', 'test-staff-1', 'Nikola', 'Tesla', DATE '1990-09-11','M','Osijek','+385950000002','STAFF'),
('user.org@gmail.com', 'google', 'test-organizator-0', 'Ana', 'Matić', DATE '1980-01-12','F','Zagreb','+385950000003','ORGANIZER');

INSERT INTO Staff (UserID, CenterID) VALUES
(pg_temp.uid('user.zagreb@gmail.com'), pg_temp.center(1)),
(pg_temp.uid('user.osijek@gmail.com'), pg_temp.center(2));

INSERT INTO Organizer (UserID, OrganizationName) VALUES
(pg_temp.uid('user.org@gmail.com'), 'Crveni križ Zagreb');


-- u produkciji prvo dodati u AppUser pa u DOnor
CREATE TEMP TABLE tmp_donors AS 
SELECT * FROM (VALUES
    ('Ivan.voda@nesto.com', 'Ivan', 'Ivanić', DATE '1990-09-09', 'M', 'O+', TRUE, 45.8000, 15.9500, '+385950000004'),
    ('Ana.voda@nesto.com', 'Ana', 'Anić', DATE '1985-03-14', 'F', 'A+', TRUE, 45.8500, 16.0000, '+385950000005'),
    ('Marko.voda@nesto.com', 'Marko', 'Markić', DATE '1998-11-22', 'M', 'B+', TRUE, 45.9000, 16.0500, '+385950000006'),
    ('Marija.voda@nesto.com', 'Marija', 'Marić', DATE '1976-07-03', 'F', 'O-', TRUE, 45.9500, 16.1000, '+385950000007'),
    ('Luka.voda@nesto.com', 'Luka', 'Lukić', DATE '2001-01-18', 'M', 'AB+', TRUE, 46.0000, 16.1500, '+385950000008'),
    ('Petra.voda@nesto.com', 'Petra', 'Petrić', DATE '1993-05-27', 'F', 'A-', TRUE, 46.0500, 16.2000, '+385950000009'),
    ('Josip.voda@nesto.com', 'Josip', 'Jurić', DATE '1988-12-11', 'M', 'B-', TRUE, 46.1000, 16.2500, '+385950000010'),
    ('Ivana.voda@nesto.com', 'Ivana', 'Ivanić', DATE '1995-04-06', 'F', 'O+', TRUE, 46.1500, 16.3000, '+385950000011'),
    ('Filip.voda@nesto.com', 'Filip', 'Matić', DATE '1982-08-19', 'M', 'A+', TRUE, 46.2000, 16.3500, '+385950000012'),
    ('Maja.voda@nesto.com', 'Maja', 'Majić', DATE '2000-02-25', 'F', 'AB-', TRUE, 46.2500, 16.4000, '+385950000013'),
    ('Petar.voda@nesto.com', 'Petar', 'Petrić', DATE '1979-10-08', 'M', 'O-', TRUE, 46.3000, 16.4500, '+385950000014'),
    ('Katarina.voda@nesto.com', 'Katarina', 'Nesto', DATE '1991-06-15', 'F', 'B+', TRUE, 46.3500, 16.5000, '+385950000015'),
    ('Matej.voda@nesto.com', 'Matej', 'Matić', DATE '1987-03-29', 'M', 'A-', TRUE, 46.4000, 16.5500, '+385950000016'),
    ('Ante.voda@nesto.com', 'Ante', 'Klarić', DATE '1999-09-12', 'F', 'O+', TRUE, 46.4500, 16.6000, '+385950000017'),
    ('Dario.voda@nesto.com', 'Dario', 'Darić', DATE '1974-12-02', 'M', 'AB+', TRUE, 46.5000, 16.6500, '+385950000018'),
    ('Nikola.voda@nesto.com', 'Nikola', 'Nikolić', DATE '1996-07-21', 'M', 'B-', TRUE, 46.5500, 16.7000, '+385950000019'),
    ('Sara.voda@nesto.com', 'Sara', 'Sarić', DATE '2002-04-17', 'F', 'A+', TRUE, 46.6000, 16.7500, '+385950000020'),
    ('Tomislav.voda@nesto.com', 'Tomislav', 'Perić', DATE '1984-11-30', 'M', 'O-', TRUE, 46.6500, 16.8000, '+385950000021'),
    ('Ema.voda@nesto.com', 'Ema', 'Bošnjak', DATE '1997-01-09', 'F', 'AB-', TRUE, 46.7000, 16.8500, '+385950000022')
) AS t(email,firstName,lastName,DateOfBirth,sex,bloodType,verified,lat,lng,phone);

INSERT INTO AppUser (Email, OAuthProvider, OAuthSubject, FirstName, LastName, DateOfBirth, Sex, Residence, PhoneNumber, Role)
SELECT email, 'google', 'google' || split_part(email, '@', 1), firstName, lastName, DateOfBirth, sex, 'Zagreb', phone, 'DONOR' FROM tmp_donors;

INSERT INTO Donor (UserID, BloodType, BloodTypeVerified, NotifySms, NotifyEmail, MaxNotifyDistanceKm, HomeLat, HomeLng)
SELECT pg_temp.uid(email), bloodType, verified, TRUE,TRUE,25,lat,lng FROM tmp_donors;

-- odrzano prije 50 dana
INSERT INTO FieldAction (OrganizerID, CenterID, ActionName, Address, Lat, Lng, ActionStart, ActionEnd, ExpectedDonors, Status) VALUES
(
    pg_temp.uid('user.org@gmail.com'), pg_temp.center(1), 'Akcija darivanja', 'Zagreb', 45.8000,16.0000,
    CURRENT_DATE - 50 + TIME '08:00',  CURRENT_DATE - 50 + TIME '15:00', 50, 'HELD'
);

-- otvorena prijava na FERU
INSERT INTO FieldAction (OrganizerID, CenterID, ActionName, Address, Lat, Lng, ActionStart, ActionEnd, ExpectedDonors, Status) VALUES
(
    pg_temp.uid('user.org@gmail.com'), pg_temp.center(1), 'Akcija darivanja FER', 'Unska 3, Zagreb', 45.8010,15.9715,
    CURRENT_DATE + 7 + TIME '08:00',  CURRENT_DATE + 7 + TIME '13:00', 100, 'REGISTRATIONS_OPEN'
);

INSERT INTO FieldAction (OrganizerID, CenterID, ActionName, Address, Lat, Lng, ActionStart, ActionEnd, ExpectedDonors, Status) VALUES
(
    pg_temp.uid('user.org@gmail.com'), pg_temp.center(1), 'Akcija darivanja Sesvete', 'Sesvete', 45.8300,16.1200,
    CURRENT_DATE + 20 + TIME '08:00',  CURRENT_DATE + 20 + TIME '10:00', 20, 'PROPOSED'
);

INSERT INTO TraumaTeam (ActionID, StaffID) 
SELECT ActionID, pg_temp.uid('user.zagreb@gmail.com') FROM FieldAction WHERE Status IN ('HELD', 'REGISTRATIONS_OPEN');

INSERT INTO BloodStockLog (CenterID, BloodType, LogTime, Quantity)
SELECT s.CenterID, s.BloodType, now() - make_interval(days => d),
    CASE WHEN d = 0 THEN s.quantity
    ELSE GREATEST(0, s.Quantity + ((d * 7 + ascii(substr(s.BloodType,1,1)) + s.CenterID) % 20))
    END
FROM CenterBloodStock s CROSS JOIN generate_series(0,30) as d;