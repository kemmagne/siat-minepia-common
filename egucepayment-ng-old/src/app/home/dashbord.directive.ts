import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
    selector: '[appDashbord]'
})
export class DashbordDirective {

    constructor(public viewContainerRef: ViewContainerRef) {}

}
