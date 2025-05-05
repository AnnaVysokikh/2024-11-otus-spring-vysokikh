insert into clients(name)
values ('Ivan Sidorov'), ('Maksim Kozlov');

insert into accounts(number, balance, client_id)
values('3332323232323', 1000.0, 1),
('32323232323', 3000.0, 2);

insert into transfers(account_id_from, account_id_to, amount)
values (1, 2, 100.0);
