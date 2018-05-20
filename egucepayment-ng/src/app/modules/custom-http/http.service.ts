import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import {
    Http,
    RequestOptions,
    RequestOptionsArgs,
    Response,
    Request,
    Headers,
    XHRBackend,
    ResponseContentType
} from '@angular/http';
import { saveAs } from 'file-saver/FileSaver';

import * as CryptoJS from 'crypto-js';

import { Config } from '../../config';

import { LoaderService } from './loader/loader.service';
import { Utils } from "../../utils";
import { Router } from "@angular/router";
import { TranslateService } from 'ng2-translate';

@Injectable()
export class HttpService extends Http {

    private apiUrl = `${Config.PROTOCOL}//${Config.REST_API_HOST}:${Config.PORT + Config.BASE_REST_API}`;

    constructor(backend: XHRBackend, private loaderService: LoaderService, private router: Router,
    private translateService: TranslateService) {
        super(backend, new RequestOptions());
    }

    get(url: string, secured?: boolean): Observable<any> {
        this.loaderService.showGet();
        return super.get(this.getFullUrl(url), this.requestOptions(secured))
            .catch(this.onCatch)
            .do((res: Response) => {
                this.onSuccess(res);
            }, (error: any) => {
                this.onError(error);
            })
            .finally(() => {
                this.loaderService.hideGet();
            });
    }

    post(url: string, body: any, unsecured?: boolean): Observable<any> {
        this.loaderService.showPost();
        return super.post(this.getFullUrl(url), body, this.requestOptions(!unsecured))
            .catch(this.onCatch)
            .do((res: Response) => {
                this.onSuccess(res);
            }, (error: any) => {
                this.onError(error);
            })
            .finally(() => {
                this.loaderService.hidePost();
            });
    }

    delete(url: string): Observable<any> {
        this.loaderService.showPost();
        return super.delete(this.getFullUrl(url), this.requestOptions(true))
            .catch(this.onCatch)
            .do((res: Response) => {
                this.onSuccess(res);
            }, (error: any) => {
                this.onError(error);
            })
            .finally(() => {
                this.loaderService.hidePost();
            });
    }

    download(url: string, secured?: boolean): Observable<any> {
        this.loaderService.showGet();
        return super.get(this.getFullUrl(url), this.requestOptions(secured, true))
            .catch(this.onCatch)
            .do((res: Response) => {
                this.onSuccess(res);
            }, (error: any) => {
                this.onError(error);
            })
            .finally(() => {
                this.loaderService.hideGet();
            });
    }

    private requestOptions(authenticated: boolean, file?: boolean): RequestOptionsArgs {
        let reqArgs: RequestOptionsArgs = {};
        let headers = new Headers();
        headers.append(Config.LOCALE_KEY, this.translateService.currentLang);
        if(authenticated) {
            headers.append(Config.USER_LOGIN_KEY, localStorage.getItem(Config.USER_LOGIN_KEY));
            headers.append(Config.USER_ROLES_KEY, localStorage.getItem(Config.USER_ROLES_KEY));
            headers.append(Config.AUTHORIZATION_KEY, Config.AUTHORIZATION_PREFIX + localStorage.getItem(Config.JWT_TOKEN_KEY));
            headers.append(Config.BROWSER_FINGER_PRINT_KEY, Utils.getBrowserPrint());
            reqArgs.headers = headers;
        } else {
            reqArgs.headers = headers;
        }
        if(file) {
            reqArgs.responseType = ResponseContentType.Blob;
        }
        return reqArgs;
    }

    private getFullUrl(url: string): string {
        return this.apiUrl + url;
    }

    private onCatch(error: any, caught: Observable<any>): Observable<any> {
        return Observable.throw(error);
    }

    private onSuccess(res: Response): Response {
        return res;
    }

    private onError(res: Response): Response {
        let status = +res.status;
        if(401 === status) {
            this.router.navigate(['/error', 'unauthorized']);
        }
        if(404 === status) {
            this.router.navigate(['/error', 'not-found']);
        }
        return res;
    }

}
