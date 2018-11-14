var processLogic = (function () {
    var LOGIC_Flag = '';

    var init = function () {
        LOGIC_Flag = '';
    };

    var setProcessLogic = function (flag) {
        LOGIC_Flag = flag;
    };

    var getProcessLogic = function () {
        return LOGIC_Flag;
    };

    return {
        init: init,
        setProcessLogic: setProcessLogic,
        getProcessLogic: getProcessLogic
    };
})();

var targetMediaFlag = (function () {
    var flag = false;

    var init = function () {
        flag = false;
    };

    var setFlag = function (f) {
        flag = f;
    };

    var getFlag = function () {
        return flag;
    };

    return {
        init: init,
        setFlag: setFlag,
        getFlag: getFlag
    };
})();

