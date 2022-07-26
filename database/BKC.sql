create database BKC
go
use BKC
go

-------------------========CUSTOMERS========-----------------
create table CUSTOMER(
IDCus int primary key not null,
Name nvarchar(100),
Address nvarchar (255),
Tel int
)

-----------------=============SALER===========---------------
create table SALER(
IDSale int primary key not null,
Name nvarchar(255),
Tel int,
Mgrid int not null,
)

alter table SALE
add constraint fk_SALE_SALE
foreign key (mgrid) 
references SALE	(IDSale) 
go

----------============SHIPPER=============----------------------
create table SHIPPER(
IDShipper int primary key not null,
CompanyName nvarchar(255),
Tel int
) 
------------==============ORDERDETAILS==============-------------------------------
create table ORDERDETAILS(
IDProduct int not null,
IDOrder int primary key not null,
Price money,
Discount numeric(4,3),
Quantity int
)
-------------============ORDERS=============-------------------------------
create table ORDERS(
IDOrder int primary key not null,
IDSale int not null,
IDCus int not null,
IDShipper int not null,
ShipPostCode int,
ShipAddress nvarchar(255),
OrderDate Datetime,
ShipDate Datetime,
constraint CHK_ShipDate  check(ShipDate >= OrderDate),
status nvarchar(255)
)
------------========CATEGORIES================-------------------------------
create table CATEGORY(
IDCategory int primary key not null,
CategoryName nvarchar(100),
Description nvarchar(255)
)
-------------=======PRODUCTS=================-------------------------------
create table PRODUCTS(
IDProduct int primary key not null,
IDSupplier int not null,
IDCategory int not null,
ProductName nvarchar (255),
Unit nvarchar(50),
Price int,
Status nvarchar (255)
)
------------===========SUPPLIER===========----------------------------------
create table SUPPLIER(
IDSupplier int primary key not null,
CompanyName nvarchar(100),
Tel int,
Address nvarchar(255),
)
-----------------======Schema===----------------
use BKC
go

create schema orders
go


alter schema product transfer dbo.PRODUCTS
go
alter schema product transfer dbo.CATEGORY
go
alter schema product transfer dbo.SUPPLIER
go

alter schema orders transfer dbo.ORDERS
go
alter schema orders transfer dbo.ORDERDETAILS
go

alter schema account transfer dbo.SHIPPER
go
alter schema account transfer dbo.SALER
go
alter schema account transfer dbo.CUSTOMER
go

------=====Set relationship====-----
alter table account.SALER
add constraint fk_SALER_SALER foreign key (Mgrid) references account.SALER(IDSale) 
go

alter table orders.ORDERDETAILS
add constraint fk_ORDERDETAILS_PRODUCTS foreign key (IDProduct)references product.PRODUCTS(IDProduct),
constraint fk_ORDERDETAILS_ORDERS foreign key (IDOrder) references orders.ORDERS(IDOrder)
go

alter table orders.ORDERS
add constraint fk_ORDERS_SALER foreign key (IDSale) references account.SALER(IDSale),
constraint fk_ORDERS_CUSTOMER foreign key (IDCus) references account.CUSTOMER(IDCus),
constraint fk_ORDERS_SHIPPER foreign key (IDShipper) references account.SHIPPER(IDShipper) 
go

alter table product.PRODUCTS
add constraint fk_PRODUCTS_SUPPLIER foreign key (IDSupplier)references product.SUPPLIER(IDSupplier) ,
constraint fk_PRODUCTS_CATEGORY foreign key (IDCategory) references product.CATEGORY(IDCategory)
go

------=============INSERT=======---------------
use BKC
go
insert BKC.account.CUSTOMER(IDCus, Name, Tel, Address)
values (1, 'Glass store A',01473030779,'97 Thai Ha, Ha Noi') 
insert BKC.account.CUSTOMER(IDCus, Name, Tel, Address)
values (2, 'Furniture installation workshop',0682549970, '1 Hoang Van Thai, Ha Noi') 

insert BKC.account.SALER(IDSale, Name, Tel, Mgrid)
values (1, 'Nguyen Thi Kim Ngoc', 0869350155, 1) 
insert BKC.account.SALER(IDSale, Name, Tel, Mgrid)
values (2, 'Tran Anh Duong', 0964521025, 1) 

insert BKC.account.SHIPPER(IDShipper,CompanyName,Tel)
values (1, 'Giao hang tiet kiem', 0396246031)
insert BKC.account.SHIPPER(IDShipper,CompanyName,Tel)
values (2, 'Giao hang nhanh', 0396241230)
insert BKC.account.SHIPPER(IDShipper,CompanyName,Tel)
values (3, 'Giao hang tiet kiem', 0396246996)
insert BKC.account.SHIPPER(IDShipper,CompanyName,Tel)
values (4, 'Ninja Van', 0396212037)
insert BKC.account.SHIPPER(IDShipper,CompanyName,Tel)
values (5, 'VNPost', 0396240671)

