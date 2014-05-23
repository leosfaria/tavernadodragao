INSERT INTO User (id, email, password, confirmPassword, username, avatarImgPath) values (2, 'teste2@teste.com', '123', '123' , 'Teste 2', '../resources/css/images/yourImageDefault.jpg');
INSERT INTO User_Requests (user_id, friend_id) values (1, 2);
COMMIT;