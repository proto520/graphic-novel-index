
    alter table COMICS 
       drop constraint FKh45ccyb835gxp1kg2w5lacyvs;

    drop table if exists COMICS cascade;

    drop table if exists SERIES cascade;

    create table COMICS (
       ID uuid not null,
        CREATION_TS bytea,
        UPDATE_TS bytea,
        RELEASE_MONTH int4,
        RELEASE_YEAR int4,
        NAME varchar(255),
        SERIES_ID uuid,
        primary key (ID)
    );

    create table SERIES (
       ID uuid not null,
        CREATION_TS bytea,
        UPDATE_TS bytea,
        NAME varchar(255),
        primary key (ID)
    );

    alter table COMICS 
       add constraint FKh45ccyb835gxp1kg2w5lacyvs 
       foreign key (SERIES_ID) 
       references SERIES;
