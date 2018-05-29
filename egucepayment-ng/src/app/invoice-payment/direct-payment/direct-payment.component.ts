import { Component, OnInit, OnDestroy } from '@angular/core';
import { CampostMobileComponent } from "./campost-mobile/campost-mobile.component";
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Message } from "primeng/primeng";
import { Config } from "../../config";

@Component({
    selector: 'app-direct-payment',
    template: `
        <div class="card">
            <div class="card-header h5">{{'direct.payment' | translate}} : {{paymentModeLabel}}</div>
            <div class="card-block">
                <form #directPaymentForm="ngForm" (submit)="searchInvoices()" role="form" class="form-validation">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group row">
                                <label for="paymentMode" class="col-md-4 control-label">{{'payment.mode' | translate}}</label>
                                <div class="col-md-8">
                                    <select class="form-control" [required]="true" name="paymentMode" [(ngModel)]="paymentMode" id="paymentMode" (change)="paymentModeChange($event)">
                                        <option value="{{pm.code}}" *ngFor="let pm of directPaymentModes">{{pm.label | translate}}</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group row">
                                <label for="invoiceNumber" class="col-md-4 control-label">{{'invoice.number' | translate}}</label>
                                <div class="col-md-8">
                                    <input type="text" [required]="true" name="invoiceNumber" class="form-control" id="invoiceNumber" [(ngModel)]="invoiceNumber">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button pButton type="submit" [disabled]="!directPaymentForm.valid" label="{{'search' | translate}}" icon="ui-icon-search"></button>
                        </div>
                    </div>
                </form>
                <p-dialog [(visible)]="!!invoicesFound" [modal]="true" width="800" [responsive]="true">
                    <div class="card">
                        <div class="card-header h5">{{'invoices.found' | translate}}</div>
                        <div class="card-block">
                            <app-invoice-table [manyInvoiceTypes]="manyInvoiceTypes" [manyBeneficiaries]="manyBeneficiaries" [manyTaxPayers]="true"
                            [(invoices)]="invoicesFound" [(invoicesToPay)]="invoicesToPay" [(totalAmount)]="totalAmount" [add]="true" (onInvoicesSelected)="invoicesFound=null"></app-invoice-table>
                        </div>
                    </div>
                </p-dialog>
            </div>
        </div>

        <div class="card">
            <div class="card-header h6">
                {{'selected.invoices' | translate}}
                <span class="float-right">{{'total.amount' | translate}} : {{totalAmount | moneyFormat}}</span>
            </div>
            <div class="card-block">
                <app-invoice-table [(invoices)]="invoicesToPay" [(invoicesToPay)]="invoicesToPay" [(totalAmount)]="totalAmount" [delete]="true"
                [hideStatus]="true"></app-invoice-table>
            </div>
        </div>

        <div>
            <app-campost-mobile *ngIf="'CAMPOST_WALLET' === paymentMode" [invoicesToPay]="invoicesToPay" (onReset)="onReset()"></app-campost-mobile>
        </div>
  `
})
export class DirectPaymentComponent implements OnInit, OnDestroy {

    private errorMsg;

    private findDirectPaymentModesSub: Subscription;
    directPaymentModes: any[];
    paymentMode: string;
    paymentModeLabel: string;
    invoiceNumber: string;
    manyInvoiceTypes: boolean;
    manyBeneficiaries: boolean;

    invoicesFound: any[];
    invoicesToPay = [];
    totalAmount = '0';

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findDirectPaymentModes();
    }

    ngOnDestroy() {
        this.findDirectPaymentModesSub.unsubscribe();
    }

    paymentModeChange(event) {
        switch(this.paymentMode) {
            case Config.PM_CAMPOST_WALLET: {
                this.paymentModeLabel = 'Campost Wallet';
                break;
            }
            case Config.PM_VISA_UBA: {
                break;
            }
        }
    }

    searchInvoices() {
        if(!this.invoiceNumber) {
            this.invoiceNumber = null;
        }
        this.http.getData(`public/invoices/by-number/${this.invoiceNumber}`).subscribe(
            data => {
                this.invoicesFound = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findDirectPaymentModes() {
        this.findDirectPaymentModesSub = this.http.getData(`public/payment/modes/by-direct/${true}`).subscribe(
            data => {
                this.directPaymentModes = [];
                this.directPaymentModes = this.directPaymentModes.concat(data.json());
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onReset() {
        this.invoicesToPay = [];
        this.totalAmount = '0';
    }

}
