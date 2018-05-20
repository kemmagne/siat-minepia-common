import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { LayoutComponent } from "../layout/layout.component";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../config";

@Component({
    selector: 'app-campost-accounts-manag',
    templateUrl: './campost-accounts-manag.component.html'
})
export class CampostAccountsManagComponent implements OnInit, OnDestroy {

    errorMessage: any;
    beneficiaries: any[];
    beneficiary;
    accounts: any[];
    private findBeneficiariesSub: Subscription;
    private findAccountsSub: Subscription;
    private saveAccountSub: Subscription;
    private deleteAccountSub: Subscription;
    showConfirm: boolean;
    showConfirmDelete: boolean;
    currentAccount;
    private tempAccount;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {
        this.findAccounts();
    }

    ngOnDestroy() {
        this.findAccountsSub.unsubscribe();
        if(!!this.findBeneficiariesSub) {
            this.findBeneficiariesSub.unsubscribe();
        }
        if(!!this.saveAccountSub) {
            this.saveAccountSub.unsubscribe();
        }
    }

    newAccount() {
        this.findBeneficiaries();
        this.currentAccount = {};
    }

    viewAccount(account) {
        this.findBeneficiaries();
        this.beneficiary = account.beneficiary.id;
        this.currentAccount = Object.assign({}, account);
    }

    askDeleteConfirmation(account) {
        this.tempAccount = Object.assign({}, account);
        this.showConfirmDelete = true;
    }

    deleteAccount() {
        this.deleteAccountSub = this.http.delete(`campost/accounts/${this.tempAccount.id}`).subscribe(
            res => {
                let response = res.json();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'account.removed');
                this.tempAccount = null;
                this.findAccounts();
            },
            error => {
                if(-1 < (<string>this.errorMessage._body).indexOf('ORA-02292')) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.delete.account');
                } else {
                    console.error(this.errorMessage);
                }
                this.tempAccount = null;
            }
        );
    }

    saveAccount() {
        this.currentAccount.beneficiary = {id: this.beneficiary};
        this.saveAccountSub = this.http.post(`campost/accounts`, this.currentAccount).subscribe(
            res => {
                let response = res.json();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'account.saved');
                this.currentAccount = null;
                this.findAccounts();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findAccounts() {
        this.findAccountsSub = this.http.get(`campost/accounts`, true).subscribe(
            data => {
                this.accounts = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findBeneficiaries() {
        this.findBeneficiariesSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BENEFICIARY}/${0}/${0}`, false).subscribe(
            data => {
                this.beneficiaries = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    onDialogHide() {
        this.currentAccount = null;
    }

}
