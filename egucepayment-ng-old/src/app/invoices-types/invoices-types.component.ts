import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../config";
import { Message } from "primeng/primeng";
import { LayoutComponent } from "../layout/layout.component";

@Component({
    selector: 'app-invoices-types',
    templateUrl: './invoices-types.component.html'
})
export class InvoicesTypesComponent implements OnInit, OnDestroy {
    deleteInvoiceTypeSub: Subscription;

    showDecideursDialog: boolean;
    ivtView: boolean;
    msgs: Message[];
    growlMessage: Message;
    showConfirmDelete: boolean;

    private errorMsg;

    alertMessage;

    private findInvoicesTypesSub: Subscription;
    InvoicesTypes: any[];
    selectedInvoiceType;

    private findBeneficiariesSub: Subscription;
    beneficiariesCodes: string[];
    beneficiaries: any[];

    private findPaymentModesSub: Subscription;
    paymentModesCodes: string[];
    paymentModes: any[];

    private saveInvoiceTypeSub: Subscription;

    showSetAccountDialog: boolean;
    private findPartnersAccountsSub: Subscription;
    beneficiaryAccounts: any[];
    selectedBeneficiary: string;
    selectedBa;
    private confirmBankAccountSub: Subscription;
    selectedBanks: any[];
    banks: any[];
    private findBanksSub: Subscription;
    showAddBanksDialog: boolean;
    private confirmBanksSub: Subscription;
    private findDebitAccountSub: Subscription;
    private findDecideursSub: Subscription;
    private confirmDecideursSub: Subscription;
    private getMessageSumSub: Subscription;
    private getMessageDetSub: Subscription;
    decideurs: any[];
    selectedDecideurs: any[];
    invoicesTypesBenefs: any[];

    showConfirm: boolean;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {
        this.findInvoicesTypes();
    }

    ngOnDestroy() {
        this.findInvoicesTypesSub.unsubscribe();
        if(this.findBeneficiariesSub) {
          this.findBeneficiariesSub.unsubscribe();
        }
        if(this.findPaymentModesSub) {
          this.findPaymentModesSub.unsubscribe();
        }
        if(this.saveInvoiceTypeSub) {
          this.saveInvoiceTypeSub.unsubscribe();
        }
        if(this.findPartnersAccountsSub) {
            this.findPartnersAccountsSub.unsubscribe();
        }
        if(this.confirmBankAccountSub) {
            this.confirmBankAccountSub.unsubscribe();
        }
        if(this.findBanksSub) {
            this.findBanksSub.unsubscribe();
        }
        if(this.confirmBanksSub) {
            this.confirmBanksSub.unsubscribe();
        }
        if(this.findDebitAccountSub) {
            this.findDebitAccountSub.unsubscribe();
        }
        if(this.getMessageDetSub) {
            this.getMessageDetSub.unsubscribe();
        }
        if(this.getMessageSumSub) {
            this.getMessageSumSub.unsubscribe();
        }
    }

