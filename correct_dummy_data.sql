-- insert admins 
INSERT INTO admin (email, is_logged_in, password, username, account_key, first_name, last_name)
VALUES
-- Creation of the only allowed Admin
('admn1@example.com', TRUE, 'pswd1', 'admn1', 'k1', 'uniqueAdmin', 'Admin')

-- insert organizations
INSERT INTO organization (email, is_logged_in, password, username, account_status, address, location, org_name, org_type, phone_number, profile_description, role)
VALUES
-- Organization 1: Valid organization (must be approved, role: 'Admin')
('info@cleanCity.gr', FALSE, 'pswd1', 'cleanCity', 'Pending', 'Eleftherias Street 15', 'Athens', 'clean City', 'Non-Profit', '2101234567', 'Organization that promotes recycling and cleanliness in public spaces.', 'Admin' ),

-- Organization 2: Valid environmental organization (must be approved)
('info@greenGreece.gr', FALSE, 'pswd4', 'greenGreece', 'Pending', 'Forest Street 76', 'Heraclion', 'Green Greece', 'Environmental', '2810123456', 'Environmental organization aiming to raise awareness about climate change,'ORGANIZATION'),

-- Organization 3: Valid health-related organization (must be approved)
('info@childHealth.gr', FALSE, 'pswd6', 'childHealth', 'Pending', 'Health Street 5', 'Athens', 'Child Health Foundation', 'Non-Profit', '2109876543', 'Supports children’s health initiatives.','ORGANIZATION'),

-- Organization 4: Valid commercial organization (must be approved)
('info@smartTech.gr', FALSE, 'pswd9', 'smartTech', 'Pending', 'Tech Park 7', 'Volos', 'Smart Tech Solutions', 'Commercial', '2105432198', 'Tech company supporting sustainability through innovation.','ORGANIZATION'),

-- insert volunteers
INSERT INTO volunteer (email, is_logged_in, password, username, account_status, date_of_birth, first_name, last_name, phone_number, profile_description, has_checked_in, role)
VALUES
-- Volunteer 1: Valid volunteer (must be approved)
('maria.karagianni@gmail.com', FALSE, 'pswd1', 'mariaK', 'Pending', '1990-03-15', 'Maria', 'Karagianni', '6941234567', 'Volunteer with a passion for environmental protection.', FALSE, 'VOLUNTEER'),

-- Volunteer 2: Valid volunteer with a focus on education (must be approved)
('eleni.dimitriou@gmail.com', FALSE, 'pswd4', 'eleniD', 'Pending', '1995-01-25', 'Eleni', 'Dimitriou', '6951230987', 'I am interested in educational activities and seminars..', FALSE, 'VOLUNTEER'),

-- Volunteer 3: Valid volunteer interested in sustainability (must be approved)
('sophia.markou@gmail.com', FALSE, 'pswd6', 'sophiaM', 'Pending', '1992-11-30', 'Sophia', 'Markou', '6932145678', 'Passionate about environmental sustainability.', FALSE, 'VOLUNTEER'),

-- Volunteer 4: Valid volunteer focused on children (must be approved)
('nikos.kids@gmail.com', FALSE, 'pswd9', 'nikosK', 'Pending', '1980-12-10', 'Nikos', 'Kids', '6945678910', 'Volunteering to support children’s education.', FALSE, 'VOLUNTEER'),

-- insert events
INSERT INTO event (date, description, location, max_num_of_volunteers, name, organization_id, status, topic)
VALUES
-- Event 1: Valid event awaiting approval
('2025-05-01', 'Beach cleanup in Alonissos to help preserve marine life.', 'Alonissos', 50, 'Beach Cleanup', 1, 'Pending', 'Environment'),

-- Event 2: Event with a shortage of max_num_of_volunteers (must be approved)
('2025-01-30', 'Distribute food to homeless individuals in .', 'Thessaloniki', 10, 'Food Distribution', 1, 'Pending', 'Community'),

-- Event 3: Event with valid data (must be approved)
('2025-02-28', 'Organize a blood donation drive for local hospitals.', 'Tempi', 200, 'Blood Donation', 2, 'Pending', 'Health'),

-- Event 4: Valid event in Crete (must be approved)
('2025-10-20', 'Organize educational workshops for local students.', 'Hania', 30, 'Education Workshop', 4, 'Pending', 'Education'),

-- insert participations
INSERT INTO participation (date, event_id, organization_id, volunteer_id, status)
VALUES
-- Participation 1: Valid participation in a cleaning action
('2025-03-01 19:00:00', 1, 1, 1, 'Pending'),

-- Participation 2: Volunteer participating in environmental action (approved)
('2025-04-15 07:00:00', 4, 2, 4, 'Approved'),

-- Participation 3: Participation in training activity (pending)
('2025-06-10 11:00:00', 4, 4, 2, 'Pending'),

-- Participation 4: Valid participation in an urban cleanup
('2025-09-15 08:00:00', 6, 1, 6, 'Pending'),

-- Participation 5: Valid participation in an educational workshop
('2025-10-20 10:00:00', 9, 4, 9, 'Pending'),

