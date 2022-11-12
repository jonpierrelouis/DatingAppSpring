import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import Login from '../models/Login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  
  private baseLoginUrl = `${environment.baseUrl}/user`;

  constructor(private http: HttpClient) { }

  /**
   * Method to check to see if the user is logged in
   * @returns The login object observable from the API
   */
  checkLoginStatus(): Observable<Login> {

    return this.http.post<Login>(`${this.baseLoginUrl}/check`, {headers: environment.jsonHeaders, withCredentials: environment.withCredentials});
  }

  /**
   * Method to login the user
   * @param email the email of the user
   * @param password the password of the user
   * @returns The login model of the user from the API
   */
  loginUser(email: string, password: string): Observable<Login> {

    let params = new HttpParams()
      .set('email', email)
      .set('password', password);

    return this.http.post<Login>(`${this.baseLoginUrl}/login`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials});
  }

  /**
   * Method to logout the user
   * @returns An empty Login Object
   */
  logoutUser(): Observable<Login> {

    return this.http.post<Login>(`${this.baseLoginUrl}/logout`, {headers: environment.jsonHeaders, withCredentials: environment.withCredentials});
  }

  /**
   * Method to create a new user
   * @param userName the user's name
   * @param email the email of the user
   * @param password the password of the user
   * @returns the login object of the new account if it was created, nothing if the account could not be created.
   */
  registerUser(userName: string, email: string, password: string): Observable<Login> {

    let params = new HttpParams()
      .set('username', userName)
      .set('email', email)
      .set('password', password);

    return this.http.post<Login>(`${this.baseLoginUrl}/register`, params, {headers: environment.paramHeaders, withCredentials: environment.withCredentials});
  }

  

}
