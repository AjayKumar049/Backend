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

Customer, Item, Estimate, Invoice tables query mentioned below
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    type VARCHAR(20),  -- Business or Individual
    salutation VARCHAR(10),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    company_name VARCHAR(100),
    email VARCHAR(100),
    gstin VARCHAR(15),
    phone VARCHAR(15)
);

CREATE TABLE shipping_address (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(id),
    attention VARCHAR(100),
    country VARCHAR(50),
    address TEXT,
    city VARCHAR(50),
    district VARCHAR(50),
    state VARCHAR(50)
);

CREATE TABLE estimate (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(id),
    estimate_date DATE,
    expiry_date DATE
); 

CREATE TABLE estimate_item (
    id SERIAL PRIMARY KEY,
    estimate_id INTEGER REFERENCES estimate(id),
    item_id INTEGER REFERENCES item(id),
    quantity INTEGER,
    unit_price NUMERIC(10,2),
    gst NUMERIC(5,2),
    discount NUMERIC(5,2),
    total_amount NUMERIC(12,2)
);



CREATE TABLE invoice (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(id),
    invoice_number VARCHAR(20),
    payment_method VARCHAR(20),  -- Cash, UPI, Cheque, etc.
    payment_terms VARCHAR(10),   -- Net15, Net30, etc.
    due_date DATE
);

CREATE TABLE invoice_item (
    id SERIAL PRIMARY KEY,
    invoice_id INTEGER REFERENCES invoice(id),
    item_id INTEGER REFERENCES item(id),
    quantity INTEGER,
    unit_price NUMERIC(10,2),
    gst NUMERIC(5,2),
    discount NUMERIC(5,2),
    total_amount NUMERIC(12,2)
);












    

 

