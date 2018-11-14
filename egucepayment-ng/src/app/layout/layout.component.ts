import { Component, OnInit, AfterViewInit, OnDestroy, ViewChild, ElementRef, NgZone, Renderer2 } from '@angular/core';
import { ScrollPanel, Message } from 'primeng/primeng';
import { TranslateService } from '@ngx-translate/core';
import { Config } from '../config';
import { UserService } from '../services';
import { LoginService } from "../login/login.service";
import { Subscription, Observable } from 'rxjs';
import { Router } from '@angular/router';

export enum MenuOrientation {
    STATIC,
    OVERLAY,
    SLIM,
    HORIZONTAL
}

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.css'],
    providers: [LoginService]
})
export class LayoutComponent implements AfterViewInit, OnDestroy, OnInit {

    msgs: Message[];

    errorMessage;
    alertMessage;

    layoutCompact = true;

    layoutMode: MenuOrientation = MenuOrientation.STATIC;

    darkMenu = false;

    profileMode = 'top';

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

    @ViewChild('layoutContainer') layourContainerViewChild: ElementRef;

    @ViewChild('scrollPanel') layoutMenuScrollerViewChild: ScrollPanel;

    rippleInitListener: any;

    rippleMouseDownListener: any;

    userLogin: string;
    password: string;
    lockScreen: boolean;
    lockScreenHeaderMsg: string;
    private loginSub: Subscription;
    private timerSub: Subscription;
    private getMessageSumSub: Subscription;
    private getMessageDetSub: Subscription;

    constructor(public renderer2: Renderer2, public zone: NgZone, private translate: TranslateService,
      private userService: UserService, private loginService: LoginService, private router: Router) {}

    ngOnInit() {
        this.zone.runOutsideAngular(() => {this.bindRipple();});

        // set session expiration
        this.initializeSessionExpiredSettings();
    }

    bindRipple() {
        this.rippleInitListener = this.init.bind(this);
        document.addEventListener('DOMContentLoaded', this.rippleInitListener);
    }

    init() {
        this.rippleMouseDownListener = this.rippleMouseDown.bind(this);
        document.addEventListener('mousedown', this.rippleMouseDownListener, false);
    }

    rippleMouseDown(e) {
        for (let target = e.target; target && target !== this; target = target['parentNode']) {
            if (!this.isVisible(target)) {
              continue;
            }

            // Element.matches() -> https://developer.mozilla.org/en-US/docs/Web/API/Element/matches
            if (this.selectorMatches(target, '.ripplelink, .ui-button')) {
                const element = target;
                this.rippleEffect(element, e);
                break;
            }
        }
    }

    selectorMatches(el, selector) {
        const p = Element.prototype;
        const f = p['matches'] || p['webkitMatchesSelector'] || p['mozMatchesSelector'] || p['msMatchesSelector'] || function (s) {
            return [].indexOf.call(document.querySelectorAll(s), this) !== -1;
        };
        return f.call(el, selector);
    }

    isVisible(el) {
        return !!(el.offsetWidth || el.offsetHeight);
    }

    rippleEffect(element, e) {
        if (element.querySelector('.ink') === null) {
            const inkEl = document.createElement('span');
            this.addClass(inkEl, 'ink');

            if (this.hasClass(element, 'ripplelink') && element.querySelector('span')) {
                element.querySelector('span').insertAdjacentHTML('afterend', '<span class=\'ink\'></span>');
            } else {
                element.appendChild(inkEl);
            }
        }

        const ink = element.querySelector('.ink');
        this.removeClass(ink, 'ripple-animate');

        if (!ink.offsetHeight && !ink.offsetWidth) {
            const d = Math.max(element.offsetWidth, element.offsetHeight);
            ink.style.height = d + 'px';
            ink.style.width = d + 'px';
        }

        const x = e.pageX - this.getOffset(element).left - (ink.offsetWidth / 2);
        const y = e.pageY - this.getOffset(element).top - (ink.offsetHeight / 2);

        ink.style.top = y + 'px';
        ink.style.left = x + 'px';
        ink.style.pointerEvents = 'none';
        this.addClass(ink, 'ripple-animate');
    }
    hasClass(element, className) {
        if (element.classList) {
            return element.classList.contains(className);
        } else {
            return new RegExp('(^| )' + className + '( |$)', 'gi').test(element.className);
        }
    }

    addClass(element, className) {
        if (element.classList) {
            element.classList.add(className);
        } else {
            element.className += ' ' + className;
        }
    }

    removeClass(element, className) {
        if (element.classList) {
            element.classList.remove(className);
        } else {
            element.className = element.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
        }
    }

    getOffset(el) {
        const rect = el.getBoundingClientRect();

        return {
          top: rect.top + (window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0),
          left: rect.left + (window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0),
        };
    }

    unbindRipple() {
        if (this.rippleInitListener) {
            document.removeEventListener('DOMContentLoaded', this.rippleInitListener);
        }
        if (this.rippleMouseDownListener) {
            document.removeEventListener('mousedown', this.rippleMouseDownListener);
        }
    }

    ngAfterViewInit() {
        this.layoutContainer = <HTMLDivElement> this.layourContainerViewChild.nativeElement;
        setTimeout(() => {this.layoutMenuScrollerViewChild.moveBar(); }, 100);
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

    onTopbarSubItemClick(event) {
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
        this.unbindRipple();
        //
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

    public diplayGrowlMessage(severity: string, summaryKey: string, detailKey: string) {
        let growlMessage: Message = {};
        growlMessage.severity = severity;
        this.getMessageSumSub = this.translate.get(summaryKey).subscribe(
            summary => {
                growlMessage.summary = summary;
                this.getMessageDetSub = this.translate.get(detailKey).subscribe(
                    detail => {
                        growlMessage.detail = detail;
                        this.msgs = [growlMessage];
                    }
                );
            }
        );
    }

    public login() {
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

    public changeLanguage(language: string) {
        this.translate.use(language);
    }

    public logout() {
        this.userService.logout(this.router);
    }

    public goToUserProfile() {
        this.router.navigate(['/app/user-profile']);
    }

    public isLoggedIn() {
        return this.userService.isLoggedIn();
    }

}
