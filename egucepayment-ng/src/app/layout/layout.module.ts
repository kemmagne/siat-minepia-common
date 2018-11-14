import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout.component';
import { LoaderComponent } from '../modules/custom-http/loader/loader.component';
import { HomeComponent } from "../home/home.component";
import { AuthenticatedGuard, NotAuthenticatedGuard } from '../guards';
import { LayoutRoutingModule, LayoutRoutingProviders } from './layout-routing.module';
import { AccordionModule, AutoCompleteModule, BreadcrumbModule, ButtonModule, CalendarModule,
  CardModule, CarouselModule, ChartModule, CheckboxModule, ChipsModule, CodeHighlighterModule,
  ConfirmDialogModule, ColorPickerModule, ContextMenuModule, DialogModule, DropdownModule,
  EditorModule, FieldsetModule, FileUploadModule, GalleriaModule, GrowlModule, InplaceModule,
  InputMaskModule, InputSwitchModule, InputTextModule, InputTextareaModule, LightboxModule,
  ListboxModule, MegaMenuModule, MenuModule, MenubarModule, MessageModule, MessagesModule,
  MultiSelectModule, OrderListModule, OrganizationChartModule, OverlayPanelModule, PaginatorModule,
  PanelModule, PanelMenuModule, PasswordModule, PickListModule, ProgressBarModule, RadioButtonModule,
  RatingModule, ScheduleModule, ScrollPanelModule, SelectButtonModule, SlideMenuModule, SliderModule,
  SpinnerModule, SplitButtonModule, StepsModule, TabMenuModule, TabViewModule, TerminalModule,
  TieredMenuModule, ToggleButtonModule, ToolbarModule, TooltipModule, TreeModule, TreeTableModule,
  ProgressSpinnerModule } from 'primeng/primeng';
import { DataViewModule } from 'primeng/dataview';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { TranslateModule } from '@ngx-translate/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { CustomHttpModule } from '../modules/custom-http/custom-http.module';

@NgModule({
  imports: [
      CommonModule, LayoutRoutingModule, HttpModule, HttpClientModule,
      CustomHttpModule,

      AccordionModule,
        AutoCompleteModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        CarouselModule,
        ChartModule,
        CheckboxModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        ColorPickerModule,
        ContextMenuModule,
        DataViewModule,
        DialogModule,
        DropdownModule,
        EditorModule,
        FieldsetModule,
        FileUploadModule,
        GalleriaModule,
        GrowlModule,
        InplaceModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessageModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OrganizationChartModule,
        OverlayPanelModule,
        PaginatorModule,
        PanelModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        RatingModule,
        ScheduleModule,
        ScrollPanelModule,
        SelectButtonModule,
        SlideMenuModule,
        SliderModule,
        SpinnerModule,
        SplitButtonModule,
        StepsModule,
        TableModule,
        TabMenuModule,
        TabViewModule,
        TerminalModule,
        TieredMenuModule,
        ToastModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        ProgressSpinnerModule,

        TranslateModule,
  ],
  exports: [LoaderComponent],
  declarations: [LoaderComponent, HomeComponent,
    ],
  providers: [AuthenticatedGuard, NotAuthenticatedGuard, LayoutRoutingProviders]
})
export class LayoutModule { }
