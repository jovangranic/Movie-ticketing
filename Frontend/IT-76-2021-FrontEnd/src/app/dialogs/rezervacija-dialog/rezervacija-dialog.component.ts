import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Film } from 'src/app/models/film';
import { Rezervacija } from 'src/app/models/rezervacija';
import { FilmService } from 'src/app/services/film.service';
import { RezervacijaService } from 'src/app/services/rezervacija.service';

@Component({
  selector: 'app-rezervacija-dialog',
  templateUrl: './rezervacija-dialog.component.html',
  styleUrls: ['./rezervacija-dialog.component.css']
})
export class RezervacijaDialogComponent {

  flag!: number;
  filmovi!: Film[];

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Rezervacija>,
    @Inject (MAT_DIALOG_DATA) public data: Rezervacija,
    public service:RezervacijaService,
    public filmService:FilmService
  ){}

  ngOnInit(): void {
    console.log("Ulazni podaci:", this.data); // Dodajemo ispis ulaznih podataka
    this.filmService.getAllFilms().subscribe(
      (data) => {
        console.log("Filmovi:", data); // Dodajemo ispis podataka o filmovima
        this.filmovi = data;
      },
      (error) => {
        console.error("Greška prilikom dohvatanja filmova:", error); // Dodajemo ispis greške ako dođe do problema
      }
    );
  }

  public compare(a:any, b:any){
    return a.id == b.id;
  }

  public add(){
    this.service.addRezervacija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodata rezervacija sa ID: ${data.id}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration:1000});
    }
  }

  public update(){
    this.service.updateRezervacija(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azurirana rezervacija sa ID: ${data.id}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno azuriranje`, `Zatvori`, {duration:1000});
    }
  }

  public delete(){
    this.service.deleteRezervacija(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${data}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno brisanje`, `Zatvori`, {duration:1000});
    }
  }

  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena`, `Zatvori`, {duration:2500});
  }
}