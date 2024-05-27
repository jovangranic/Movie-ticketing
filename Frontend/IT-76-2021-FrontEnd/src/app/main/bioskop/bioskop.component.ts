
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
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
}
