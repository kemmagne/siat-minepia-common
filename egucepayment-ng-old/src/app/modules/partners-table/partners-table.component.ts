import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../custom-http/http.service";
import { LayoutComponent } from "../../layout/layout.component";
import { Config } from "../../config";
import { LazyLoadEvent } from "primeng/primeng";

@Component({
    selector: 'app-partners-table',
    templateUrl: './partners-table.component.html'
})
export class PartnersTableComponent implements OnInit {

    showConfirmDelete: boolean;
    deletePartnerSub: Subscription;
    getMessageDetSub: any;
    getMessageSumSub: any;
    countPrincipalsSub: Subscription;

    showConfirmRemoveVL: boolean;
    showConfirmSaveVL: boolean;
    showValidationLevelForm: boolean;
    findValidationsLevelsSub: Subscription;
    addValidationsLevelsSub: Subscription;
    removeValidationsLevelsSub: Subscription;
    showValidLevels: boolean;
    validationsLevels: any[];

    validationUser: string;
    partnerUsers: any[];
    findPartnerUsersSub: Subscription;
    paymentMode;
    paymentModes: any[];
    findPaymentModesSub: Subscription;
    private saveValidationLevelSub: Subscription;

    parentPartner: any;
    tempUs;

    private errorMsg;

    partnerGroupsCodes: string[];
    private newPartnerSub: Subscription;
    selectedPartner;
    tempPartner;

    viewPartnerBas: boolean;
    viewNewBa: boolean;
    selectedBa;
    showConfirmSaveBa: boolean;

    private findPartnersSub: Subscription;
    private findPartnersGroupsSub: Subscription;
    private savePartnerSub: Subscription;
    private findPartnerAccountsSub: Subscription;
    private savePartnerAccountSub: Subscription;
    private findAgenciesSub: Subscription;
    private findBanksSub: Subscription;

    @Input()
    partners: any[];
    @Output('onPartnerSave')
    partnerSave = new EventEmitter<any>();
    @Input()
    partnersGroups: any[];
    @Input()
    nbPartners: Number;
    @Input()
    lazy: boolean;
    @Output('onLazyLoad')
    lazyLoad = new EventEmitter<LazyLoadEvent>();
    @Input()
    partnerType: string;
    @Input()
    emptyTableTitle: string;
    @Input()
    hasBas: boolean;
    @Input()
    parentLabel: string;
    @Input()
    newMsg: string;
    @Input()
    headerMsg: string;
    @Input()
    saveMsg: string;

    showConfirm: boolean;

    partnerAccounts: any[];
    banks: any[];

    update: boolean;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {}

    ngOnDestroy() {
        if(this.findPartnersSub) {
            this.findPartnersSub.unsubscribe();
        }
        if(this.newPartnerSub) {
            this.newPartnerSub.unsubscribe();
        }
        if(this.savePartnerSub) {
            this.savePartnerSub.unsubscribe();
        }
        if(this.findPartnerAccountsSub) {
            this.findPartnerAccountsSub.unsubscribe();
        }
        if(this.savePartnerAccountSub) {
            this.savePartnerAccountSub.unsubscribe();
        }
        if(this.findPartnersGroupsSub) {
            this.findPartnersGroupsSub.unsubscribe();
        }
        if(this.findAgenciesSub) {
            this.findAgenciesSub.unsubscribe();
        }
        if(this.findBanksSub) {
            this.findBanksSub.unsubscribe();
        }
        if(this.findValidationsLevelsSub) {
            this.findValidationsLevelsSub.unsubscribe();
        }
        if(this.findPartnerUsersSub) {
            this.findPartnerUsersSub.unsubscribe();
        }
        if(this.findPaymentModesSub) {
            this.findPaymentModesSub.unsubscribe();
        }
        if(this.findValidationsLevelsSub) {
            this.findValidationsLevelsSub.unsubscribe();
        }
        if(this.addValidationsLevelsSub) {
            this.addValidationsLevelsSub.unsubscribe();
        }
        if(this.removeValidationsLevelsSub) {
            this.removeValidationsLevelsSub.unsubscribe();
        }
        if(this.getMessageDetSub) {
            this.getMessageDetSub.unsubscribe();
        }
        if(this.getMessageSumSub) {
            this.getMessageSumSub.unsubscribe();
        }
    }

    newPartner() {
        this.selectedPartner = {};
        if(this.isAgency()) {
            this.findBanks();
        }
        /*if(this.showTaxPayer) {
            this.partnerGroupsCodes = [];
            this.findPartnersGroups();
        }*/
    }

