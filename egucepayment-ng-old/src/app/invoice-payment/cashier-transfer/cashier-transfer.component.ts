import { Component, OnInit, OnDestroy } from '@angular/core';
import {Message} from 'primeng/primeng';

import {HttpService} from '../../modules/custom-http/http.service';

import {Config} from '../../config';
import { Subscription } from "rxjs/Subscription";
import { LayoutComponent } from "../../layout/layout.component";

@Component({
    selector: 'app-cashier-transfer',
    templateUrl: './cashier-transfer.component.html'
})
export class CashierTransferComponent implements OnInit, OnDestroy {

    alertMessage;
    private errorMessage: any;

    invoicesFound: any[];
    invoicesToPay = [];
    totalAmount = '0';

    paymentModesCodes: string;
    paymentModes: any[];
    selectedPaymentMode: string;
    partnerRefLabel: string;
    partnerRef: string;
    beneficiaryCode: string;

    taxPayers: any[];

    bankAccounts: any[];
    agencyCode: string;
    agencyLabel: string;
    selectedBankAccount: any;

    showResetDialog: boolean;
    showValidationDialog: boolean;

    private findBankAccountsSub: Subscription;
    private validPaymentSub: Subscription;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {
        this.paymentModes = [];
        this.paymentModes.push({code: Config.PM_COUNTER_TRANSFER, label: Config.PM_COUNTER_TRANSFER_LABEL, partnerRefLabel: Config.PM_COUNTER_TRANSFER_PREF_LABEL});
        this.paymentModes.push({code: Config.PM_CHECK, label: Config.PM_CHECK_LABEL, partnerRefLabel: Config.PM_CHECK_PREF_LABEL});
        this.paymentModes.push({code: Config.PM_CASH, label: Config.PM_CASH_LABEL, partnerRefLabel: Config.PM_CASH_PREF_LABEL});
    }

    ngOnDestroy() {
        if(this.validPaymentSub) {
            this.validPaymentSub.unsubscribe();
        }
        if(this.findBankAccountsSub) {
            this.findBankAccountsSub.unsubscribe();
        }
    }

    paymentModeChange(event) {
        for(let pm of this.paymentModes) {
            if(pm.code === this.selectedPaymentMode) {
                this.partnerRefLabel = pm.partnerRefLabel;
                break;
            }
        }
    }

    canValidPayment(): boolean {
        return !!this.invoicesToPay && 0 < this.invoicesToPay.length && !!this.partnerRef && !!this.selectedBankAccount;
    }

    showResetConfirmationDialog(reset) {
        if(reset) {
            this.resetPaymentForm();
        }
    }

    showValidationConfirmationDialog(validate) {
        if(validate) {
            this.validPayment();
        }
    }

    resetPaymentForm() {
        this.invoicesToPay = [];
        this.totalAmount = '0';
        this.selectedPaymentMode = null;
        this.partnerRefLabel = null;
        this.partnerRef = null;
        this.selectedBankAccount = null;
        this.agencyCode = null;
        this.agencyLabel = null;
    }

    validPayment() {
        let transferInfos = {
            invoices: this.invoicesToPay,
            debitAccout: this.selectedBankAccount,
            partnerRef: this.partnerRef,
            paymentModeCode: this.selectedPaymentMode,
            originMessage: 'originMessage',
            privateKey: 'privateKey'
        };
        this.validPaymentSub = this.http.postData(`transfers/orders/initiation/${true}`, transferInfos).subscribe(
            res => {
                let response = res.json();
                let responseData = response.data;
                if(0 === +responseData) {
                    //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'cannot.pay.invoice.types'};
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.invoice.types');
                    return;
                }
                if(1 === +responseData) {
                    //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'cannot.pay.many.beneficiaries'};
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.many.beneficiaries');
                    return;
                }
                if(2 === +responseData) {
                    //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'cannot.pay.many.taxpayers'};
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.many.taxpayers');
                    return;
                }
                if(3 === +responseData) {
                    //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'no.invoice.provided'};
                    this.layout.diplayGrowlMessage('warning', 'warning', 'no.invoice.provided');
                    return;
                }
                //this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'to.transmitted'};
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'to.transmitted');
                this.resetPaymentForm();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    findBankAccounts() {
        this.findBankAccountsSub = this.http.getData('cashier/banks/accounts', true).subscribe(
            data => {
                let response = data.json();
                if(response.data) {
                    if(0 === +response.data) {
                        this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.delete.ba');
                        //this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'no.agency.for.bank'};
                        return;
                    }
                    return;
                }
                this.bankAccounts = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

}
