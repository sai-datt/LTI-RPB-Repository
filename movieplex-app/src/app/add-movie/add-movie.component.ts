import { Component, OnInit, Input } from '@angular/core';
import { Movie } from './../model/movie.model';
import {ViewMoviesListComponent} from './../view-movies-list/view-movies-list.component';



@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  constructor() {     
  }

  @Input()
  moviesList:Movie[];  

  addMovie(txtName: HTMLInputElement, txtCategory: HTMLInputElement, txtProducer: HTMLInputElement,
    txtDirector: HTMLInputElement, txtReleaseDate: HTMLInputElement, txtLanguage: HTMLInputElement) {

    let movie = new Movie(null, txtName.value, txtCategory.value, txtProducer.value, txtDirector.value,
           txtReleaseDate.value, txtLanguage.value);
    
      this.moviesList.push(movie);   

    //Reset the movie adding form
    txtName.value = "";
    txtCategory.value = "";
    txtProducer.value = "";
    txtDirector.value = "";
    txtReleaseDate.value = "";
    txtLanguage.value = "";
  }

  ngOnInit(): void {
  }

}
