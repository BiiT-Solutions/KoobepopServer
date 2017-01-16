
    create table brands (
        ID bigint not null,
        comparationId varchar(190) not null,
        createdBy DOUBLE,
        creationTime datetime not null,
        updateTime datetime,
        updatedBy DOUBLE,
        name varchar(255),
        company_ID bigint not null,
        primary key (ID)
    );

    create table companies (
        ID bigint not null,
        comparationId varchar(190) not null,
        createdBy DOUBLE,
        creationTime datetime not null,
        updateTime datetime,
        updatedBy DOUBLE,
        country varchar(255),
        description longtext,
        name varchar(255) not null,
        primary key (ID)
    );

    create table contacts (
        ID bigint not null,
        comparationId varchar(190) not null,
        createdBy DOUBLE,
        creationTime datetime not null,
        updateTime datetime,
        updatedBy DOUBLE,
        mail varchar(255),
        name varchar(255),
        phone varchar(255),
        company_ID bigint not null,
        primary key (ID)
    );

    create table products (
        ID bigint not null,
        comparationId varchar(190) not null,
        createdBy DOUBLE,
        creationTime datetime not null,
        updateTime datetime,
        updatedBy DOUBLE,
        name varchar(255),
        company_ID bigint not null,
        primary key (ID)
    );

    create table services (
        ID bigint not null,
        comparationId varchar(190) not null,
        createdBy DOUBLE,
        creationTime datetime not null,
        updateTime datetime,
        updatedBy DOUBLE,
        name varchar(255),
        company_ID bigint not null,
        primary key (ID)
    );

    alter table brands 
        add constraint UK_63cl4vrv0l1u5olpfgu4boabr  unique (ID);

    alter table brands 
        add constraint UK_nyy90xnpja6key6186cdkodx9  unique (comparationId);

    alter table companies 
        add constraint UK_oywkn5b5ix8q5chvig3rcujkf  unique (ID);

    alter table companies 
        add constraint UK_mys0v4ijceuwyo36n3wxi1jjt  unique (comparationId);

    alter table contacts 
        add constraint UK_c3xmupshnkpc9x0rvqor3utjw  unique (ID);

    alter table contacts 
        add constraint UK_eto4kt1yn6ohcac5neprakxj2  unique (comparationId);

    alter table products 
        add constraint UK_38cdqoaf39eu3mubej9y1kr8i  unique (ID);

    alter table products 
        add constraint UK_5yty196mx2pywru86c9bmr3lq  unique (comparationId);

    alter table services 
        add constraint UK_lw5399jcp6u2ss4ntighdup8a  unique (ID);

    alter table services 
        add constraint UK_mevai8xr6q7yniqks4vged8gq  unique (comparationId);

    alter table brands 
        add constraint FK_ojghuhhbgv12j6sgbcl7wi7ln 
        foreign key (company_ID) 
        references companies (ID);

    alter table contacts 
        add constraint FK_la6e2ulihn5cijqhorithgtu 
        foreign key (company_ID) 
        references companies (ID);

    alter table products 
        add constraint FK_bgfrjxn7gki1n722muivoa7lp 
        foreign key (company_ID) 
        references companies (ID);

    alter table services 
        add constraint FK_bfrs1k3lhppe2rvwgjapxqrrv 
        foreign key (company_ID) 
        references companies (ID);

	CREATE TABLE `hibernate_sequence` (
		`next_val` bigint(20) DEFAULT NULL
	);

	LOCK TABLES `hibernate_sequence` WRITE;
	INSERT INTO `hibernate_sequence` VALUES (1);
	UNLOCK TABLES;


