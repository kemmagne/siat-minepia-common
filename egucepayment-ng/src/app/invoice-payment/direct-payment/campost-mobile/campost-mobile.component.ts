import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { DirectPaymentComponent } from "../direct-payment.component";
import { HttpService } from "../../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { LayoutComponent } from "../../../layout/layout.component";

@Component({
    selector: 'app-campost-mobile',
    templateUrl: './campost-mobile.component.html'
})
export class CampostMobileComponent implements OnInit, OnDestroy {
    cardNumberPart: any;
    errorMsg: any;
    pacCodeTwoLabel: string;
    pacCodeOneLabel: string;
    paymentReference: string;
    pacCodeOne: string;
    pacCodeTwo: string;
    amount: string;

    @Input()
    invoicesToPay: any[];

    @Output()
    onReset = new EventEmitter<boolean>();

    countryCode: string;
    phoneNumber: string;
    showResetDialog: boolean;
    showValidationDialog: boolean;
    showCancelDialog: boolean;
    showConfirm: boolean;
    
    private sendFirstRequestSub: Subscription;
    private sendFinalResponseSub: Subscription;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {}

    ngOnDestroy() {
        if(this.sendFirstRequestSub) {
            this.sendFirstRequestSub.unsubscribe();
        }
        if(this.sendFinalResponseSub) {
            this.sendFinalResponseSub.unsubscribe();
        }
    }

    resetPaymentForm() {
        this.countryCode = null;
        this.phoneNumber = null;
        this.onReset.emit(true);
    }

    sendFirstRequest() {
        if(0 === this.invoicesToPay.length) {
            this.layout.diplayGrowlMessage('warning', 'warning', 'no.invoice.provided');
            return;
        }
        let campostMobileFirstReq = {
            beneficiaryCode: this.invoicesToPay[0].beneficiary.code,
            invoices: this.invoicesToPay,
            mobile: this.countryCode.trim() + this.phoneNumber.trim()
        };
        this.sendFirstRequestSub = this.http.post('payment/campost/request/first', campostMobileFirstReq).subscribe(
            res => {
                let response = res.json();
                if(response.data) {
                    if(0 === +response.data) {
                        this.layout.diplayGrowlMessage('warning', 'warning', 'beneficiary.dont.have.account');
                        return;
                    }
                    return;
                }
                let campostMobileFirstRes = res.json();
                let responseCode = campostMobileFirstRes.responseCode;
                if(1 === responseCode) { // success
                    this.paymentReference = campostMobileFirstRes.orderNumber;
                    this.pacCodeOneLabel = 'Code ' + campostMobileFirstRes.pacCodeOneLabel;
                    this.pacCodeTwoLabel = 'Code ' + campostMobileFirstRes.pacCodeTwoLabel;
                    this.amount = campostMobileFirstRes.amount;
                    this.cardNumberPart = campostMobileFirstRes.cardNumberPart;
                } else if(0 === responseCode) {
                    this.layout.diplayGrowlMessage('danger', 'operation.failed', 'first.req.failed');
                }
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            },
            () => {
                this.showConfirm = true;
            }
        );
    }

    confirmPayment() {
        this.sendFinalResponse(false);
    }

    cancelPayment() {
        this.sendFinalResponse(true);
    }

    private sendFinalResponse(cancel: boolean) {
        let campostMobileSecondReq = {
            paymentReference: this.paymentReference,
            pacCodeOne: this.pacCodeOne,
            pacCodeTwo: this.pacCodeTwo,
            cancel: cancel
        };
        this.sendFinalResponseSub = this.http.post('payment/campost/request/second', campostMobileSecondReq).subscribe(
            res => {
                let responseCode = +res.json().data;
                if(1 === responseCode) {
                    this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'payment.succeeded');
                } else if(0 === responseCode) {
                    this.layout.diplayGrowlMessage('danger', 'operation.failed', 'payment.succeeded');
                } else if(2 === responseCode) {
                    
                }
                this.pacCodeOneLabel = null;
                this.pacCodeOne = null;
                this.pacCodeTwoLabel = null;
                this.pacCodeTwo = null;
                this.showConfirm = false;
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

}
