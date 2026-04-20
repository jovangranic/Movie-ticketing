import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { SalaDialogComponent } from 'src/app/dialogs/sala-dialog/sala-dialog.component';
import { Bioskop } from 'src/app/models/bioskop';
import { Sala } from 'src/app/models/sala';
import { SalaService } from 'src/app/services/sala.service';

@Component({
  selector: 'app-sala',
  templateUrl: './sala.component.html',
  styleUrls: ['./sala.component.css'],
})
export class SalaComponent implements OnInit, OnDestroy {
  displayedColumns = ['id', 'kapacitet', 'brojRedova', 'bioskop', 'actions'];
  dataSource!: MatTableDataSource<Sala>;
  subscription!: Subscription;
  parentSelectedSala!: Sala;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private service: SalaService, public dialog: MatDialog) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    // console.log('Log sale', this.loadData);
    this.loadData();
  }

  public selectRow(row: Sala) {
    this.parentSelectedSala = row;
  }

  public loadData() {
    (this.subscription = this.service.getAllSalas().subscribe((data) => {
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
    kapacitet?: number,
    broj_redova?: number,
    bioskop?: Bioskop
  ) {
    const dialogRef = this.dialog.open(SalaDialogComponent, {
      data: { id, kapacitet, broj_redova, bioskop },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
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
