import {Component, Input, OnInit} from '@angular/core';
import {trigger, state, style, transition, animate} from '@angular/animations';
import {MenuItem} from 'primeng/primeng';
import { LayoutComponent } from "./layout/layout.component";
import { UserService } from "./services";
import { Config } from "./config";

@Component({
    selector: 'app-menu',
    template: `
        <ul app-submenu [item]="model" root="true" class="ultima-menu ultima-main-menu clearfix" [reset]="reset" visible="true"></ul>
    `
})
export class AppMenuComponent implements OnInit {

    @Input() reset: boolean;

    model: any[];

    constructor(public app: LayoutComponent, private userService: UserService) {}

    ngOnInit() {
        this.model = [
            {label: 'home', icon: 'home', routerLink: ['/app/home'], visible: this.userService.isLoggedIn()},
            {
                label: 'invoice.payment', icon: 'payment',
                visible: !this.userService.isLoggedIn() || this.userService.hasRoles([Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CAISSIER]),
                items: [
                    {
                        label: 'principal.transfer', icon: 'desktop_mac', routerLink: ['/app/principal-transfer'],
                        visible: this.userService.hasRoles([Config.ROLE_DONNEUR_ORDRE])
                    },
                    {
                        label: 'cashier.transfer', icon: 'input', routerLink: ['/app/cashier-transfer'],
                        visible: this.userService.hasRoles([Config.ROLE_CAISSIER])
                    },
                    {
                        label: 'asset.clearance', icon: 'input', routerLink: ['/app/asset-clearance'],
                        visible: false && this.userService.hasRoles([Config.ROLE_DONNEUR_ORDRE])
                    },
                    {
                        label: 'direct.payment', icon: 'input', routerLink: ['/app/direct-payment'],
                        visible: !this.userService.isLoggedIn() || this.userService.hasRoles([Config.ROLE_DONNEUR_ORDRE])
                    }
                ]
            },
            {label: 'invoice.state', icon: 'info', routerLink: ['/app/invoices-state'], visible: this.userService.isLoggedIn()},
            {label: 'invoice.type.management', icon: 'toc', routerLink: ['/app/invoices-types-management'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {label: 'users.management', icon: 'group', routerLink: ['/app/users-management'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {label: 'accounts.activation', icon: 'check', routerLink: ['/app/users-management', 'activation'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {label: 'sitting.date.management', icon: 'alarm', routerLink: ['/app/sitting-date-management'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {label: 'campost.accounts', icon: 'alarm', routerLink: ['/app/campost-accounts-management'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {label: 'receipts.monitoring', icon: 'adjust', routerLink: ['/app/receipts-monitoring'], visible: this.userService.hasRoles([Config.ROLE_ADMIN])},
            {
                label: 'partners.management', icon: 'group', visible: this.userService.hasRoles([Config.ROLE_ADMIN]),
                items: [
                    {label: 'principals', icon: 'group', routerLink: ['/app/partners-management/principals']},
                    {label: 'beneficiaries', icon: 'group', routerLink: ['/app/partners-management/beneficiaries']},
                    {label: 'banks', icon: 'group', routerLink: ['/app/partners-management/banks']},
                    {label: 'agencies', icon: 'group', routerLink: ['/app/partners-management/agencies']}
                ]
            },
            {
                label: 'to.management', icon: 'payment',
                visible: this.userService.hasRoles([
                    Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_AUDITEUR_DONNEUR_ORDRE,
                    Config.ROLE_CAISSIER, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE, Config.ROLE_AUDITEUR_BANQUE,
                    Config.ROLE_BENEFICIAIRE, Config.ROLE_DECIDEUR
                ]),
                items: [
                    {
                        label: 'to.validation', icon: 'check', routerLink: ['/app/transfer-orders-validation'],
                        visible: this.userService.hasRoles([
                            Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE
                        ])
                    },
                    {
                        label: 'to.follow.up', icon: 'input', routerLink: ['/app/transfer-orders-follow-up'],
                        visible: this.userService.hasRoles([
                            Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_CAISSIER,
                            Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE
                        ])
                    },
                    {
                        label: 'to.history', icon: 'input', routerLink: ['/app/transfer-orders-history'],
                        visible: this.userService.hasRoles([
                            Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_AUDITEUR_DONNEUR_ORDRE,
                            Config.ROLE_CAISSIER, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE, Config.ROLE_AUDITEUR_BANQUE,
                            Config.ROLE_BENEFICIAIRE, Config.ROLE_DECIDEUR
                        ])
                    },
                    {
                        label: 'to.acknowled', icon: 'input', routerLink: ['/app/transfer-orders-acknowled'],
                        visible: this.userService.hasRoles([Config.ROLE_BENEFICIAIRE])
                    }
                ]
            },
            {
                label: 'muliple.validation', icon: 'payment', visible: false,
                items: [
                    {label: 'mv.principals', icon: 'desktop_mac', routerLink: ['/app/multiple-validation-principals']},
                    {label: 'mv.banks', icon: 'input', routerLink: ['/app/multiple-validation-banks']}
                ]
            },
        ];
    }
}

@Component({
	/* tslint:disable:component-selector */
    selector: '[app-submenu]',
	/* tslint:enable:component-selector */
    template: `
        <ng-template ngFor let-child let-i="index" [ngForOf]="(root ? item : item.items)">
            <li [ngClass]="{'active-menuitem': isActive(i)}" *ngIf="child.visible === false ? false : true">
                <a [href]="child.url||'#'" (click)="itemClick($event,child,i)" (mouseenter)="onMouseEnter(i)"
                   class="ripplelink" *ngIf="!child.routerLink"
                    [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target">
                    <i class="material-icons">{{child.icon}}</i>
                    <span>{{child.label | translate}}</span>
                    <i class="material-icons submenu-icon" *ngIf="child.items">keyboard_arrow_down</i>
                </a>

                <a (click)="itemClick($event,child,i)" (mouseenter)="onMouseEnter(i)" class="ripplelink" *ngIf="child.routerLink"
                    [routerLink]="child.routerLink" routerLinkActive="active-menuitem-routerlink"
                   [routerLinkActiveOptions]="{exact: true}" [attr.tabindex]="!visible ? '-1' : null" [attr.target]="child.target">
                    <i class="material-icons">{{child.icon}}</i>
                    <span>{{child.label | translate}}</span>
                    <i class="material-icons submenu-icon" *ngIf="child.items">keyboard_arrow_down</i>
                </a>
                <div class="layout-menu-tooltip">
                    <div class="layout-menu-tooltip-arrow"></div>
                    <div class="layout-menu-tooltip-text">{{child.label | translate}}</div>
                </div>
                <ul app-submenu [item]="child" *ngIf="child.items" [visible]="isActive(i)" [reset]="reset"
                    [@children]="(app.isSlim()||app.isHorizontal())&&root ? isActive(i) ?
                    'visible' : 'hidden' : isActive(i) ? 'visibleAnimated' : 'hiddenAnimated'"></ul>
            </li>
        </ng-template>
    `,
    animations: [
        trigger('children', [
            state('hiddenAnimated', style({
                height: '0px'
            })),
            state('visibleAnimated', style({
                height: '*'
            })),
            state('visible', style({
                height: '*'
            })),
            state('hidden', style({
                height: '0px'
            })),
            transition('visibleAnimated => hiddenAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')),
            transition('hiddenAnimated => visibleAnimated', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class AppSubMenuComponent {

    @Input() item: MenuItem;

    @Input() root: boolean;

    @Input() visible: boolean;

    _reset: boolean;

    activeIndex: number;

    constructor(public app: LayoutComponent) {}

    itemClick(event: Event, item: MenuItem, index: number) {
        if (this.root) {
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }

        // avoid processing disabled items
        if (item.disabled) {
            event.preventDefault();
            return true;
        }

        // activate current item and deactivate active sibling if any
        this.activeIndex = (this.activeIndex === index) ? null : index;

        // execute command
        if (item.command) {
            item.command({originalEvent: event, item: item});
        }

        // prevent hash change
        if (item.items || (!item.url && !item.routerLink)) {
            event.preventDefault();
        }

        // hide menu
        if (!item.items) {
            if (this.app.isHorizontal() || this.app.isSlim()) {
                this.app.resetMenu = true; } else {
                this.app.resetMenu = false; }

            this.app.overlayMenuActive = false;
            this.app.staticMenuMobileActive = false;
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }
    }

    onMouseEnter(index: number) {
        if (this.root && this.app.menuHoverActive && (this.app.isHorizontal() || this.app.isSlim())) {
            this.activeIndex = index;
        }
    }

    isActive(index: number): boolean {
        return this.activeIndex === index;
    }

    @Input() get reset(): boolean {
        return this._reset;
    }

    set reset(val: boolean) {
        this._reset = val;

        if (this._reset && (this.app.isHorizontal() || this.app.isSlim())) {
            this.activeIndex = null;
        }
    }
}
