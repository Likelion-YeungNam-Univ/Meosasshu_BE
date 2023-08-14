insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');
insert into authority (authority_name) values ('ROLE_SELLER');


INSERT INTO Account (email, password, name) VALUES ('admin', '$2a$10$o9v4FooOAgnwk1GevPx0N.xJu8IZsrIP.O2cS8u1kzZ4O2ANdasd2', '더미');
-- INSERT INTO Account (email, password) VALUES ('user', '$10$yGh.OpZ21O4nowC6WXNI3eA05nRDctOT4NXFroGzEmWs4liaefkJ2');


insert into account_authority (account_id, authority_name) values (1, 'ROLE_USER');
insert into account_authority (account_id, authority_name) values (1, 'ROLE_ADMIN');
-- insert into account_authority (account_id, authority_name) values (2, 'ROLE_USER');





-- Category 테이블에 데이터 삽입 예시
INSERT INTO Category (name, description)
VALUES
    ('의류', '다양한 종류의 의류'),
    ('건강식품', '영양 보충과 건강에 좋은 식품'),
    ('의료용품', '의료 장비와 용품'),
    ('생활용품', '다양한 생활용품'),
    ('기저귀', '성인용 및 아기 기저귀');



-- Product 테이블에 데이터 삽입 예시
-- INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id,sales_count)
-- VALUES
--     ('Example Brand', '성인기저귀', 49.99, 1, 2, 100, '우리의 제품은 고품질의 성인용 기저귀로, 편안하게 착용할 수 있도록 설계되었습니다. 뛰어난 흡수력으로 배변을 신속히 흡수하며, 피부 친화적인 소재를 사용하여 피부 건강을 지킵니다. 매일의 생활에서 활기찬 시간을 보내기 위한 필수 아이템으로, 자신감 있는 일상을 위해 우리 제품을 선택하세요. 최고 품질의 성인용 기저귀를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', 'A great product for your needs.', 1,10),
--     ('Sample Co.', '홍삼캔디', 29.99, 3, 4, 50, '새콤달콤 홍삼캔디로 건강한 생활 시작! 한 알에는 홍삼의 풍부한 영양이 담겨있어 면역력 강화와 피로 회복을 도와줍니다. 비타민과 미네랄이 가득한 이 캔디는 활력을 공급하면서도 맛있게 즐길 수 있어요. 어디서나 간편하게 즐겨보세요! ', 'The best choice for you.', 2,100);

-- 의류 카테고리 상품 추가
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id, sales_count)
VALUES
    ('Fashion Trend', '멋진 셔츠', 29.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 200, '우리의 멋진 셔츠는 고품질 소재로 제작되어 편안한 착용감을 선사합니다. 다양한 컬러와 디자인으로 여러분의 패션 스타일을 완성하세요. 스타일리시한 셔츠로 여러분의 일상을 더욱 화사하게 만들어드립니다. 지금 바로 주문하세요!', '스타일리시한 셔츠', 1, 50),
    ('Fashionista', '여름 원피스', 39.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 150, '우리의 여름 원피스는 시원한 소재로 제작되어 더욱 편안한 여름을 보내실 수 있습니다. 다양한 스타일로 세련된 룩을 연출해보세요. 여름의 열기를 피해가며 여유로운 룩을 완성해보세요. 시원한 여름룩을 위한 필수 아이템으로, 최고의 품질을 가격대비로 제공합니다. 지금 주문하세요!', '시원한 여름룩', 1, 30),
    ('Casual Wear', '캐주얼 바지', 24.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 300, '우리의 캐주얼 바지는 편안한 착용감을 위해 최적화된 디자인입니다. 다양한 색상과 스타일로 다양한 상황에 어울립니다. 데일리 캐주얼룩의 완성을 위한 필수 아이템으로, 최고 품질을 저렴한 가격에 제공합니다. 편안하면서도 세련된 캐주얼룩을 위해 지금 주문하세요!', '편안한 캐주얼룩', 1, 90);

