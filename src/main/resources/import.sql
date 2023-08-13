insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');
insert into authority (authority_name) values ('ROLE_SELLER');


INSERT INTO Account (email, password, name) VALUES ('admin', '$2a$10$o9v4FooOAgnwk1GevPx0N.xJu8IZsrIP.O2cS8u1kzZ4O2ANdasd2', '관리자');
-- INSERT INTO Account (email, password) VALUES ('user', '$10$yGh.OpZ21O4nowC6WXNI3eA05nRDctOT4NXFroGzEmWs4liaefkJ2');


insert into account_authority (account_id, authority_name) values (1, 'ROLE_USER');
insert into account_authority (account_id, authority_name) values (1, 'ROLE_ADMIN');
-- insert into account_authority (account_id, authority_name) values (2, 'ROLE_USER');

-- Category 테이블에 데이터 삽입 예시
INSERT INTO Category (name, description)
VALUES
    ('Electronics', 'Electronic devices and gadgets'),
    ('Clothing', 'Various types of clothing'),
    ('Home Appliances', 'Appliances for home use');


-- Product 테이블에 데이터 삽입 예시
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id,sales_count)
VALUES
    ('Example Brand', '성인기저귀', 49.99, 1, 2, 100, '우리의 제품은 고품질의 성인용 기저귀로, 편안하게 착용할 수 있도록 설계되었습니다. 뛰어난 흡수력으로 배변을 신속히 흡수하며, 피부 친화적인 소재를 사용하여 피부 건강을 지킵니다. 매일의 생활에서 활기찬 시간을 보내기 위한 필수 아이템으로, 자신감 있는 일상을 위해 우리 제품을 선택하세요. 최고 품질의 성인용 기저귀를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', 'A great product for your needs.', 1,10),
    ('Sample Co.', '홍삼캔디', 29.99, 3, 4, 50, '새콤달콤 홍삼캔디로 건강한 생활 시작! 한 알에는 홍삼의 풍부한 영양이 담겨있어 면역력 강화와 피로 회복을 도와줍니다. 비타민과 미네랄이 가득한 이 캔디는 활력을 공급하면서도 맛있게 즐길 수 있어요. 어디서나 간편하게 즐겨보세요! ', 'The best choice for you.', 2,100);

INSERT INTO Keyword (keyword_name)
VALUES
    ('맛있어요'),
    ('분위기가 좋아요'),
    ('포장이 꼼꼼해요');

-- Account 및 Product 테이블의 ID 값은 실제 데이터에 맞게 입력해야 합니다.
-- Review 테이블에 데이터 삽입 예시
INSERT INTO Review (account_id, file_id, product_id, comment)
VALUES
    (1, 1, 1, '맛있는 음식을 제공해 주셔서 감사합니다.'),
    (1, 1, 1, '서비스가 훌륭하고 분위기가 좋아요.'),
    (1, 1, 1, '포장이 정말 꼼꼼하게 되어 있어요.');


-- Review_Keyword 테이블에 관계 데이터 삽입 예시
INSERT INTO review_keyword (review_id, keyword_name)
VALUES
    (1, '맛있어요'),
    (1, '분위기가 좋아요'),
    (2, '분위기가 좋아요'),
    (3, '포장이 꼼꼼해요');


