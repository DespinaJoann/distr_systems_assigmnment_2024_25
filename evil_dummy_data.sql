-- insert admins > 1
INSERT INTO admin (email, is_logged_in, password, username, account_key, first_name, last_name)
VALUES
-- Attempt to enter second Admin (should fail)
('admn2@example.com', FALSE, 'pswd2', 'admn2', 'k2', 'secondAdmin', 'secAdmin')

-- insert organizations
INSERT INTO organization (email, is_logged_in, password, username, account_status, address, location, org_name, org_type, phone_number, profile_description, role)
VALUES
-- Organization 5: Organization with bad content in the description (should be rejected)
('contact@darkPractices.gr', FALSE, 'pswd2', 'dark practices', 'Pending', 'Black Street 32', 'Thessaloniki', 'Dark Practices', 'Commercial', '2310123456', 'Organization that supports illegal activities and violence.', 'ORGANIZATION'),

-- Organization 6: Organization without location (should be rejected)
('support@help.gr', FALSE, 'pswd3', 'help', 'Pending', 'Hope Street 5', NULL, 'Help for Everyone', 'Non-Profit', '2107654321', 'Small organization that offers assistance to vulnerable groups, but without a declared location.','ORGANIZATION'),

-- Organization 7: Organization with duplicate name (should be rejected as duplicate)
('duplicate@cleanCity.gr', FALSE, 'pswd5', 'duplicateCleanCity', 'Pending', 'Recycling Street 3', 'Larisa', 'Clean City', 'Non-Profit', '2610123456', 'Duplicate registration for "Clean City','ORGANIZATION'),

-- Organization 8: Organization with no address (should be rejected)
('info@missingAddress.gr', FALSE, 'pswd7', 'missingAddress', 'Pending', NULL, 'Sparti', 'Missing Address Org', 'Non-Profit', '2610234567', 'Organization without a valid address.','ORGANIZATION'),

-- Organization 9: Organization with duplicate username (should be rejected)
('contact@duplicateUser.gr', FALSE, 'pswd8', 'cleanCity', 'Pending', 'Clean Street 9', 'Ioannina', 'Clean Planet', 'Environmental', '2651076543', 'Duplicate username with "Clean City".','ORGANIZATION'),

-- Organization 10: Organization with bad content (should be rejected)
('contact@badOrg.gr', FALSE, 'pswd10', 'badOrg', 'Pending', 'Dark Alley 13', 'Halkida', 'Dark Org', 'Non-Profit', '2101239876', 'Promotes unethical and illegal practices.','ORGANIZATION');

-- insert volunteers
INSERT INTO volunteer (email, is_logged_in, password, username, account_status, date_of_birth, first_name, last_name, phone_number, profile_description, has_checked_in, role)
VALUES
-- Volunteer 5: Volunteer under the age limit (must be rejected)
('giorgos.papadopoulos@gmail.com', FALSE, 'pswd2', 'giorgosP', 'Pending', '2010-05-20', 'Giorgos', 'Papadopoulos', '6949876543', 'I look forward to helping with local community activities.', FALSE, 'VOLUNTEER'),

-- Volunteer 6: Volunteer with bad content in the description (should be rejected)
('nikos.kostopouloa@gmail.com', FALSE, 'pswd3', 'nikosK', 'Pending', '1985-09-12', 'Nikos', 'Kostoloulos', '6934567890', 'I like sex & alcohol.', FALSE, 'VOLUNTEER'),

-- Volunteer 7: Volunteer with incomplete description (should be rejected)
('kostas.amanatidis@gmail.com', FALSE, 'pswd5', 'kostasA', 'Pending', '1988-06-30', 'Kostas', 'Amanatidis', '6923456789', NULL, FALSE, 'VOLUNTEER'),

-- Volunteer 8: Volunteer with blank username (should be rejected)
('giannis.blank@gmail.com', FALSE, 'pswd7', NULL, 'Pending', '1985-07-21', 'Giannis', 'Blank', '6909876543', 'Enthusiastic volunteer with no username.', FALSE, 'VOLUNTEER'),

-- Volunteer 9: Volunteer with duplicate email (should be rejected)
('maria.karagianni@gmail.com', FALSE, 'pswd8', 'mariaDuplicate', 'Pending', '1990-03-15', 'Maria', 'Duplicate', '6941234567', 'Duplicate entry.', FALSE, 'VOLUNTEER'),

-- Volunteer 10: Volunteer with inappropriate profile description (should be rejected)
('john.bad@gmail.com', FALSE, 'pswd10', 'johnB', 'Pending', '1988-01-01', 'John', 'Bad', '6951234567', 'Promotes violence and inappropriate behavior.', FALSE, 'VOLUNTEER');

-- insert events
INSERT INTO event (date, description, location, max_num_of_volunteers, name, organization_id, status, topic)
VALUES
-- Event 5: Event without description (should be rejected)
('2025-02-25', NULL, 'Parnassos', 30, 'Tree Planting', 2, 'Pending', 'Sustainability'),

-- Event 6: Event with bad content in the description (should be rejected)
('2025-08-09', 'illegal activities and weapon distribution.', 'Athens', 100, 'Illegal Event', 3, 'Pending', 'Crime'),

-- Event 7: Event with too many volunteers (should be reviewed for feasibility)
('2025-12-10', 'Tree planting in Parnitha National Park.', 'Parnitha', 500, 'Tree Planting Marathon', 4, 'Pending', 'Sustainability'),

-- Event 8: Event without a name (should be rejected)
('2025-11-25', 'Organize a charity run for children.', 'Trikala', 150, NULL, 3, 'Pending', 'Health');

-- Event 9: Event with inappropriate description (should be rejected)
('2025-11-05', 'Illegal gambling activity to raise funds.', 'Mykonos', 50, 'Gambling for Good', 2, 'Pending', 'Community'),

-- Event 10: Event with too many volunteers for a small action (needs to be reviewed)
('2025-07-30', 'Clean the park of a small neighborhood.', 'Patmos', 300, 'Neighborhood Park Cleanup', 3, 'Pending', 'Environment');


-- insert participations
INSERT INTO participation (date, event_id, organization_id, volunteer_id, status)
VALUES
-- Participation 3: Volunteer rejected due to inappropriate profile
('2025-05-20 16:00:00', 3, 3, 3, 'Rejected'),

-- Participation 5: Volunteer with double participation in the same event (must be rejected)
('2025-07-01 12:00:00', 1, 1, 1, 'Rejected'),

-- Participation 7: Volunteer with duplicate participation in the same event (should be rejected)
('2025-09-15 09:00:00', 6, 1, 6, 'Rejected'),

-- Participation 8: Volunteer under age limit participating (should be rejected)
('2025-12-10 10:00:00', 7, 2, 7, 'Rejected'),

-- Participation 10: Participation in a banned event (should be rejected)
('2025-11-05 15:00:00', 8, 2, 10, 'Rejected');
