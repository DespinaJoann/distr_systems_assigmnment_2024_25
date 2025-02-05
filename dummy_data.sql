-- Insert Unique Admin
insert into admin (username, password, email, first_name, last_name, role) values ('deva', 'dev123!!', 'devmain@gmail.com', 'Despina - Vasiliki', 'Chalkiadaki - Koutsi', 'ADMIN');

-- Insert Volunteers
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('alan', '123abc!!?', 'alanHog@gmail.com', 'Alan', 'Hog', '6923108835', '1996-02-19',
        'I want to actively work to make the world a better place', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('emma', 'em@123!!', 'emmaGreen@gmail.com', 'Emma', 'Green', '6987654321', '1993-08-15',
        'Passionate about environmental conservation and social impact.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('john', 'j0hn@123!!', 'johnSmith@gmail.com', 'John', 'Smith', '6954301234', '1989-11-02',
        'Eager to contribute to community development and youth education.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('maria', 'm4r1@Volunteer', 'mariaLopez@gmail.com', 'Maria', 'Lopez', '6920101023', '1997-06-30',
        'I enjoy helping in refugee support programs and community-based projects.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('alex', 'alex123!!@', 'alexParker@gmail.com', 'Alex', 'Parker', '6986738492', '1994-03-12',
        'Tech enthusiast passionate about using innovation to address global challenges.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('olga', '0lga@volunteer', 'olga.kovacs@gmail.com', 'Olga', 'Kovacs', '0623456789', '1994-07-22',
        'Volunteer coordinator in Hungary, passionate about helping displaced families and supporting community building.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('peter', 'p3ter@help', 'peter.fischer@t-online.de', 'Peter', 'Fischer', '017612345678', '1986-04-13',
        'Active volunteer in Germany, focusing on sustainability projects and youth education.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('lucie', 'luci3@123', 'lucie.martin@orange.fr', 'Lucie', 'Martin', '0634567891', '1991-12-02',
        'French volunteer working with underprivileged children and youth outreach programs.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('daniel', 'dan!el2025', 'daniel@rsmith.uk', 'Daniel', 'Smith', '07798765432', '1987-03-28',
        'British volunteer, focused on international relief work, particularly in disaster-stricken regions.', 'VOLUNTEER', 'pending');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description, role, account_status)
values ('sara', 'sar@volunteer1', 'sara.sanchez@gmail.com', 'Sara', 'Sanchez', '0034956123456', '1992-05-19',
        'Spanish volunteer, passionate about environmental protection and raising awareness about climate change.', 'VOLUNTEER', 'pending');


-- Insert Organizations
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('betterWorld22', 'b3t3r4all!!', 'better.world@gmail.org', 'Better World', '6987672345', '1, D. Gounari Street 151 24 Marousi', 'Marousi, Attica', 'non-profit',
        'We focus on promoting environmental sustainability and humanitarian efforts.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('greenEarth', 'gr33n@earth!!', 'contact@greenearth.org', 'Green Earth', '6976543210', '10, Kifisias Avenue, 10564 Athens', 'Athens, Greece', 'non-profit',
        'Committed to creating awareness and taking action for environmental preservation.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('helpingHands', 'help1ng@h4nds', 'info@helpinghands.org', 'Helping Hands', '6948005500', '30, Ermou Street, 10672 Athens', 'Athens, Greece', 'NGO',
        'We offer support to marginalized communities and provide resources to improve education.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('ecoFuture', 'ec0future!!', 'contact@ecofuture.org', 'EcoFuture', '0647654321', '7, Via Dante, 20121 Milan', 'Milan, Italy', 'NGO',
        'Our mission is to promote sustainable living practices and renewable energy solutions throughout Europe.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('food4all', 'f00d4all!!', 'info@food4all.org', 'Food For All', '04987654321', '34, Rue de Rivoli, 75001 Paris', 'Paris, France', 'NGO',
        'We provide food and essential supplies to homeless people and those in need across Europe.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('worldHealth', 'wh3alth2025', 'contact@worldhealth.org', 'World Health Initiative', '003514987654', '3, Grafton Street, Dublin 2', 'Dublin, Ireland', 'NGO',
        'Working globally to improve health and provide medical aid in underserved areas.', 'ORGANIZATION', 'pending');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description, role, account_status)
values ('climateAction', 'clim@act1on', 'climateaction@uk.org', 'Climate Action UK', '00447890876543', '5, Oxford Street, London', 'London, UK', 'NGO',
        'A leading organization focused on raising awareness and promoting legislative action on climate change.', 'ORGANIZATION', 'pending');


-- Insert Events
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Second Attica Tree Planting Event', 'A community event focused on planting trees to combat deforestation in the region.',
        'Parnasos, Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Beach Cleanup Campaign', 'Join us for a beach cleanup to reduce pollution and protect marine life.',
        'Vouliagmeni Beach, Athens', 'Environmental Cleanup', '2025-04-15', 0, 2, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Youth Education Support', 'Help teach basic skills and provide mentorship to underprivileged youth in local communities.',
        'Nea Smyrni, Athens', 'Youth Education', '2025-05-05', 0, 1, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Food Donation Drive', 'Help gather and distribute food to families in need across Athens.',
        'Central Athens', 'Charity, Food Distribution', '2025-06-10', 0, 3, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Clean Energy Awareness', 'Promoting clean energy solutions and sustainable practices through community workshops and events.',
        'Kifisia, Athens', 'Sustainability, Clean Energy', '2025-07-15', 0, 2, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Health and Wellness Fair', 'A day focused on health awareness and wellness programs for local communities.',
        'Marousi, Athens', 'Health, Wellness', '2025-08-20', 0, 1, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Animal Rights Awareness', 'An event to raise awareness about animal rights and promote adoption programs.',
        'Athens Zoo', 'Animal Welfare', '2025-09-10', 0, 3, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Water Conservation Workshop', 'A workshop on water-saving techniques for households and local businesses.',
        'Athens City Hall', 'Water Conservation', '2025-10-01', 0, 2, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Community Gardening Day', 'Join us for a community gardening project to beautify urban spaces and promote local agriculture.',
        'Piraeus, Athens', 'Community Gardening', '2025-11-05', 0, 1, 'pending');
insert into event (name, description, location, topic, date, number_of_volunteers, organization_id, status)
values ('Climate Change Awareness March', 'A march to raise awareness about the global climate crisis and encourage eco-friendly practices.',
        'Syntagma Square, Athens', 'Climate Change', '2025-12-01', 0, 2, 'pending');