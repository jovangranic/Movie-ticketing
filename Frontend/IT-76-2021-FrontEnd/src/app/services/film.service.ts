import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FILM_URL } from '../constants';
import { Film } from '../models/film';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  constructor(private httpClient: HttpClient) { }

  public getAllFilms():Observable<any> {
    return this.httpClient.get(`${FILM_URL}`)
  }

  public createFilm(film: Film): Observable<Film> {
    return this.httpClient.post<Film>(FILM_URL, film);
  }

  public updateFilm(film: Film): Observable<Film> {
    return this.httpClient.put<Film>(`${FILM_URL}/id/${film.id}`, film);
  }

  public deleteFilm(filmId: number): Observable<string> {
    return this.httpClient.delete<string>(`${FILM_URL}/id/${filmId}`, { responseType: "text" as "json" });
  }
}
