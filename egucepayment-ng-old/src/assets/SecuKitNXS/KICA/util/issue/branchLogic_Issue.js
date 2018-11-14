var _copyMedia = null;

function NX_branchLogic_ISSUE() {
    var Logic_flag = processLogic.getProcessLogic();
    if (Logic_flag.indexOf('ISSUE') !== -1) {
        //issueCertificate.branchLogic(Logic_flag);

        if (Logic_flag === 'KICA.ISSUE.RenewCertificateInfo') {
            $('#nx-pki-ui-wrapper').hide();
            SecuKitNX_Result('updateCertInfo');
        }

        if (Logic_flag === 'KICA.ISSUE.RenewCertificate') {

            // show -ing alert
            $('.nx-issue-ing-alert-head-msg').remove();
            var headMessage = '';
            var alertMessage = '';
            headMessage += '<div class=\"nx-issue-ing-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_28 + '</h>';
            alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_29 + '</span></p></div>';
            headMessage += '</div>';
            $('#nx-issue-ing-alert-head').append(headMessage);
            $('#issue-ing-alert-message').remove();
            $('#nx-issue-ing-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-ing-alert').show();
            }, 100);

            setTimeout(function () { issueCertificate.updateCert(); }, 200);

        }

        // RevokeCert
        if (Logic_flag === 'KICA.ISSUE.RevokeCert') {
            issueCertificate.revokeCert();
        }

        // HoldCert
        if (Logic_flag === 'KICA.ISSUE.HoldCert') {
            issueCertificate.holdCert();
        }

        // IssueKmCert
        if (Logic_flag === 'KICA.ISSUE.IssueKmCert') {
            issueCertificate.issueKmCert();
        }
    }

    if (Logic_flag.indexOf('MANAGEMENT') !== -1) {
        //delete cert
        if ((Logic_flag === 'KICA.MANAGEMENT.DeleteCertificate')) {
            var mediaType = SelectMediaInfo.getMediaType();
            var mediaTypeString = '';

            if (mediaType === 'HDD') {
                mediaTypeString = NX_TARGET_DIALOG_TEXT_2;
            }
            if (mediaType === 'USB') {
                mediaTypeString = NX_TARGET_DIALOG_TEXT_3;
            }
            if (mediaType === 'HSM') {
                mediaTypeString = NX_TARGET_DIALOG_TEXT_5;
            }
            if (mediaType === 'BIOHSM') {
                mediaTypeString = NX_TARGET_DIALOG_TEXT_6;
            }

            //cert cn 
            var selectCertIndex = certListInfo.getCertListIndex();
            var certListObj = certListInfo.getCertListInfo();
            var certName = certListObj[selectCertIndex].cn;

            $('#nx-cert-delete-box-msg').remove();

            var deleteMsg = '<div id=\"nx-cert-delete-box-msg\">';
            deleteMsg += '<p>' + NX_ISSUE_TEXT_20 + mediaType + NX_ISSUE_TEXT_21 + '<br />';
            deleteMsg += NX_ISSUE_TEXT_22 + certName + NX_ISSUE_TEXT_23 + '<br />';
            deleteMsg += '<strong>' + NX_ISSUE_TEXT_19 + '</strong></p>';
            deleteMsg += '</div>';

            $('#nx-cert-delete-box').append(deleteMsg);
            $('#nx-cert-select').hide();
            $('#nx-cert-delete').show();
        }

        // export pfx
        if (Logic_flag === 'KICA.MANAGEMENT.EXPORT_PFX') {
            var certType = '',  //(SignCert, EncryptCert)
                filePath = '',  //if the path is '' save in cert path
                fileName = '',  
                certID = '';    

            fileName = $('#exportP12Name').val();
            $('#exportP12Name').val('');

            // with km or not
            var flag_WithKm = document.getElementById('exportWithKm');
            var flag_WithOutKm = document.getElementById('exportWithoutKm');

            if (flag_WithKm.checked) {
                // with KmCert
                certType = 'EncryptCert';
            }

            if (flag_WithOutKm.checked) {
                // without KmCert
                certType = 'SignCert';
            }

            // certID
            certID = certListInfo.getCertID();
            //alert(certType + '  :  ' + filePath + '  :  ' + fileName + '  :  ' + certID);

            // export p12
            CertManagement.exportP12(certType, filePath, fileName, certID);
        }

        // import pfx
        if (Logic_flag === 'KICA.MANAGEMENT.IMPORT_PFX') {
            var fullfilePath = '',  
                fileName = '',  
                pfxpwd = '',  
                certID = '';    

            fullfilePath = $('#importPFXFileName').val();
            fileName = fullfilePath.replace(/^.*(\\|\/|\:)/, '');
            pfxpwd = $('#importPFXPwd').val();
            $('#importPFXPwd').val('');

            var tmp = fullfilePath.indexOf(fileName);
            var filePath = fullfilePath.substring(0, tmp);

            // import p12
            CertManagement.importP12(filePath, fileName, pfxpwd, certID);
        }

        // copy cert
        if (Logic_flag === 'KICA.MANAGEMENT.CopyCert') {
            var media = SelectMediaInfo.getMediaType();
            _copyMedia = document.getElementById(media);
            _copyMedia.disabled = true;

            // target media box
            $('#nx-targetMedia-select').show();
        }

        // CheckPassword
        if (Logic_flag === 'KICA.MANAGEMENT.CheckPassword') {
            var certListIndex = certListInfo.getCertListIndex();
            var certID = certListInfo.getCertID();
            CertManagement.checkPassword(certListIndex, certID);
        }

        // ShowCert
        if (Logic_flag === 'KICA.MANAGEMENT.ShowCert') {
            var certType = 'SignCert';
            var sourceString = 'test1234567890!@#$%^&*()Test';
            var algorithm = 'SHA256';
            var certID = certListInfo.getCertID();
            CertManagement.showCert(certType, sourceString, algorithm, certID);
        }

        // AuthIdentify
        if (Logic_flag === 'KICA.MANAGEMENT.AuthIdentify') {
            CertManagement.recoverIdentity();
        }

        // verify identity
        if (Logic_flag === 'KICA.MANAGEMENT.VerifyIdentify') {
            // show input ssn
            $('#nx-cert-VerifyIdentify').show();
        }

        // ChangePassword
        if (Logic_flag === 'KICA.MANAGEMENT.ChangePassword') {
            // show input password
            $('#nx-pwd-insert').show();
        }

        // CertValidation
        //if (Logic_flag === 'KICA.MANAGEMENT.CertValidation') {
        //}
    }

    if (Logic_flag === '') {
        $('#nx-pki-ui-wrapper').hide();
    }
}


