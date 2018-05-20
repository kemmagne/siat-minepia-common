function validPassword(pwd1, pwd2) {
    var alpaBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var alpaSmall = "abcdefghijklmnopqrstuvwxyz";
    var num = "01234567890";
    var minLength = 10;
    var pwdCheck_0 = 0;  // conunt same char
    var pwdCheck_1 = 0;  // + contineous count
    var pwdCheck_2 = 0;  // - contineous count
    var tmpPwd1;
    var tmpPwd2;
    var tmpPwd3;
    var errorMsg = "";

    if (pwd1 === pwd2) {
        if (pwd1 == null || pwd1 == " ") {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'validPassword';
            var reason = 'validPassword';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_INPUT');
            errorMsg = KICA_Error.getScriptError();
            return errorMsg;
        }

        if (pwd1.length < minLength) {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'validPassword';
            var reason = 'validPassword';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_RANGE');
            errorMsg = KICA_Error.getScriptError();
            return errorMsg;
        }

        if (!pwd1.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)) {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'validPassword';
            var reason = 'validPassword';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_SPECIAL');
            errorMsg = KICA_Error.getScriptError();
            return errorMsg;
        }


        for (var i = 0 ; i < pwd1.length ; i++) {
            tmpPwd1 = pwd1.charAt(i);
            tmpPwd2 = pwd1.charAt(i + 1);
            tmpPwd3 = pwd1.charAt(i + 2);

            if (tmpPwd1 === tmpPwd2 && tmpPwd1 === tmpPwd3) {
                pwdCheck_0 = pwdCheck_0 + 1;
            }

            // + contineous count
            if (tmpPwd1.charCodeAt(0) - tmpPwd2.charCodeAt(0) == 1) {
                if (tmpPwd2.charCodeAt(0) - tmpPwd3.charCodeAt(0) == 1) {
                    pwdCheck_1 = pwdCheck_1 + 1;
                }
            }

            // - contineous count
            if (tmpPwd1.charCodeAt(0) - tmpPwd2.charCodeAt(0) == -1) {
                if (tmpPwd2.charCodeAt(0) - tmpPwd3.charCodeAt(0) == -1) {
                    pwdCheck_2 = pwdCheck_2 + 1;
                }
            }
        }

        if (pwdCheck_0 > 0) {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'validPassword';
            var reason = 'validPassword';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_SPECIAL_SAME');
            errorMsg = KICA_Error.getScriptError();
            return errorMsg;
        }

        if (pwdCheck_1 > 0 || pwdCheck_2 > 0) {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'validPassword';
            var reason = 'validPassword';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_SPECIAL_STRAIGHT');
            errorMsg = KICA_Error.getScriptError();
            return errorMsg;
        }
    } else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        var location = 'validPassword';
        var reason = 'validPassword';
        KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_VALID_PW_INVALID');
        errorMsg = KICA_Error.getScriptError();
        return errorMsg;
    }
    return 'result_OK';
}
