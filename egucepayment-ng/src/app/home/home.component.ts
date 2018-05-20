import { Component, OnInit, OnDestroy, AfterViewInit, ViewChild, ComponentFactoryResolver, Type } from '@angular/core';
import { DashbordDirective } from "./dashbord.directive";
import { DashbordItem } from "./dashbord.item";
import { AdminDashbordComponent } from "./admin-dashbord/admin-dashbord.component";
import { DecideurDashbordComponent } from "./decideur-dashbord/decideur-dashbord.component";
import { UserService } from "../services";
import { BanqueDashboardComponent } from "./banque-dashboard/banque-dashboard.component";
import { Config } from "../config";
import { BeneficiaireDashboardComponent } from "./beneficiaire-dashboard/beneficiaire-dashboard.component";

@Component({
    selector: 'app-home',
    template: `
        <div>
            <ng-template appDashbord></ng-template>
        </div>
  `
})
export class HomeComponent implements OnInit, OnDestroy {

    currentDashbord: DashbordItem;

    @ViewChild(DashbordDirective)
    private dashbord: DashbordDirective;

    constructor(private componentFactoryResolver: ComponentFactoryResolver, private userService: UserService) {}

    ngOnInit() {
        // suivant le rôle de l'utilisateur, on charge son dashbord
        if(this.userService.hasRoles([Config.ROLE_CAISSIER, Config.ROLE_CONTROLEUR_AGENCE, Config.ROLE_CONTROLEUR_BANQUE, Config.ROLE_AUDITEUR_BANQUE])) {
            this.changeDashboard(BanqueDashboardComponent);
        } else if(this.userService.hasRoles([Config.ROLE_BENEFICIAIRE])) {
            this.changeDashboard(BeneficiaireDashboardComponent);
        } else if(this.userService.hasRoles([Config.ROLE_DECIDEUR])) {
            this.changeDashboard(DecideurDashbordComponent);
        } else if(this.userService.hasRoles([Config.ROLE_ADMIN])) {
            this.changeDashboard(AdminDashbordComponent);
        }
    }

    ngOnDestroy() {
        this.currentDashbord = null;
    }

    private changeDashboard(dashboard: Type<any>) {
        this.currentDashbord = new DashbordItem(dashboard);
        this.show(this.currentDashbord);
    }

    private show(dashbordItem: DashbordItem) {
        let viewContainerRef = this.dashbord.viewContainerRef;
        viewContainerRef.clear();
        let componentFactory = this.componentFactoryResolver.resolveComponentFactory(dashbordItem.component);
        let componentRef = viewContainerRef.createComponent(componentFactory);
    }

}
