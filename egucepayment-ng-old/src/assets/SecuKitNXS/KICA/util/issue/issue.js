/**
 * @public
 * @class
 * @description for issue certificate class
 */
var issueCertificate = (function () {
    var refCode = '',
        authCode = '',
        password = '',
        confirmPassword = '';


    var issueCertInit = function () {
        refCode = '';
        authCode = '';
        password = '';
        confirmPassword = '';
    };

    var setRefCode = function (ref) {
        refCode = ref;
    };

    var setAuthCode = function (auth) {
        authCode = auth;
    };

    var setPw = function (pw) {
        password = pw;
    };

    var setConfirmPw = function (copw) {
        confirmPassword = copw;
    };

    //issue cert
    var issueCert = function () {
        var cmd = 'issueCertificate.issueCert';
        var mediaType = TargetMediaInfo.getMediaType();
        var extraValue = TargetMediaInfo.getExtraValue();

        var Data = {
            'refCode': refCode,
            'authCode': authCode,
            'password': password,
            'confirmPassword': confirmPassword,
            'mediaType': mediaType,
            'extraValue': extraValue
        };
        issueCertInit();
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    //issue callback
    var issueCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.issueCert === 'true') {

                // save result
                NX_ISSUE_Result.init();
                NX_ISSUE_Result.setResult(reply.issueCert);
                NX_ISSUE_Result.setIssueUserName(reply.username);
                NX_ISSUE_Result.setIssueCertDN(reply.userDN);
                NX_ISSUE_Result.setIssueCertVaildateFrom(reply.validateFrom);
                NX_ISSUE_Result.setIssueCertVaildateTo(reply.validateTo);

                // remove -ing alert
                $('#nx-issue-ing-alert').hide();

                // success alert
                $('.nx-issue-success-alert-head-msg').remove();
                $('#nx-issue-success-alert-msg').remove();


                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_1 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);


                var alertMessage = '<div class=\"nx-issue-success-alert-msg\" id=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_1 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);

                //result
                SecuKitNX_Result('issueCert');
            }

        } else {
            // save result
            NX_ISSUE_Result.init();
            NX_ISSUE_Result.setResult('false');

            // remove -ing alert
            $('#nx-issue-ing-alert').hide();

            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_2 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_3;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);

            //call result function
            SecuKitNX_Result('issueCert');
        }
    };

    //reissue cert
    var reIssueCert = function () {
        var cmd = 'issueCertificate.reIssueCert';
        var mediaType = TargetMediaInfo.getMediaType();
        var extraValue = TargetMediaInfo.getExtraValue();
        var Data = {
            'refCode': refCode,
            'authCode': authCode,
            'password': password,
            'confirmPassword': confirmPassword,
            'mediaType': mediaType,
            'extraValue': extraValue
        };
        issueCertInit();
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    var reIssueCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.reIssueCert === 'true') {

                // save result
                NX_ISSUE_Result.init();
                NX_ISSUE_Result.setResult(reply.reIssueCert);
                NX_ISSUE_Result.setIssueUserName(reply.username);
                NX_ISSUE_Result.setIssueCertDN(reply.userDN);
                NX_ISSUE_Result.setIssueCertVaildateFrom(reply.validateFrom);
                NX_ISSUE_Result.setIssueCertVaildateTo(reply.validateTo);

                // remove -ing alert
                $('#nx-issue-ing-alert').hide();

                // success alert
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_4 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_4 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);

                //call result function
                SecuKitNX_Result('reIssueCert');
            }
        } else {
            // save result
            NX_ISSUE_Result.init();
            NX_ISSUE_Result.setResult('false');

            // remove -ing alert
            $('#nx-issue-ing-alert').hide();

            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_5 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_6;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);

            //call result function
            SecuKitNX_Result('reIssueCert');
        }
    };

    //updateCert
    //using parameter only CertID
    var updateCert = function () {
        var cmd = 'issueCertificate.updateCert';
        var certID = certListInfo.getCertID();
        var Data = {
            'password': '',
            'confirmPassword': '',
            'mediaType': '',
            'extraValue': '',
            'certID': certID
        };
        issueCertInit();
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    var updateCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.updateCert === 'true') {

                // save result
                NX_ISSUE_Result.init();
                NX_ISSUE_Result.setResult(reply.updateCert);
                NX_ISSUE_Result.setIssueUserName(reply.username);
                NX_ISSUE_Result.setIssueCertDN(reply.userDN);
                NX_ISSUE_Result.setIssueCertVaildateFrom(reply.validateFrom);
                NX_ISSUE_Result.setIssueCertVaildateTo(reply.validateTo);

                // remove -ing alert
                $('#nx-issue-ing-alert').hide();

                // success alert
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_7 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_7 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);

                //call result function
                SecuKitNX_Result('updateCert');
            }
        } else {
            // save result
            NX_ISSUE_Result.init();
            NX_ISSUE_Result.setResult('false');

            // remove -ing alert
            $('#nx-issue-ing-alert').hide();

            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_8 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_9;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);

            //call result function
            SecuKitNX_Result('updateCert');
        }
    };

    //issue kmcert
    var issueKmCert = function () {
        var cmd = 'issueCertificate.issueKmCert';
        var certID = certListInfo.getCertID();
        var Data = {
            'certID': certID
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    var issueKmCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.issueKmCert === 'true') {

                // success alert
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_10 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_10 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);
            }
        } else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_11 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_12;
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    //hold cert
    var holdCert = function () {
        var cmd = 'issueCertificate.holdCert';
        var certID = certListInfo.getCertID();
        var Data = {
            'certID': certID
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    var holdCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.holdCert === 'true') {
                // success alert
                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_13 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_13 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);
            }
        } else {
            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_14 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_15 + '';
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);
        }
    };

    //revoke
    var revokeCert = function () {
        var cmd = 'issueCertificate.revokeCert';
        var certID = certListInfo.getCertID();
        var Data = {
            'certID': certID
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX(cmd, param);
    };

    var revokeCertCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        //issueCertificate data init
        issueCertificate.issueCertInit();

        if (errorCheck === undefined) {
            if (reply.revokeCert === 'true') {

                // save result
                NX_ISSUE_Result.init();
                NX_ISSUE_Result.setResult('true');

                $('.nx-issue-success-alert-head-msg').remove();
                var headMessage = '<div class=\"nx-issue-success-alert-head-msg\">';
                headMessage += '<h1>' + NX_ISSUE_TEXT_16 + '</h1>';
                headMessage += '</div>';
                $('#nx-issue-success-alert-head').append(headMessage);

                $('.nx-issue-success-alert-msg').remove();
                var alertMessage = '<div class=\"nx-issue-success-alert-msg\">';
                alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_success.png" alt=\"\" /></div>';
                alertMessage += '<div class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_TEXT_16 + '</span></p></div>';
                alertMessage += '</div>';
                $('#nx-issue-succes-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-pki-ui-wrapper').show();
                    $('#nx-issue-success-alert').show();
                }, 200);

                //call result function
                SecuKitNX_Result('revokeCert');
            }
        } else {

            // save result
            NX_ISSUE_Result.init();
            NX_ISSUE_Result.setResult('false');

            // fail alert
            $('.nx-issue-fail-alert-head-msg').remove();
            var headMessage = '<div class=\"nx-issue-fail-alert-head-msg\">';
            headMessage += '<h1>' + NX_ISSUE_TEXT_17 + '</h1>';
            headMessage += '</div>';
            $('#nx-issue-fail-alert-head').append(headMessage);

            $('.nx-issue-fail-alert-msg').remove();
            var alertMessage = '<div class=\"nx-issue-fail-alert-msg\">';
            alertMessage += '<div class=\"bg-img-area\"><img src=\"' + NX_DEFAULT_IMG_URL + 'img_issue_fail.png" alt=\"\" /></div>';
            alertMessage += '<div class=\"gray-box2\"><p class=\"txt-l\"><span class=\"inline-tit2\">' + NX_ISSUE_TEXT_18 + '';
            alertMessage += '<br />';
            alertMessage += '<br />';
            alertMessage += '[ ErrorCode ] : ' + reply.ERROR_CODE;
            alertMessage += '<br />';
            alertMessage += '[ ErrorMessage ] : ' + reply.ERROR_MESSAGE;
            alertMessage += '</span></p></div>';
            alertMessage += '</div>';

            $('#nx-issue-fail-alert-box').append(alertMessage);

            setTimeout(function () {
                $('#nx-pki-ui-wrapper').show();
                $('#nx-issue-fail-alert').show();
            }, 200);

            //call result function
            SecuKitNX_Result('revokeCert');
        }
    };

    return {
        issueCertInit: issueCertInit,
        setRefCode: setRefCode,
        setAuthCode: setAuthCode,
        setPw: setPw,
        setConfirmPw: setConfirmPw,

        issueCert: issueCert,
        issueCertCallback: issueCertCallback,

        reIssueCert: reIssueCert,
        reIssueCertCallback: reIssueCertCallback,

        updateCert: updateCert,
        updateCertCallback: updateCertCallback,

        issueKmCert: issueKmCert,
        issueKmCertCallback: issueKmCertCallback,

        holdCert: holdCert,
        holdCertCallback: holdCertCallback,

        revokeCert: revokeCert,
        revokeCertCallback: revokeCertCallback
    };
})();