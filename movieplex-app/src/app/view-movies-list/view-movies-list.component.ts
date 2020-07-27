import { Component, OnInit, Input } from '@angular/core';
import { Movie } from './../model/movie.model';
import { MovieService } from './../movie.service';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-view-movies-list',
  templateUrl: './view-movies-list.component.html',
  styleUrls: ['./view-movies-list.component.css']
})
export class ViewMoviesListComponent implements OnInit {

  //For holding list of movies
  moviesList: Movie[];
  //movieService: MovieService;


  constructor(private movieService: MovieService) {
    

    /* this.moviesList = [
     new Movie("1", "Fast 5", "Action", "Henricks", "Spielberg", "To be decided", "English"),
     new Movie("2", "The Mummy Returns", "Fantasy", "Horner", "Cameroon", "Today", "English"),
     new Movie("3", "The Scorpion Age", "Action", "Squirrel", "Battleman", "24/07/2020", "English")
   ]  */
  }

  ngOnInit(): void {
    //this.movieService.getMoviesList().subscribe(moviesList => this.moviesList);
     this.movieService.getMoviesList().subscribe((data)=>{this.moviesList=data},
        err=>console.log(err),
             
      );
    }
   
  }


