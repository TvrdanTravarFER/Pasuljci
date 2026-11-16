INSERT INTO Center (CenterName, Address, Lat,Lng) VALUES
('Transfuzijska medicina KBC Split', 'Spinčićeva 1, Split', 43.5176, 16.4637),
('Transfuzijska medicina KBC Rijeka', 'Krešimirova 42, Rijeka', 45.3303, 14.4468),
('Hrvatski zavod za transfuzijsku medicinu', 'Petrova 3, Zagreb', 45.8165, 15.9915),
('Transfuzijska medicina KBC Osijek', 'Josipa Huttlera 4, Osijek', 45.5290, 18.7150),
('Transfuzijska medicina OB Šibenik', 'Stjepana Radića 83, Šibenik', 43.7354, 15.8952),
('Transfuzijska medicina OB Slavonski Brod', 'Andrije Štampara 42, Slavonski Brod', 45.1603, 18.0156),
('Transfuzijska medicina KBC Sestre milosrdnice', 'Vinogradska cesta 29, Zagreb', 45.8152, 15.9572),
('Transfuzijska medicina KB Dubrava', 'Avenija Gojka Šuška 6, Zagreb', 45.8385, 16.0565),
('Transfuzijska medicina KBC Zagreb', 'Kišpatićeva 12, Zagreb', 45.8208, 16.0022),
('Transfuzijska medicina OB Zadar', 'Bože Peričića 5, Zadar', 44.1053, 15.2285),
('Transfuzijska medicina OB Dubrovnik', 'Roka Mišetića bb, Dubrovnik', 42.6507, 18.0912),
('Transfuzijska medicina OB Varaždin', 'Ivana Meštrovića bb, Varaždin', 46.3057, 16.3378),
('Transfuzijska medicina OB Pula', 'Negrijeva 6, Pula', 44.8666, 13.8496),;

INSERT INTO CenterWorktime (CenterID, DayOfWeek, WorkStart, WorkEnd)
SELECT c.CenterID, day, TIMETZ '08:00:00+1', TIMETZ '13:00:00+1' FROM Center c CROSS JOIN generate_series(1,5) AS day;

INSERT INTO CenterBloodThreshold (CenterID, BloodType, LowThreshold, CriticalThreshold)
SELECT c.CenterID, b.BloodType,
    (SELECT ConstValue::SMALLINT FROM Constants WHERE ConstKey = 'DEFAULT_LOW_THRESHOLD'),
    (SELECT ConstValue::SMALLINT FROM Constants WHERE ConstKey = 'DEFAULT_CRITICAL_THRESHOLD')
FROM Center c CROSS JOIN BloodTypeRef b;

INSERT INTO SelfAssesmentQuestion (Question, DisqualifyingQuestion) VALUES
('Jeste li u posljednjih 6 mjeseci napravili tetovažu ili piercing?', TRUE),
('Jeste li u posljednjih 6 mjeseci putovali u područje s povećanim rizikom od zaraznih bolesti?', TRUE),
('Jeste li u posljednja 4 tjedna primili cjepivo?', TRUE),
('Uzimate li antibiotike?', TRUE),
('Imate li trenutno povišenu temperaturu ili simptome prehlade ili gripe?', FALSE);