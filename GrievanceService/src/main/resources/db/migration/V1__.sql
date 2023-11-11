CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE comment_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE department_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE grievance_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE category
(
    id            BIGINT       NOT NULL,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    created_by    BIGINT       NOT NULL,
    updated_by    BIGINT       NOT NULL,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    department_id BIGINT,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE comment
(
    id           BIGINT        NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP,
    created_by   BIGINT        NOT NULL,
    updated_by   BIGINT        NOT NULL,
    comment      VARCHAR(3000) NOT NULL,
    attachments  VARCHAR(255),
    grievance_id BIGINT,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE department
(
    id          BIGINT NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    created_by  BIGINT NOT NULL,
    updated_by  BIGINT NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE grievance
(
    id          BIGINT NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    created_by  BIGINT NOT NULL,
    updated_by  BIGINT NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    category_id BIGINT,
    status      VARCHAR(255),
    attachments VARCHAR(255),
    assigned_to BIGINT NOT NULL,
    resolved_by BIGINT NOT NULL,
    CONSTRAINT pk_grievance PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_GRIEVANCE FOREIGN KEY (grievance_id) REFERENCES grievance (id);

ALTER TABLE grievance
    ADD CONSTRAINT FK_GRIEVANCE_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);