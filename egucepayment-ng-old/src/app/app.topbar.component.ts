import {Component} from '@angular/core';
import {AppComponent} from './app.component';
import { TranslateService } from "ng2-translate";
import { LayoutComponent } from "./layout/layout.component";
import { UserService } from "./services";
import { Router } from "@angular/router";
import { LoginService } from "./login/login.service";

@Component({
    selector: 'app-topbar',
    template: `
        <div class="topbar clearfix">
            <div class="topbar-left">
                <div class="logo"></div>
            </div>

            <div *ngIf="false" class="topbar-center">
                <h1 style="color:white" class="justify-content-center">e-GUCE PAYMENT SYSTEM</h1>
            </div>

            <div class="topbar-right">
                <a id="menu-button" href="javascript:void(0)" (click)="app.onMenuButtonClick($event)">
                    <i></i>
                </a>

                <a id="topbar-menu-button" href="javascript:void(0)" (click)="app.onTopbarMenuButtonClick($event)">
                    <i class="material-icons">menu</i>
                </a>

                <ul class="topbar-items animated fadeInDown" [ngClass]="{'topbar-items-visible': app.topbarMenuActive}">
                    <li>
                        <a href="javascript:void(0)" (click)="logout()">
                            <i class="topbar-icon material-icons">{{isLoggedIn() ? 'power_settings_new' : 'exit_to_app'}}</i>
                            <span class="topbar-item-name">{{(isLoggedIn() ? 'logout' : 'login') | translate}}</span>
                        </a>
                    </li>
                    <li *ngIf="isLoggedIn()" #settings [ngClass]="{'active-top-menu':app.activeTopbarItem === settings}">
                        <a href="javascript:void(0)" (click)="app.onTopbarItemClick($event, settings)">
                            <i class="topbar-icon material-icons">person</i>
                            <span class="topbar-item-name">{{'user' | translate}}</span>
                        </a>
                        <ul class="ultima-menu animated fadeInDown">
                            <li role="menuitem">
                                <a href="javascript:void(0)" (click)="goToUserProfile()">
                                    <i class="material-icons">perm_identity</i>
                                    <span>{{'profile' | translate}}</span>
                                </a>
                            </li>
                            <li *ngIf="isLoggedIn()" role="menuitem">
                                <a href="javascript:void(0)" (click)="lockScreen()">
                                    <i class="material-icons">lock</i>
                                    <span>{{'lock.screen' | translate}}</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li #language [ngClass]="{'active-top-menu':app.activeTopbarItem === language}">
                        <a href="javascript:void(0)" (click)="app.onTopbarItemClick($event, language)">
                            <i class="topbar-icon material-icons">language</i>
                            <span class="topbar-item-name">{{'language' | translate}}</span>
                        </a>
                        <ul class="ultima-menu animated fadeInDown">
                            <li role="menuitem">
                                <a href="javascript:void(0)" (click)="changeLanguage('fr')">
                                    <i class="material-icons">language</i><span>{{'fr' | translate}}</span>
                                </a>
                            </li>
                            <li role="menuitem">
                                <a href="javascript:void(0)" (click)="changeLanguage('en')">
                                    <i class="material-icons">language</i><span>{{'en' | translate}}</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    `
})
export class AppTopbarComponent {

    constructor(public app: LayoutComponent, private translate: TranslateService, private userService: UserService, private router: Router) {}

    changeLanguage(language: string) {
        this.translate.use(language);
    }

    lockScreen() {
        this.userService.clearUserInfos();
        this.app.lockScreenHeaderMsg = 'screen.locked';
        this.app.lockScreen = true;
    }

    logout() {
        this.userService.logout(this.router);
    }

    goToUserProfile() {
        this.router.navigate(['/app/user-profile']);
    }

    isLoggedIn() {
        return this.userService.isLoggedIn();
    }

}
