import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bioskop } from 'src/app/models/bioskop';
import { Sala } from 'src/app/models/sala';
import { SalaService } from 'src/app/services/sala.service';
import { BioskopService } from 'src/app/services/bioskop.service';

@Component({
  selector: 'app-sala-dialog',
  templateUrl: './sala-dialog.component.html',
  styleUrls: ['./sala-dialog.component.css'],
})
export class SalaDialogComponent implements OnInit {
  flag!: number;
  bioskopi!: Bioskop[];

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Sala>,
    @Inject(MAT_DIALOG_DATA) public data: Sala,
    public service: SalaService,
    public bioskopService: BioskopService
  ) {}

  ngOnInit(): void {
    this.bioskopService.getAllBioskop().subscribe((data) => {
      this.bioskopi = data;
    });
  }

  public compare(a: any, b: any) {
    return a.id == b.id;
  }

  public add() {
    this.service.addSala(this.data).subscribe((data) => {
      this.snackBar.open(`Uspešno dodata sala sa ID: ${data.id}`, `U redu`, {
        duration: 2500,
      });
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open(`Neuspešno dodavanje`, `Zatvori`, {
          duration: 2500,
        });
      };
  }

  public update() {
    this.service.updateSala(this.data).subscribe((data) => {
      this.snackBar.open(
        `Sala sa ID: ${data.id} je uspešno ažurirana`,
        `U redu`,
        { duration: 2500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open(`Neuspešno ažuriranje`, `Zatvori`, {
          duration: 2500,
        });
      };
  }

  public delete() {
    this.service.deleteSala(this.data.id).subscribe((data) => {
      this.snackBar.open(
        `Sala sa ID: ${data.id} je uspešno obrisana`,
        `U redu`,
        { duration: 2500 }
      );
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open(`Neuspešno brisanje`, `Zatvori`, { duration: 2500 });
      };
  }

  public cancel() {
    // console.log(JSON.stringify(this.data));
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena!`, `Zatvori`, {
      duration: 2500,
    });
  }
}
