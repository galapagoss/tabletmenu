DROP TABLE IF EXISTS daily_menu_2_item;
DROP TABLE IF EXISTS daily_menu;
DROP TABLE IF EXISTS product_discount;
DROP TABLE IF EXISTS product_2_tag;
DROP TABLE IF EXISTS product_2_category;
DROP TABLE IF EXISTS product_2_language;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category_2_language;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS domain;

CREATE TABLE "domain" ( "_id" int(10)  NOT NULL , "label" varchar(5) NOT NULL, "description" varchar(50) NOT NULL DEFAULT '', "unlock_key" varchar(32) NOT NULL DEFAULT '', PRIMARY KEY ("_id"));
CREATE TABLE "language" ( "label" varchar(5) NOT NULL, "description" varchar(50) NOT NULL DEFAULT '', "_id" int(10)  NOT NULL , PRIMARY KEY ("label"));
CREATE TABLE "language_label" ( "lang" varchar(5) NOT NULL, "label" varchar(20) NOT NULL, "value" varchar(50) NOT NULL, PRIMARY KEY ("lang","label") CONSTRAINT "FK_language_label1" FOREIGN KEY ("lang") REFERENCES "language" ("label"));
CREATE INDEX "language_label_FK_language_label1" ON "language_label" ("lang");
CREATE TABLE "currency" ( "_id" int(10)  NOT NULL , "label" varchar(5) NOT NULL, "symbol" varchar(3) NOT NULL DEFAULT '', "digit" int(10)  NOT NULL , "separator_decimal" varchar(1) NOT NULL DEFAULT '', "separator_thousand" varchar(3) NOT NULL DEFAULT '', PRIMARY KEY ("_id"));
CREATE TABLE "attachment" ( "_id" int(10)  NOT NULL , "id_domain" int(10)  NOT NULL, "path" varchar(255) NOT NULL DEFAULT '', "filename" varchar(100) NOT NULL DEFAULT '', "filetype" varchar(4) NOT NULL DEFAULT '', "width" int(10)  DEFAULT NULL, "height" int(10)  DEFAULT NULL, "size" bigint(20)  NOT NULL DEFAULT '0', PRIMARY KEY ("_id") CONSTRAINT "FK_attachment_domain" FOREIGN KEY ("id_domain") REFERENCES "domain" ("_id") ON DELETE CASCADE ON UPDATE CASCADE );
CREATE INDEX "attachment_FK_attachment_domain" ON "attachment" ("id_domain");
CREATE TABLE "menu" ( "_id" int(10)  NOT NULL , "domain" int(10)  NOT NULL, "title" varchar(100) NOT NULL, "logo" int(10)  DEFAULT NULL, "id_currency" int(10)  NOT NULL, "version" int(10)  DEFAULT NULL, "version_date" timestamp NULL DEFAULT NULL, "main_lang" varchar(5) NOT NULL, PRIMARY KEY ("_id") CONSTRAINT "FK_menu1" FOREIGN KEY ("domain") REFERENCES "domain" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_menu3" FOREIGN KEY ("logo") REFERENCES "attachment" ("_id") ON DELETE SET NULL ON UPDATE SET NULL, CONSTRAINT "FK_menu4" FOREIGN KEY ("main_lang") REFERENCES "language" ("label"));
CREATE INDEX "menu_FK_menu1" ON "menu" ("domain");
CREATE INDEX "menu_FK_menu3" ON "menu" ("logo");
CREATE INDEX "menu_FK_menu4" ON "menu" ("main_lang");
CREATE TABLE "tag" ( "_id" int(10)  NOT NULL , "label" varchar(50) NOT NULL DEFAULT '', PRIMARY KEY ("_id"));
CREATE TABLE "category" ( "_id" int(10)  NOT NULL , "img" int(10)  DEFAULT NULL, "item_order" smallint(6) DEFAULT NULL, PRIMARY KEY ("_id") CONSTRAINT "FK_category2" FOREIGN KEY ("img") REFERENCES "attachment" ("_id") ON DELETE SET NULL ON UPDATE SET NULL );
CREATE INDEX "category_FK_category2" ON "category" ("img");
CREATE TABLE "category_2_language" ( "_id" int(10)  NOT NULL , "id_category" int(10)  NOT NULL, "lang" varchar(5) NOT NULL, "title" varchar(50) NOT NULL, "subtitle" text NOT NULL, "body" text NOT NULL, PRIMARY KEY ("_id") CONSTRAINT "FK_category_2_language1" FOREIGN KEY ("lang") REFERENCES "language" ("label") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_category_2_language2" FOREIGN KEY ("id_category") REFERENCES "category" ("_id") ON DELETE CASCADE ON UPDATE CASCADE );
CREATE INDEX "category_2_language_FK_category_2_language1" ON "category_2_language" ("lang");
CREATE INDEX "category_2_language_FK_category_2_language2" ON "category_2_language" ("id_category");
CREATE TABLE "product" ("_id" int(10)  NOT NULL , "img" int(10)  DEFAULT NULL, "price" decimal(10,2)  NOT NULL DEFAULT '0.00', "item_order" smallint(6) DEFAULT NULL, PRIMARY KEY ("_id") CONSTRAINT "FK_product2" FOREIGN KEY ("img") REFERENCES "attachment" ("_id") ON DELETE SET NULL ON UPDATE SET NULL);
CREATE INDEX "product_FK_product2" ON "product" ("img");
CREATE TABLE "product_2_language" ("_id" int(10)  NOT NULL , "id_product" int(10)  NOT NULL, "lang" varchar(5) NOT NULL, "title" varchar(50) NOT NULL, "subtitle" text NOT NULL, "body" text NOT NULL, PRIMARY KEY ("_id") CONSTRAINT "FK_product_2_language1" FOREIGN KEY ("lang") REFERENCES "language" ("label") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_product_2_language2" FOREIGN KEY ("id_product") REFERENCES "product" ("_id") ON DELETE CASCADE ON UPDATE CASCADE);
CREATE INDEX "product_2_language_FK_product_2_language1" ON "product_2_language" ("lang");
CREATE INDEX "product_2_language_FK_product_2_language2" ON "product_2_language" ("id_product");
CREATE TABLE "product_2_category" ("id_product" int(10)  NOT NULL, "id_category" int(10)  NOT NULL, PRIMARY KEY ("id_product","id_category") CONSTRAINT "FK_product_2_category1" FOREIGN KEY ("id_category") REFERENCES "category" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_product_2_category2" FOREIGN KEY ("id_product") REFERENCES "product" ("_id") ON DELETE CASCADE ON UPDATE CASCADE);
CREATE INDEX "product_2_category_FK_product_2_category1" ON "product_2_category" ("id_category");
CREATE INDEX "product_2_category_FK_product_2_category2" ON "product_2_category" ("id_product");
CREATE TABLE "product_2_tag" ("id_product" int(10)  NOT NULL, "id_tag" int(10)  NOT NULL, "typetag" varchar(1)  NOT NULL, PRIMARY KEY ("id_product","id_tag") CONSTRAINT "FK_product_2_tag1" FOREIGN KEY ("id_tag") REFERENCES "tag" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_product_2_tag2" FOREIGN KEY ("id_product") REFERENCES "product" ("_id") ON DELETE CASCADE ON UPDATE CASCADE);
CREATE INDEX "product_2_tag_FK_product_2_tag1" ON "product_2_tag" ("id_tag");
CREATE INDEX "product_2_tag_FK_product_2_tag2" ON "product_2_tag" ("id_product");
CREATE TABLE "product_discount" ("_id" int(10)  NOT NULL ,"id_product" int(10)  NOT NULL, "recurring" int(10) NOT NULL, "fromtime" timestamp NOT NULL, "totime" timestamp NOT NULL, "price" decimal(10,2)  NOT NULL DEFAULT '0.00', "roworder" int(10)  NOT NULL, PRIMARY KEY ("_id","id_product") CONSTRAINT "FK_product_discount1" FOREIGN KEY ("id_product") REFERENCES "product" ("_id") ON DELETE CASCADE ON UPDATE CASCADE);
CREATE INDEX "product_discount_FK_product_discount1" ON "product_discount" ("id_product");
CREATE TABLE "daily_menu" ("_id" int(10)  NOT NULL ,"week_day" int(10)  NOT NULL, "day_type" int(10) NOT NULL, "price" decimal(10,2)  NOT NULL DEFAULT '0.00', PRIMARY KEY ("_id"));
CREATE TABLE "daily_menu_2_item" ("daily_menu" int(10)  NOT NULL, "id_category" int(10) NULL, "id_product" int(10) NULL, "id_tag" int(10) NULL, "item_order" int(10)  NOT NULL, PRIMARY KEY ("daily_menu","id_category","id_product","id_tag") CONSTRAINT "FK_daily_menu_2_item1" FOREIGN KEY ("id_category") REFERENCES "category" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_daily_menu_2_item2" FOREIGN KEY ("id_product") REFERENCES "product" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_daily_menu_2_item3" FOREIGN KEY ("id_tag") REFERENCES "tag" ("_id") ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT "FK_daily_menu_2_item4" FOREIGN KEY ("daily_menu") REFERENCES "daily_menu" ("_id") ON DELETE CASCADE ON UPDATE CASCADE);
CREATE INDEX "daily_menu_2_item_FK_daily_menu_2_item1" ON "daily_menu_2_item" ("id_category");
CREATE INDEX "daily_menu_2_item_FK_daily_menu_2_item2" ON "daily_menu_2_item" ("id_product");
CREATE INDEX "daily_menu_2_item_FK_daily_menu_2_item3" ON "daily_menu_2_item" ("id_tag");
CREATE INDEX "daily_menu_2_item_FK_daily_menu_2_item4" ON "daily_menu_2_item" ("daily_menu");
INSERT INTO domain(`_id`,`label`,`description`,`unlock_key`) VALUES (2,'OXY','OxyBay Consulting S.L.','86649186e17a842492a685f114df2d78');

INSERT INTO language(`label`,`description`,`_id`) VALUES ('es_ES','Español',5);
INSERT INTO language(`label`,`description`,`_id`) VALUES ('en_EN','English',4);
INSERT INTO language(`label`,`description`,`_id`) VALUES ('it_IT','Italiano',9);
INSERT INTO language(`label`,`description`,`_id`) VALUES ('ru_RU','Русский',14);

INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('es_ES','promotion','Promotion');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('es_ES','dailymenu','Daily Menu');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('es_ES','order','Order');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('en_EN','promotion','Promotion');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('en_EN','dailymenu','Daily Menu');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('en_EN','order','Order');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('it_IT','promotion','OFFERTA');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('it_IT','dailymenu','Piatti consigliati');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('it_IT','order','Ordinazione');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('ru_RU','promotion','рекламирование');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('ru_RU','dailymenu','Ежедневное меню');
INSERT INTO language_label(`lang`,`label`,`value`) VALUES ('ru_RU','order','указание');

INSERT INTO menu(`_id`,`domain`,`title`,`logo`,`id_currency`,`version`,`version_date`,`main_lang`) VALUES (3,2,'Menu senza titolo',3,1,NULL,NULL,'it_IT');

INSERT INTO currency(`_id`,`label`,`symbol`,`digit`,`separator_decimal`,`separator_thousand`) VALUES (1,'EUR','€',2,',','''');

INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (3,2,'20120729','Anse Source d''Argent.jpg','jpg',150,150,8793);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (5,2,'20120829','antipasti.jpg','jpg',340,150,21838);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (24,2,'20120823','piattounico.JPG','jpg',340,150,23034);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (4,2,'20120829','dessert.jpg','jpg',340,150,41754);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (7,2,'20120829','spaghetti-al-pomodoro-del-mazzo.jpg','jpg',340,150,26408);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (25,2,'20120823','panino.jpg','jpg',340,150,8034);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (17,2,'20120810','tuna-carpaccio.jpg','jpg',150,150,7546);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (26,2,'20120823','tunasalad.jpg','jpg',480,360,45599);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (27,2,'20120823','salmonsalad.jpg','jpg',480,360,29891);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (22,2,'20120810','tortillagambas.jpg','jpg',150,150,5580);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (21,2,'20120810','polloavocado.jpg','jpg',150,150,10555);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (18,2,'20120810','lamb.JPG','jpg',150,150,10474);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (20,2,'20120810','trotalimone.jpg','jpg',150,150,8631);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (11,2,'20120810','cheesecake1.jpg','jpg',150,150,36381);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (14,2,'20120810','mousse-di-cioccolato.jpg','jpg',150,150,7952);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (15,2,'20120810','Tiramisu.gif','gif',150,150,16292);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (8,2,'20120810','sacher.jpg','jpg',150,150,12327);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (9,2,'20120810','tortamele.jpg','jpg',150,150,6244);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (10,2,'20120810','crostata meringata al limone.jpg','jpg',150,150,19252);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (13,2,'20120810','creme-caramel-1.jpg','jpg',150,150,10066);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (19,2,'20120810','linguine.jpg','jpg',150,150,9625);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (23,2,'20120810','bocadillo_york_queso.jpg','jpg',150,150,46673);
INSERT INTO attachment(`_id`,`id_domain`,`path`,`filename`,`filetype`,`width`,`height`,`size`) VALUES (28,2,'20120823','tunasandwich.jpg','jpg',480,360,214429);

INSERT INTO tag(`_id`,`label`) VALUES (1,'Scorte Limitate');
INSERT INTO tag(`_id`,`label`) VALUES (2,'Pranzo');
INSERT INTO tag(`_id`,`label`) VALUES (4,'Cena');
INSERT INTO tag(`_id`,`label`) VALUES (5,'Contorni');
INSERT INTO tag(`_id`,`label`) VALUES (6,'Meta Prezzo');

INSERT INTO category(`_id`,`img`,`item_order`) VALUES (1,5,1);
INSERT INTO category(`_id`,`img`,`item_order`) VALUES (2,24,2);
INSERT INTO category(`_id`,`img`,`item_order`) VALUES (16,4,12);
INSERT INTO category(`_id`,`img`,`item_order`) VALUES (17,7,13);
INSERT INTO category(`_id`,`img`,`item_order`) VALUES (18,25,14);

INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (3,1,'es_ES','Entrantes','Para empezar con <b>gusto</b>',' ');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (13,17,'es_ES','Pasta','Muchos tipos de pastas con&nbsp;<u>las salsas màs sabrosas</u>&nbsp;
and blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (14,16,'es_ES','Postres','<i>Los dulces màs frescos y gustosos</i>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (17,18,'es_ES','Bocadillos','<span style="font-family: impact; ">Big tasty sandwiches</span>&nbsp;<br>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (19,2,'es_ES','Platos principales','<br>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (1,1,'en_EN','Starters','Starting <b>tasty</b>',' ');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (4,2,'en_EN','Main Course',' ',' ');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (9,16,'en_EN','Desserts','<i><span style="color: rgb(49, 45, 46); font-family: Arial; line-height: 18px; text-align: justify; ">The freshest and finest cakes and delights</span>&nbsp;</i><br>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (11,17,'en_EN','Pasta','A lot of different pastas with <u>the tastiest sauces</u>&nbsp;and blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (15,18,'en_EN','Sandwiches','<font face="impact">Big tasty sandwiches</font>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (2,1,'it_IT','Antipasti','Per iniziare con <b>gusto</b>',' ');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (10,16,'it_IT','Dolci','<i>Le delizie più fresche e buone</i><br>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (12,17,'it_IT','Pasta','Tanti tipi di pasta con le più <u>deliziose salse</u>&nbsp;
and blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (16,18,'it_IT','Panini','<span style="font-family: impact; ">Big tasty sandwiches</span>&nbsp;<br>','<br>');
INSERT INTO category_2_language(`_id`,`id_category`,`lang`,`title`,`subtitle`,`body`) VALUES (18,2,'it_IT','Piatto unico','<br>','<br>');

INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (1,11,6.50,11);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (4,17,9.00,10);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (5,19,8.50,4);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (6,21,10.00,5);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (7,26,8.00,6);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (8,14,6.00,7);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (9,15,6.00,8);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (10,8,6.00,9);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (11,9,6.00,1);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (12,10,6.00,12);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (13,18,8.50,13);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (14,20,9.00,14);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (16,27,9.00,16);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (17,13,6.00,17);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (18,12,7.00,18);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (19,22,6.00,19);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (20,23,6.00,20);
INSERT INTO product(`_id`,`img`,`price`,`item_order`) VALUES (21,28,6.50,21);

INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (16,4,'es_ES','Carpaccio de atun','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (23,7,'es_ES','Ensalada de atun','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (28,8,'es_ES','Mousse de chocolate','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (32,10,'es_ES','Sacher Torte','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (35,11,'es_ES','Tarta de manzana','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (38,12,'es_ES','Tarta merengue y limòn','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (41,13,'es_ES','Cordero en salsa de menta','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (42,1,'es_ES','Tarta de queso','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (43,5,'es_ES','Linguini con setas','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (44,6,'es_ES','Pollo con aguacate','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (45,9,'es_ES','Tiramisu ','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (48,14,'es_ES','Trucha fresca con salsa de limòn','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (52,16,'es_ES','Ensalada de salmòn ahumado','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (56,17,'es_ES','Flan','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (61,19,'es_ES','Tortilla de gambas','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (65,20,'es_ES','Amanda','York y queso<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod
 tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim 
veniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea 
commodo consequat. Duis aute irure dolor in reprehenderit in voluptate 
velit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint 
occaecat cupidatat non proident, sunt in culpa qui officia deserunt 
mollit anim id est laborum');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (67,21,'es_ES','Amelie','Tuna, queso y tomate','Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab
 illo inventore veritatis et quasi architecto beatae vitae dicta sunt 
explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut 
odit aut fugit, sed quia consequuntur magni dolores eos qui ratione 
voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum 
quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam 
eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat 
voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam 
corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?
 Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse 
quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo 
voluptas nulla pariatur');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (9,1,'en_EN','CheeseCake',' ',' ');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (14,4,'en_EN','Tuna Carpaccio','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (17,5,'en_EN','Mushroom Linguini','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (19,6,'en_EN','Avocado Chicken','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (21,7,'en_EN','Tuna Salad','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (24,8,'en_EN','Chocolate Mousse','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (26,9,'en_EN','Homemade Tiramisu','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (30,10,'en_EN','Sacher Torte','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (33,11,'en_EN','Apple Pie','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (36,12,'en_EN','Lemon meringue','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (39,13,'en_EN','Lamb in a mint sauce','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (47,14,'en_EN','Fresh Trout in a lemon sauce','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (50,16,'en_EN','Smoked salmon salad','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (54,17,'en_EN','Crème caramel','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (60,19,'en_EN','Prawns omelet','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (63,20,'en_EN','Amanda','Ham and cheese<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod
 tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim 
veniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea 
commodo consequat. Duis aute irure dolor in reprehenderit in voluptate 
velit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint 
occaecat cupidatat non proident, sunt in culpa qui officia deserunt 
mollit anim id est laborum');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (66,21,'en_EN','Amelie','Tuna, cheese and tomato','Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab
 illo inventore veritatis et quasi architecto beatae vitae dicta sunt 
explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut 
odit aut fugit, sed quia consequuntur magni dolores eos qui ratione 
voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum 
quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam 
eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat 
voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam 
corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?
 Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse 
quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo 
voluptas nulla pariatur');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (10,1,'it_IT','Cheese Cake',' ',' ');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (15,4,'it_IT','Carpaccio di tonno','asdadsaaa<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (18,5,'it_IT','Linguine ai funghi','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (20,6,'it_IT','Pollo con avogado','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (22,7,'it_IT','Insalata di tonno','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (25,8,'it_IT','Mousse al cioccolato','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (27,9,'it_IT','Tiramisu ','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (31,10,'it_IT','Sacher Torte','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (34,11,'it_IT','Torta di mele','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (37,12,'it_IT','Meringata al limone','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (40,13,'it_IT','Agnello in salsa di menta','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (49,14,'it_IT','Trota fresca in salsa di limone','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (51,16,'it_IT','Salmone affumicato in insalata','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (55,17,'it_IT','Crème caramel','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (62,19,'it_IT','Frittata di gamberi','<br>','<br>');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (64,20,'it_IT','Amanda','Prosciutto cotto e formaggio<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod
 tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim 
veniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea 
commodo consequat. Duis aute irure dolor in reprehenderit in voluptate 
velit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint 
occaecat cupidatat non proident, sunt in culpa qui officia deserunt 
mollit anim id est laborum');
INSERT INTO product_2_language(`_id`,`id_product`,`lang`,`title`,`subtitle`,`body`) VALUES (68,21,'it_IT','Amelie','Tonno, formaggio e pomodoro','Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab
 illo inventore veritatis et quasi architecto beatae vitae dicta sunt 
explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut 
odit aut fugit, sed quia consequuntur magni dolores eos qui ratione 
voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum 
quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam 
eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat 
voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam 
corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?
 Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse 
quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo 
voluptas nulla pariatur');

INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (1,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (4,1);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (5,17);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (6,2);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (7,1);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (8,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (9,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (10,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (11,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (12,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (13,2);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (14,2);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (16,1);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (17,16);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (19,1);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (20,18);
INSERT INTO product_2_category(`id_product`,`id_category`) VALUES (21,18);

INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (7,1,'P');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (7,2,'P');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (1,4,'P');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (4,6,'P');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (11,6,'P');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (4,5,'C');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (7,5,'C');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (16,5,'C');
INSERT INTO product_2_tag(`id_product`,`id_tag`,`typetag`) VALUES (19,5,'C');

INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (19,7,'96','00:00:00','00:00:00',8.80,1);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (18,7,'31','12:00:00','14:00:00',7.60,2);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (22,4,'127','00:00:00','00:00:00',4.50,3);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (22,11,'127','00:00:00','00:00:00',3.00,4);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (21,4,'1','18:45:00','20:15:00',6.00,5);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (21,7,'1','18:45:00','20:15:00',6.00,6);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (21,16,'1','18:45:00','20:15:00',6.00,7);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (21,19,'1','18:45:00','20:15:00',6.00,8);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (13,1,'3','20:00:00','00:00:00',6.05,9);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (15,7,'3','20:00:00','05:00:00',8.80,10);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (17,7,'4','00:00:00','23:59:00',5.30,11);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (20,4,'32','16:00:00','19:30:00',5.00,12);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (20,7,'32','16:00:00','19:30:00',5.00,13);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (20,16,'32','16:00:00','19:30:00',5.00,14);
INSERT INTO product_discount(`_id`,`id_product`,`recurring`,`fromtime`,`totime`,`price`,`roworder`) VALUES (20,19,'32','16:00:00','19:30:00',5.00,15);

INSERT INTO daily_menu(`_id`,`week_day`,`day_type`,`price`) VALUES (4,1,0,3.00);
INSERT INTO daily_menu(`_id`,`week_day`,`day_type`,`price`) VALUES (6,2,0,3.00);

INSERT INTO daily_menu_2_item(`daily_menu`,`id_category`,`id_product`,`id_tag`,`item_order`) VALUES (4,1,NULL,NULL,1);
INSERT INTO daily_menu_2_item(`daily_menu`,`id_category`,`id_product`,`id_tag`,`item_order`) VALUES (4,16,NULL,NULL,4);
INSERT INTO daily_menu_2_item(`daily_menu`,`id_category`,`id_product`,`id_tag`,`item_order`) VALUES (4,NULL,5,NULL,2);
INSERT INTO daily_menu_2_item(`daily_menu`,`id_category`,`id_product`,`id_tag`,`item_order`) VALUES (4,NULL,20,NULL,3);
INSERT INTO daily_menu_2_item(`daily_menu`,`id_category`,`id_product`,`id_tag`,`item_order`) VALUES (6,NULL,11,NULL,1);

