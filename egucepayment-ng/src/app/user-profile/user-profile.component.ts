import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { UserService } from "../services";
import { Router } from "@angular/router";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html'
})
export class UserProfileComponent implements OnInit, OnDestroy {

    errorMessage: any;

    alertMessage: {
        severity: string;
        summary: string;
        detail: string;
    };

    actualPassword: string;
    passwordFirst: string;
    passwordSecond: string;
    private setPasswordSub: Subscription;
    private findUserSub: Subscription;
    showConfirm: boolean;
    currentUser;

    constructor(private http: HttpService, private userService: UserService, private router: Router) {}

    ngOnInit() {
        this.findUser();
    }

    ngOnDestroy() {
        if(this.setPasswordSub) {
          this.setPasswordSub.unsubscribe();
        }
        this.findUserSub.unsubscribe();
    }

    private setPassword() {
        if(this.passwordFirst !== this.passwordSecond) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'passwords.doesnt.matched'};
            return;
        }
        let setPasswordDto = {
            actual: this.actualPassword,
            update: this.passwordFirst
        }
        this.setPasswordSub = this.http.post('users/password', setPasswordDto).subscribe(
            res => {
                let response = res.json();
                if(0 === +response.data) {
                    this.alertMessage = {severity: 'danger', summary: 'error', detail: 'Password incorrect'};
                } else {
                    this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'password.changed'};
                    this.actualPassword = null;
                    this.passwordFirst = null;
                    this.passwordSecond = null;
                }
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    onConfirm(event) {
        this.setPassword();
    }

    private findUser() {
        this.findUserSub = this.http.get('users/by-login', true).subscribe(
            data => {
                let response = data.json();
                if(response.data) {
                    this.userService.goToHome(this.router);
                } else {
                    this.currentUser = response;
                }
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

}
