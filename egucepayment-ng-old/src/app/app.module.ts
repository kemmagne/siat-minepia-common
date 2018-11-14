import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule, Http} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import 'rxjs/add/operator/toPromise';

import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate';
import { ButtonModule, DialogModule, DataTableModule, FileUploadModule, GrowlModule, InputTextModule } from "primeng/primeng";

import {InvoiceTableModule} from './modules/invoice-table/invoice-table.module';
import {BankAccountTableModule} from './modules/bank-account-table/bank-account-table.module';
import {CustomHttpModule} from './modules/custom-http/custom-http.module';

import {AppComponent} from './app.component';
import {AppMenuComponent, AppSubMenuComponent} from './app.menu.component';
import {AppTopbarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';

import {LoginComponent} from './login/login.component';

import {PipesModule} from './pipes/pipes.module';

import { InvoiceStateComponent } from './invoice-state/invoice-state.component';
import { DefaultPathComponent } from "./default-path.component";
import { UserService } from "./services";
import { LayoutComponent } from "./layout/layout.component";
import { LayoutModule } from "./layout/layout.module";
import { AppRoutingModule } from "./app.routes";
import { ConfirmDialogModule } from "./modules/confirm-dialog/confirm-dialog.module";
import { ErrorComponent } from './error/error.component';
import { AlertModule } from "./modules/alert/alert.module";
import { UserSubscriptionComponent } from "./user-subscription/user-subscription.component";
import { NotAuthenticatedGuard } from "./guards";
import { PersistenceModule } from 'angular-persistence';

export function createTranslateLoader(http: Http) {
    return new TranslateStaticLoader(http, 'assets/i18n', '.json');
}

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpModule,
        ButtonModule, DialogModule,
        TranslateModule.forRoot({
		    provide: TranslateLoader,
		    useFactory: (createTranslateLoader),
		    deps: [Http]
		}),
        LayoutModule, PipesModule, InvoiceTableModule, ConfirmDialogModule, AlertModule, DialogModule, DataTableModule, FileUploadModule, GrowlModule,
        InputTextModule, PersistenceModule
    ],
    declarations: [
        AppComponent,
        AppMenuComponent,
        AppSubMenuComponent,
        AppTopbarComponent,
        AppFooterComponent,
        LayoutComponent,
        LoginComponent,
        DefaultPathComponent,
        ErrorComponent, UserSubscriptionComponent
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        UserService, NotAuthenticatedGuard
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
