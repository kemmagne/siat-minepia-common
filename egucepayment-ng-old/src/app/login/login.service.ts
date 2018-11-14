import {Injectable, Inject} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { Config } from '../config';
import { Utils } from '../utils';

@Injectable()
export class LoginService {

    private url = `${Config.PROTOCOL}//${Config.REST_API_HOST}:${Config.PORT + Config.BASE_REST_API}`;

    constructor(private http: Http) {}

    login(userLogin, password: string): Observable<any> {
        const headers = new Headers();
        headers.append(Config.USER_LOGIN_KEY, userLogin);
        headers.append(Config.USER_PASSWORD_KEY, password);
        headers.append(Config.BROWSER_FINGER_PRINT_KEY, Utils.getBrowserPrint());
        //
        return this.http.post(this.url + 'auth/login', null, {headers: headers})
        .map((response: Response) => {
            return response.json();
        })
        .catch((error: Response) => {
            return Observable.throw(error);
        });
    }

    setPassword(userLogin, password, token, userRoles: string): Observable<any> {
        const headers = new Headers();
        headers.append(Config.USER_LOGIN_KEY, userLogin);
        headers.append(Config.USER_ROLES_KEY, userRoles);
        headers.append(Config.AUTHORIZATION_KEY, Config.AUTHORIZATION_PREFIX + token);
        headers.append(Config.BROWSER_FINGER_PRINT_KEY, Utils.getBrowserPrint());
        //
        return this.http.post(this.url + 'users/password', {update: password}, {headers: headers})
        .map((response: Response) => {
            return response.json();
        })
        .catch((error: Response) => {
            return Observable.throw(error);
        });
    }

}
