import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartnersTableComponent } from './partners-table.component';
import { TranslateModule } from "ng2-translate";
import { DataTableModule, SharedModule, ButtonModule, DialogModule } from "primeng/primeng";
import { AlertModule } from "../alert/alert.module";
import { FormsModule } from "@angular/forms";
import { ConfirmDialogModule } from "../confirm-dialog/confirm-dialog.module";
import { BankAccountTableModule } from "../bank-account-table/bank-account-table.module";

@NgModule({
    imports: [
        CommonModule, TranslateModule, DataTableModule, SharedModule, ButtonModule, DialogModule, AlertModule,
        FormsModule, ConfirmDialogModule, BankAccountTableModule
    ],
    declarations: [PartnersTableComponent],
    exports: [PartnersTableComponent]
})
export class PartnersTableModule { }
