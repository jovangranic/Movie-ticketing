import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BIOSKOP_URL } from '../constants';
import { Bioskop } from '../models/bioskop';

@Injectable({
  providedIn: 'root'
})
export class BioskopService {

  constructor(private httpClient:HttpClient) { }

  public getAllBioskop():Observable<any>{
    return this.httpClient.get(`${BIOSKOP_URL}`);
  }

  public addBioskop(bioskop:Bioskop):Observable<any>{
    return this.httpClient.post(`${BIOSKOP_URL}`, bioskop);
  }

  public updateBioskop(bioskop:Bioskop):Observable<any>{
    return this.httpClient.put(`${BIOSKOP_URL}/id/${bioskop.id}`, bioskop);
  }

  public deleteBioskop(bioskopId:number):Observable<any> {
    return this.httpClient.delete(`${BIOSKOP_URL}/id/${bioskopId}`, {responseType:"text"});
  }
}