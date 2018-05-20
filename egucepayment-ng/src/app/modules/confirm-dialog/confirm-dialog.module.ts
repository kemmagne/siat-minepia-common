import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfirmDialogComponent } from './confirm-dialog.component';
import { DialogModule, ButtonModule } from "primeng/primeng";
import { TranslateModule } from "ng2-translate";

@NgModule({
    imports: [
      CommonModule, DialogModule, TranslateModule, ButtonModule
    ],
    declarations: [ConfirmDialogComponent],
    exports: [ConfirmDialogComponent]
})
export class ConfirmDialogModule {}
