import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Film } from 'src/app/models/film';
import { Rezervacija } from 'src/app/models/rezervacija';
import { RezervacijaService } from 'src/app/services/rezervacija.service';
import { RezervacijaDialogComponent } from '../../dialogs/rezervacija-dialog/rezervacija-dialog.component';
import { Sala } from 'src/app/models/sala';

@Component({
  selector: 'app-rezervacija',
  templateUrl: './rezervacija.component.html',
  styleUrls: ['./rezervacija.component.css'],
})
export class RezervacijaComponent implements OnChanges {
  dataSource!: MatTableDataSource<Rezervacija>;
  displayedColumns = [
    'id',
    'brojOsoba',
    'cenaKarte',
    'datum',
    'placeno',
    'film',
    'actions',
  ];
  subscription!: Subscription;

  @Input() childSelectSala!: Sala;
  constructor(
    private rezervacijaService: RezervacijaService,
    public dialog: MatDialog
  ) {}

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData(): void {
    this.subscription = this.rezervacijaService
      .getRezervacijaBySala(this.childSelectSala.id)
      .subscribe({
        next: (data) => {
          this.dataSource = new MatTableDataSource(data);
        },
        error: (error: Error) => {
          console.log(error.name + ' ' + error.message);
        },
      });
  }

  public openDialog(
    flag: number,
    id?: number,
    broj_osoba?: number,
    cena?: number,
    datum?: Date,
    placeno?: boolean,
    film?: Film
  ): void {
    const dialogRef = this.dialog.open(RezervacijaDialogComponent, {
      data: { id, broj_osoba, cena, datum, placeno, film },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.componentInstance.data.sala = this.childSelectSala;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }
}
