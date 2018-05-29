import { Component, OnInit, OnDestroy, Input, Output, EventEmitter, OnChanges } from '@angular/core';

import {TranslatePipe, TranslateService} from 'ng2-translate';

import { Message, DataTable, SelectItem, LazyLoadEvent } from 'primeng/primeng';

import {Config} from '../../config';
import { HttpService } from "../custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { LayoutComponent } from "../../layout/layout.component";

@Component({
    selector: 'app-invoice-table',
    template: `
        <p-dataTable [value]="invoices" dataKey="id" rows="{{rows}}" [paginator]="true" [pageLinks]="pageLinks" [responsive]="true"
        emptyMessage="{{'no.invoices' | translate}}" [rowsPerPageOptions]="rowsPerPageOptions" [(selection)]="selectedInvoices" [headerCheckboxToggleAllPages]="true">
            <p-header *ngIf="!!headerText">{{headerText | translate}}</p-header>
            <p-column *ngIf="add" [style]="{'width':'5%'}" selectionMode="multiple"></p-column>
            <p-column field="invoiceNumber" header="{{'invoice.number' | translate}}" [style]="{'width':'20%'}"></p-column>
            <p-column header="{{'invoice.type'| translate}}" [style]="{'width':'12%'}">
                <ng-template let-invoice="rowData" pTemplate="body">{{invoice.invoiceType?.label || ''}}</ng-template>
            </p-column>
            <p-column header="{{'taxpayer'| translate}}" [style]="{'width':'17%'}">
                <ng-template let-invoice="rowData" pTemplate="body">{{invoice.owner.name}}</ng-template>
            </p-column>
            <p-column header="{{'beneficiary'| translate}}" [style]="{'width':'13%'}">
                <ng-template let-invoice="rowData" pTemplate="body">{{invoice.beneficiary?.name || ''}}</ng-template>
            </p-column>
            <p-column header="{{'invoice.amount'| translate}}" [style]="{'width':'15%'}">
                <ng-template let-invoice="rowData" pTemplate="body">{{invoice.totalAmount | moneyFormat}}</ng-template>
            </p-column>
            <p-column header="{{'status'| translate}}" [hidden]="hideStatus">
                <ng-template let-invoice="rowData" pTemplate="body">{{invoice.status | getLabel | translate}}</ng-template>
            </p-column>
            <p-column header="{{'details'| translate}}">
                <ng-template let-invoice="rowData" pTemplate="body">
                    <button type="button" pButton (click)="showInvoiceDetails(invoice)" icon="ui-icon-dehaze"></button>
                </ng-template>
            </p-column>
            <p-column [hidden]="!delete">
                <ng-template let-invoice="rowData" pTemplate="body">
                    <button *ngIf="delete" type="button" pButton (click)="removeInvoice(invoice)" icon="ui-icon-remove" class="ui-button-danger"></button>
                </ng-template>
            </p-column>
        </p-dataTable>
        <hr/>
        <button type="button" pButton (click)="selectInvoices()" icon="ui-icon-check" *ngIf="add"></button>
        <p-dialog [(visible)]="viewInvoiceDetails" [modal]="true" width="800" [responsive]="true" appendTo="body">
            <div class="card">
                <div class="card-header h5">{{'invoice.details' | translate}}</div>
                <div class="card-block">
                    <div class="row h6">
                        <label class="col-md-4">{{('invoice.number' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.invoiceNumber}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('invoice.type' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.invoiceType.label}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('Mention' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.subType?.label || ''}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('taxpayer' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.owner.name}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('beneficiary' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.beneficiary?.name || ''}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('invoice.amount' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.totalAmount | moneyFormat}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('paid.amount' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.paidAmount | moneyFormat}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('amount.to.pay' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.amountToPay | moneyFormat}}</label>
                    </div>
                    <div class="row h6">
                        <label class="col-md-4">{{('status' | translate) + ' : '}}</label>
                        <label class="col-md-8">{{currentInvoice?.status | getLabel | translate}}</label>
                    </div>
                    <hr/>
                    <p-dataTable [value]="currentInvoice?.subInvoices" dataKey="id" rows="5" [paginator]="true" [pageLinks]="3" [responsive]="true"
                    emptyMessage="{{'no.sub.invoice' | translate}}">
                        <p-header>{{'sub.invoices' | translate}}</p-header>
                        <p-column field="invoiceNumber" header="{{'invoice.number'| translate}}"></p-column>
                        <p-column header="{{'beneficiary'| translate}}">
                            <ng-template let-invoice="rowData" pTemplate="body">{{invoice.beneficiary.name}}</ng-template>
                        </p-column>
                        <p-column header="{{'invoice.amount'| translate}}">
                            <ng-template let-invoice="rowData" pTemplate="body">{{invoice.totalAmount | moneyFormat}}</ng-template>
                        </p-column>
                        <p-column header="{{'amount.payed'| translate}}">
                            <ng-template let-invoice="rowData" pTemplate="body">{{invoice.paidAmount | moneyFormat}}</ng-template>
                        </p-column>
                        <p-column header="{{'amount.to.pay'| translate}}">
                            <ng-template let-invoice="rowData" pTemplate="body">{{invoice.amountToPay | moneyFormat}}</ng-template>
                        </p-column>
                    </p-dataTable>
                </div>
            </div>
        </p-dialog>
    `
})
export class InvoiceTableComponent implements OnInit, OnDestroy {

