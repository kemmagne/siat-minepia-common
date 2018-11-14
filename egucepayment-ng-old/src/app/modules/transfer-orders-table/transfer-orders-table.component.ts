import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { HttpService } from "../custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { LazyLoadEvent } from "primeng/primeng";

@Component({
    selector: 'app-transfer-orders-table',
    template: `
      <p-dataTable [value]="transferOrders" dataKey="reference" [rows]="rows" [paginator]="true" [pageLinks]="pageLinks" [responsive]="true"
      emptyMessage="{{'no.to' | translate}}" [rowsPerPageOptions]="rowsPerPageOptions"
        [lazy]="!!transferOrders" [totalRecords]="nbTos" (onLazyLoad)="onLazyLoad($event)">
          <p-header *ngIf="!!headerMsg || toValidate">
                <span *ngIf="!!transferOrders">{{headerMsg | translate}}</span>
                <span *ngIf="toValidate">{{'waiting.validation.to' | translate}} ({{transferOrders?.length || 0}})</span>
          </p-header>
          <p-column field="reference" header="{{'reference'| translate}}"></p-column>
          <p-column header="{{'amount'| translate}}">
                <ng-template let-to="rowData" pTemplate="body">{{to.amount | moneyFormat}}</ng-template>
          </p-column>
          <p-column header="{{'taxpayer'| translate}}">
              <ng-template let-to="rowData" pTemplate="body">{{to.taxPayer.name}}</ng-template>
          </p-column>
          <p-column header="{{'beneficiary'| translate}}">
              <ng-template let-to="rowData" pTemplate="body">{{to.beneficiary?.name || ''}}</ng-template>
          </p-column>
          <p-column header="{{'invoice.type'| translate}}">
              <ng-template let-to="rowData" pTemplate="body">{{to.invoiceType.label}}</ng-template>
          </p-column>
          <p-column header="{{'details'| translate}}">
              <ng-template let-to="rowData" pTemplate="body">
                    <button type="button" pButton (click)="showToDetails(to)" icon="ui-icon-dehaze"></button>
              </ng-template>
          </p-column>
          <p-column [hidden]="!toValidate" styleClass="col-button">
              <ng-template let-to="rowData" pTemplate="body">
                  <button type="button" pButton (click)="askConfirmValid(to)" icon="ui-icon-check"></button>
                  <button type="button" pButton (click)="askConfirmCancel(to)" icon="ui-icon-undo" class="ui-button-danger"></button>
              </ng-template>
          </p-column>
      </p-dataTable>

      <p-dialog [(visible)]="viewToDetails" [modal]="true" width="1000" [responsive]="true" appendTo="body">
            <div class="card">
                <div class="card-header h5">{{'to.details' | translate}}</div>
                <div class="card-block">
                    <app-invoice-table [invoices]="currentTo?.invoices" [hideStatus]="true" [headerText]="'invoices'"></app-invoice-table>
                </div>
            </div>
      </p-dialog>

      <app-confirm-dialog [contentText]="'confirm.operation'" [(display)]="showConfirmValid" (onCancel)="tempTo=null" (onClose)="tempTo=null" (onConfirm)="onConfirmValid($event)"></app-confirm-dialog>
      <app-confirm-dialog [contentText]="'confirm.operation'" [(display)]="showConfirmCancel" (onCancel)="tempTo=null" (onClose)="tempTo=null" (onConfirm)="onConfirmCancel($event)"></app-confirm-dialog>
    `
})
export class TransferOrdersTableComponent implements OnInit, OnDestroy {

    private errorMsg;
    viewToDetails: boolean;
    currentTo;
    @Input()
    headerMsg: string;

    @Input()
    toValidate: boolean;

    @Input()
    transferOrders: any[];
    @Output()
    transferOrdersChange = new EventEmitter<any[]>();

    @Input()
    pageLinks: number;
    @Input()
    rows: number;
    @Input()
    rowsPerPageOptions: number[];
    @Input()
    lazy: boolean;
    @Input('total')
    nbTos: Number;
    @Output('onLazyLoad')
    onLazyLoadTos = new EventEmitter<LazyLoadEvent>();

    tempTo;
    toInvoices: any[];
    toReference: string;
    showConfirmValid: boolean;
    showConfirmCancel: true;

    private sendResponseSub: Subscription;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.pageLinks = !!this.pageLinks ? this.pageLinks : 3;
        this.rows = !!this.rows ? this.rows : 5;
        this.rowsPerPageOptions = !!this.rowsPerPageOptions ? this.rowsPerPageOptions : [5];
    }

    ngOnDestroy() {
        if(this.sendResponseSub) {
            this.sendResponseSub.unsubscribe();
        }
    }

    viewTransferOrderInvoices(to) {
        this.toInvoices = to.invoices;
        this.toReference = to.reference;
    }

    onToInvoicesDialogHide($event) {
        this.toInvoices = null;
        this.toReference = null;
    }

    askConfirmValid(to) {
        this.tempTo = Object.assign({}, to);
        this.showConfirmValid = true;
    }

    askConfirmCancel(to) {
        this.tempTo = Object.assign({}, to);
        this.showConfirmCancel = true;
    }

    onConfirmValid(event) {
        this.validateTransferOrder(this.tempTo);
    }

    onConfirmCancel(event) {
        this.cancelTransferOrder(this.tempTo);
    }

    private validateTransferOrder(to) {
        this.sendResponse(to, true);
    }

    private cancelTransferOrder(to) {
        this.sendResponse(to, false);
    }

    private sendResponse(to: any, valid: boolean) {
        let transferValidation = {
            reference: to.reference,
            originMessage: "originMessage",
            privateKey: "privateKey",
            valid: valid
        };
        this.http.postData("transfers/orders/validations", transferValidation).subscribe(
            res => {
                let response = res.json();
                let toIndex: number;
                for(let index in this.transferOrders) {
                    if(to.reference == this.transferOrders[index]) {
                        toIndex = +index;
                    }
                }
                this.transferOrders.splice(toIndex, 1);
                let temp = Object.assign([], this.transferOrders);
                this.transferOrders = Object.assign([], temp);
                this.transferOrdersChange.emit(this.transferOrders);
            },
            error => {
                this.errorMsg = error;
            }
        );
    }

    onLazyLoad(event: LazyLoadEvent) {
        this.onLazyLoadTos.emit(event);
    }

    showToDetails(to) {
        this.currentTo = Object.assign({}, to);
        this.viewToDetails = true;
    }

}
