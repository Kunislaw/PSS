import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, from, throwError } from 'rxjs';
import { User } from './User';
import { catchError } from 'rxjs/operators';
import { Delegation } from './Delegation';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  rootUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  public registerUser(user: User): Observable<User> {
      return this.http.post<User>(this.rootUrl + "/register", user)
      .pipe(
        catchError(this.handleError)
      );
  }

  public getAllUsers(): Observable<User[]> {
    let credentials = localStorage.getItem("credentials");
    const headers = new HttpHeaders({Authorization: "Basic " + credentials});
    return this.http.get<User[]>(this.rootUrl + "/home/allusers",{headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  public changePassword(userId:number, newPassword: string): Observable<{}>{
    const params = new HttpParams();
    params.set("userId", userId.toString());
    params.set("newPassword", newPassword);
    return this.http.put(this.rootUrl + "/home/changepassword", null, {params : params})
    .pipe(
      catchError(this.handleError)
    );
  }
  public deleteUser(userId: number): Observable<{}>{
    const params = new HttpParams();
    params.set("userId", userId.toString());
    return this.http.delete(this.rootUrl + "/home/deleteuser", { params: params })
    .pipe(
      catchError(this.handleError)
    );
  }

  public addDelegation(userId: number, delegation: Delegation): Observable<Delegation>{
    const params = new HttpParams();
    params.set("userId", userId.toString());
    return this.http.post<Delegation>(this.rootUrl + "/home/adddelegation", delegation, {params: params})
    .pipe(
      catchError(this.handleError)
    )
  }

  public deleteDelegation(userId: number, delegationId: number): Observable<{}> {
    const params = new HttpParams();
    params.set("userId", userId.toString());
    params.set("delegationId", delegationId.toString());
    return this.http.delete(this.rootUrl + "/home/deletedelegation", {params: params})
    .pipe(
      catchError(this.handleError)
    )
  }

  public getAllUsersByRoleName(roleName: string): Observable<User[]> {
    const params = new HttpParams();
    params.set("roleName", roleName);
    return this.http.get<User[]>(this.rootUrl + "/home/getAllUsersByRoleName", {params: params})
    .pipe(
      catchError(this.handleError)
    );
  }
  public login(username: string, password: string){
     const headers = new HttpHeaders({Authorization: "Basic " + btoa(username+":"+password)});
     return this.http.get("http://localhost:8080/login",{headers,responseType:'text' as 'json'});
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  };

}
