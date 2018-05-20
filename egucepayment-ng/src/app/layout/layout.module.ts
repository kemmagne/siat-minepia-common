import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule, Http} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LayoutRoutingModule, LayoutRoutingProviders } from "./layout-routing.module";
import { TranslateModule } from "ng2-translate";

import {AutoCompleteModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import {SharedModule} from 'primeng/primeng';
import {DataTableModule} from 'primeng/primeng';
import {DialogModule} from 'primeng/primeng';
import {GrowlModule} from 'primeng/primeng';
import {InputTextModule} from 'primeng/primeng';
import {MessagesModule} from 'primeng/primeng';
import {RadioButtonModule} from 'primeng/primeng';
import { BlockUIModule, DropdownModule, ChartModule, FieldsetModule, CalendarModule, InputMaskModule, MultiSelectModule, FileUploadModule, TriStateCheckboxModule, SpinnerModule } from 'primeng/primeng';

import { AuthenticatedGuard, AdminGuard, DonneurOrdreGuard, DecideurGuard, BeneficiaireGuard, AuditeurBanqueGuard, ControleurBanqueGuard,
    CaissierGuard, AuditeurDonneurOrdreGuard, ControleurDonneurOrdreGuard, NotAuthenticatedGuard, ControleurAgenceGuard
} from "../guards";
import { CashierTransferComponent } from "../invoice-payment/cashier-transfer/cashier-transfer.component";
import { PrincipalTransferComponent } from "../invoice-payment/principal-transfer/principal-transfer.component";
import { AssetClearanceComponent } from "../invoice-payment/asset-clearance/asset-clearance.component";
import { DirectPaymentComponent } from "../invoice-payment/direct-payment/direct-payment.component";
import { InvoiceStateComponent } from "../invoice-state/invoice-state.component";
import { HomeComponent } from "../home/home.component";
import { InvoiceTableModule } from "../modules/invoice-table/invoice-table.module";
import { BankAccountTableModule } from "../modules/bank-account-table/bank-account-table.module";
import { CustomHttpModule } from "../modules/custom-http/custom-http.module";
import { LoaderComponent } from "../modules/custom-http/loader/loader.component";
import { ConfirmDialogModule } from "../modules/confirm-dialog/confirm-dialog.module";
import { ToValidationComponent } from "../transfer-order/to-validation/to-validation.component";
import { UserProfileComponent } from "../user-profile/user-profile.component";
import { ToFollowUpComponent } from "../transfer-order/to-follow-up/to-follow-up.component";
import { DashbordDirective } from "../home/dashbord.directive";
import { DecideurDashbordComponent } from "../home/decideur-dashbord/decideur-dashbord.component";
import { AdminDashbordComponent } from "../home/admin-dashbord/admin-dashbord.component";
import { TransferOrdersTableModule } from "../modules/transfer-orders-table/transfer-orders-table.module";
import { InvoicesTypesComponent } from "../invoices-types/invoices-types.component";
import { UsersManagementComponent } from "../users-management/users-management.component";
import { AlertModule } from "../modules/alert/alert.module";
import { InvoiceSearchModule } from "../modules/invoice-search/invoice-search.module";
import { ConfigsManagementComponent } from "../configs-management/configs-management.component";
import { PipesModule } from "../pipes/pipes.module";
import { BanqueDashboardComponent } from "../home/banque-dashboard/banque-dashboard.component";
import { BeneficiaireDashboardComponent } from "../home/beneficiaire-dashboard/beneficiaire-dashboard.component";
import { ToHistoryComponent } from "../transfer-order/to-history/to-history.component";
import { TransferOrderFilterModule } from "../modules/transfer-order-filter/transfer-order-filter.module";
import { SittingDateManagementComponent } from "../sitting-date-management/sitting-date-management.component";
import { ReceiptsMonitoringComponent } from "../receipts-monitoring/receipts-monitoring.component";
import { ToAcknowledComponent } from "../transfer-order/to-acknowled/to-acknowled.component";
import { PrincipalsComponent } from "../partners/principals/principals.component";
import { PartnersTableModule } from "../modules/partners-table/partners-table.module";
import { BeneficiariesComponent } from "../partners/beneficiaries/beneficiaries.component";
import { BanksComponent } from "../partners/banks/banks.component";
import { AgenciesComponent } from "../partners/agencies/agencies.component";
import { CampostAccountsManagComponent } from "../campost-accounts-manag/campost-accounts-manag.component";
import { CampostMobileModule } from "../invoice-payment/direct-payment/campost-mobile/campost-mobile.module";

@NgModule({
  imports: [
      BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        BrowserAnimationsModule,
        AutoCompleteModule,
        ButtonModule,
        SharedModule,
        DataTableModule,
        DialogModule,
        GrowlModule,
        MessagesModule, TriStateCheckboxModule, SpinnerModule,
        RadioButtonModule,
        BlockUIModule, DropdownModule, ChartModule,
        CommonModule, LayoutRoutingModule, TranslateModule, FileUploadModule,
        InvoiceTableModule, BankAccountTableModule, CustomHttpModule, ConfirmDialogModule, TransferOrdersTableModule, AlertModule, GrowlModule,
        InvoiceSearchModule, PipesModule, TransferOrderFilterModule, FieldsetModule, CalendarModule, InputMaskModule, SharedModule,
        MultiSelectModule, PartnersTableModule, CampostMobileModule
  ],
  declarations: [
      CashierTransferComponent, PrincipalTransferComponent, AssetClearanceComponent, DirectPaymentComponent,
      InvoiceStateComponent, HomeComponent, LoaderComponent, ToValidationComponent, ToFollowUpComponent,
      UserProfileComponent, DashbordDirective, AdminDashbordComponent, DecideurDashbordComponent, InvoicesTypesComponent, UsersManagementComponent,
      ConfigsManagementComponent, BanqueDashboardComponent, BeneficiaireDashboardComponent,
      ToHistoryComponent, SittingDateManagementComponent, ReceiptsMonitoringComponent, ToAcknowledComponent, PrincipalsComponent,
      BeneficiariesComponent, BanksComponent, AgenciesComponent, CampostAccountsManagComponent
  ],
  entryComponents: [AdminDashbordComponent, DecideurDashbordComponent, BanqueDashboardComponent, BeneficiaireDashboardComponent],
  exports: [LoaderComponent],
  providers: [
      AuthenticatedGuard, NotAuthenticatedGuard, AdminGuard, DonneurOrdreGuard, ControleurDonneurOrdreGuard, AuditeurDonneurOrdreGuard, CaissierGuard,
      ControleurAgenceGuard, ControleurBanqueGuard, AuditeurBanqueGuard, BeneficiaireGuard, DecideurGuard, LayoutRoutingProviders
  ]
})
export class LayoutModule {}
