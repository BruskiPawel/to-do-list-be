CREATE TABLE IF NOT EXISTS roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    user_id CHAR(36) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    user_email VARCHAR(255),
    user_password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_roles (
    user_id CHAR(36),
    CONSTRAINT FK_user_role_id FOREIGN KEY(user_id) REFERENCES users(user_id),
    role_id int,
    CONSTRAINT FK_role_id FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id CHAR(36) PRIMARY KEY,
    task_date DATE,
    task_time TIME,
    task_content VARCHAR(255) NOT NULL,
    user_id CHAR(36),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);