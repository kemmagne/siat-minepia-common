import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InvoiceSearchComponent } from './invoice-search.component';
import { ButtonModule, RadioButtonModule, AutoCompleteModule } from "primeng/primeng";
import { TranslateModule } from "ng2-translate";

@NgModule({
  imports: [
    CommonModule, ButtonModule, RadioButtonModule, FormsModule, TranslateModule, AutoCompleteModule
  ],
  declarations: [InvoiceSearchComponent],
  exports: [InvoiceSearchComponent]
})
export class InvoiceSearchModule { }
