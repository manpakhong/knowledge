CREATE TABLE
    APP_USERS
    (
        ID bigint NOT NULL,
        ENABLED bit,
        LAST_LOGIN_DATETIME DATETIME,
        NAME VARCHAR(255) NOT NULL,
        PASSWORD VARCHAR(255),
        USERNAME VARCHAR(255) NOT NULL,
        PRIMARY KEY (ID),
        CONSTRAINT UK_ap337s7dja9dglwl5ne1qsabt UNIQUE (USERNAME)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;

    
CREATE TABLE
    APP_ROLES
    (
        ID bigint NOT NULL,
        NAME VARCHAR(255) NOT NULL,
        PRIMARY KEY (ID),
        CONSTRAINT UK_q7ah6i3m0xdsx0oogipdmk2lx UNIQUE (NAME)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;

CREATE TABLE
    APP_PRIVILEGES
    (
        ID bigint NOT NULL,
        APP_ROLE_ID bigint NOT NULL,
        APP_USER_ID bigint NOT NULL,
        NAME VARCHAR(255) NOT NULL,
        PRIMARY KEY (ID),
        CONSTRAINT FKe1xx9pfeet23xauqonsqmguk1 FOREIGN KEY (APP_USER_ID) REFERENCES `APP_USERS`
        (`ID`) ,
        CONSTRAINT FKqh3xlk6dvi1ovfirlt6eduwp5 FOREIGN KEY (APP_ROLE_ID) REFERENCES `APP_ROLES`
        (`ID`),
        CONSTRAINT UK_APP_PRIVILEGES UNIQUE (APP_ROLE_ID, APP_USER_ID),
        CONSTRAINT UKkwfdo9hyo2svo6y63u76s0hhx UNIQUE (APP_ROLE_ID, APP_USER_ID),
        INDEX FKe1xx9pfeet23xauqonsqmguk1 (APP_USER_ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE=utf8_general_ci;
