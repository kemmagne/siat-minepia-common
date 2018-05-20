import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampostMobileComponent } from "./campost-mobile.component";
import { TranslateModule } from "ng2-translate";
import { FormsModule } from "@angular/forms";
import { ButtonModule, DialogModule } from "primeng/primeng";
import { ConfirmDialogModule } from "../../../modules/confirm-dialog/confirm-dialog.module";

@NgModule({
    imports: [
        CommonModule, TranslateModule, ButtonModule, FormsModule, ConfirmDialogModule, DialogModule
    ],
    declarations: [CampostMobileComponent],
    exports: [CampostMobileComponent]
})
export class CampostMobileModule {}
