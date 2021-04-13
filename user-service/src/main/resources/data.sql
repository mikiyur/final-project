insert into users(user_name, password, balance, secret_key)
values ('Yurii', '111111', 2000, ''),
      ('Yura', '222222', 2000, ''),
      ('Eugen', '333333', 2000, ''),
      ('Donald', 'Trump', 2000, ''),
      ('Ben', 'White', 2000, '');

insert into items( user_id, tour_id, registered_at)
values  (1,2,'2021-05-01 10:30:30'),
        (1,3,'2021-05-02 10:30:30'),
        (1,5,'2021-05-03 10:30:30'),
        (2,7,'2021-05-04 10:30:30'),
        (2,9,'2021-05-05 10:30:30');