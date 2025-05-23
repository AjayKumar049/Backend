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

##The table queries for Customer, Item, Estimate, and Invoice were mentioned below
-- Customer Table
CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY, 
    type VARCHAR(20) Not NULL HECK (type IN ('Individual', 'Business')),  -- Added constraint
    salutation VARCHAR(10),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    company_name VARCHAR(100),
    email VARCHAR(100) NOT NULL,
    gstin VARCHAR(15),
    phone VARCHAR(15),
    attention VARCHAR(100),
    shipping_address TEXT,
    city VARCHAR(50),
    district VARCHAR(50),
    state VARCHAR(50),
    country VARCHAR(50)
);

    

-- Item Table 
CREATE TABLE item (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dosage VARCHAR(100) NOT NULL, --500mg 
    medicine_type VARCHAR(50) NOT NULL CHECK (medicine_type IN ('Tablet', 'Tonic', 'Liquid', 'Cream', 'Oil', 'Powder', 'Syrup')),
    manufacturer VARCHAR(100),
    hsn_code VARCHAR(6) UNIQUE,
    stock INTEGER DEFAULT 0 CHECK (stock >= 0),
    tax_status VARCHAR(15) CHECK (tax_status IN ('Taxable', 'Non-Taxable')),
    gst DECIMAL(5,2),
    discount DECIMAL(5,2),
    selling_price DECIMAL(10,2) NOT NULL,
    expiry_date DATE
);

-- Estimate Table
CREATE TABLE estimate (
    estimate_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customer(customer_id),
    estimate_number VARCHAR(100),
    salesperson VARCHAR(100),
    estimate_date DATE NOT NULL,
    expiry_date DATE
); 

-- Estimate Items Table
CREATE TABLE estimate_item (
    estimate_itemid SERIAL PRIMARY KEY,
    estimate_id INTEGER NOT NULL REFERENCES estimate(estimate_id),
    item_id INTEGER NOT NULL REFERENCES item(item_id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    tax_status VARCHAR(15) CHECK (tax_status IN ('Taxable', 'Non-Taxable')),
    gst DECIMAL(5,2),
    discount DECIMAL(5,2),
    unit_price DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL
);

-- Invoice Table
CREATE TABLE invoice (
    invoice_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customer(customer_id),
    invoice_number VARCHAR(20) NOT NULL,
    invoice_date DATE NOT NULL,
    payment_method VARCHAR(20),      -- Cash, UPI, Cheque, etc.
    payment_terms VARCHAR(10),       -- Net15, Net30, etc.
    due_date DATE
);

-- Invoice Items Table
CREATE TABLE invoice_item (
    invoice_itemid SERIAL PRIMARY KEY,
    invoice_id INTEGER NOT NULL REFERENCES invoice(invoice_id),
    item_id INTEGER NOT NULL REFERENCES item(item_id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    tax_status VARCHAR(15) CHECK (tax_status IN ('Taxable', 'Non-Taxable')),
    gst DECIMAL(5,2),
    discount DECIMAL(5,2),
    unit_price DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL
);


 

