function secunx_Check() {
    SECUKITNX_CHECK.check([secukitnxInfo], "secunx_CheckCallback");
}

function secunx_CheckCallback(check) {

    if (check.status) {
        SECUKITNX_LOADING("nxsModuleChk");
    } else {
        setTimeout(function () {
            secunx_Check();
        }, 500);
    }
}

function secunx_Loading() {
    var localPage_href = window.document.location.href;
    var localPage = localPage_href.indexOf(NX_INSTALL_PAGENAME);
    if (localPage === -1) {
        // block WebUI before client loading
        if (NXSBlockWrapLayer === true)
        {
            try {
                $('.nxs_loading_block').show();
            } catch (e) { console.log(e); }
        }
    }

    SECUKITNX_CHECK.check([secukitnxInfo], "secunx_LoadingCallback");
}

function secunx_LoadingCallback(check) {
        
    nxlog("secunx_LoadingCallback", check);
    if (check.status) {
        SECUKITNX_LOADING("secunx_ModuleInit");
    }
    else {
        $('.nx-cert-select').hide();
        $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        var location = NX_SECUKIT_NX_TEXT_1;
        var reason = '';
        var errorcode = '';
        if (SECUKITNX_CONST.nextVerCheck == true)
        {
            switch(SECUKITNX_CONST.serviceType)
            {
                case 1: errorcode = 'ERR_CLIENT_RELEASE_VERSION';
                    break;
                case 2: errorcode = 'ERR_CLIENT_RELEASE_VERSION_XML';
                    break;
                case 3: errorcode = 'ERR_CLIENT_RELEASE_VERSION_KMS';
                    break;
                default: errorcode = 'ERR_CLIENT_RELEASE_VERSION';
                    break;
            }
            
        } else {
           switch (SECUKITNX_CONST.serviceType) {
                case 1: errorcode = 'ERR_CLIENT_NO_INSTALL';
                    break;
                case 2: errorcode = 'ERR_CLIENT_NO_INSTALL_XML';
                    break;
                case 3: errorcode = 'ERR_CLIENT_NO_INSTALL_KMS';
                    break;
                default: errorcode = 'ERR_CLIENT_NO_INSTALL';
                    break;
            }
        }
        
        KICA_ERROR_RESOURCE.ErrorMessage(location, reason, errorcode);
        var ScriptErrorMessage = KICA_Error.getScriptError();

        var localPage_href = window.document.location.href;
        var localPage = localPage_href.indexOf(NX_INSTALL_PAGENAME);

        if (NX_INSTALL_FLAG === false) {
            // no other install page
            alert(ScriptErrorMessage);
            NXdownClientURL();
            secunx_Check();
        } else {
            // if install pages be there in application
            if (localPage !== -1 && downClientFlag === false) {
                // install page, install file was did downloaded
                NXdownClientURL();
                secunx_Check();
            } else if (localPage === -1 && downClientFlag === false) {
                // called page is not install
                alert(ScriptErrorMessage);
                window.location = NX_INSTALL_PAGE;
                return;
            }
        }
        SecuKitNX_Ready(false);
    }
}

function secunx_ModuleInit(result) {
    // WebUI block out
    if (NXSBlockWrapLayer === true) {
        try {
            $('.nxs_loading_block').hide();
        } catch (e) { console.log(e); }
    }

    if (result) {
        nxsModuleChk();
        
    } else {
        SecuKitNX_Ready(false);
        SECUKITNX_CONST.load = false;
    }
}