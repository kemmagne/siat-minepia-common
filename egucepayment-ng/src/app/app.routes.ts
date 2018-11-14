import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

import { DefaultPathComponent } from "./default-path.component";
import {LayoutComponent} from './layout/layout.component';
import { ErrorComponent } from './error/error.component';
import { LoginComponent } from './login/login.component';

import {NotAuthenticatedGuard} from './guards';

export const routes: Routes = [

    // 
    {path: '', component: DefaultPathComponent},
    { path: 'app', component: LayoutComponent },
    // login rout
    {path: 'login', component: LoginComponent, canActivate: [NotAuthenticatedGuard]},
    //{path: 'users-subscription', component: UserSubscriptionComponent, canActivate: [NotAuthenticatedGuard]},
    { path: 'error', component: ErrorComponent },
    { path: 'error/:type', component: ErrorComponent },
    { path: '**', redirectTo: 'error/not-found', pathMatch: 'full' }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes, {useHash: true});
