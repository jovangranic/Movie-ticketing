
import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription, forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Bioskop } from 'src/app/models/bioskop';
import { BioskopService } from 'src/app/services/bioskop.service';
import { BioskopDialogComponent } from '../../dialogs/bioskop-dialog/bioskop-dialog.component';

@Component({
  selector: 'app-bioskop',
  templateUrl: './bioskop.component.html',
  styleUrls: ['./bioskop.component.css']
})
export class BioskopComponent {
  dataSource!: MatTableDataSource<Bioskop>;
  displayedColumns = ['id', 'naziv', 'adresa', 'actions'];
  subscription!: Subscription;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(
    private bioskopService: BioskopService,
    public dialog: MatDialog
  ) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    (this.subscription = this.bioskopService
      .getAllBioskop()
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource(data);
        this.bindTableFeatures();
      })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    naziv?: string,
    adresa?: string
  ){
    const dialogRef = this.dialog.open(BioskopDialogComponent, {data: { id, naziv, adresa }, });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }

  public applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim();

    if (filterValue === '') {
      this.loadData();
    } else {
      forkJoin({
        byNaziv: this.bioskopService
          .searchByNaziv(filterValue)
          .pipe(catchError(() => of([] as Bioskop[]))),
        byAdresa: this.bioskopService
          .searchByAdresa(filterValue)
          .pipe(catchError(() => of([] as Bioskop[])))
      }).subscribe(({ byNaziv, byAdresa }) => {
        const merged = [...byNaziv, ...byAdresa];
        const unique = merged.filter(
          (bioskop, index, self) =>
            index === self.findIndex((item) => item.id === bioskop.id)
        );

        this.dataSource = new MatTableDataSource(unique);
        this.bindTableFeatures();
      });
    }
  }

  private bindTableFeatures(): void {
    if (this.sort) {
      this.dataSource.sort = this.sort;
    }
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }
  }
}
