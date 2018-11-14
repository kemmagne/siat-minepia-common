import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Message } from 'primeng/primeng';
import { HttpService } from "../custom-http/http.service";
import { LayoutComponent } from "../../layout/layout.component";
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-bank-account-table',
  template: `
      <p-dataTable [value]="bankAccounts" dataKey="id" rows="5" [paginator]="true" [pageLinks]="3" [responsive]="true" emptyMessage="{{'no.ba' | translate}}">
          <p-header *ngIf="headerMsg">{{headerMsg | translate}}</p-header>
          <p-column field="bankCode" header="{{'bank.code'| translate}}"></p-column>
          <p-column field="bankLabel" header="{{'bank.label'| translate}}"></p-column>
          <p-column field="agencyCode" header="{{'agency.code'| translate}}"></p-column>
          <p-column field="agencyLabel" header="{{'agency.label'| translate}}"></p-column>
          <p-column field="accountNumber" header="{{'account.number'| translate}}" [hidden]="!showAccountInfos"></p-column>
          <p-column field="accountKey" header="{{'account.key'| translate}}" [hidden]="!showAccountInfos"></p-column>
          <p-column field="accountLabel" header="{{'account.label'| translate}}" [hidden]="!showAccountInfos"></p-column>
          <p-column styleClass="col-button">
              <ng-template let-ba="rowData" pTemplate="body">
                  <button *ngIf="!noSelection" type="button" pButton (click)="select(ba)" icon="ui-icon-check"></button>
                  <button *ngIf="canDelete" class="ui-button-danger" type="button" pButton (click)="askConfirmDeteleBa(ba)" icon="ui-icon-delete-forever"></button>
              </ng-template>
          </p-column>
      </p-dataTable>

      <app-confirm-dialog [contentText]="'confirm.operation'" [(display)]="showConfirmDeleteBa" (onConfirm)="deleteBa()"></app-confirm-dialog>
  `
})
export class BankAccountTableComponent implements OnInit {
    errorMsg: any;
    deleteBaSub: Subscription;

    @Output('onBaSelected')
    baChange = new EventEmitter<boolean>();
    @Output('onDelete')
    baDeleted = new EventEmitter<boolean>();

    @Input()
    bankAccounts: any[];

    @Input()
    selectedBankAccount;
    @Output()
    selectedBankAccountChange = new EventEmitter<any>();

    @Input()
    showAccountInfos: boolean;
    @Input()
    noSelection: boolean;
    @Input()
    canDelete: boolean;
    @Input()
    headerMsg: string;

    showConfirmDeleteBa: boolean;
    tempBa;

    constructor(private http: HttpService, private layout: LayoutComponent) {}

    ngOnInit() {}

    select(ba) {
        this.selectedBankAccount = Object.assign(ba);
        this.selectedBankAccountChange.emit(this.selectedBankAccount);
        this.baChange.emit(true);
    }

    deleteBa() {
        this.deleteBaSub = this.http.delete(`banks/accounts/${this.tempBa.id}`).subscribe(
            res => {
                let response = res.json().data;
                this.layout.diplayGrowlMessage('success', 'operation.succeeded', 'ba.deleted');
                this.tempBa = null;
                this.baDeleted.emit(true);
            },
            error => {
                this.errorMsg = error;
                if(-1 < (<string>this.errorMsg._body).indexOf('ORA-02292')) {
                    this.layout.diplayGrowlMessage('warning', 'warning', 'cannot.delete.ba');
                } else {
                    console.error(this.errorMsg);
                }
                this.tempBa = null;
            }
        );
    }

    askConfirmDeteleBa(ba) {
        this.tempBa = Object.assign({}, ba);
        this.showConfirmDeleteBa = true;
    }

}
