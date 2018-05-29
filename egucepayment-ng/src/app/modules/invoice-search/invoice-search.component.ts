import { Component, OnInit, OnDestroy, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../custom-http/http.service";
import { Config } from "../../config";
import { LazyLoadEvent } from "primeng/primeng";

@Component({
    selector: 'app-invoice-search',
    templateUrl: './invoice-search.component.html'
})
export class InvoiceSearchComponent implements OnInit, OnDestroy/*, OnChanges*/ {

    private errorMessage;

    @Input()
    invoicesFound: any[];
    @Output()
    invoicesFoundChange = new EventEmitter<any[]>();

    @Input()
    groups: boolean;
    @Input()
    pms: string;

    private searchInvoicesSub: Subscription;
    private findInvoicesTypesSub: Subscription;
    private findBeneficiariesSub: Subscription;
    private findMentionsSub: Subscription;

    invoicesTypes: any[];
    beneficiaryPartners: any[];
    filteredTaxPayers: any[];
    mentions: any[];

    beneficiaryCode: string;
    invoiceNumber: string;
    taxPayerNumber: string;
    invoiceTypeCode: string;
    mention: string;
    private filterInvoices;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findInvoicesTypes();
        this.findMentions();
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

    searchInvoices() {
        if('null' === this.mention) {
            this.mention = null;
        }
        if('null' === this.invoiceTypeCode) {
            this.invoiceTypeCode = null;
        }
        if('null' === this.beneficiaryCode) {
            this.beneficiaryCode = null;
        }
        this.filterInvoices = {
            invoiceNumber: this.invoiceNumber,
            taxPayerNumber: this.taxPayerNumber,
            invoiceTypeCode: this.invoiceTypeCode,
            beneficiaryCode: this.beneficiaryCode,
            invoiceMinAmount: null,
            invoiceMaxAmount: null,
            invoiceStatus: null,
            subTypeCode: this.mention,
            groups: this.groups,
            child: false,
            start: 0,
            end: 0,
            count: false
        };
        this.searchInvoicesSub = this.http.postData(`invoices/filter`, this.filterInvoices).subscribe(
            data => {
                this.invoicesFound = data.json();
                this.invoicesFoundChange.emit(this.invoicesFound);
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findInvoicesTypes() {
        this.findInvoicesTypesSub = this.http.getData(`invoices/types/by-pms/${this.pms}`, true).subscribe(
            data => {
                this.invoicesTypes = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    private findMentions() {
        this.findMentionsSub = this.http.getData(`invoices/types/${false}/${true}`, true).subscribe(
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
        this.findBeneficiariesSub = this.http.getData(`partners/beneficiaries/${this.invoiceTypeCode}`, true).subscribe(
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

    invoiceTypeChange(event) {
        this.findBeneficiaries();
    }

}
