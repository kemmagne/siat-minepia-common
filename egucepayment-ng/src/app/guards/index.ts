import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { UserService } from "../services";
import { Config } from "../config";

@Injectable()
export class AuthenticatedGuard implements CanActivate {

    constructor(private userService: UserService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if(!this.userService.isLoggedIn()) {
            this.router.navigate(["/login"]);
            return false;
        }
        return true;
    }

}

@Injectable()
export class NotAuthenticatedGuard implements CanActivate {

    constructor(private userService: UserService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return !this.userService.isLoggedIn();
    }

}

@Injectable()
export class AdminGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_ADMIN]);
    }

}

@Injectable()
export class DonneurOrdreGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_DONNEUR_ORDRE]);
    }

}

@Injectable()
export class ControleurDonneurOrdreGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_CONTROLEUR_DONNEUR_ORDRE]);
    }

}

@Injectable()
export class AuditeurDonneurOrdreGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_AUDITEUR_DONNEUR_ORDRE]);
    }

}

@Injectable()
export class CaissierGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_CAISSIER]);
    }

}

@Injectable()
export class ControleurAgenceGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_CONTROLEUR_AGENCE]);
    }

}

@Injectable()
export class ControleurBanqueGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_CONTROLEUR_BANQUE]);
    }

}

@Injectable()
export class AuditeurBanqueGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_AUDITEUR_BANQUE]);
    }

}

@Injectable()
export class BeneficiaireGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_BENEFICIAIRE]);
    }

}

@Injectable()
export class DecideurGuard implements CanActivate {
    
    constructor(private userService: UserService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return this.userService.hasRoles([Config.ROLE_DECIDEUR]);
    }

}