----------- dobavljaci -----------------------------------------------------

INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt") VALUES(nextval('dobavljac_seq'), 'AD Imlek', 'Industrijsko naselje bb, Padinska skela, Beograd', '+381 11 30 50 505');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt") VALUES(nextval('dobavljac_seq'), 'Henkel Srbija', 'Bulevar oslobodenja 383, 11040 Beograd, Srbija', '+381 11 20 72 200');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt") VALUES(nextval('dobavljac_seq'), 'Fruit D.O.O.', 'Justina Popovica 3, 11080 Zemun, Beograd', '+381 11 3143 171');
INSERT INTO "dobavljac"("id", "naziv", "adresa", "kontakt") VALUES(nextval('dobavljac_seq'), 'CENTROPROIZVOD', 'DOBANOVACKI PUT B.B. 11271, SURCIN', '+381 11 3773 600');


----------- artikli -----------------------------------------------------

INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Moja Kravica sveže mleko 2,8% MM 1l', 'AD Imlek');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Moja Kravica dugotrajno mleko 3,2% MM 1l', 'AD Imlek');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Moja Kravica Beli sir 1kg', 'AD Imlek');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Persil Regular Prašak 2kg', 'Henkel Srbija');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Persil Regular Gel 2l', 'Henkel Srbija');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Persil Duo-Caps Color pak', 'Henkel Srbija');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Jagoda', 'Fruit D.O.O.');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Jabuka', 'Fruit D.O.O.');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Kajsija', 'Fruit D.O.O.');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Šlag pena', 'CENTROPROIZVOD');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Puding vanila', 'CENTROPROIZVOD');
INSERT INTO "artikl"("id", "naziv", "proizvodjac") VALUES(nextval('artikl_seq'), 'Puding jagoda', 'CENTROPROIZVOD');
