create table if not exists Cars
(
    id            bigint unsigned auto_increment
    primary key,
    licence_plate varchar(50) not null,
    price         double      not null,
    EntryDate     date        null,
    Brand         varchar(30) not null,
    OriginCountry varchar(50) not null,
    Model         varchar(50) not null,
    Sold          tinyint(1)  not null,
    constraint id
    unique (id)
    );

create table if not exists Company
(
    id      int auto_increment
    primary key,
    Name    varchar(50) not null,
    Address varchar(50) not null,
    nbOrder mediumtext  not null,
    Siret   varchar(30) not null,
    constraint Company_id_uindex
    unique (id)
    );

create table if not exists CountryTaxes
(
    Country_Code varchar(10) not null
    primary key,
    Taxe_Rate    double      not null,
    constraint CountryTaxes_Country_id_uindex
    unique (Country_Code)
    );

create table if not exists Particular
(
    id      int auto_increment
    primary key,
    Name    varchar(50) not null,
    Address varchar(50) not null,
    nbOrder mediumtext  not null,
    constraint Particular_id_uindex
    unique (id)
    );

create table if not exists Sales
(
    Buyer          int         not null,
    BroughtVehicle int         not null,
    Status         varchar(15) not null,
    Payed          tinyint(1)  not null
    );

create table if not exists Scooters
(
    id            bigint unsigned auto_increment
    primary key,
    licence_plate varchar(50) not null,
    price         double      not null,
    EntryDate     date        null,
    Brand         varchar(30) null,
    OriginCountry varchar(50) null,
    Model         varchar(50) null,
    Sold          tinyint(1)  not null,
    constraint Scooter_id_uindex
    unique (id)
    );

