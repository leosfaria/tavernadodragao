INSERT INTO User (id, email, password, confirmPassword, username) values (1, 'leonardo@gmail.com', '123', '123', 'Leonardo Serra');
INSERT INTO User (id, email, password, confirmPassword, username) values (2, 'raphael@gmail.com', '123', '123' , 'Leo Moura');
INSERT INTO User (id, email, password, confirmPassword, username) values (3, 'marcelo@gmail.com', '123', '123' , 'Marcelo Bap');
INSERT INTO User (id, email, password, confirmPassword, username) values (4, 'asd', 'asd', 'asd', 'asd');
INSERT INTO User_Friends (User_ID, Friend_ID) values (1, 4);
INSERT INTO User_Friends (User_ID, Friend_ID) values (4, 1);
INSERT INTO User_Friends (User_ID, Friend_ID) values (2, 4);
INSERT INTO User_Friends (User_ID, Friend_ID) values (4, 2);