import { RouterModule, Routes, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LayoutComponent } from "./layout.component";
import { AuthenticatedGuard, AdminGuard, DonneurOrdreGuard, DecideurGuard, BeneficiaireGuard, AuditeurBanqueGuard, ControleurBanqueGuard,
  CaissierGuard, AuditeurDonneurOrdreGuard, ControleurDonneurOrdreGuard, ControleurAgenceGuard, NotAuthenticatedGuard } from "../guards";
import { HomeComponent } from "../home/home.component";
import { PrincipalTransferComponent } from "../invoice-payment/principal-transfer/principal-transfer.component";
import { CashierTransferComponent } from "../invoice-payment/cashier-transfer/cashier-transfer.component";
import { AssetClearanceComponent } from "../invoice-payment/asset-clearance/asset-clearance.component";
import { DirectPaymentComponent } from "../invoice-payment/direct-payment/direct-payment.component";
import { InvoiceStateComponent } from "../invoice-state/invoice-state.component";
import { UserProfileComponent } from "../user-profile/user-profile.component";
import { ToValidationComponent } from "../transfer-order/to-validation/to-validation.component";
import { ToHistoryComponent } from "../transfer-order/to-history/to-history.component";
import { ToFollowUpComponent } from "../transfer-order/to-follow-up/to-follow-up.component";
import { InvoicesTypesComponent } from "../invoices-types/invoices-types.component";
import { UsersManagementComponent } from "../users-management/users-management.component";
import { UserSubscriptionComponent } from "../user-subscription/user-subscription.component";
import { ConfigsManagementComponent } from "../configs-management/configs-management.component";
import { SittingDateManagementComponent } from "../sitting-date-management/sitting-date-management.component";
import { UserService } from "../services";
import { Config } from "../config";
import { ReceiptsMonitoringComponent } from "../receipts-monitoring/receipts-monitoring.component";
import { ToAcknowledComponent } from "../transfer-order/to-acknowled/to-acknowled.component";
import { PrincipalsComponent } from "../partners/principals/principals.component";
import { BeneficiariesComponent } from "../partners/beneficiaries/beneficiaries.component";
import { BanksComponent } from "../partners/banks/banks.component";
import { AgenciesComponent } from "../partners/agencies/agencies.component";
import { CampostAccountsManagComponent } from "../campost-accounts-manag/campost-accounts-manag.component";

const routes: Routes = [
    {
        path: 'app',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            // home route
            {path: 'home', component: HomeComponent, canActivate: [AuthenticatedGuard]},
            // user profile 
            {path: 'user-profile', component: UserProfileComponent, canActivate: [AuthenticatedGuard]},
            // paympent routes
            {path: 'principal-transfer', component: PrincipalTransferComponent, canActivate: [DonneurOrdreGuard]},
            {path: 'cashier-transfer', component: CashierTransferComponent, canActivate: [CaissierGuard]},
            {path: 'asset-clearance', component: AssetClearanceComponent, canActivate: [DonneurOrdreGuard]},
            {path: 'direct-payment', component: DirectPaymentComponent, canActivate: ['directPaymentGuard']},
            // invoices-state
            {path: 'invoices-state', component: InvoiceStateComponent, canActivate: [AuthenticatedGuard]},
            // invoices types
            {path: 'invoices-types-management', component: InvoicesTypesComponent, canActivate: [AdminGuard]},
            // users-management
            {path: 'users-management', component: UsersManagementComponent, canActivate: [AdminGuard]},
            // users-management
            {path: 'users-management/:activation', component: UsersManagementComponent, canActivate: [AdminGuard]},
            // configs-management
            {path: 'configs-management', component: ConfigsManagementComponent, canActivate: [AdminGuard]},
            // sitting-date-management
            {path: 'sitting-date-management', component: SittingDateManagementComponent, canActivate: [AdminGuard]},
            // campost-accounts-management
            {path: 'campost-accounts-management', component: CampostAccountsManagComponent, canActivate: [AdminGuard]},
            // partners-management
            {path: 'partners-management/principals', component: PrincipalsComponent, canActivate: [AdminGuard]},
            {path: 'partners-management/beneficiaries', component: BeneficiariesComponent, canActivate: [AdminGuard]},
            {path: 'partners-management/banks', component: BanksComponent, canActivate: [AdminGuard]},
            {path: 'partners-management/agencies', component: AgenciesComponent, canActivate: [AdminGuard]},
            // partners-management
            {path: 'receipts-monitoring', component: ReceiptsMonitoringComponent, canActivate: [AdminGuard]},
            // transfer orders
            {path: 'transfer-orders-validation', component: ToValidationComponent, canActivate: ['toValidationGuard']},
            {path: 'transfer-orders-follow-up', component: ToFollowUpComponent, canActivate: ['toFollowUpGuard']},
            {path: 'transfer-orders-history', component: ToHistoryComponent, canActivate: ['toHistoryGuard']},
            {path: 'transfer-orders-acknowled', component: ToAcknowledComponent, canActivate: [BeneficiaireGuard]}
        ]
    }
];

export const LayoutRoutingProviders = [
    {provide: 'toHistoryGuard', useValue: toHistoryGuard},
    {provide: 'toFollowUpGuard', useValue: toFollowUpGuard},
    {provide: 'toValidationGuard', useValue: toValidationGuard},
    {provide: 'directPaymentGuard', useValue: directPaymentGuard}
];

export const LayoutRoutingModule = RouterModule.forChild(routes);

export function toHistoryGuard(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let userService = new UserService();
    return userService.hasRoles([
        Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_AUDITEUR_DONNEUR_ORDRE, Config.ROLE_CAISSIER,
        Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE, Config.ROLE_AUDITEUR_BANQUE, Config.ROLE_BENEFICIAIRE,
        Config.ROLE_DECIDEUR
    ]);
}

export function toFollowUpGuard(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let userService = new UserService();
    return userService.hasRoles([
        Config.ROLE_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_CAISSIER,
        Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE
    ]);
}

export function toValidationGuard(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let userService = new UserService();
    return userService.hasRoles([Config.ROLE_CONTROLEUR_DONNEUR_ORDRE, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE]);
}

export function directPaymentGuard(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let userService = new UserService();
    return !userService.isLoggedIn() || userService.hasRoles([Config.ROLE_DONNEUR_ORDRE]);
}