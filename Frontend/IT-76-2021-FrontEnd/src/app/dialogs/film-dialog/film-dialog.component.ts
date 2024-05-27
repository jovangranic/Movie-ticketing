import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Film } from 'src/app/models/film';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-film-dialog',
  templateUrl: './film-dialog.component.html',
  styleUrls: ['./film-dialog.component.css']
})
export class FilmDialogComponent {

  flag!:number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Film>,
    @Inject (MAT_DIALOG_DATA) public data: Film,
    public service: FilmService
  ){}

  public cancel() {
    this.dialogRef.close();
    this.snackBar.open("Odustali ste od izmena", "Zatvori", {duration: 3500});
  }

  public add() {
    this.service.createFilm(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Film sa nazivom ${data.naziv} je uspešno dodat`, "U redu", {duration:3500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno dodavanje!", "Zatvori", {duration:3500});
    }
  }

  public update() {
    this.service.updateFilm(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Film sa nazivom ${data.naziv} je uspešno ažuriran`, "U redu", {duration:3500})
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno ažuriranje!", "Zatvori", {duration:3500});
    }
  }

  public delete() {
    this.service.deleteFilm(this.data.id).subscribe(
      (data) => {
        this.snackBar.open("Uspešno brisanje", "U redu", {duration:3500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno brisanje!", "Zatvori", {duration:3500});
    }
  }
}