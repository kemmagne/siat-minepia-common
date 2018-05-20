import { Component, AfterViewInit, ElementRef, Renderer, ViewChild, OnDestroy, OnInit, Inject } from '@angular/core';
import { LoginService } from "../login/login.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../config";
import { DOCUMENT } from "@angular/common";
import { Router, NavigationEnd } from "@angular/router";
import { Observable } from "rxjs/Observable";
import { UserService } from "../services";
import { Message } from "primeng/primeng";
import { TranslateService } from "ng2-translate";

enum MenuOrientation {
    STATIC,
    OVERLAY,
    SLIM,
    HORIZONTAL
}

declare var jQuery: any;

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css'],
    providers: [LoginService]
})
export class LayoutComponent implements AfterViewInit, OnInit, OnDestroy {

    msgs: Message[];

    errorMessage;
    alertMessage;

    layoutCompact = true;

    layoutMode: MenuOrientation = MenuOrientation.STATIC;

    darkMenu = false;

    profileMode = 'inline';

    rotateMenuButton: boolean;

    topbarMenuActive: boolean;

    overlayMenuActive: boolean;

    staticMenuDesktopInactive: boolean;

    staticMenuMobileActive: boolean;

    rightPanelActive: boolean;

    rightPanelClick: boolean;

    layoutContainer: HTMLDivElement;

    layoutMenuScroller: HTMLDivElement;

    menuClick: boolean;

    topbarItemClick: boolean;

    activeTopbarItem: any;

    resetMenu: boolean;

    menuHoverActive: boolean;

    userLogin: string;
    password: string;
    lockScreen: boolean;
    lockScreenHeaderMsg: string;
    private loginSub: Subscription;
    private timerSub: Subscription;
    private getMessageSumSub: Subscription;
    private getMessageDetSub: Subscription;

    @ViewChild('layoutContainer') layourContainerViewChild: ElementRef;

    @ViewChild('layoutMenuScroller') layoutMenuScrollerViewChild: ElementRef;

    constructor(private router: Router, public renderer: Renderer, private loginService: LoginService, @Inject(DOCUMENT) private document,
    private userService: UserService, private translateService: TranslateService) {}

    ngOnInit() {
        // Scroll to top on route change
        this.router.events.subscribe((evt) => {
            if (!(evt instanceof NavigationEnd)) {
              return;
            }
            document.body.scrollTop = 0;
        });

        // set session expiration
        this.initializeSessionExpiredSettings();
    }

    ngAfterViewInit() {
        this.layoutContainer = <HTMLDivElement> this.layourContainerViewChild.nativeElement;
        this.layoutMenuScroller = <HTMLDivElement> this.layoutMenuScrollerViewChild.nativeElement;

        setTimeout(() => {
            jQuery(this.layoutMenuScroller).nanoScroller({flash: true});
        }, 10);
    }

    onLayoutClick() {
        if (!this.topbarItemClick) {
            this.activeTopbarItem = null;
            this.topbarMenuActive = false;
        }

        if (!this.menuClick) {
            if (this.isHorizontal() || this.isSlim()) {
                this.resetMenu = true;
            }

            if (this.overlayMenuActive || this.staticMenuMobileActive) {
                this.hideOverlayMenu();
            }

            this.menuHoverActive = false;
        }

        if (!this.rightPanelClick) {
            this.rightPanelActive = false;
        }

        this.topbarItemClick = false;
        this.menuClick = false;
        this.rightPanelClick = false;
    }

    onMenuButtonClick(event) {
        this.menuClick = true;
        this.rotateMenuButton = !this.rotateMenuButton;
        this.topbarMenuActive = false;

        if (this.layoutMode === MenuOrientation.OVERLAY) {
            this.overlayMenuActive = !this.overlayMenuActive;
        } else {
            if (this.isDesktop()) {
                this.staticMenuDesktopInactive = !this.staticMenuDesktopInactive; } else {
                this.staticMenuMobileActive = !this.staticMenuMobileActive; }
        }

        event.preventDefault();
    }

    onMenuClick($event) {
        this.menuClick = true;
        this.resetMenu = false;

        if (!this.isHorizontal()) {
            setTimeout(() => {
                jQuery(this.layoutMenuScroller).nanoScroller();
            }, 500);
        }
    }

