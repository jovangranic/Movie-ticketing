import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FilmService {
  constructor(private httpClient: HttpClient) {}

  public getAllFilms(): Observable<any> {
    return this.httpClient.get('http://localhost:8082/film');
  }
}
