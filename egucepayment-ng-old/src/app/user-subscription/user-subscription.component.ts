import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../modules/custom-http/http.service";
import { Config } from "../config";
import { LazyLoadEvent, Message } from "primeng/primeng";
import { TranslateService } from "ng2-translate";
import { LayoutComponent } from "../layout/layout.component";

@Component({
    selector: 'app-user-subscription',
    templateUrl: './user-subscription.component.html'
})
export class UserSubscriptionComponent implements OnInit, OnDestroy {
    getMessageDetSub: Subscription;
    msgs: Message[];
    getMessageSumSub: Subscription;

    countPrincipalsSub: Subscription;
    nbPartners: number;
    partnerType: any;

    private errorMsg;

    alertMessage: {
        severity: string;
        summary: string;
        detail: string;
    };
    partnerCode: string;
    newPasswordFirst: string;
    newPasswordSecond: string;
    selectedUser;
    showConfirm: boolean;
    userRole: string;

    private findRolesSub: Subscription;
    private saveUserSub: Subscription;
    roles: any[];
    private findPartnersSub: Subscription;
    partners: any[];

    constructor(private http: HttpService, private translateService: TranslateService) {}

    ngOnInit() {
        this.selectedUser = {};
        this.findRoles();
    }

    ngOnDestroy() {
        if(this.saveUserSub) {
            this.saveUserSub.unsubscribe();
        }
        this.findRolesSub.unsubscribe();
        if(this.countPrincipalsSub) {
            this.countPrincipalsSub.unsubscribe();
        }
        if(this.findPartnersSub) {
            this.findPartnersSub.unsubscribe();
        }
        if(this.getMessageDetSub) {
            this.getMessageDetSub.unsubscribe();
        }
        if(this.getMessageSumSub) {
            this.getMessageSumSub.unsubscribe();
        }
    }

    askConfirm() {
        this.showConfirm = true;
    }

    onConfirm(event) {
        this.saveUser();
    }

    private saveUser() {
        if(this.newPasswordFirst && 0 < this.newPasswordFirst.length) {
            // il veut éditer le password
            if(this.newPasswordFirst != this.newPasswordSecond) {
                this.diplayGrowlMessage('warning', 'warning', 'passwords.doesnt.matched');
                //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'passwords.doesnt.matched'};
                return;
            }
        }
        if(!this.selectedUser.partner) {
            this.diplayGrowlMessage('warning', 'warning', 'you.must.select.partner');
            return;
        }
        //this.selectedUser.partner = {id: this.partnerCode};
        this.selectedUser.password = this.newPasswordFirst;
        this.selectedUser.roles = [this.userRole];
        this.saveUserSub = this.http.postData('public/users', this.selectedUser).subscribe(
            res => {
                let response = res.json();
                if(response.data) {
                    if(0 === +response.data) {
                        this.diplayGrowlMessage('warning', 'warning', 'user.already.exists');
                        //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'user.already.exists'};
                    } else if(1 === +response.data) {
                        //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'user.already.exists'};
                        this.diplayGrowlMessage('warning', 'warning', 'email.incorrect');
                    } else {
                        console.info(response);
                    }
                    return;
                } else {
                    this.diplayGrowlMessage('success', 'operation.succeeded', 'user.saved');
                    //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'user.saved'};
                    this.selectedUser = {};
                    this.userRole = null;
                    this.newPasswordFirst = null;
                    this.newPasswordSecond = null;
                }
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findRoles() {
        this.findRolesSub = this.http.getData('public/users/roles/all').subscribe(
            data => {
                this.roles = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onUserRoleChange(event) {
        this.selectedUser.partner = null;
        if(this.userRole === Config.ROLE_AUDITEUR_BANQUE || this.userRole === Config.ROLE_CONTROLEUR_BANQUE) {
            this.partnerType = Config.PARTNER_TYPE_BANK;
        } else if(this.userRole === Config.ROLE_CAISSIER || this.userRole === Config.ROLE_CONTROLEUR_AGENCE) {
            this.partnerType = Config.PARTNER_TYPE_BANK_AGENCY;
        } else if(this.userRole === Config.ROLE_AUDITEUR_DONNEUR_ORDRE || this.userRole === Config.ROLE_CONTROLEUR_DONNEUR_ORDRE || this.userRole === Config.ROLE_DONNEUR_ORDRE) {
            this.partnerType = Config.PARTNER_TYPE_PRINCIPAL;
        } else if(this.userRole === Config.ROLE_BENEFICIAIRE || this.userRole === Config.ROLE_DECIDEUR) {
            this.partnerType = Config.PARTNER_TYPE_BENEFICIARY;
        }
    }

    showPartnerEdition() {
        if(Config.PARTNER_TYPE_PRINCIPAL === this.partnerType) {
            this.countPrincipals();
        } else {
            this.findPartners(0, 0);
        }
    }

    chooseUserPartner(partner) {
        this.selectedUser.partner = Object.assign({}, partner);
        this.partners = null;
    }

    isPrincipalal(): boolean {
        return !!this.partnerType && Config.PARTNER_TYPE_PRINCIPAL === this.partnerType;
    }

    loadPartnerLazy(event: LazyLoadEvent) {
        setTimeout(() => {
            let rows = +event.rows;
            this.findPartners(event.first, event.first + rows);
        }, Config.LAZY_TIMER);
    }

    countPrincipals() {
        this.countPrincipalsSub = this.http.getData('public/partners/principals/count').subscribe(
            res => {
                this.nbPartners = +res.json().data;
                this.findPartners(0, 0);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    findPartners (start, end: number) {
        if(!this.userRole) {
            this.diplayGrowlMessage('warning', 'warning', 'select.user.role');
            //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'select.user.role'};
            return;
        }
        let url: string;
        if(Config.PARTNER_TYPE_PRINCIPAL !== this.partnerType) {
            url = `public/partners/by-types/${this.partnerType}/${start}/${end}`;
        } else {
            if(start === end) {
                end = 5;
            }
            url = `public/partners/principals/${start}/${end}`;
        }
        this.findPartnersSub = this.http.getData(url).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    changeLanguage(lang: string) {
        this.translateService.use(lang);
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

    canSetPartner(): boolean {
        return !!this.userRole && Config.ROLE_ADMIN !== this.userRole;
    }

}