-- 건강식품 카테고리 상품 추가
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id, sales_count)
VALUES
    ('Healthy Life', '멀티비타민', 19.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 100, '우리의 멀티비타민은 모든 영양소를 한 번에 공급하는 제품입니다. 건강한 라이프스타일을 유지하기 위한 필수 아이템으로, 비타민과 미네랄로 가득 찬 영양보충제입니다. 날씨 변화에도 유연한 몸을 유지하고 면역력을 강화하세요. 최고 품질의 멀티비타민을 가격대비로 제공합니다. 지금 주문하세요!', '다양한 영양소', 2, 70),
    ('Green Valley', '유기농 채소 스퀴즈', 14.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg','https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 120, '우리의 유기농 채소 스퀴즈는 신선한 유기농 채소를 스퀴즈한 건강한 음료입니다. 비타민과 미네랄로 가득 찬 채소 스퀴즈로 피로 회복과 면역력 강화를 도와줍니다. 언제 어디서나 간편하게 즐길 수 있는 건강한 음료입니다. 최상의 품질을 저렴한 가격에 제공합니다. 지금 주문하세요!', '건강한 음료', 2, 40),
    ('Vitality', '식이섬유 보충제', 9.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 200, '우리의 식이섬유 보충제는 매일의 식이섬유 섭취를 돕는 간편한 제품입니다. 소화와 건강을 위한 필수 아이템으로, 간편하게 섭취하여 정상적인 소화 기능을 유지하세요. 편리한 보충제로 더욱 건강한 라이프스타일을 지향하세요. 최고 품질의 식이섬유 보충제를 저렴한 가격에 제공합니다. 지금 주문하세요!', '간편한 건강', 2, 20);

-- 의료용품 카테고리 상품 추가
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id, sales_count)
VALUES
    ('MediCare', '혈압 모니터', 89.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 80, '우리의 디지털 혈압 모니터는 정확한 혈압 측정을 위해 설계되었습니다. 건강 관리에 필수인 이 제품은 정확한 측정 결과를 보장하여 신속한 조치를 취할 수 있도록 도와줍니다. 심장 건강을 지키고 혈압 변화를 모니터링하세요. 최고 품질의 혈압 모니터를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', '정확한 혈압 측정', 3, 60),
    ('HealthHub', '마사지 의자', 499.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 30, '우리의 마사지 의자는 자동 마사지 기능이 있는 편안한 제품으로, 스트레스를 풀어보세요. 피로한 일상에서 편안한 마사지를 경험하고 몸과 마음을 힐링하세요. 다양한 마사지 프로그램으로 원하는 스타일을 즐기세요. 집에서 마사지 느낌을 느껴보세요. 최고 품질의 마사지 의자를 저렴한 가격에 제공합니다. 지금 주문하세요!', '집에서 마사지', 3, 10),
    ('CareMed', '요추벨트', 29.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 100, '우리의 요추 벨트는 허리 통증 완화를 위해 디자인되었습니다. 조절 가능한 강도로 편안한 착용을 제공하며, 허리 부위를 안정적으로 지지해줍니다. 편안한 착용으로 허리 통증을 완화하고 일상 생활에서 편안함을 누려보세요. 품질과 기능을 저렴한 가격에 제공합니다. 지금 주문하세요!', '허리 통증 완화', 3, 50);