    private findInvoicesTypes() {
        this.findInvoicesTypesSub = this.http.getData(`invoices/types/all`, true).subscribe(
            ivts => {
                this.InvoicesTypes = ivts.json();
                this.ivtView = false;
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findBeneficiaries() {
        let partnerType = Config.PARTNER_TYPE_BENEFICIARY;
        this.findBeneficiariesSub = this.http.getData(`public/partners/by-types/${partnerType}/${0}/${0}`, true).subscribe(
            data => {
                this.beneficiaries = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findPaymentModes() {
        this.findPaymentModesSub = this.http.getData('admin/payment/modes/all', true).subscribe(
            data => {
                this.paymentModes = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    viewInvoiceType(ivt) {
        this.selectedInvoiceType = Object.assign({}, ivt);
        this.findBeneficiaries();
        this.findPaymentModes();
        //
        this.beneficiariesCodes = [];
        let benefs = ivt.beneficiaries;
        for(let benef of benefs) {
            this.beneficiariesCodes.push(benef.code);
        }
        //
        this.paymentModesCodes = [];
        let pms = ivt.paymentModes;
        for(let pm of pms) {
            this.paymentModesCodes.push(pm.code);
        }
        this.ivtView = true;
    }

    newInvoiceType() {
        this.findBeneficiaries();
        this.findPaymentModes();
        this.selectedInvoiceType = {};
        this.beneficiariesCodes = [];
        this.paymentModesCodes = [];
        this.ivtView = true;
    }

    onConfirm($event) {
        this.saveInvoiceType();
    }

    private saveInvoiceType() {
        if(!this.selectedInvoiceType.id) {
            for(let ivt of this.InvoicesTypes) {
                if(this.selectedInvoiceType.code == ivt.code) {
                    //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'invoice.type.already.exists'};
                    this.layout.diplayGrowlMessage('warning', 'warning', 'invoice.type.already.exists');
                    return;
                }
            }
        }
        this.selectedInvoiceType.beneficiaries = [];
        for(let benefCode of this.beneficiariesCodes) {
            for(let benef of this.beneficiaries) {
                if(benefCode === benef.code) {
                    this.selectedInvoiceType.beneficiaries.push(benef);
                }
            }
        }
        this.selectedInvoiceType.paymentModes = [];
        for(let pmCode of this.paymentModesCodes) {
            for(let pm of this.paymentModes) {
                if(pmCode === pm.code) {
                    this.selectedInvoiceType.paymentModes.push(pm);
                }
            }
        }
        this.http.postData('admin/invoices/types', this.selectedInvoiceType).subscribe(
            res => {
                let ivt = res.json();
                if(!this.selectedInvoiceType.id) {
                    this.selectedInvoiceType = {};
                    this.beneficiaries = null;
                    this.paymentModesCodes = null;
                }
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'invoice.type.saved');
                //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'invoice.type.saved'};
                this.findInvoicesTypes();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    cancel() {
        this.ivtView = false;
        this.selectedInvoiceType = null;
    }

    onDialogHide(event) {
        this.selectedInvoiceType = null;
        this.findInvoicesTypes();
    }

    editBankAccount(ivt) {
        this.selectedInvoiceType = Object.assign({}, ivt);
        //
        this.beneficiariesCodes = [];
        this.invoicesTypesBenefs = Object.assign([], ivt.beneficiaries);
        for(let benef of this.invoicesTypesBenefs) {
            this.beneficiariesCodes.push(benef.code);
        }
        this.showSetAccountDialog = true;
    }

    beneficiaryChange(event) {
        this.findPartnersAccounts();
    }

    private findPartnersAccounts() {
        this.findPartnersAccountsSub = this.http.getData(`banks/accounts/by-owner/${this.selectedBeneficiary}`, true).subscribe(
            data => {
                this.beneficiaryAccounts = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.findDebitAccount();
            }
        );
    }

    confirmBankAccount() {
        if(!this.selectedBeneficiary) {
            console.info('no beneficiary');
            return;
        }
        if(!this.selectedBa) {
            console.info('no account');
            return;
        }
        this.confirmBankAccountSub = this.http.postData(`admin/invoices/types/accounts/${this.selectedInvoiceType.code}/${this.selectedBeneficiary}`,
        this.selectedBa).subscribe(
            res => {
                let response = res.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    canConfirmAccount(): boolean {
        return false;
    }

    addBanks(ivt) {
        this.selectedInvoiceType = Object.assign({}, ivt);
        this.findBanks(ivt, true);
    }

    private findBanks(ivt, view?: boolean) {
        this.findBanksSub = this.http.getData(`public/partners/by-types/${Config.PARTNER_TYPE_BANK}/${0}/${0}`, true).subscribe(
            data => {
                this.banks = data.json();
                this.selectedBanks = Object.assign([], ivt.banks);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.showAddBanksDialog = view;
            }
        );
    }

    cancelBaSelection() {
        this.selectedBa = null;
    }

    cancelBanksSelection() {
        this.selectedBanks = null;
    }

    confirmBanks() {
        this.confirmBanksSub = this.http.postData(`admin/invoices/types/banks/${this.selectedInvoiceType.code}`, this.selectedBanks).subscribe(
            res => {
                let response = res.json();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'update.done');
                //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'update.done'};
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findDebitAccount() {
        this.findDebitAccountSub = this.http.getData(`admin/invoices/types/accounts/${this.selectedInvoiceType.code}/${this.selectedBeneficiary}`, true).subscribe(
            data => {
                let ba = data.json();
                if(ba.id) {
                    this.selectedBa = Object.assign({}, ba);
                }
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findDecideurs(ivt, view?: boolean) {
        this.findDecideursSub = this.http.getData(`users/by-roles/${Config.ROLE_DECIDEUR}`, true).subscribe(
            data => {
                this.decideurs = data.json();
                this.selectedDecideurs = Object.assign([], ivt.decideurs);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.showDecideursDialog = view;
            }
        );
    }

    setDecisionMakers(ivt) {
        this.selectedInvoiceType = Object.assign({}, ivt);
        this.findDecideurs(ivt, true);
    }

    confirmDecisionMakers() {
        this.confirmDecideursSub = this.http.postData(`admin/invoices/types/decision-makers/${this.selectedInvoiceType.code}`, this.selectedDecideurs).subscribe(
            res => {
                let response = res.json();
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'update.done');
                //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'update.done'};
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    askConfirmDeleteInvoiceType(ivt) {
        this.selectedInvoiceType = Object.assign({}, ivt);
        this.showConfirmDelete = true;
    }

    deleteInvoiceType() {
        this.deleteInvoiceTypeSub = this.http.delete(`admin/invoices/types/${this.selectedInvoiceType.id}`).subscribe(
            res => {
                let response = res.json().data;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'invoice.type.deleted');
                this.selectedInvoiceType = null;
                this.findInvoicesTypes();
            },
            error => {
                this.errorMsg = error;
                if(-1 < (<string>this.errorMsg._body).indexOf('ORA-02292')) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.delete.invoice.type');
                } else {
                    console.error(this.errorMsg);
                }
            }
        );
    }

}
