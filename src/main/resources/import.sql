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
insert into category (name, description)
values
    ('Electronics', 'Electronic devices and gadgets'),
    ('Clothing', 'Various types of clothing'),
    ('Home Appliances', 'Appliances for home use');


-- Product 테이블에 데이터 삽입 예시
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id,sales_count)
VALUES
    ('Example Brand', 'Product A', 49.99, 1, 2, 100, 'This is an example product description.', 'A great product for your needs.', 1,10),
    ('Sample Co.', 'Product B', 29.99, 3, 4, 50, 'Check out the features of this product.', 'The best choice for you.', 2,100);

insert into keyword (keyword_name)
values
    ('맛있어요'),
    ('분위기가 좋아요'),
    ('포장이 꼼꼼해요');

-- Account 및 Product 테이블의 ID 값은 실제 데이터에 맞게 입력해야 합니다.
-- Review 테이블에 데이터 삽입 예시
insert into review (account_id, image, product_id, comment)
values
    (1, 1, 1, '맛있는 음식을 제공해 주셔서 감사합니다.'),
    (1, 1, 1, '서비스가 훌륭하고 분위기가 좋아요.'),
    (1, 1, 1, '포장이 정말 꼼꼼하게 되어 있어요.');


-- Review_Keyword 테이블에 관계 데이터 삽입 예시
insert into review_keyword (review_id, keyword_name)
values
    (1, '맛있어요'),
    (1, '분위기가 좋아요'),
    (2, '분위기가 좋아요'),
    (3, '포장이 꼼꼼해요');