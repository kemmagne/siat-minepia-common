import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Config } from "../config";
import { Subscription } from "rxjs/Subscription";
import { LazyLoadEvent, SelectItem } from "primeng/primeng";

@Component({
    selector: 'app-invoice-state',
    templateUrl: './invoice-state.component.html'
})
export class InvoiceStateComponent implements OnInit, OnDestroy {

    viewInvoiceDetails: boolean;
    nbInvoices: number;
    amounts: boolean;

    errorMessage: any;

    invoicesFound: any[];

    private searchInvoicesSub: Subscription;
    private findInvoicesTypesSub: Subscription;
    private findBeneficiariesSub: Subscription;
    private findMentionsSub: Subscription;

    invoicesTypes: any[];
    beneficiaryPartners: any[];
    mentions: any[];
    currentInvoice;

    invoiceNumber: string;
    taxPayerNumber: string;
    invoiceTypeCode: string;
    invoiceStatus: string;
    mention: string;
    invoiceMinAmount: string;
    invoiceMaxAmount: string;
    beneficiaryCode: string;
    private filterInvoices;

    invoiceColumns: SelectItem[];
    selectedColumns: any[];

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findInvoicesTypes();
        this.findBeneficiaries();
        this.findMentions();
        this.invoiceColumns = [
            {value: 'invoiceNumber', label: 'invoice.number'},
            {value: 'invoiceType', label: 'invoice.type'},
            {value: 'subType', label: 'Mention'},
            {value: 'owner', label: 'taxpayer'},
            {value: 'beneficiary', label: 'beneficiary'},
            {value: 'totalAmount', label: 'total.amount'},
            {value: 'paidAmount', label: 'paid.amount'},
            {value: 'amountToPay', label: 'amount.to.pay'}
        ];
        this.selectedColumns = [];
        for(let col of this.invoiceColumns) {
            if('subType' !== col.value || 'amountToPay' != col.value) {
                this.selectedColumns.push(col.value);
            }
        }
    }

    ngOnDestroy() {
        if(this.searchInvoicesSub) {
            this.searchInvoicesSub.unsubscribe();
        }
        if(this.findBeneficiariesSub) {
            this.findBeneficiariesSub.unsubscribe();
        }
        this.findInvoicesTypesSub.unsubscribe();
        this.findMentionsSub.unsubscribe();
    }

    countInvoices() {
        if('null' === this.mention) {
            this.mention = null;
        }
        if('null' === this.invoiceTypeCode) {
            this.invoiceTypeCode = null;
        }
        if('null' === this.beneficiaryCode) {
            this.beneficiaryCode = null;
        }
        this.checkAmmounts();
        this.filterInvoices = {
            invoiceNumber: this.invoiceNumber,
            taxPayerNumber: this.taxPayerNumber,
            invoiceTypeCode: this.invoiceTypeCode,
            beneficiaryCode: this.beneficiaryCode,
            invoiceMinAmount: this.invoiceMinAmount,
            invoiceMaxAmount: this.invoiceMaxAmount,
            invoiceStatus: this.invoiceStatus,
            subTypeCode: this.mention,
            groups: false
        };
        this.searchInvoicesSub = this.http.post(`invoices/filter/${0}/${0}/${true}`, this.filterInvoices).subscribe(
            res => {
                this.nbInvoices = +res.json().data;
                this.searchInvoices(0, 10);
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    searchInvoices(start, end: number) {
        this.filterInvoices = {
            invoiceNumber: this.invoiceNumber,
            taxPayerNumber: this.taxPayerNumber,
            invoiceTypeCode: this.invoiceTypeCode,
            beneficiaryCode: this.beneficiaryCode,
            invoiceMinAmount: this.invoiceMinAmount,
            invoiceMaxAmount: this.invoiceMaxAmount,
            invoiceStatus: this.invoiceStatus,
            subTypeCode: this.mention,
            groups: false
        };
        this.searchInvoicesSub = this.http.post(`invoices/filter/${start}/${end}/${false}`, this.filterInvoices).subscribe(
            data => {
                this.invoicesFound = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findInvoicesTypes() {
        this.findInvoicesTypesSub = this.http.get(`invoices/types/${true}/${false}`, true).subscribe(
            data => {
                this.invoicesTypes = [{code: 'null', label: ''}];
                this.invoicesTypes = this.invoicesTypes.concat(data.json());
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findMentions() {
        this.findMentionsSub = this.http.get(`invoices/types/${false}/${true}`, true).subscribe(
            data => {
                this.mentions = [{code: 'null', label: ''}];
                this.mentions = this.mentions.concat(data.json());
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
                this.beneficiaryPartners = [{code: 'null', name: ''}]
                this.beneficiaryPartners = this.beneficiaryPartners.concat(data.json());
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    onLazyLoadInvoices(event: LazyLoadEvent) {
        let rows = +event.rows;
        setTimeout(() => {
            this.searchInvoices(event.first, event.first + rows);
        }, Config.LAZY_TIMER);
    }

    showInvoiceDetails(invoice) {
        this.currentInvoice = Object.assign({}, invoice);
        this.viewInvoiceDetails = true;
    }

    private checkAmmounts() {
        if(!!this.invoiceMinAmount) {
            let a: number;
            a = Number.parseFloat(this.invoiceMinAmount);
            console.info('min = ' + a);
            if(a === NaN) {
                console.info('min is not a number');
            } 
        }
        if(!!this.invoiceMaxAmount) {
            let a: number;
            a = Number.parseFloat(this.invoiceMaxAmount);
            console.info('max = ' + a);
            if(a === NaN) {
                console.info('min is not a number');
            } 
        }
    }

}
