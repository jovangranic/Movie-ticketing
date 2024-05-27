import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FilmComponent } from './main/film/film.component';
import { RezervacijaComponent } from './main/rezervacija/rezervacija.component';
import { BioskopComponent } from './main/bioskop/bioskop.component';
import { SalaComponent } from './main/sala/sala.component';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatTableModule} from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FilmDialogComponent } from './dialogs/film-dialog/film-dialog.component';
import { BioskopDialogComponent } from './dialogs/bioskop-dialog/bioskop-dialog.component';
import { SalaDialogComponent } from './dialogs/sala-dialog/sala-dialog.component';
import { RezervacijaDialogComponent } from './dialogs/rezervacija-dialog/rezervacija-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    FilmComponent,
    RezervacijaComponent,
    BioskopComponent,
    SalaComponent,
    HomeComponent,
    AboutComponent,
    AuthorComponent,
    FilmDialogComponent,
    BioskopDialogComponent,
    SalaDialogComponent,
    RezervacijaDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatExpansionModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatButtonModule,
    MatToolbarModule,
    MatTableModule,
    MatDialogModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSelectModule,
    MatSortModule,
    MatPaginatorModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
