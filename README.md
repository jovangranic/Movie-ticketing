# Movie-ticketing

Ovaj projekat predstavlja kompletan sistem za prodaju karata za filmove, dizajniran za upravljanje filmovima, bioskopima, salama i rezervacijama. Sistem je podeljen na frontend i backend deo, svaki sa specifičnim funkcionalnostima.

## Opis Projekta

### Frontend
Frontend deo aplikacije je razvijen koristeći **Angular**, moderan framework za izgradnju korisničkih interfejsa. Omogućava korisnicima da:

- **Pregledaju filmove**: Vide listu filmova sa detaljima kao što su naziv, recenzija, trajanje i žanr.
- **Dodaju filmove**: Popunjavaju formu za dodavanje novih filmova u sistem.
- **Ažuriraju filmove**: Izmene informacije o postojećim filmovima.
- **Brišu filmove**: Uklanjaju filmove iz sistema.
- **Pregledaju bioskope**: Vide sve bioskope u sistemu.
- **Rezervišu karte**: Odabiraju filmove i rezervišu karte za različite prikaze u bioskopima.

### Backend
Backend deo aplikacije je razvijen koristeći **Spring Boot**, popularan framework za izgradnju Java aplikacija. Backend pruža REST API sa sledećim funkcionalnostima:

- **Upravljanje filmovima**: CRUD (Create, Read, Update, Delete) operacije za filmove.
- **Upravljanje bioskopima**: CRUD operacije za bioskope.
- **Upravljanje salama**: CRUD operacije za sale.
- **Upravljanje rezervacijama**: CRUD operacije za rezervacije, kao i mogućnost pretrage rezervacija na osnovu različitih kriterijuma kao što su cena i status plaćanja.

## Instalacija

1. **Frontend**:
   - Preuzmite Angular aplikaciju sa repozitorijuma.
   - Pokrenite `npm install` za instalaciju svih potrebnih zavisnosti.
   - Pokrenite aplikaciju sa `ng serve` i otvorite `http://localhost:4200` u web pregledaču.

2. **Backend**:
   - Preuzmite Spring Boot aplikaciju sa repozitorijuma.
   - Konfigurišite aplikaciju u `application.properties` datoteci prema vašim potrebama (npr. konekcija sa bazom podataka).
   - Pokrenite aplikaciju kao Spring Boot aplikaciju koristeći `mvn spring-boot:run` ili IDE opciju za pokretanje.

## Korišćenje

- **Frontend**: Kroz web interfejs na URL-u http://localhost:4200/ možete pristupiti svim funkcionalnostima aplikacije. Možete dodavati, ažurirati i brisati filmove, kao i rezervisati karte za prikaze u bioskopima.
- **API**: Backend API pruža različite endpoint-e za upravljanje filmovima, bioskopima, salama i rezervacijama.
