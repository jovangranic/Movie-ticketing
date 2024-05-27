import { Film } from "./film";
import { Sala } from "./sala";

export class Rezervacija {
    id!:number;
    brojOsoba!:number;
    cenaKarte!:number;
    datum!:Date;
    placeno!:boolean;
    film!:Film;
    sala!:Sala;
}