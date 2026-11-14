INSERT INTO Center (CenterName, Address, Lat,Lng) VALUES
('Transfuzijska medicina KBC Split', 'Spinčićeva 1, Split', 43.5176, 16.4637),
('Transfuzijska medicina KBC Rijeka', 'Krešimirova 42, Rijeka', 45.3303, 14.4468),
('Hrvatski zavod za transfuzijsku medicinu', 'Petrova 3, Zagreb', 45.8165, 15.9915),
('Transfuzijska medicina KBC Osijek', 'Josipa Huttlera 4, Osijek', 45.5290, 18.7150);

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
('Uzimate li antibiotike)', TRUE),
('Imate li trenutno povišenu temperaturu ili simptome prehlade ili gripe?', FALSE);