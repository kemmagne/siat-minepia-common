import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../modules/custom-http/http.service";
import { ActivatedRoute, Params } from "@angular/router";
import { LazyLoadEvent, Message, DataTable } from "primeng/primeng";
import { Config } from "../config";
import { LayoutComponent } from "../layout/layout.component";
import { UserService } from "../services";

@Component({
    selector: 'app-users-management',
    templateUrl: './users-management.component.html'
})
export class UsersManagementComponent implements OnInit, OnDestroy {
    searchSub: Subscription;

    resetPasswordSub: Subscription;
    showConfirmResetPass: boolean;
    msgs: Message[];
    alertMessage: {
        severity: string;
        summary: string;
        detail: string;
    };
    confirmSave: boolean;
    showConfirmActive: boolean;
    showConfirmLock: boolean;

    private tempUser;

    private errorMsg;

    private findUsersSub: Subscription;
    users: any[];
    selectedUser;
    partnerCode: string
    userPartner;
    filteredPartners: any[];

    private findRolesSub: Subscription;
    roles: any[];
    role: string;

    private findPartnersSub: Subscription;
    partners: any[];
    lazyPartners: any[];
    nbPartners: number;
    partnerType: string;

    private saveUserSub: Subscription;
    private updateLockedStateSub: Subscription;
    private updateActiveStateSub: Subscription;
    private filterUsersSub: Subscription;
    private countPrincipalsSub: Subscription;
    private getMessageSumSub: Subscription;
    private getMessageDetSub: Subscription;

    oldPassword: string;
    newPasswordFirst: string;
    newPasswordSecond: string;

