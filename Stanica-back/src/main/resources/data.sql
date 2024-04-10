INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
              


INSERT INTO prevoznici (id, adresa, naziv, pib) VALUE (1,"Novosadski put bb","Dunavprevoz","123412300");
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUE (2,"Bulevar Stefana Nemanje","Nis ekspres","234198766");
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUE (3,"Bulevar Cara Dusana","Severtrans","098712345");
INSERT INTO prevoznici (id, adresa, naziv, pib) VALUE (4,"Bulevar Kralja Milana","Lasta DOO","190234198");



INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(1,50,600,"08:00","BackaPalanka-NoviSad",1,30);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(2,50,500,"18:00","Nis-Pirot",2,20);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(3,50,800,"08:30","Beograd-NoviSad",4,3);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(4,50,1000,"07:00","Subotica-NoviSad",3,12);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(5,50,450,"07:20","Sombor-Subotica",3,40);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(6,50,200,"10:00","BackaPalanka-Celarevo",1,0);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(7,50,600,"11:45","Nis-Leskovac",2,10);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(8,50,800,"12:10","Beograd-Smederevo",4,4);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(9,50,350,"13:00","BackaPalanka-Begec",1,13);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(10,50,600,"14:00","Aleksinac-Nis",2,0);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(11,50,550,"20:00","Subotica-Apatin",3,0);
INSERT INTO linije (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id, broj_slobodnih_mesta)  
VALUES(12,50,1000,"13:40","Beograd-Subotica",4,11);


INSERT INTO rezervacije(id, datum_rezervacije, destinacija, broj_mesta, prevoznik_id, linija_id) 
VALUES(1,"22.04.2024.","BackaPalanka-NoviSad",4,1,1);

