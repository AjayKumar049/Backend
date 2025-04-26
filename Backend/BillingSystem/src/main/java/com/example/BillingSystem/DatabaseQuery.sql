-- signup table
CREATE TABLE signup (
    signup_id SERIAL PRIMARY KEY,
    username VARCHAR(250),
    email VARCHAR(250) UNIQUE,
    password VARCHAR(250),
    created_date DATE
);

-- signin table (refers to signup.signup_id)
CREATE TABLE signin (
    signin_id SERIAL PRIMARY KEY,
    signup_id INT,
    email VARCHAR(250),
    password VARCHAR(250),
    login_time TIMESTAMP,
    FOREIGN KEY (signup_id) REFERENCES signup(signup_id)
);

-- forgotpassword table (refers to signup.signup_id)
CREATE TABLE forgotpassword (
    forgotpassword_id SERIAL PRIMARY KEY,
    signup_id INT,
    email VARCHAR(250),
    requested_date TIMESTAMP,
    FOREIGN KEY (signup_id) REFERENCES signup(signup_id)
);

-- resetpassword table (refers to signup.signup_id)
CREATE TABLE resetpassword (
    resetpassword_id SERIAL PRIMARY KEY,
    signup_id INT,
    token VARCHAR(250) UNIQUE,
    new_password VARCHAR(250),
    FOREIGN KEY (signup_id) REFERENCES signup(signup_id)
);
