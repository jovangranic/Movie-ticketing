import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FilmComponent } from './main/film/film.component';
import { BioskopComponent } from './main/bioskop/bioskop.component';
import { SalaComponent } from './main/sala/sala.component';
import { RezervacijaComponent } from './main/rezervacija/rezervacija.component';
import { AboutComponent } from './utility/about/about.component';
import { HomeComponent } from './utility/home/home.component';
import { AuthorComponent } from './utility/author/author.component';

const routes: Routes = [
{path: 'film', component:FilmComponent},
{path: 'bioskop', component:BioskopComponent},
{path: 'sala', component:SalaComponent},
{path: 'rezervacija', component:RezervacijaComponent},
{path: 'about', component:AboutComponent},
{path: 'home', component:HomeComponent},
{path: 'author', component:AuthorComponent},
{path: '', component:HomeComponent, pathMatch:"full"}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
