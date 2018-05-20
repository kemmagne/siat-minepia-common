import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";
import { DatePipe } from "@angular/common";
import { Utils } from "../../utils";
import { Http } from "@angular/http";
import 'rxjs/add/operator/toPromise';
import { saveAs } from 'file-saver/FileSaver';
import { TranslateService, TranslatePipe } from "ng2-translate";
import { UserService } from "../../services";

@Component({
    selector: 'app-to-history',
    templateUrl: './to-history.component.html'
})
export class ToHistoryComponent implements OnInit, OnDestroy {

    alertMessage: {
        severity: string;
        summary: string;
        detail: string;
    };

    private errorMsg;

    transferOrders: any[];

    invoicesTypes: any[];
    beneficiaryPartners: any[];
    banks: any[];
    bankCode: string;

    toReference: string;
    taxPayerNumber: string;
    beneficiaryCode: string;
    invoiceTypeCode: string;
    minDate: string;
    maxDate: string;
    minAmount: number;
    maxAmount: number;
    toStatus: string;

    period: number = 0;
    type: number;
    filterDto;
    nbTransferOrders: Number;

    private filterTosSub: Subscription;
    private findBanksSub: Subscription;
    private findBeneficiariesSub: Subscription;
    private findInvoicesTypesSub: Subscription;
    private exportPdfSub: Subscription;
    private exportExcelSub: Subscription;

    constructor(private http: HttpService, private translateService: TranslateService, private userService: UserService) {}

    ngOnInit() {
        this.findInvoicesTypes();
        this.findBeneficiaries();
        if(this.userService.hasRoles([Config.ROLE_AUDITEUR_BANQUE, Config.ROLE_CAISSIER, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE])) {
            this.type = 1;
        } else if(this.userService.hasRoles([Config.ROLE_BENEFICIAIRE])) {
            this.type = 2;
            this.findBanks();
        } else if(this.userService.hasRoles([Config.ROLE_DECIDEUR])) {
            this.type = 3;
            this.findBanks();
        }
    }

    ngOnDestroy() {
        if(this.filterTosSub) {
            this.filterTosSub.unsubscribe();
        }
        this.findInvoicesTypesSub.unsubscribe();
        this.findBeneficiariesSub.unsubscribe();
        if(this.exportExcelSub) {
            this.exportExcelSub.unsubscribe();
        }
        if(this.exportPdfSub) {
            this.exportPdfSub.unsubscribe();
        }
        if(this.findBanksSub) {
            this.findBanksSub.unsubscribe();
        }
    }

    private checkNull() {
        if('null' === this.invoiceTypeCode) {
            this.invoiceTypeCode = null;
        }
        if('null' === this.beneficiaryCode) {
            this.beneficiaryCode = null;
        }
        if('null' === this.bankCode) {
            this.bankCode = null;
        }
    }

    countTos() {
        this.checkNull();
        let datePipe = new DatePipe('fr');
        this.period = 0;
        this.filterDto = {
            toReference: this.toReference,
            taxPayerNumber: this.taxPayerNumber,
            invoiceTypeCode: this.invoiceTypeCode,
            beneficiaryCode: this.beneficiaryCode,
            minAmount: this.minAmount,
            maxAmount: this.maxAmount,
            minDate: datePipe.transform(this.minDate, 'dd/MM/yyyy HH:mm'),
            maxDate: datePipe.transform(this.maxDate, 'dd/MM/yyyy HH:mm'),
            status: this.toStatus,
            bank: this.bankCode
        };
        this.filterTosSub = this.http.post(`transfers/orders/filter/${this.type}/${this.period}/${0}/${0}/${true}`, this.filterDto).subscribe(
            res => {
                this.nbTransferOrders = +res.json().data;
                this.filterTos(0, 10);
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    filterTos(start, end: number) {
        this.checkNull();
        let datePipe = new DatePipe('fr');
        this.period = 0;
        this.filterDto = {
            toReference: this.toReference,
            taxPayerNumber: this.taxPayerNumber,
            invoiceTypeCode: this.invoiceTypeCode,
            beneficiaryCode: this.beneficiaryCode,
            minAmount: this.minAmount,
            maxAmount: this.maxAmount,
            minDate: datePipe.transform(this.minDate, 'dd/MM/yyyy HH:mm'),
            maxDate: datePipe.transform(this.maxDate, 'dd/MM/yyyy HH:mm'),
            status: this.toStatus,
            bank: this.bankCode
        };
        this.filterTosSub = this.http.post(`transfers/orders/filter/${this.type}/${this.period}/${start}/${end}/${false}`, this.filterDto).subscribe(
            data => {
                this.transferOrders = data.json();
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    private findBeneficiaries() {
        this.findBeneficiariesSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BENEFICIARY}/${0}/${0}`).subscribe(
            data => {
                this.beneficiaryPartners = [{code: 'null', label: ''}];
                this.beneficiaryPartners = this.beneficiaryPartners.concat(data.json());
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
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
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    countByPeriod(period: number) {
        this.period = period;
        this.filterTosSub = this.http.post(`transfers/orders/filter/${this.type}/${this.period}/${0}/${0}/${true}`, null).subscribe(
            res => {
                this.nbTransferOrders = +res.json().data;
                this.searchByPeriod(this.period, 0, 10);
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    searchByPeriod(period, start, end: number) {
        this.period = period;
        this.filterTosSub = this.http.post(`transfers/orders/filter/${this.type}/${this.period}/${start}/${end}/${false}`, null).subscribe(
            data => {
                this.transferOrders = data.json();
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    exportPdf() {
        if(!this.isPeriodValid()) {
            return;
        }
        this.exportPdfSub = this.http.download(`report/transfer/orders/pdf/${this.translateService.currentLang}/${this.type}/${this.period}`, true).subscribe(
            res => {
                Utils.downloadFile(res);
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    exportExcel() {
        if(!this.isPeriodValid()) {
            return;
        }
        this.exportExcelSub = this.http.download(`report/transfer/orders/excel/${this.translateService.currentLang}/${this.type}/${this.period}`, true).subscribe(
            res => {
                Utils.downloadFile(res);
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            }
        );
    }

    private isPeriodValid() {
        if(this.period === 0) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'bad.export.period'};
            return false;
        }
        return true;
    }

    private findBanks() {
        this.findBanksSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BANK}/${0}/${0}`, true).subscribe(
            data => {
                this.banks = [{code: 'null', label: ''}];
                this.banks = this.banks.concat(data.json());
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onLazyLoad(event) {
        if(!this.transferOrders) {
            return;
        }
        let rows = +event.rows;
        setTimeout(() => {
            if(0 === this.period) {
                this.filterTos(event.first, event.first + rows);
            } else {
                this.searchByPeriod(this.period, event.first, event.first + rows);
            }
        }, Config.LAZY_TIMER);
    }

}
