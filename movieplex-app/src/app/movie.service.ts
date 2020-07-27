import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Movie } from './model/movie.model';



@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private base_url = "http://localhost:6060/movie-api/";

  constructor(private httpClient: HttpClient) { }

  getMoviesList(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>('${this.base_url}' + 'getMoviesList');
  }
}