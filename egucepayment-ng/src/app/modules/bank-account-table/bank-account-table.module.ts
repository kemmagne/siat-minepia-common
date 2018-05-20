import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BankAccountTableComponent } from './bank-account-table.component';

import {TranslateModule} from 'ng2-translate';

import { DataTableModule, ButtonModule, MessagesModule, SharedModule } from 'primeng/primeng';
import { ConfirmDialogModule } from "../confirm-dialog/confirm-dialog.module";


@NgModule({
  imports: [
    CommonModule, TranslateModule, DataTableModule, SharedModule, ButtonModule, MessagesModule, ConfirmDialogModule
  ],
  declarations: [BankAccountTableComponent],
  exports: [BankAccountTableComponent]
})
export class BankAccountTableModule { }