    onTopbarMenuButtonClick(event) {
        this.topbarItemClick = true;
        this.topbarMenuActive = !this.topbarMenuActive;

        this.hideOverlayMenu();

        event.preventDefault();
    }

    onTopbarItemClick(event, item) {
        this.topbarItemClick = true;

        if (this.activeTopbarItem === item) {
            this.activeTopbarItem = null; } else {
            this.activeTopbarItem = item; }

        event.preventDefault();
    }

    onRightPanelButtonClick(event) {
        this.rightPanelClick = true;
        this.rightPanelActive = !this.rightPanelActive;
        event.preventDefault();
    }

    onRightPanelClick() {
        this.rightPanelClick = true;
    }

    hideOverlayMenu() {
        this.rotateMenuButton = false;
        this.overlayMenuActive = false;
        this.staticMenuMobileActive = false;
    }

    isTablet() {
        const width = window.innerWidth;
        return width <= 1024 && width > 640;
    }

    isDesktop() {
        return window.innerWidth > 1024;
    }

    isMobile() {
        return window.innerWidth <= 640;
    }

    isOverlay() {
        return this.layoutMode === MenuOrientation.OVERLAY;
    }

    isHorizontal() {
        return this.layoutMode === MenuOrientation.HORIZONTAL;
    }

    isSlim() {
        return this.layoutMode === MenuOrientation.SLIM;
    }

    changeToStaticMenu() {
        this.layoutMode = MenuOrientation.STATIC;
    }

    changeToOverlayMenu() {
        this.layoutMode = MenuOrientation.OVERLAY;
    }

    changeToHorizontalMenu() {
        this.layoutMode = MenuOrientation.HORIZONTAL;
    }

    changeToSlimMenu() {
        this.layoutMode = MenuOrientation.SLIM;
    }

    ngOnDestroy() {
        jQuery(this.layoutMenuScroller).nanoScroller({flash: true});
        this.timerSub.unsubscribe();
        if(this.loginSub) {
            this.loginSub.unsubscribe();
        }
        if(this.getMessageDetSub) {
            this.getMessageDetSub.unsubscribe();
        }
        if(this.getMessageSumSub) {
            this.getMessageSumSub.unsubscribe();
        }
    }

    login() {
        this.loginSub = this.loginService.login(localStorage.getItem(Config.USER_LOGIN_KEY), this.password).subscribe(
            res => {
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
                    return;
                } else {
                    //this.userLogin = null;
                    this.password = null;
                    this.userService.writeUserInfos(response);
                    this.lockScreen = false;
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

    private initializeSessionExpiredSettings() {
        document.onclick = function() {
            localStorage.setItem(Config.ELAPSED_IDLE_TIME_KEY, '0');
        };
        document.onmousemove = function() {
            localStorage.setItem(Config.ELAPSED_IDLE_TIME_KEY, '0');
        };
        document.onkeypress = function() {
            localStorage.setItem(Config.ELAPSED_IDLE_TIME_KEY, '0');
        };
        
        this.timerSub = Observable.timer(1000, 3000).subscribe(
            t => {
                if(this.userService.isLoggedIn() && !this.lockScreen) {
                    this.checkIdleTime();
                }
            }
        );
    }

    private checkIdleTime() {
        let idleSecondsCounter: number;
        idleSecondsCounter = Number(localStorage.getItem(Config.ELAPSED_IDLE_TIME_KEY));
        idleSecondsCounter += 3;
        localStorage.setItem(Config.ELAPSED_IDLE_TIME_KEY, idleSecondsCounter + '');
        if (idleSecondsCounter >= Config.IDLE_TIMEOUT) {
            localStorage.setItem(Config.ELAPSED_IDLE_TIME_KEY, '0');
            let fullUrl = window.location.href;
            if(!fullUrl.includes('login')) {
                this.userService.clearUserInfos();
                this.lockScreenHeaderMsg = 'screen.locked';
                this.lockScreen = true;
            }
        }
    }

    diplayGrowlMessage(severity: string, summaryKey: string, detailKey: string) {
        let growlMessage: Message = {};
        growlMessage.severity = severity;
        this.getMessageSumSub = this.translateService.get(summaryKey).subscribe(
            summary => {
                growlMessage.summary = summary;
                this.getMessageDetSub = this.translateService.get(detailKey).subscribe(
                    detail => {
                        growlMessage.detail = detail;
                        this.msgs = [growlMessage];
                    }
                );
            }
        );
    }

}
