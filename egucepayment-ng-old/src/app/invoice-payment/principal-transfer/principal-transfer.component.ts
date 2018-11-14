import { Component, OnInit, OnDestroy } from '@angular/core';
import { Message } from "primeng/primeng";
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";
import { LayoutComponent } from "../../layout/layout.component";

@Component({
    selector: 'app-principal-transfer',
    templateUrl: './principal-transfer.component.html'
})
export class PrincipalTransferComponent implements OnInit, OnDestroy {

    private errorMsg;

    invoicesFound: any[];
    invoicesToPay = [];
    totalAmount = '0';

    taxPayers: any[];

    beneficiaryCode: string;
    bankAccounts: any[];
    debitAccount;

    showResetDialog: boolean;
    showValidationDialog: boolean;

    private findBankAccountsSub: Subscription;
    private validPaymentSub: Subscription;
    private findTaxPayersSub: Subscription;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {}

    ngOnDestroy() {
        if(this.validPaymentSub) {
            this.validPaymentSub.unsubscribe();
        }
        if(this.findBankAccountsSub) {
            this.findBankAccountsSub.unsubscribe();
        }
    }

    canValidPayment(): boolean {
        return !!this.invoicesToPay && 0 < this.invoicesToPay.length && !!this.debitAccount;
    }

    showResetConfirmationDialog(event) {
        this.resetPaymentForm();
    }

    showValidationConfirmationDialog(event) {
        this.validPayment();
    }

    private resetPaymentForm() {
        this.invoicesToPay = [];
        this.totalAmount = '0';
        this.debitAccount = null;
    }

    private validPayment() {
        let transferInfos = {
            invoices: this.invoicesToPay,
            debitAccout: this.debitAccount,
            partnerRef: null,
            paymentModeCode: Config.PM_PRINCIPAL_TRANSFER,
            beneficiaryCode: this.beneficiaryCode,
            originMessage: 'originMessage',
            privateKey: 'privateKey'
        };
        this.validPaymentSub = this.http.postData(`transfers/orders/initiation/${false}`, transferInfos).subscribe(
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
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    findBankAccounts() {
        this.findBankAccountsSub = this.http.getData(`banks/accounts/by-owner/${null}`, true).subscribe(
            data => {
                this.bankAccounts = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

}
