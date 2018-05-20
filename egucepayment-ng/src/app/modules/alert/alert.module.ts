// Angular Imports
import { NgModule } from '@angular/core';
import { TranslateModule } from 'ng2-translate';

// This Module's Components
import { AlertComponent } from './alert.component';

@NgModule({
    imports: [ TranslateModule ],
    declarations: [ AlertComponent ],
    exports: [ AlertComponent ]
})
export class AlertModule {}
