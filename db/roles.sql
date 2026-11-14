-- krvnaveza_app -> backend: citanje/pisanje, nema brisanja povijest
-- krvnaveza_readonly -> statistika, pogledi i nepovjerljive tablice

DO $$ BEGIN
    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'krvnaveza_app') THEN
    CREATE ROLE krvnaveza_app LOGIN; 
    END IF;

    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'krvnaveza_readonly') THEN
    CREATE ROLE krvnaveza_readonly LOGIN; 
    END IF;
END $$;

-- privremena lozinka
ALTER ROLE krvnaveza_app WITH LOGIN PASSWORD '4'
    NOSUPERUSER NOCREATEDB NOCREATEROLE NOREPLICATION;
ALTER ROLE krvnaveza_readonly WITH LOGIN PASSWORD '4'
    NOSUPERUSER NOCREATEDB NOCREATEROLE NOREPLICATION;

REVOKE CREATE ON SCHEMA public FROM PUBLIC;
GRANT CONNECT ON DATABASE krvnaveza TO krvnaveza_app, krvnaveza_readonly;
GRANT USAGE ON SCHEMA public TO krvnaveza_app, krvnaveza_readonly;

REVOKE ALL ON ALL TABLES IN SCHEMA public FROM krvnaveza_app, krvnaveza_readonly;
REVOKE ALL ON ALL SEQUENCES IN SCHEMA public FROM krvnaveza_app, krvnaveza_readonly;

GRANT SELECT, INSERT, UPDATE, DELETE ON
    Center, CenterWorktime, CenterAppointmentSlot, CenterBloodThreshold, Staff, Organizer, RoleRequest, FieldAction, TraumaTeam,
    SelfAssesmentQuestion, SelfAssessment, SelfAssessmentAnswer, Notification
TO krvnaveza_app;

GRANT SELECT, INSERT, UPDATE ON
    AppUser, Donor, Appointment, BloodDose, DonorDeferral, UrgentCall, UrgentCallRecipientLog
TO krvnaveza_app;

GRANT SELECT, INSERT ON 
    SensitiveAccessLog,DonorBadge, StockForecast
TO krvnaveza_app;

GRANT SELECT ON Constants, BloodTypeRef TO krvnaveza_app;
GRANT UPDATE (ConstValue) ON Constants TO krvnaveza_app;

GRANT SELECT ON 
    CenterBloodStock, BloodStockTotal, StockForecast, UrgentCallRecipientLog, ActionStats
TO krvnaveza_app;

GRANT SELECT ON 
Constants, BloodTypeRef, Center, CenterWorktime, CenterAppointmentSlot, CenterBloodThreshold,
FieldAction, BloodStockLog, UrgentCallRecipientLog, BloodStockLog, BloodStockTotal, StockForecast, ActionStats
TO krvnaveza_readonly;
