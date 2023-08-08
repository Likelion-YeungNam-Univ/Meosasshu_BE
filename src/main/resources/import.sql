-- INSERT INTO Account (email, password) VALUES ('admin', '$2a$10$o9v4FooOAgnwk1GevPx0N.xJu8IZsrIP.O2cS8u1kzZ4O2ANdasd2');
-- INSERT INTO Account (email, password) VALUES ('user', '$10$yGh.OpZ21O4nowC6WXNI3eA05nRDctOT4NXFroGzEmWs4liaefkJ2');

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');
insert into authority (authority_name) values ('ROLE_SELLER');
--
-- insert into account_authority (account_id, authority_name) values (1, 'ROLE_USER');
-- insert into account_authority (account_id, authority_name) values (1, 'ROLE_ADMIN');
-- insert into account_authority (account_id, authority_name) values (2, 'ROLE_USER');

-- Category 테이블에 데이터 삽입 예시
INSERT INTO Category (name, description)
VALUES
    ('Electronics', 'Electronic devices and gadgets'),
    ('Clothing', 'Various types of clothing'),
    ('Home Appliances', 'Appliances for home use');


-- Product 테이블에 데이터 삽입 예시
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id)
VALUES
    ('Example Brand', 'Product A', 49.99, 1, 2, 100, 'This is an example product description.', 'A great product for your needs.', 1),
    ('Sample Co.', 'Product B', 29.99, 3, 4, 50, 'Check out the features of this product.', 'The best choice for you.', 2);
