import { XHRBackend } from '@angular/http';
import { HttpService } from './http.service';
import { LoaderService } from './loader/loader.service';
import { Router } from "@angular/router";
import { TranslateService } from 'ng2-translate';

function httpServiceFactory(backend: XHRBackend, loaderService: LoaderService, router: Router,
    translateService: TranslateService) {
    return new HttpService(backend, loaderService, router, translateService);
}

export { httpServiceFactory };