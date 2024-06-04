insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030'),
                   (2, 'zz', 'yy', 'city', '62-030'),
                   (3, 'xx', 'yy', 'city', '62-030');

insert into doctor (id, first_name, last_name, specialization, telephone_number, email, doctor_number)
            values (1, 'Jan', 'Kowalski', 'SURGEON', '1-123456789', 'doc1@example.com', '1'),
                   (2, 'Jan', 'Nowak', 'OCULIST', '2-123456789', 'doc2@example.com', '2'),
                   (3, 'Jan', 'Kowalski', 'SURGEON', '3-123456789', 'doc3@example.com', '3'),
                   (4, 'Jan', 'Beton', 'SURGEON', '14-123456789', 'doc4@example.com', '4'),
                   (5, 'Jan', 'Krzywy', 'OCULIST', '5-123456789', 'doc5@example.com', '5'),
                   (6, 'Jan', 'Wojak', 'SURGEON', '6-123456789', 'doc6@example.com', '6'),
                   (7, 'Jan', 'Cygwin', 'SURGEON', '7-123456789', 'doc7@example.com', '7'),
                   (8, 'Jan', 'Terefere', 'OCULIST', '8-123456789', 'doc8@example.com', '8'),
                   (9, 'Jan', 'Kuku', 'SURGEON', '4-423456789', 'doc9@example.com', '9');

insert into doctor_to_address (doctor_id, address_id)
            values (1, 1), (2, 3), (3, 1), (4, 2), (5, 2);

insert into patient (id, first_name, last_name, telephone_number, email, date_of_birth, patient_number)
            values (1, 'Zbigniew', 'Kowalski', '3-123456789', 'qYUeh2@example.com', '2021-01-01', '123456788'),
                   (2, 'Krzysio', 'Nowak', '4-123456789', 'Nowak3@example.com', '2021-01-01', '123456789'),
                   (3, 'Kajetan', 'Beton', '5-523456789', 'Beton4@example.com', '2021-01-01', '123456780'),
                   (4, 'Benek', 'Bobo', '5-223456789', 'Benek.Bobo@example.com', '2021-01-01', '123456781');

insert into patient_to_address (patient_id, address_id)
            values (1, 1), (2, 2), (3, 2), (4, 1);

insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'description1', '2021-01-01 12:00:00', 1, 1),
                   (2, 'description2', '2021-11-01 13:00:00', 1, 2),
                   (3, 'description3', '2021-11-01 14:00:00', 3, 2),
                   (4, 'description4', '2021-11-01 15:00:00', 3, 3),
                   (5, 'description5', '2021-01-01 16:00:00', 4, 1),
                   (6, 'description6', '2021-11-01 17:00:00', 5, 2),
                   (7, 'description7', '2021-11-01 18:00:00', 6, 2),
                   (8, 'description8', '2021-11-02 19:00:00', 6, 3),
                   (9, 'description9', '2021-01-03 20:00:00', 7, 1),
                   (10, 'description10', '2021-11-04 12:00:00', 5, 2),
                   (11, 'description11', '2021-11-05 12:00:00', 7, 4),
                   (12, 'description12', '2021-11-06 12:00:00', 5, 3),
                   (13, 'description13', '2021-01-07 12:00:00', 3, 1),
                   (14, 'description14', '2021-11-08 12:00:00', 5, 2),
                   (15, 'description15', '2021-11-09 12:00:00', 4, 2),
                   (16, 'description16', '2021-11-10 12:00:00', 3, 3);

insert into medical_treatment (id, description, type, visit_id)
            values (1, 'description', 'EKG', 1),
                   (2, 'description', 'USG', 2),
                   (3, 'description', 'RTG', 2),
                   (4, 'description', 'RTG', 3),
                   (5, 'description', 'EKG', 4),
                   (6, 'description', 'USG', 5),
                   (7, 'description', 'RTG', 6),
                   (8, 'description', 'RTG', 7),
                   (9, 'description', 'EKG', 8),
                   (10, 'description', 'USG', 8),
                   (11, 'description', 'RTG', 9),
                   (12, 'description', 'RTG', 10),
                   (13, 'description', 'EKG', 11),
                   (14, 'description', 'USG', 12),
                   (15, 'description', 'RTG', 13),
                   (16, 'description', 'RTG', 14);