//****************************
// result of issue/reissue/renew/revoke
//****************************
var NX_ISSUE_Result = (function () {
    var ISSUE_RES = 'false',            // success or not
        ISSUE_USERNAME = '',            // cert NAME
        ISSUE_DN = '',                  // cert DN
        ISSUE_Vaildate_From = '',       
        ISSUE_Vaildate_To = '';         


    var init = function () {
        ISSUE_RES = 'false';
        ISSUE_USERNAME = '';
        ISSUE_DN = '';
        ISSUE_Vaildate_From = '';
        ISSUE_Vaildate_To = '';
    };

    var setResult = function (res) {
        ISSUE_RES = res;
    };

    var getResult = function () {
        return ISSUE_RES;
    };

    var setIssueUserName = function (name) {
        ISSUE_USERNAME = name;
    };

    var getIssueUserName = function () {
        return ISSUE_USERNAME;
    };

    var setIssueCertDN = function (dn) {
        ISSUE_DN = dn;
    };

    var getIssueCertDN = function () {
        return ISSUE_DN;
    };

    var setIssueCertVaildateFrom = function (f) {
        ISSUE_Vaildate_From = f;
    };

    var getIssueCertVaildateFrom = function () {
        return ISSUE_Vaildate_From;
    };

    var setIssueCertVaildateTo = function (t) {
        ISSUE_Vaildate_To = t;
    };

    var getIssueCertVaildateTo = function () {
        return ISSUE_Vaildate_To;
    };

    return {
        init: init,
        setResult: setResult,
        getResult: getResult,

        setIssueUserName: setIssueUserName,
        getIssueUserName: getIssueUserName,

        setIssueCertDN: setIssueCertDN,
        getIssueCertDN: getIssueCertDN,

        setIssueCertVaildateFrom: setIssueCertVaildateFrom,
        getIssueCertVaildateFrom: getIssueCertVaildateFrom,

        setIssueCertVaildateTo: setIssueCertVaildateTo,
        getIssueCertVaildateTo: getIssueCertVaildateTo
    };
})();

//****************************
// result of show cert
//****************************
var NX_CertINFO_Result = (function () {
    var USERDN = '',
        SIGNCERT = '',
        SINGED_DATA = '';

    var init = function () {
        USERDN = '';
        SIGNCERT = '';
        SINGED_DATA = '';
    };

    var setUserDN = function (dn) {
        USERDN = dn;
    };

    var getUserDN = function () {
        return USERDN;
    };

    var setSignCert = function (sign) {
        SIGNCERT = sign;
    };

    var getSignCert = function () {
        return SIGNCERT;
    };

    var setSigendData = function (data) {
        SINGED_DATA = data;
    };

    var getSignedData = function () {
        return SINGED_DATA;
    };

    return {
        init: init,
        setUserDN: setUserDN,
        getUserDN: getUserDN,
        setSignCert: setSignCert,
        getSignCert: getSignCert,
        setSigendData: setSigendData,
        getSignedData: getSignedData
    };
})();