    showConfirm: boolean;
    activation: boolean;
    userRole: string;
    certificate;
    usersTable: DataTable;

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute, private layout: LayoutComponent, private userService: UserService) {}

    ngOnInit() {
         this.activatedRoute.params.forEach(
             (params : Params) => {
                 let param = params['activation'];
                 if(!!param && param === 'activation') {
                    this.activation = true;
                    this.findUsersToActive();
                 /*} else if(!!param && param === 'unlock') {
                    this.findUsersToUnlock();
                 */} else {
                    this.findAllUsers();
                 }
             }
         );
    }

    ngOnDestroy() {
        if(this.updateActiveStateSub) {
            this.updateActiveStateSub.unsubscribe();
        }
        if(this.updateLockedStateSub) {
            this.updateLockedStateSub.unsubscribe();
        }
        this.findUsersSub.unsubscribe();
        if(this.saveUserSub) {
            this.saveUserSub.unsubscribe();
        }
        if(this.findPartnersSub) {
            this.findPartnersSub.unsubscribe();
        }
        if(this.findRolesSub) {
            this.findRolesSub.unsubscribe();
        }
        if(this.filterUsersSub) {
            this.filterUsersSub.unsubscribe();
        }
        if(this.countPrincipalsSub) {
            this.countPrincipalsSub.unsubscribe();
        }
        if(this.searchSub) {
            this.searchSub.unsubscribe();
        }
    }

    newUser() {
        this.selectedUser = {};
        this.role = null;
        this.findRoles();
    }

    viewUser(user) {
        this.selectedUser = Object.assign({}, user);
        this.role = this.selectedUser.roles[0];
        this.findRoles();
        this.onUserRoleChange(null);
    }

    private updateLockedState(user) {
        user.locked = !user.locked;
        this.updateLockedStateSub = this.http.post('users/locked', user).subscribe(
            res => {
                let response = res.json();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', user.locked ? 'user.locked' : 'user.unlocked');
                this.findAllUsers();
            },
            error => {
                console.error(this.errorMsg);
                this.errorMsg = error;
            },
            () => {
                this.tempUser = null;
            }
        );
    }

    private updateActiveState(user) {
        user.active = !user.active;
        this.updateActiveStateSub = this.http.post('users/active', user).subscribe(
            res => {
                let response = res.json();
                this.findUsersToActive();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'user.activated');
            },
            error => {
                console.error(this.errorMsg);
                this.errorMsg = error;
            },
            () => {
                this.tempUser = null;
            }
        );
    }

    private findAllUsers() {
        this.findUsersSub = this.http.get('users/all', true).subscribe(
            res => {
                this.users = res.json();
            },
            error => {
                console.error(this.errorMsg);
                this.errorMsg = error;
            }
        );
    }

    askConfirm() {
        this.showConfirm = true;
    }

    onDialogHide(event) {
        this.selectedUser = null;
        this.newPasswordFirst = null;
        this.newPasswordSecond = null;
        this.partnerCode = null;
    }

    onConfirm(event) {
        this.saveUser();
    }

    onAskConfirm(event) {
        this.showConfirm = true;
    }

    askActiveConfirmation(user) {
        this.tempUser = Object.assign({}, user);
        this.showConfirmActive = true;
    }

    askLockConfirmation(user) {
        this.tempUser = Object.assign({}, user);
        this.showConfirmLock = true;
    }

    onConfirmActive(event) {
        this.updateActiveState(this.tempUser);
    }

    onConfirmLock(event) {
        this.updateLockedState(this.tempUser);
    }

    onCancelActive(event) {
        this.tempUser = null;
    }

    onCloseActive(event) {
        this.tempUser = null;
    }

    onCancelLock(event) {
        this.tempUser = null;
    }

    onCloseLock(event) {
        this.tempUser = null;
    }

    onUserRoleChange(event) {
        if(!!event) {
            this.selectedUser.partner = null;
        }
        if(this.role === Config.ROLE_AUDITEUR_BANQUE || this.role === Config.ROLE_CONTROLEUR_BANQUE) {
            this.partnerType = Config.PARTNER_TYPE_BANK;
        } else if(this.role === Config.ROLE_CAISSIER || this.role === Config.ROLE_CONTROLEUR_AGENCE) {
            this.partnerType = Config.PARTNER_TYPE_BANK_AGENCY;
        } else if(this.role === Config.ROLE_AUDITEUR_DONNEUR_ORDRE || this.role === Config.ROLE_CONTROLEUR_DONNEUR_ORDRE || this.role === Config.ROLE_DONNEUR_ORDRE) {
            this.partnerType = Config.PARTNER_TYPE_PRINCIPAL;
        } else if(this.role === Config.ROLE_BENEFICIAIRE || this.role === Config.ROLE_DECIDEUR) {
            this.partnerType = Config.PARTNER_TYPE_BENEFICIARY;
        }
    }

    countPrincipals() {
        this.partnerCode = null;
        this.countPrincipalsSub = this.http.get('partners/principals/count').subscribe(
            res => {
                this.nbPartners = +res.json().data;
                this.findPartners(0, 5);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    findPartners (start, end: number) {
        if(!this.role) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'select.user.role'};
            return;
        }
        let url: string;
        if(!this.isPrincipal()) {
            url = `partners/by-types/${this.partnerType}/${start}/${end}`;
        } else {
            url = `partners/principals/${start}/${end}`;
        }
        this.findPartnersSub = this.http.get(url).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findRoles() {
        this.findRolesSub = this.http.get('users/roles/all').subscribe(
            data => {
                this.roles = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private saveUser() {
        if(!this.selectedUser.partner) {
            this.layout.diplayGrowlMessage('warning', 'warning', 'you.must.select.partner');
            return;
        }
        if(!this.activation) {
            this.selectedUser.active = true;
            this.selectedUser.roles = [this.role];
        }
        this.selectedUser.passwordToBeReset = !this.selectedUser.id;
        this.saveUserSub = this.http.post('users', this.selectedUser).subscribe(
            res => {
                let response = res.json();
                if(response.data) {
                    if(0 === +response.data) {
                        //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'user.already.exists'};
                        this.layout.diplayGrowlMessage('warning', 'warning', 'user.already.exists');
                    } else if(1 === +response.data) {
                        //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'user.already.exists'};
                        this.layout.diplayGrowlMessage('warning', 'warning', 'email.incorrect');
                    } else {
                        console.info(response);
                    }
                    return;
                } else {
                    //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'user.saved'};
                    this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'user.saved');
                    this.role = null;
                    this.selectedUser = null;
                    if(!this.activation) {
                        this.findAllUsers();
                    } else {
                        this.findUsersToActive();
                    }
                }
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    showPartnerEdition() {
        if(this.isPrincipal()) {
            this.countPrincipals();
        } else {
            this.findPartners(0, 0);
        }
    }

    private findUsersToActive() {
        this.findUsersSub = this.http.get('users/desactivated', true).subscribe(
            res => {
                this.users = res.json();
            },
            error => {
                console.error(this.errorMsg);
                this.errorMsg = error;
            }
        );
    }

    filterUsers() {
        if('ALL' === this.userRole) {
            this.findAllUsers();
        } else {
            this.filterUsersSub = this.http.get(`users/by-roles/${this.userRole}`, true).subscribe(
                data => {
                    this.users = data.json();
                },
                error => {
                    this.errorMsg = error;
                    console.error(this.errorMsg);
                }
            );
        }
    }

    findUsersToUnlock() {
        this.findUsersSub = this.http.get('users/locked', true).subscribe(
            res => {
                this.users = res.json();
            },
            error => {
                console.error(this.errorMsg);
                this.errorMsg = error;
            }
        );
    }

    lockUserStyle(rowData) {
        return rowData.locked ? 'text-white bg-danger' : '';
    }

    loadPartnerLazy(event: LazyLoadEvent) {
        setTimeout(() => {
            let rows = +event.rows;
            this.findPartners(event.first, event.first + rows);
        }, Config.LAZY_TIMER);
    }

    chooseUserPartner(partner) {
        this.selectedUser.partner = Object.assign({}, partner);
        this.partners = null;
    }

    isPrincipal(): boolean {
        return !!this.partnerType && Config.PARTNER_TYPE_PRINCIPAL === this.partnerType;
    }

    isAgency(): boolean {
        return !!this.partnerType && Config.PARTNER_TYPE_BANK_AGENCY === this.partnerType;
    }

    private resetPassword() {
        this.resetPasswordSub = this.http.post('users/password/reset', this.tempUser).subscribe(
            res => {
                let data: string = res.json().data;
                if("OK" === data) {
                    this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'password.reset');
                } else {
                    this.msgs = [{severity: 'success', summary: 'Success', detail: data}]
                }
                this.tempUser = null;
            },
            error => {
                this.errorMsg = error;
                this.tempUser = null;
                console.error(this.errorMsg);
            }
        );
    }

    askConfirmResetPass(user) {
        this.tempUser = Object.assign({}, user);
        this.showConfirmResetPass = true;
    }

    onConfirmResetPass(event) {
        this.resetPassword();
    }

    getUserLogin() {
        return this.userService.getUserLogin();
    }

    canLockUser(user): boolean {
        return !this.activation && user.login !== this.userService.getUserLogin();
    }

    filterByLockedState(event, dt: DataTable) {
        try {
            let value = event.value;
            console.info(value);
            value.toString();
            setTimeout(() => {
                console.info('filter');
                dt.filter(value, 'locked', 'equals');
            }, Config.LAZY_TIMER);
        } catch(e) {
            if(e instanceof TypeError){
                console.info('reset');
                dt.reset();
            }
        }
    }

    search() {
        this.nbPartners = null;
        this.searchSub = this.http.get(`partners/by-code/${this.partnerCode}/${null}`).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    canSetPartner(): boolean {
        return !!this.userRole && Config.ROLE_ADMIN !== this.userRole;
    }

}
