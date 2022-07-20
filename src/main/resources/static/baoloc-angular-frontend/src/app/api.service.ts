import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { catchError, Observable, throwError } from 'rxjs';
import { Result } from './results-table/results-table.component';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private readonly apiUrl = environment.serverUrl;

  constructor(private http: HttpClient) {}

  getResults(): Observable<Result[]> {
    return this.http
      .get<Result[]>(this.apiUrl.concat('/results'))
      .pipe(catchError(this.handleError));
  }

  deleteResult(result: Result): Observable<string> {
    return this.http
      .delete(this.apiUrl.concat(`/results/${result.id}`), {
        responseType: 'text',
      })
      .pipe(catchError(this.handleError));
  }

  process(customResult: number): Observable<Result> {
    return this.http
      .post<Result>(this.apiUrl.concat('/process'), {
        expectedResult: customResult,
      })
      .pipe(catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}
