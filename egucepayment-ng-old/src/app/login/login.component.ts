import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

import {LoginService} from './login.service';

import { Config } from '../config';
import { Utils } from '../utils';
import { Subscription } from "rxjs/Subscription";
import { TranslateService } from "ng2-translate";
import { UserService } from "../services";
import { PersistenceService, StorageType } from "angular-persistence";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    providers: [LoginService, UserService]
})
export class LoginComponent implements OnInit, OnDestroy {

    alertMessage: {
        severity: string;
        summary: string;
        detail: string;
    };
    private errorMessage: any;

    resetPassword: boolean;

    private authenticatedUser: any;

    public userLogin: string;
    public password: string;
    private token: string;
    private userRoles: string

    private loginSub: Subscription;
    private setPasswordSub: Subscription;

    newPasswordFirst: string;
    newPasswordSecond: string;
    setPasswordButEnable: boolean;

    lang: string;

    constructor(private loginService: LoginService, private router: Router, private translateService: TranslateService,
    private userService: UserService, private persistenceService: PersistenceService) {}

    ngOnInit() {
        this.lang = this.translateService.currentLang;
    }

    ngOnDestroy() {
        if(this.loginSub) {
            this.loginSub.unsubscribe();
        }
        if(this.setPasswordSub) {
            this.setPasswordSub.unsubscribe();
        }
    }

    login() {
        this.persistenceService.set('test', 'test', {type: StorageType.MEMORY});
        this.loginSub = this.loginService.login(this.userLogin, this.password).subscribe(
            res => {
                console.info(res);
                let response = res;
                if(response.data) {
                    if(0 === +response.data) {
                        this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'account.no.yet.activated'};
                        return;
                    }
                    if(1 === +response.data) {
                        this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'account.locked'};
                        return;
                    }
                    if(2 === +response.data) {
                        this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'dont.have.partner'};
                        return;
                    }
                    return;
                } else {
                    this.authenticatedUser = response;
                    // le mot de passe doit t-il être réinitialisé ???
                    if(this.authenticatedUser.passwordToBeReset) {
                        // on ouvre un dialogue pr lui demande de mettre à jour son mot de passe
                        this.token = this.authenticatedUser.token;
                        this.userRoles = JSON.stringify(this.authenticatedUser.roles);
                        this.resetPassword = true;
                        return;
                    }
                    this.validateConnection();
                }
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
                let statusCode = this.errorMessage.status;
                if(statusCode === Config.NOT_ACCEPTABLE_STATUS_CODE) {
                    this.alertMessage = {severity: 'danger', summary: 'error', detail: 'login.password.incorrect'};
                } else {
                    //this.loginMessage = {severity: 'danger', summary: 'error', detail: 'any.error.msg'};
                }
            }
        );
    }

    setPassword() {
        if(this.newPasswordFirst !== this.newPasswordSecond) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'passwords.doesnt.matched'};
            return;
        }
        this.setPasswordSub = this.loginService.setPassword(this.userLogin, this.newPasswordFirst, this.token, this.userRoles).subscribe(
            res => {
                let response = res;
                if(0 === +response.data) {
                    this.alertMessage = {severity: 'danger', summary: 'error', detail: 'Login incorrect'};
                    return;
                }
                this.resetPassword = false;
                this.validateConnection();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private validateConnection() {
        this.userService.writeUserInfos(this.authenticatedUser);
        this.userService.goToHome(this.router);
    }

    subscribe() {
        this.router.navigate(['/users-subscription']);
    }

    changeLanguage(lang: string) {
        this.translateService.use(lang);
    }

}
