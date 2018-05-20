import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransferOrdersTableComponent } from './transfer-orders-table.component';
import { DialogModule, DataTableModule, ButtonModule, SharedModule } from "primeng/primeng";
import { TranslateModule } from "ng2-translate";
import { PipesModule } from "../../pipes/pipes.module";
import { InvoiceTableModule } from "../invoice-table/invoice-table.module";
import { ConfirmDialogModule } from "../confirm-dialog/confirm-dialog.module";

@NgModule({
    imports: [
        CommonModule, TranslateModule, DataTableModule, SharedModule, ButtonModule, PipesModule, DialogModule, InvoiceTableModule, ConfirmDialogModule
    ],
    declarations: [TransferOrdersTableComponent],
    exports: [TransferOrdersTableComponent]
})
export class TransferOrdersTableModule {}
