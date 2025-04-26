-- signup table
CREATE TABLE signup (
    id SERIAL PRIMARY KEY,
    username VARCHAR(250),
    email VARCHAR(250) UNIQUE,
    password VARCHAR(250),
    created_date DATE
);

-- signin table (refers to signup.id)
CREATE TABLE signin (
    id SERIAL PRIMARY KEY,
    signup_id INT,
    password VARCHAR(250),
    login_time TIMESTAMP,
    FOREIGN KEY (signup_id) REFERENCES signup(id)
);

-- forgotpassword table (refers to signup.id)
CREATE TABLE forgotpassword (
    id SERIAL PRIMARY KEY,
    signup_id INT,
    FOREIGN KEY (signup_id) REFERENCES signup(id)
);

-- resetpassword table (already referred to signup.id)
CREATE TABLE resetpassword (
    resetpassword_id INT,
    token VARCHAR(250) UNIQUE,
    new_password VARCHAR(250),
    FOREIGN KEY (resetpassword_id) REFERENCES signup(id)
);
