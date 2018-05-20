import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";

@Component({
    selector: 'app-receipts-monitoring',
    templateUrl: './receipts-monitoring.component.html'
})
export class ReceiptsMonitoringComponent implements OnInit, OnDestroy {

    errorMessage: any;

    receipts: any[];
    invoicetype;
    invoicesTypes: any[];

    private findInvoicesTypesSub: Subscription;
    private findReceiptsSub: Subscription;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findInvoicesTypes();
    }

    ngOnDestroy() {
        this.findInvoicesTypesSub.unsubscribe();
        if(this.findReceiptsSub) {
            this.findReceiptsSub.unsubscribe();
        }
    }

    private findInvoicesTypes() {
        this.findInvoicesTypesSub = this.http.get(`invoices/types/${true}/${false}`, true).subscribe(
            data => {
                this.invoicesTypes = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    invoiceTypeChange(event) {
        this.findReceiptsSub = this.http.get(`admin/receipts/by-invoice-type/${this.invoicetype}`, true).subscribe(
            data => {
                this.receipts = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

}
