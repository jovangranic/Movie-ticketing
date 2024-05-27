import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { FilmDialogComponent } from 'src/app/dialogs/film-dialog/film-dialog.component';
import { Film } from 'src/app/models/film';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.css']
})
export class FilmComponent implements OnInit, OnDestroy{
  displayedColumns = ['id', 'naziv', 'recenzija', 'trajanje', 'zanr', 'actions'];
  dataSource!:MatTableDataSource<Film>;
  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;
  constructor(private service:FilmService, public dialog:MatDialog){}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public loadData() {
    this.subscription = this.service.getAllFilms().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
    }
  }

  public openDialog(flag:number, id?:number, naziv?:string, recenzija?: number, trajanje?:number, zanr?:string) {
    const dialogRef = this.dialog.open(FilmDialogComponent, {data : {id, naziv, recenzija, trajanje, zanr}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }

  public applyFilter(filter:any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

}
