import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LocationStrategy, HashLocationStrategy} from '@angular/common';
import {AppRoutes} from './app.routes';

import {AppComponent} from './app.component';
import {AppMenuComponent, AppSubMenuComponent} from './app.menu.component';
import {AppTopbarComponent} from './app.topbar.component';
import {AppFooterComponent} from './app.footer.component';
import {AppRightpanelComponent} from './app.rightpanel.component';
import {AppInlineProfileComponent} from './app.profile.component';

import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { UserService } from './services';
import { DefaultPathComponent } from "./default-path.component";
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component'
import { LayoutModule } from './layout/layout.module';
import { HttpModule } from '@angular/http';
import { ScrollPanelModule, DropdownModule, DialogModule, InputTextModule, ButtonModule, PasswordModule } from 'primeng/primeng';

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutes,
        HttpClientModule,
        HttpModule,
        BrowserAnimationsModule,
        ScrollPanelModule, DropdownModule, DialogModule, InputTextModule, ButtonModule,
        PasswordModule,
        
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),

        LayoutModule
    ],
    declarations: [
        AppComponent,
        DefaultPathComponent,
        LayoutComponent,
        LoginComponent,
        ErrorComponent,

        AppMenuComponent,
        AppSubMenuComponent,
        AppTopbarComponent,
        AppFooterComponent,
        AppRightpanelComponent,
        AppInlineProfileComponent
    ],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        UserService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
