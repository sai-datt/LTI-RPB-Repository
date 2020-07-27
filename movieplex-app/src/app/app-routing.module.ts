import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewMoviesListComponent } from './view-movies-list/view-movies-list.component';


const routes: Routes = [
  {path:"viewMoviesList",component: ViewMoviesListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
