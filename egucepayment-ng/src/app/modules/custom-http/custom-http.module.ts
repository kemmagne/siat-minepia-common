import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { XHRBackend } from '@angular/http';

import {DialogModule, ProgressSpinnerModule, ProgressBarModule} from 'primeng/primeng';

import { HttpService } from './http.service';
import { httpServiceFactory } from './http-service.factory';
import { LoaderService } from './loader/loader.service';
import { LoaderComponent } from './loader/loader.component';
import { TranslateModule, TranslateService } from "@ngx-translate/core";
import { Router } from "@angular/router";

@NgModule({
  imports: [
      CommonModule, TranslateModule, DialogModule,
      ProgressSpinnerModule, ProgressBarModule
  ],
  exports: [
      //LoaderComponent
  ],
  declarations: [
      //LoaderComponent
  ],
  providers: [
      LoaderService, {
          provide: HttpService,
          useFactory: httpServiceFactory,
          deps: [XHRBackend, LoaderService, Router, TranslateService]
      }
  ]
})
export class CustomHttpModule {}
