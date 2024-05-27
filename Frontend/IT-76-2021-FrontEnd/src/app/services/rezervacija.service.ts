import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { REZERVACIJA_BY_SALA_URL, REZERVACIJA_URL } from '../constants';
import { Rezervacija } from '../models/rezervacija';

@Injectable({
  providedIn: 'root'
})
export class RezervacijaService {

  constructor(private httpClient: HttpClient) { }

  public getAllRezervacijas():Observable<any> {
    return this.httpClient.get(`${REZERVACIJA_URL}`);
  }

  public getRezervacijaBySala(salaId:number):Observable<any> {
    return this.httpClient.get(`${REZERVACIJA_BY_SALA_URL}/${salaId}`);
  }

  public addRezervacija(rezervacija:Rezervacija):Observable<any>{
    return this.httpClient.post(`${REZERVACIJA_URL}`, rezervacija);
  }

  public updateRezervacija(rezervacija:Rezervacija):Observable<any>{
    return this.httpClient.put(`${REZERVACIJA_URL}/id/${rezervacija.id}`, rezervacija);
  }

  public deleteRezervacija(rezervacijaId:number):Observable<any>{
    return this.httpClient.delete(`${REZERVACIJA_URL}/id/${rezervacijaId}`, {responseType:"text"});
  }
}