import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {TranslateModule} from 'ng2-translate';

import { DataTableModule, SharedModule , ButtonModule, MessagesModule, DialogModule } from 'primeng/primeng';

import { InvoiceTableComponent } from './invoice-table.component';

import {PipesModule} from '../../pipes/pipes.module';
import { AlertModule } from "../alert/alert.module";
import { FormsModule } from "@angular/forms";

@NgModule({
    imports: [
        CommonModule, TranslateModule, DataTableModule, SharedModule, ButtonModule, MessagesModule, PipesModule, DialogModule, AlertModule,
        FormsModule
    ],
    declarations: [InvoiceTableComponent],
    exports: [InvoiceTableComponent]
})
export class InvoiceTableModule { }
