
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'Zrenjaninski bioskop', 'Bulevar Kralja Petra I bb, Zrenjanin');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'CINEPLEX BIG Zrenjanin', 'Bagljas Zapad 4, Zrenjanin');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'CineStar Novi Sad', 'Bulevar Oslobođenja 102, Novi Sad');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'Cineplexx Ušće Shopping Center', 'Bulevar Mihajla Pupina 4, Beograd');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'CineGrand Delta City', 'Jurija Gagarina 16, Beograd');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'), 'Cineplexx Niš', 'Jadranska 19, Niš');

INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Shawshank Redemption', 9.3, 142, 'Drama');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Godfather', 9.2, 175, 'Krimi');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Dark Knight', 9.0, 152, 'Akcija');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Schindler''s List', 8.9, 195, 'Biografija');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Pulp Fiction', 8.9, 154, 'Krimi');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Lord of the Rings: The Return of the King', 8.9, 201, 'Avantura');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Good, the Bad and the Ugly', 8.8, 178, 'Western');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Fight Club', 8.8, 139, 'Drama');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Forrest Gump', 8.8, 142, 'Drama');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Inception', 8.8, 148, 'Akcija');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Lord of the Rings: The Fellowship of the Ring', 8.8, 178, 'Avantura');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Star Wars: Episode V - The Empire Strikes Back', 8.7, 124, 'Akcija');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'The Matrix', 8.7, 136, 'Akcija');
INSERT INTO "film"("id", "naziv", "recenzija", "trajanje", "zanr") VALUES(nextval('film_seq'), 'Goodfellas', 8.7, 146, 'Biografija');

INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 1, 150, 8);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 2, 130, 6);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 1, 200, 10);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 2, 175, 9);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 3, 180, 9);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 2, 220, 11);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 1, 250, 12);
INSERT INTO "sala"("id", "bioskop", "kapacitet", "broj_redova") VALUES (nextval('sala_seq'), 3, 180, 9);

INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 1, 1, 3, 750, to_date('01.01.2024.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 2, 1, 2, 1050, to_date('04.03.2024.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 1, 1, 1, 750, to_date('09.09.2023.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 2, 1, 2, 1050, to_date('03.02.2024.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 3, 2, 3, 600, to_date('12.11.2021.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 4, 2, 3, 900, to_date('15.01.2024.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 5, 3, 2, 800, to_date('08.10.2023.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 6, 3, 1, 1200, to_date('22.08.2022.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 7, 4, 1, 600, to_date('25.02.2022.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 8, 4, 2, 1100, to_date('21.12.2022.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 9, 5, 3, 750, to_date('16.09.2023.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 10, 5, 1, 1000, to_date('28.04.2024.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 11, 6, 2, 550, to_date('12.03.2024.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 12, 6, 3, 900, to_date('18.06.2021.', 'dd.mm.yyyy.'), true);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 13, 1, 2, 1000, to_date('03.10.2022.', 'dd.mm.yyyy.'), false);
INSERT INTO "rezervacija"("id", "film", "sala", "broj_osoba", "cena", "datum", "placeno") VALUES (nextval('rezervacija_seq'), 14, 2, 3, 1100, to_date('27.07.2022.', 'dd.mm.yyyy.'), true);