-- 생활용품 카테고리 상품 추가
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id, sales_count)
VALUES
    ('Home Comforts', '컴포트러블 침구세트', 79.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 70, '우리의 컴포트러블 침구세트는 편안한 잠자리를 만들기 위해 디자인되었습니다. 아늑한 밤을 보내기 위한 필수 아이템으로, 퀄리티와 스타일을 함께한 침구세트입니다. 부드러운 소재로 피부에 자극 없이 편안한 숙면을 즐겨보세요. 다양한 컬러와 디자인으로 침실을 더 아름답게 만들어보세요. 최고 품질의 컴포트러블 침구세트를 저렴한 가격에 제공합니다. 지금 주문하세요!', '아늑한 침구', 4, 30),
    ('Kitchen Essentials', '다용도 조리기구 세트', 49.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 90, '우리의 다용도 조리기구세트는 주방에서 필요한 모든 조리 도구를 갖추었습니다. 다양한 조리용품이 한 세트로, 요리를 더욱 쉽고 편리하게 만들어줍니다. 고객님의 요리 스타일에 맞는 다양한 도구를 사용해보세요. 식재료를 다루는데 필요한 모든 도구를 이 세트에서 찾을 수 있습니다. 품질과 다양성을 저렴한 가격에 제공합니다. 지금 주문하세요!', '다용도 조리 도구', 4, 50),
    ('Home Organizer', '수납 박스 세트', 34.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 120, '우리의 수납 박스 세트는 정리정돈을 위한 필수 아이템입니다. 다양한 크기로 다양한 물건을 정리하고 보관할 수 있습니다. 집 안에서 더 깔끔한 공간을 만들기 위한 도우미로 활용하세요. 간단한 디자인으로 어떤 공간에도 어울리며, 실용적인 수납 솔루션을 제공합니다. 품질과 실용성을 저렴한 가격에 제공합니다. 지금 주문하세요!', '정리정돈 도우미', 4, 40);

-- 기저귀 카테고리 상품 추가
INSERT INTO Product (brand, name, price, thumbnail, description_image, stock_quantity, description, short_description, category_id, sales_count)
VALUES
    ('DiaperMax', '최고 품질 성인기저귀', 49.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 100, '우리의 최고 품질 성인기저귀는 편안한 착용을 위해 디자인되었습니다. 피부에 더욱 친숙한 소재를 사용하여 피부 건강을 지키면서, 뛰어난 흡수력으로 배변을 신속히 흡수합니다. 자신감 있는 일상을 위해 우리 제품을 선택하세요. 최고 품질의 성인용 기저귀를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', '편안한 착용, 피부 건강', 5, 20),
    ('EasyComfort', '부드러운 기저귀', 19.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 200, '우리의 편안한 기저귀는 편안한 착용을 위해 디자인되었습니다. 피부에 자극 없이 편안한 소재를 사용하여, 매일의 생활에서 활기찬 시간을 보내도록 돕습니다. 자신감 있는 일상을 위해 우리 제품을 선택하세요. 최고 품질의 기저귀를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', '부드러운 아기 기저귀', 5, 50),
    ('AllDayDry', '긴 시간 흡수 기저귀', 29.99, 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1418673921604278-b68d789c-ba44-4c28-83da-f330fc257087.jpg', 'https://st.kakaocdn.net/product/gift/editor/20221218215757_fd758b21ac92445e8b51d506ee721369.jpg', 150, '우리의 긴 시간 흡수 기저귀는 배변을 신속히 흡수하는 고품질 기저귀입니다. 피부 건강을 유지하면서도, 긴 시간 착용해도 편안한 착용감을 제공합니다. 매일의 생활에서 활기찬 시간을 보내기 위한 필수 아이템입니다. 최고 품질의 흡수 기저귀를 저렴한 가격에 제공합니다. 지금 바로 주문하세요!', '긴 시간 착용에 최적', 5, 30);

INSERT INTO Keyword (keyword_name)
VALUES
    ('가성비가 좋아요.'),
    ('품질이 우수해요.'),
    ('배송이 빨라요.');

-- Account 및 Product 테이블의 ID 값은 실제 데이터에 맞게 입력해야 합니다.
-- Review 테이블에 데이터 삽입 예시
INSERT INTO Review (account_id, file_id, product_id, comment)
VALUES
    (1, 1, 1, '좋은 상품을 제공해 주셔서 감사합니다.'),
    (1, 1, 1, '서비스가 훌륭하고 분위기가 좋아요.'),
    (1, 1, 1, '포장이 정말 꼼꼼하게 되어 있어요.');


-- Review_Keyword 테이블에 관계 데이터 삽입 예시
INSERT INTO review_keyword (review_id, keyword_name)
VALUES
    (1, '가성비가 좋아요.'),
    (1, '품질이 우수해요.'),
    (2, '배송이 빨라요.'),
    (3, '가성비가 좋아요.');




