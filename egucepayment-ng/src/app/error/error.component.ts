import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from "@angular/router";
import { UserService } from "../services";

@Component({
    selector: 'app-error',
    templateUrl: './error.component.html',
    providers: [UserService]
})
export class ErrorComponent implements OnInit {

    topClass: string;
    errorCode: string;
    errorTitle: string;
    errorMsg: string;

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {}

    ngOnInit() {
        this.route.params.forEach(
            (params : Params) => {
                let status = params['type'];
                if('unauthorized' === status) {
                    this.topClass = 'accessdenied';
                    this.errorCode = '401';
                    this.errorTitle = 'unauthorized';
                    this.errorMsg = 'not.logged.in';
                } else if('forbidden' === status) {
                    this.topClass = 'accessdenied';
                    this.errorCode = '403';
                    this.errorTitle = 'forbidden';
                    this.errorMsg = 'cannot.access.resource';
                } else if('server-error' === status) {
                    this.topClass = 'error';
                    this.errorCode = 'error';
                    this.errorTitle = 'server.error';
                    this.errorMsg = 'cannot.access.resource';
                } else {
                    this.topClass = 'notfound';
                    this.errorCode = '404';
                    this.errorTitle = 'page.not.found';
                    this.errorMsg = 'resource.does.not.exist';
                }
            }
        );
    }

    goToHome() {
        this.userService.goToHome(this.router);
    }

}
