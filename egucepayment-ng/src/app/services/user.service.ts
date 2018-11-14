import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Config } from '../config';

@Injectable()
export class UserService {

    constructor() {}

    isLoggedIn(): boolean {
        return /*true || */(!!localStorage.getItem(Config.JWT_TOKEN_KEY) && !!localStorage.getItem(Config.USER_LOGIN_KEY)
        && !!localStorage.getItem(Config.USER_ROLES_KEY));
    }

    logout(router: Router) {
        this.clearUserInfos();
        localStorage.removeItem(Config.USER_LOGIN_KEY);
        router.navigate(['/login']);
    }

    hasRoles(roleNames: string[]): boolean {
        if(!this.isLoggedIn()) {
            return false;
        }
        let userRolesNames = JSON.parse(localStorage.getItem(Config.USER_ROLES_KEY));
        
        if(userRolesNames) {
            for(let role of roleNames) {
                if(-1 < userRolesNames.indexOf(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    getUserFullName(): string {
        return localStorage.getItem(Config.USER_LOGIN_KEY);
    }

    getUserLogin(): string {
        return localStorage.getItem(Config.USER_LOGIN_KEY);
    }

    goToHome(router: Router) {
        if(this.isLoggedIn()) {
            router.navigate(['/app/home']);
        } else {
            router.navigate(['/login']);
        }
    }

    clearUserInfos() {
        localStorage.removeItem(Config.JWT_TOKEN_KEY);
        //localStorage.removeItem(Config.USER_LOGIN_KEY);
        localStorage.removeItem(Config.USER_ROLES_KEY);
    }

    writeUserInfos(user) {
        localStorage.setItem(Config.USER_LOGIN_KEY, user.login);
        localStorage.setItem(Config.JWT_TOKEN_KEY, user.token);
        localStorage.setItem(Config.USER_ROLES_KEY, JSON.stringify(user.roles));
    }

}
