import {Component} from '@angular/core';
import { LayoutComponent } from './layout/layout.component';

@Component({
    selector: 'app-topbar',
    template: `
        <div class="topbar clearfix">
            <div class="topbar-left">
                <div class="logo"></div>
            </div>

            <div class="topbar-right">
                <a id="menu-button" href="#" (click)="app.onMenuButtonClick($event)">
                    <i></i>
                </a>

                <a id="rightpanel-menu-button" href="#" (click)="app.onRightPanelButtonClick($event)">
                    <i class="material-icons">more_vert</i>
                </a>

                <a id="topbar-menu-button" href="#" (click)="app.onTopbarMenuButtonClick($event)">
                    <i class="material-icons">menu</i>
                </a>

                <ul class="topbar-items animated fadeInDown" [ngClass]="{'topbar-items-visible': app.topbarMenuActive}">
                    <li #profile class="profile-item" *ngIf="app.profileMode==='top'||app.isHorizontal()"
                        [ngClass]="{'active-top-menu':app.activeTopbarItem === profile}">

                        <a href="#" (click)="app.onTopbarItemClick($event,profile)">
                            <i class="topbar-icon material-icons">person</i>
                            <span class="topbar-item-name">{{'user' | translate}}</span>
                        </a>

                        <ul class="ultima-menu animated fadeInDown">
                            <li role="menuitem">
                                <a href="#" (click)="app.onTopbarSubItemClick($event)">
                                    <i class="material-icons">person</i>
                                    <span>{{'profile' | translate}}</span>
                                </a>
                            </li>
                            <li role="menuitem">
                                <a href="#" (click)="app.onTopbarSubItemClick($event)">
                                    <i class="material-icons">lock</i>
                                    <span>{{'lock.screen' | translate}}</span>
                                </a>
                            </li>
                            <li role="menuitem">
                                <a href="#" (click)="logout()">
                                    <i class="material-icons">power_settings_new</i>
                                    <span>{{(app.isLoggedIn() ? 'logout' : 'login') | translate}}</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li #languageSettings [ngClass]="{'active-top-menu':app.activeTopbarItem === languageSettings}">
                        <a href="#" (click)="app.onTopbarItemClick($event,languageSettings)">
                            <i class="topbar-icon material-icons">language</i>
                            <span class="topbar-item-name">{{'language' | translate}}</span>
                        </a>
                        <ul class="ultima-menu animated fadeInDown">
                            <li role="menuitem">
                                <a href="#" (click)="app.changeLanguage('fr')">
                                    <i class="material-icons">language</i><span>{{'fr' | translate}}</span>
                                </a>
                            </li>
                            <li role="menuitem">
                                <a href="#" (click)="app.changeLanguage('en')">
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

    constructor(public app: LayoutComponent) {}

}
