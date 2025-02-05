
-- Insert Dummy Data

-- Id is generated with IDENTITY strategy automatically in spring boot
-- Insert Volunteers
insert into volunteer  (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
          'I want to actively work to make the world a better place');
-- Change the info accordingly to create new Volunteers
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');
insert into volunteer (username, password, email, first_name, last_name, phone_number, date_of_birth, profile_description
) values ('alan', '123abc!!?','alanHog@gmail.com','Alan','Hog','6923108835', '1996-02-19'
    'I want to actively work to make the world a better place');

-- Insert Organizations
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
-- Change the info accordingly to create new Organizations
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');
insert into organization (username, password, email, org_name, phone_number, address, location, org_type, profile_description
) values ('betterWorld22', 'b3t3r4all!!','better.world@gmail.org','Better World','6987672345',
          '1, D. Gounari Street 151 24 Marousi','Marousi, Attica', 'non-profit',
          'I want to actively work to make the world a better place');

-- Insert Events
-- number of volunteers is initially 0;
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
-- Change the info accordingly to create new Events
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
insert into event (name, desrciption, location, topic, date, number_of_volunteers, organization_id)
values ('Second Attica Tree Planting Event', ' ... ', 'Parnasos Attica, Greece', 'Tree Planting', '2025-03-22', 0, 1);
