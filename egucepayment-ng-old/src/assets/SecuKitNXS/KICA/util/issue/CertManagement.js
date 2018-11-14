/**
 * @public
 * @class
 * @description for cert management class
 */
var CertManagement = (function () {
    var NXPWD = '',
        NEW_NXPWD = '',
        NEW_NXPWD_CONFIRM = '',
        NXSSN = '';

    var init = function () {
        NXPWD = '';
        NEW_NXPWD = '';
        NEW_NXPWD_CONFIRM = '';
        NXSSN = '';
    };

    var setPwd = function (p) {
        NXPWD = p;
    };

    var setNEW_NXPWD = function (np) {
        NEW_NXPWD = np;
    };

    var setNEW_NXPWD_CONFIRM = function (npc) {
        NEW_NXPWD_CONFIRM = npc;
    };

    var setNXSSN = function (s) {
        NXSSN = s;
    };

    /**
    * @public
    * @memberof CertManagement
    * @method copyCert
    * @description 
    * @param 
    */
    var copyCert = function (mediaType, extraValue, overWrite) {
        if ((InsertNullCheck(mediaType) === false) &&
            (InsertNullCheck(extraValue) === false)) {

            var certID = certListInfo.getCertID();
            var cmd = "CertManagement.copyCert";
            var Data = {
                'mediaType': mediaType,
                'extraValue': extraValue,
                'password': NXPWD,
                'overWrite': overWrite,
                'certID': certID
            };

            var param = JSON.stringify(Data);
            secukitnxInterface.SecuKitNX(cmd, param);
        } else {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'CertManagement.copyCert';
            var reason = '';
            var errorcode = '';

            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, errorcode);
            var ScriptErrorMessage = KICA_Error.getScriptError();
            alert(ScriptErrorMessage);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method copyCertCallback
     * @description copyCert callback
     * @param reply callback data
     * @returns 
     */
    var copyCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            var flag = reply.copyCert;

            if (flag === 'true') {

                CertManagement.init();

                //success window : copy
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_22 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_22 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-issue-success-alert').show();
                }, 200);

            }
            else if (flag === 'exist') {
                //remove past cert alert
                $('#nx-cert-copy-duplication').show();
            }
        }
        else {
            CertManagement.init();

            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_1 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_2;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method deleteCertificate
     * @description delete cert
     * @param id => not use
     * @param password => not use
     * @param certID delete cert CertID
     */
    var deleteCertIssue = function (id, pwd, certID) {
        var cmd = "CertManagement.deleteCertIssue";
        var Data = {
            'ID': id,
            'password': pwd,
            'certID': certID
        };

        CertManagement.init();

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
    * @public
    * @memberof CertManagement
    * @method deleteCertificateCallback
    * @description delete cert callback
    * @param 
    */
    var deleteCertIssueCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            if (reply.deleteCertificate === 'true') {

                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_3 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_revoke.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_3 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-issue-success-alert').show();
                }, 200);
            }
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_4 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_revoke.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_5;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method checkPassword
     * @description check password function
     * @param 
     */
    var checkPassword = function (certListIndex, certID) {
        var cmd = "CertManagement.checkPassword";
        var Data = {
            'certListIndex': certListIndex,
            'password': NXPWD,
            'certID': certID
        };

        CertManagement.init();

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
     * @public
     * @memberof CertManagement
     * @method checkPasswordCallback
     * @description cjeckpassword callback
     * @param 
     */
    var checkPasswordCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {

            //success window : check password
            $('.nx-issue-success-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_23 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);

            $('.nx-issue-success-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_23 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_6 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_7;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method changePassword
     * @description change password function
     * @param 
     */
    var changePassword = function () {
        var cmd = "CertManagement.changePassword";
        var certID = certListInfo.getCertID();
        var Data = {
            'newPassword': NEW_NXPWD,
            'newConfirmPassword': NEW_NXPWD_CONFIRM,
            'certID': certID
        };

        CertManagement.init();

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
     * @public
     * @memberof CertManagement
     * @method changePasswordCallback
     * @description change password callback
     * @param 
     */
    var changePasswordCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            //success window : change password
            $('.nx-issue-success-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_24 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);

            $('.nx-issue-success-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_24 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_8 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_9;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method verifyIdentity
     * @description verify identity function
     * @param 
     */
    var verifyIdentity = function () {
        var cmd = "CertManagement.verifyIdentity";
        var certID = certListInfo.getCertID();
        var Data = {
            'ssn': NXSSN,
            'certID': certID
        };

        CertManagement.init();

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
     * @public
     * @memberof CertManagement
     * @method verifyIdentityCallback
     * @description verify identity callback
     * @param 
     */
    var verifyIdentityCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            if (reply.verifyID === 'true') {
                //success window : verify identity
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_25 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_25 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-issue-success-alert').show();
                }, 200);
            }
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_10 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_11;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method showCert
     * @description show (DN)
     * @param 
     */
    var showCert = function (certType, sourceString, algorithm, certID) {
        var cmd = "CertManagement.showCert";
        var Data = {
            'certType': certType,
            'sourceString': sourceString,
            'algorithm': algorithm,
            'certID': certID
        };

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
     * @public
     * @memberof CertManagement
     * @method showCertCallback
     * @description show(DN), callback
     * @param 
     */
    var showCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {

            //save result
            NX_CertINFO_Result.init();
            NX_CertINFO_Result.setUserDN(reply.userDN);
            NX_CertINFO_Result.setSignCert(reply.signCert);
            NX_CertINFO_Result.setSigendData(reply.signedData);

            //send result
            SecuKitNX_Result('ShowCert');

            //success window : showcert
            $('.nx-issue-success-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_26 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);

            $('.nx-issue-success-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_26 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_27 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_13;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
     * @public
     * @memberof CertManagement
     * @method recoverIdentity
     * @description recoverIdentity function
     * @param 
     */
    var recoverIdentity = function () {
        var cmd = "CertManagement.recoverIdentity";
        var certID = certListInfo.getCertID();
        var Data = {
            'certID': certID
        };

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
     * @public
     * @memberof CertManagement
     * @method recoverIdentityCallback
     * @description recoverIdentity callback
     * @param 
     */
    var recoverIdentityCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            //success window : recover identity
            $('.nx-issue-success-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_28 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);

            $('.nx-issue-success-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_28 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_14 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_15;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
    * @public
    * @memberof CertManagement
    * @method exportP12
    * @description exportP12 function
    * @param 
    */
    var exportP12 = function (certType, filePath, fileName, certID) {
        var cmd = "CertManagement.exportP12";
        var Data = {
            'certType': certType,
            'filePath': filePath,
            'fileName': fileName,
            'certID': certID
        };

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
    * @public
    * @memberof CertManagement
    * @method exportP12Callback
    * @description exportP12 function callback
    * @param 
    */
    var exportP12Callback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {

            //success window : export p12
            $('.nx-issue-success-alert-head-msg').remove();
            $('#nx-issue-success-alert-msg').remove();


            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_16 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);


            var alertMessage = '<div class=\"nx-issue-success-alert-msg\" id=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_16 + '</span></p><span class=\"inline-tit2\">' + NX_WEBUI_EX_PFX_FILE_TEXT_8 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);

        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_17 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_18;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    /**
    * @public
    * @memberof CertManagement
    * @method importP12
    * @description importP12 function
    * @param 
    */
    var importP12 = function (filePath, fileName, password, certID) {
        var cmd = "CertManagement.importP12";
        var Data = {
            'filePath': filePath,
            'fileName': fileName,
            'password': password,
            'certID': certID
        };

        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    /**
    * @public
    * @memberof CertManagement
    * @method importP12Callback
    * @description importP12 function callback
    * @param 
    */
    var importP12Callback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            //success window : import p12
            $('.nx-issue-success-alert-head-msg').remove();
            $('.nx-issue-success-alert-msg').remove();


            var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_19 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-success-alert-head').append(headMessage);


            var alertMessage = '<div class=\"nx-issue-success-alert-msg\" id=\"nx-issue-success-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_CERT_MANAGEMENT_TEXT_19 + '</span></p></div>';
            alertMessage += '</div>';
            $('#nx-issue-succes-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-success-alert').show();
            }, 200);
        }
        else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_CERT_MANAGEMENT_TEXT_20 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_CERT_MANAGEMENT_TEXT_21;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    return {

        init: init,
        setPwd: setPwd,
        setNEW_NXPWD: setNEW_NXPWD,
        setNEW_NXPWD_CONFIRM: setNEW_NXPWD_CONFIRM,
        setNXSSN: setNXSSN,

        copyCert: copyCert,
        copyCertCallback: copyCertCallback,

        deleteCertIssue: deleteCertIssue,
        deleteCertIssueCallback: deleteCertIssueCallback,

        checkPassword: checkPassword,
        checkPasswordCallback: checkPasswordCallback,

        changePassword: changePassword,
        changePasswordCallback: changePasswordCallback,

        verifyIdentity: verifyIdentity,
        verifyIdentityCallback: verifyIdentityCallback,

        showCert: showCert,
        showCertCallback: showCertCallback,

        recoverIdentity: recoverIdentity,
        recoverIdentityCallback: recoverIdentityCallback,

        exportP12: exportP12,
        exportP12Callback: exportP12Callback,

        importP12: importP12,
        importP12Callback: importP12Callback
    };
})();

function NXGetPFXFilePath() {
    var cmd = 'NXGetPFXFilePath.getFilePath';
    var Data = {
        'fileType': "pfx"
    };

    var param = JSON.stringify(Data);
    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

function NXGetPFXFilePathCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        console.log(err);
    }

    if (errorCheck === undefined) {
        var res = reply.getFilePath;
        document.getElementById('importPFXFileName').value = res;
    }
    else {
        KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}