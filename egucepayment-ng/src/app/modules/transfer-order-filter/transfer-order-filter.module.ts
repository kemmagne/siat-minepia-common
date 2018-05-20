import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransferOrderFilterComponent } from './transfer-order-filter.component';
import { TranslateModule } from "ng2-translate";
import { FormsModule } from "@angular/forms";
import { ButtonModule, RadioButtonModule, CalendarModule, FieldsetModule } from "primeng/primeng";

@NgModule({
    imports: [
        CommonModule, ButtonModule, FormsModule, TranslateModule, RadioButtonModule, CalendarModule, FieldsetModule
    ],
    declarations: [TransferOrderFilterComponent],
    exports: [TransferOrderFilterComponent]
})
export class TransferOrderFilterModule {}
