import { XHRBackend } from '@angular/http';
import { HttpService } from './http.service';
import { LoaderService } from './loader/loader.service';
import { Router } from "@angular/router";
import { TranslateService } from '@ngx-translate/core';

function httpServiceFactory(backend: XHRBackend, loaderService: LoaderService, router: Router,
    translateService: TranslateService) {
    return new HttpService(backend, loaderService, router, translateService);
}

export { httpServiceFactory };