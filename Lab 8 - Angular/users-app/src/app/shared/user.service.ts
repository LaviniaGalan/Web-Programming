import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost/api';
  users: User[] = [];

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<User[]> {
    return this.httpClient
      .get<Array<User>>(`${this.baseUrl}/get-all.php`)
      .pipe(catchError(this.handleError));
  }
  deleteUser(id: number): Observable<any>{
    return this.httpClient
      .delete<User>(`${this.baseUrl}/delete-user.php/?id=${id}`)
      .pipe(catchError(this.handleError));
  }
  addUser(user: User): Observable<any>{
    console.log(user);
    return this.httpClient
      .post<User>(`${this.baseUrl}/add-user.php`, user)
      .pipe(catchError(this.handleError));
  }
  updateUser(user: User): Observable<any>{
    return this.httpClient
      .post<User>(`${this.baseUrl}/update-user.php`, user)
      .pipe(catchError(this.handleError));
  }
  filterUsers(filterBy: string, filterValue: string): Observable<any>{
    const params = new HttpParams()
      .set('filterBy', filterBy)
      .set('filterValue', filterValue);
    return this.httpClient
      .get<User>(`${this.baseUrl}/filter-users.php`, {params})
      .pipe(catchError(this.handleError));
  }
  handleError(response): Observable<any> {
    let errorMessage = '';
    if (response.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${response.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${response.status}\nMessage: ${response.error.message}`;
    }
    return throwError(errorMessage);
  }
}
