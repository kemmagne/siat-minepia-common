var KICA_Error = (function () {
    var SCRIPT_ERROR_LOCATION = '',
        SCRIPT_ERROR_REASON = '',
        SCRIPT_ERROR_MESSAGE = '',
        ERROR_CODE = '',
        ERROR_MESSAGE = '',
        ALERT_ERROR = '';

    var init = function () {
        SCRIPT_ERROR_LOCATION = '';
        SCRIPT_ERROR_REASON = '';
        SCRIPT_ERROR_MESSAGE = '';
        ERROR_CODE = '';
        ERROR_MESSAGE = '';
        ALERT_ERROR = '';
    };

    var setError = function (code, msg) {
        ERROR_CODE = code;
        ERROR_MESSAGE = msg;
    };

    var getError = function () {
        ALERT_ERROR = '[' + 'ERROR CODE :' + ERROR_CODE + ']' + '\n' + ERROR_MESSAGE;
        return ALERT_ERROR;
    };

    var setScriptError = function (location, reason, msg) {
        SCRIPT_ERROR_LOCATION = location;
        SCRIPT_ERROR_REASON = reason;
        SCRIPT_ERROR_MESSAGE = msg;
    };

    var getScriptError = function () {
        //ALERT_ERROR = '[ErrorLocation] : ' + SCRIPT_ERROR_LOCATION + ' ' + '\n' + '[SCRIPT ERROR] : ' + SCRIPT_ERROR_REASON + ' ' + SCRIPT_ERROR_MESSAGE;
        ALERT_ERROR = SCRIPT_ERROR_MESSAGE;
        return ALERT_ERROR;
    };

    return {
        init: init,
        setError: setError,
        getError: getError,
        setScriptError: setScriptError,
        getScriptError: getScriptError
    };
})();