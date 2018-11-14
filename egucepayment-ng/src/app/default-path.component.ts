import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './services';

@Component({
    template: '...'
})
export class DefaultPathComponent implements OnInit {

    constructor(private router: Router, private userService: UserService) {}

    ngOnInit() {
        this.userService.goToHome(this.router);
    }

}