    // error message
    private errorMessage: any;

    alertMessage;
    viewInvoiceDetails: boolean;
    currentInvoice;
    selectedInvoices: any[];

    @Input()
    invoices: any[];
    @Input()
    headerText: string;

    @Input()
    invoicesToPay: any[];
    @Output()
    invoicesToPayChange = new EventEmitter<any[]>();
    @Output()
    onInvoicesSelected = new EventEmitter<boolean>();

    @Input()
    totalAmount: string;
    @Output()
    totalAmountChange = new EventEmitter<string>();

    @Input()
    manyInvoiceTypes: boolean;
    @Input()
    manyBeneficiaries: boolean;
    @Input()
    manyTaxPayers: boolean;

    @Input()
    add: boolean;
    @Input()
    delete: boolean;
    @Input()
    hideStatus: boolean;
    @Input()
    hideSubInvoices: boolean;

    @Input()
    pageLinks: number;
    @Input()
    rows: number;
    @Input()
    rowsPerPageOptions: number[];
    @Input()
    lazy: boolean;
    @Input()
    nbInvoices: number;
    @Output()
    onLazyLoadInvoices = new EventEmitter<LazyLoadEvent>();

    private addAmountSubscription: Subscription;
    private subtractAmountSubscription: Subscription;

    constructor(private http: HttpService, private translateService: TranslateService, private layout: LayoutComponent) {}

    ngOnInit() {
        this.pageLinks = !!this.pageLinks ? this.pageLinks : 3;
        this.rows = !!this.rows ? this.rows : 5;
        this.rowsPerPageOptions = !!this.rowsPerPageOptions ? this.rowsPerPageOptions : [5];
    }

    private addInvoice(invoice): boolean {
        if(Config.INVOICE_PAID === invoice.status || Config.INVOICE_PAYMENT_STARTED === invoice.status) {
            return;
        }
        let invoiceIndex = -1;
        for(let currentInvoice of this.invoicesToPay) {
            if(currentInvoice.id === invoice.id) {
                invoiceIndex = 0;
            }
        }
        if (invoiceIndex === -1) { // pas encore là
            // plusieurs types ?
            if(!this.manyInvoiceTypes && this.invoicesToPay.length > 0 && invoice.invoiceType.code !== this.invoicesToPay[0].invoiceType.code) {
                this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.invoice.types');
                return false;
            }
            // plusieurs bénéficiaires ?
            if(!this.manyBeneficiaries && this.invoicesToPay.length > 0 && !!invoice.beneficiary && invoice.beneficiary.code !== this.invoicesToPay[0].beneficiary.code) {
                this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.many.beneficiaries');
                return false;
            }
            // plusieurs donneurs d'ordre ?
            if(!this.manyTaxPayers && this.invoicesToPay.length > 0 && invoice.owner.code !== this.invoicesToPay[0].owner.code) {
                this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.pay.many.taxpayers');
                return false;
            }
            let actualAmount = this.totalAmount;
            let newAmount = invoice.amountToPay;
            let operation = "add";
            this.addAmountSubscription = this.http.getData(`public/computing/amount/${actualAmount}/${newAmount}/${operation}`).subscribe(
                total => {
                    this.invoicesToPay.push(invoice);
                    let temp = Object.assign([], this.invoicesToPay);
                    this.invoicesToPay = Object.assign([], temp);
                    this.invoicesToPayChange.emit(this.invoicesToPay);
                    this.totalAmount = total.json().data;
                    this.totalAmountChange.emit(this.totalAmount);
                    this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'invoice.added');
                },
                error => {
                    this.errorMessage = error;
                    console.error(this.errorMessage);
                },
                () => {
                    return true;
                }
            );
        } else {
            this.layout.diplayGrowlMessage('warning', 'warning', 'invoice.already.added');
        }
    }

    removeInvoice(invoice) {
        let invoiceIndex: number = -1;
        let nbinvoices = this.invoicesToPay.length;
        for(let index in this.invoicesToPay) {
            if(invoice.id === this.invoicesToPay[index].id) {
                invoiceIndex = +index;
            }
        }
        let actualAmount = this.totalAmount;
        let newAmount = invoice.amountToPay;
        let operation = 'subtract';
        this.subtractAmountSubscription = this.http.getData(`public/computing/amount/${actualAmount}/${newAmount}/${operation}`).subscribe(
            total => {
                this.invoicesToPay.splice(invoiceIndex, 1);
                let temp = Object.assign([], this.invoicesToPay);
                this.invoicesToPay = Object.assign([], temp);
                this.invoicesToPayChange.emit(this.invoicesToPay);
                this.totalAmount = total.json().data;
                this.totalAmountChange.emit(this.totalAmount);
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'invoice.removed');
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    ngOnDestroy() {
        if(this.addAmountSubscription) {
            this.addAmountSubscription.unsubscribe();
        }
        if(this.subtractAmountSubscription) {
            this.subtractAmountSubscription.unsubscribe();
        }
    }

    showInvoiceDetails(invoice) {
        this.currentInvoice = Object.assign({}, invoice);
        this.viewInvoiceDetails = true;
    }
    
    selectInvoices() {
        for(let invoice of this.selectedInvoices) {
            this.addInvoice(invoice);
        }
        this.onInvoicesSelected.emit(true);
        this.selectedInvoices = null;
    }

}
