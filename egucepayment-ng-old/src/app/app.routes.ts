import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

import { DefaultPathComponent } from './default-path.component';
import {LoginComponent} from './login/login.component';
import { LayoutComponent } from "./layout/layout.component";
import { ErrorComponent } from "./error/error.component";
import { UserSubscriptionComponent } from "./user-subscription/user-subscription.component";
import { NotAuthenticatedGuard } from "./guards/index";

export const routes: Routes = [
    // 
    {path: '', component: DefaultPathComponent},
    { path: 'app', component: LayoutComponent },
    // login rout
    {path: 'login', component: LoginComponent, canActivate: [NotAuthenticatedGuard]},
    {path: 'users-subscription', component: UserSubscriptionComponent, canActivate: [NotAuthenticatedGuard]},
    { path: 'error', component: ErrorComponent },
    { path: 'error/:type', component: ErrorComponent },
    { path: '**', redirectTo: 'error/not-found', pathMatch: 'full' },
];

export const AppRoutingModule = RouterModule.forRoot(routes, {useHash: true});