insert BKC.orders.ORDERS(IDOrder, IDSale, IDCus, IDShipper, ShipPostCode, OrderDate, ShipDate, ShipAddress, Status)
values (1, 1, 1, 5, 001, '2022-03-13 00:00:00:00', '2022-03-15 00:00:00:00',  '68 Tran Nhan Tong, Ha Noi', 'Delivery successful')
insert BKC.orders.ORDERS(IDOrder, IDSale, IDCus, IDShipper, ShipPostCode, OrderDate, ShipDate, ShipAddress, Status)
values (2, 1, 2, 3, 001, '2022-04-13 00:00:00:00', '2022-04-20 00:00:00:00',  'Lanmark81 Building, Ho Chi Minh', 'Waiting for delivery')

insert BKC.product.CATEGORY(IDCategory,CategoryName,Description)
values (1, 'Window', 'Window')
insert BKC.product.CATEGORY(IDCategory,CategoryName,Description)
values (2, 'Door', 'Door')

insert BKC.product.PRODUCTS(IDProduct, ProductName, Unit, Price, IDSupplier, IDCategory, Status)
values (1, 'Tempered glass door 500', 'square metre', '300000', 2, 2, 'In stocks')
insert BKC.product.PRODUCTS(IDProduct, ProductName, Unit, Price, IDSupplier, IDCategory, Status)
values (2, 'Tempered glass door 1000', 'square metre', '1000000', 1, 2, 'In stocks')
insert BKC.product.PRODUCTS(IDProduct, ProductName, Unit, Price, IDSupplier, IDCategory, Status)
values (3, 'Wood grain iron door', 'pcs', '300000', 3, 2, 'Coming soon')
insert BKC.product.PRODUCTS(IDProduct, ProductName, Unit, Price, IDSupplier, IDCategory, Status)
values (4, 'Floral window', 'pcs', '145000', 3, 1, 'In stocks')

insert BKC.product.SUPPLIER(IDSupplier, CompanyName, Tel, Address)
values (1, 'CDI Viet Nam', 0396123666, 'Dong Da, Ha Noi')
insert BKC.product.SUPPLIER(IDSupplier, CompanyName, Tel, Address)
values (2, 'ABS Ha Noi', 0323648200, 'Ba Binh, Ha Noi')
insert BKC.product.SUPPLIER(IDSupplier, CompanyName, Tel, Address)
values (3, 'Cat Ba Glass', 0921520064, 'Cat Ba, Hai Phong')

insert BKC.orders.ORDERDETAILS(IDProduct, IDOrder, Price, Quantity, Discount)
values (1, 2, 300000, 30, 0.1)
insert BKC.orders.ORDERDETAILS(IDProduct, IDOrder, Price, Quantity, Discount)
values (4, 1, 150000, 50, 0.25)

--------------------------------
select * from account.CUSTOMER
go
select Name from account.CUSTOMER
go
select IDCus, Tel as inf from account.CUSTOMER
go

select * from account.SALER
go
select * from account.SALER
where IDSale=2
go
select S.Name, S.Tel from account.SALER as S
where Mgrid=1
go

Select * from orders.ORDERS
go
select * from orders.ORDERS
where OrderDate between '2022-03-01 00:00:00.000' and '2022-04-01 00:00:00.000' 
go

select * from account.SHIPPER
go
declare @shipid int
set @shipid = 3
select * from account.SHIPPER
where IDShipper = @shipid
go

Select * from orders.ORDERDETAILS
go
select SUM(Quantity)as SumQtt from orders.ORDERDETAILS 
go

select * from product.PRODUCTS
go
select MAX (product.PRODUCTS.Price) as MaxPri
from product.PRODUCTS
go
select IDProduct, ProductName , Price
from product.PRODUCTS
where product.PRODUCTS.Price =  (select Min (product.PRODUCTS.Price ) from product.PRODUCTS)
go

select * from product.SUPPLIER
go
select * from product.SUPPLIER
where product.SUPPLIER.Address like '%Ha Noi'
go

select * from product.CATEGORY
go


select * from product.PRODUCTS
go

update product.PRODUCTS set Price = 165000 where IDProduct=4

select * from product.PRODUCTS
go


select * from account.SHIPPER
go
delete from account.SHIPPER where CompanyName='Giao hang nhanh'
select * from account.SHIPPER
go

select * from orders.ORDERS
go
select * from account.SALER
go

select O.IDSale,S.Name, S.Tel, O.status
from account.SALER as S inner join orders.ORDERS as O
on S.IDSale= O.IDSale
go

select * from account.SHIPPER
go
select * from orders.ORDERS
go
select *
from orders.ORDERS as O left join account.SHIPPER as S
on S.IDShipper = O.IDShipper
go