    viewPartner(partner) {
        this.selectedPartner = Object.assign({}, partner);
        if(this.isAgency()) {
            this.findBanks();
        }
        /*if(this.showTaxPayer) {
            this.partnerGroupsCodes = [];
            for(let pg of this.selectedPartner.partnerGroups) {
                this.partnerGroupsCodes.push(pg.code);
            }
            this.findPartnersGroups();
        }*/
    }

    onDialogHide($event) {
        this.selectedPartner = null;
        if(this.update) {
            this.partnerSave.emit(this.parentPartner);
            this.parentPartner = null;
            this.update = false;
        }
    }

    onConfirm($event) {
        this.savePartner();
    }

    private savePartner() {
        this.selectedPartner.partnerTypes = [{code: this.partnerType}];
        /*if(this.showTaxPayer) {
            this.selectedPartner.partnerGroups = [];
            for(let pgCode of this.partnerGroupsCodes) {
                for(let pg of this.partnersGroups) {
                    if(pgCode == pg.code) {
                        this.selectedPartner.partnerGroups.push(pg);
                    }
                }
            }
        }*/
        if(this.parentPartner) {
            this.selectedPartner.parent = {};
            this.selectedPartner.parent.code = this.parentPartner;
        }
        this.savePartnerSub = this.http.postData('admin/partners', this.selectedPartner).subscribe(
            res => {
                let response = res.json();
                if(response.data) {
                    if(0 === +response.data) {
                        this.layout.diplayGrowlMessage('warning', 'warning', 'partner.already.exists');
                    }
                    return;
                }
                let partner = response;
                if(!this.selectedPartner.id) {
                    this.partnerGroupsCodes = [];
                }
                this.selectedPartner = null;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'partner.saved');
                this.partnerSave.emit(this.parentPartner);
                //this.parentPartner = null;
                this.update = true;
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findPartnersGroups() {
        this.findPartnersGroupsSub = this.http.getData('partners/groups/all', true).subscribe(
            data => {
                this.partnersGroups = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    cancel() {
        this.selectedPartner = null;
    }

    showBas(partner) {
        this.tempPartner = Object.assign({}, partner);
        this.findPartnerAccounts(this.tempPartner, true);
    }

    addBa(partner) {
        this.selectedBa = {};
        this.viewNewBa = true;
    }

    onBasDialogHide(event) {
        this.tempPartner = null;
    }

    findPartnerAccounts(partner, view?: boolean) {
        this.findPartnerAccountsSub = this.http.getData(`banks/accounts/by-owner/${partner.code}`, true).subscribe(
            data => {
                this.partnerAccounts = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.viewPartnerBas = view;
            }
        );
    }

    private savePartnerAccount(partner) {
        this.savePartnerAccountSub = this.http.postData(`banks/accounts/by-owner/${partner.code}`, this.selectedBa).subscribe(
            res => {
                let response = res.json();
                if(0 === +response.data) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'bank.code.incorrect');
                    return;
                }
                if(1 === +response.data) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'agency.code.incorrect');
                    return;
                }
                this.selectedBa = {};
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'bank.account.saved');
                this.viewNewBa = false;
                this.findPartnerAccounts(partner, true);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onConfirmSaveBa(event) {
        this.savePartnerAccount(this.tempPartner);
    }

    viewValidationsLevels(partner) {
        this.tempPartner = Object.assign({}, partner);
        this.findValidationsLevels(partner, true);
    }

    onValidLevelsDialogHide(event) {
        this.tempPartner = null;
    }

    private findValidationsLevels(partner, show?: boolean) {
        this.findValidationsLevelsSub = this.http.getData(`admin/multiple/validation/user/step/by-partner/${partner.id}`, true).subscribe(
            data => {
                this.validationsLevels = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.showValidLevels = show;
            }
        );
    }

    removeValidationLevel(event) {
        this.removeValidationsLevelsSub = this.http.postData(`admin/multiple/validation/user/step/by-partner/${this.tempPartner.id}/${this.partnerType}/${false}`, this.tempUs).subscribe(
            res => {
                let response = res.json();
                this.validationUser = null;
                this.paymentMode = null;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'vl.removed');
                this.findValidationsLevels(this.tempPartner, true);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    newValidationLevel() {
        this.findPartnerUsers();
        this.findPaymentModes();
        this.showValidationLevelForm = true;
    }

    private findPartnerUsers() {
        let roles: string;
        if(Config.PARTNER_TYPE_BANK === this.partnerType) {
            roles = Config.ROLE_CONTROLEUR_BANQUE;
        } else if(Config.PARTNER_TYPE_BANK_AGENCY === this.partnerType) {
            roles = Config.ROLE_CONTROLEUR_AGENCE;
        } else if(Config.PARTNER_TYPE_PRINCIPAL === this.partnerType) {
            roles = Config.ROLE_CONTROLEUR_DONNEUR_ORDRE;
        }
        let url = `users/by-partner/${this.tempPartner.id}/${roles}`;
        this.findPartnerUsersSub = this.http.getData(url, true).subscribe(
            data => {
                this.partnerUsers = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findPaymentModes() {
        this.findPaymentModesSub = this.http.getData(`public/payment/modes/by-direct/${false}`).subscribe(
            data => {
                this.paymentModes = [{code: 'null', label: ''}];
                this.paymentModes = this.paymentModes.concat(data.json());
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
                this.paymentModes = [{code: 'null', label: ''}];
            }
        );
    }

    addValidationLevel(event) {
        let validationLevel = {
            id: null,
            user: null,
            paymentMode: null,
            level: 0
        };
        for(let user of this.partnerUsers) {
            if(this.validationUser === user.login) {
                validationLevel.user = Object.assign({}, user);
            }
        }
        if(!!this.paymentMode && 'null' != this.paymentMode) {
            for(let pm of this.paymentModes) {
                if(this.paymentMode === pm.code) {
                    validationLevel.paymentMode = Object.assign({}, pm);
                }
            }
            if(this.checkUserByPm(this.paymentMode, this.validationUser)) {
                this.layout.diplayGrowlMessage('warning', 'warning', 'user.already.has.level');
                return;
            }
            validationLevel.level = this.countVlByPm(this.paymentMode) + 1;
        } else {
            if(this.checkUserWithoutPm(this.validationUser)) {
                this.layout.diplayGrowlMessage('warning', 'warning', 'user.already.has.level');
                return;
            }
            validationLevel.level = this.countVlWithoutPm() + 1;
        }
        let ok = false;
        this.addValidationsLevelsSub = this.http.postData(`admin/multiple/validation/user/step/by-partner/${this.tempPartner.id}/${this.partnerType}/${true}`, validationLevel).subscribe(
            res => {
                let response = res.json();
                this.validationUser = null;
                this.paymentMode = null;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'vl.added');
                this.findValidationsLevels(this.tempPartner, true);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private countVlByPm(pmCode: string): number {
        let counter = 0;
        for(let vl of this.validationsLevels) {
            if(!!vl.paymentMode && pmCode === vl.paymentMode.code) {
                ++counter;
            }
        }
        return counter;
    }

    private countVlWithoutPm(): number {
        let counter = 0;
        for(let vl of this.validationsLevels) {
            if(!vl.paymentMode) {
                ++counter;
            }
        }
        return counter;
    }

    private checkUserByPm(pmCode, login: string): boolean {
        for(let vl of this.validationsLevels) {
            if(!!vl.paymentMode && pmCode === vl.paymentMode.code && login === vl.user.login) {
                return true;
            }
        }
        return false;
    }

    private checkUserWithoutPm(login: string): boolean {
        for(let vl of this.validationsLevels) {
            if(!vl.paymentMode && login === vl.user.login) {
                return true;
            }
        }
        return false;
    }

    askConfirmRemoveVL(us) {
        this.tempUs = Object.assign({}, us);
        this.showConfirmRemoveVL = true;
    }

    isPrincipalal(): boolean {
        return !!this.partnerType && Config.PARTNER_TYPE_PRINCIPAL === this.partnerType;
    }

    isAgency(): boolean {
        return !!this.partnerType && Config.PARTNER_TYPE_BANK_AGENCY === this.partnerType;
    }

    loadPartnersLazy(event: LazyLoadEvent) {
      this.lazyLoad.emit(event);
    }

    askConfirmDeletePartner(partner) {
        this.tempPartner = Object.assign({}, partner);
        this.showConfirmDelete = true;
    }

    deletePartner(partner) {
        this.deletePartnerSub = this.http.delete(`admin/partners/${partner.id}`).subscribe(
            res => {
                let response = res.json().data;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'partner.deleted');
                this.partnerSave.emit(null);
            },
            error => {
                this.errorMsg = error;
                if(-1 < (<string>this.errorMsg._body).indexOf('ORA-02292')) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.delete.partner');
                } else {
                    console.error(this.errorMsg);
                }
            }
        );
    }

    private findBanks() {
        this.findBanksSub = this.http.getData(`public/partners/by-types/${Config.PARTNER_TYPE_BANK}/${0}/${0}`, true).subscribe(
            data => {
                this.banks = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

}
