/**
 * @public
 * @class
 * @description define function that have certificate infomation through selection box (like class)
 */
var CertInfo = (function () {
    var certInfo = {
        'version': '',
        'serial': '',
        'signatureAlgorithmID': '',
        'issueDN': '',
        'issuer': '',
        'issuerToString': '',
        'userDN': '',
        'userPublicKeyAlgorithmID': '',
        'validateFrom': '',
        'validateTo': '',
        'detailValidateTo': '',
        'singature': '',
        'username': '',
        'usage': '',
        'policy': '',
        'policyToString': '',
        'policyToName': '',
        'distributionPoints': '',
        'authorityKeyID': '',
        'userKeyID': '',
        'publicKey': '',
        'keySize': '',
        'vidRandom': '',
        'certPEM': '',
        'encryptCertPEM': ''
    };

    var init = function () {
        certInfo = {
            'version': '',
            'serial': '',
            'signatureAlgorithmID': '',
            'issueDN': '',
            'issuer': '',
            'issuerToString': '',
            'userDN': '',
            'userPublicKeyAlgorithmID': '',
            'validateFrom': '',
            'validateTo': '',
            'detailValidateTo': '',
            'singature': '',
            'username': '',
            'usage': '',
            'policy': '',
            'policyToString': '',
            'policyToName': '',
            'distributionPoints': '',
            'authorityKeyID': '',
            'userKeyID': '',
            'publicKey': '',
            'keySize': '',
            'vidRandom': '',
            'certPEM': '',
            'encryptCertPEM': ''
        };
    };

    var setCertInfo = function (certInfoObj) {
        certInfo.version = certInfoObj.version;
        certInfo.serial = certInfoObj.serial;
        certInfo.signatureAlgorithmID = certInfoObj.signatureAlgorithmID;
        certInfo.issueDN = certInfoObj.issueDN;
        certInfo.issuer = certInfoObj.issuer;
        certInfo.issuerToString = certInfoObj.issuerToString;
        certInfo.userDN = certInfoObj.userDN;
        certInfo.userPublicKeyAlgorithmID = certInfoObj.userPublicKeyAlgorithmID;
        certInfo.validateFrom = certInfoObj.validateFrom;
        certInfo.validateTo = certInfoObj.validateTo;
        certInfo.detailValidateTo = certInfoObj.detailValidateTo;
        certInfo.singature = certInfoObj.singature;
        certInfo.username = certInfoObj.username;
        certInfo.policy = certInfoObj.policy;
        certInfo.policyToString = certInfoObj.policyToString;
        certInfo.policyToName = certInfoObj.policyToName;
        certInfo.distributionPoints = certInfoObj.distributionPoints;
        certInfo.authorityKeyID = certInfoObj.authorityKeyID;
        certInfo.userKeyID = certInfoObj.userKeyID;
        certInfo.publicKey = certInfoObj.publicKey;
        certInfo.keySize = certInfoObj.keySize;
        certInfo.vidRandom = certInfoObj.vidRandom;
        certInfo.certPEM = certInfoObj.certPEM;
        certInfo.encryptCertPEM = certInfoObj.encryptCertPEM;
    };

    var getVidRandom = function () {
        return certInfo.vidRandom;
    };

    var getVersion = function () {
        return certInfo.version;
    };

    var getSerial = function () {
        return certInfo.serial;
    };

    var getSignatureAlgorithmID = function () {
        return certInfo.signatureAlgorithmID;
    };

    var getIssueDN = function () {
        return certInfo.issueDN;
    };

    var getIssuer = function () {
        return certInfo.issuer;
    };

    var getIssuerToString = function () {
        return certInfo.issuerToString;
    };

    var getUserDN = function () {
        return certInfo.userDN;
    };

    var getUserPublicKeyAlgorithmID = function () {
        return certInfo.userPublicKeyAlgorithmID;
    };

    var getValidateFrom = function () {
        return certInfo.validateFrom;
    };

    var getValidateTo = function () {
        return certInfo.validateTo;
    };

    var getDetailValidateTo = function () {
        return certInfo.detailValidateTo;
    };

    var getSingature = function () {
        return certInfo.singature;
    };

    var getUsername = function () {
        return certInfo.username;
    };

    var getPolicy = function () {
        return certInfo.policy;
    };

    var getPolicyToString = function () {
        return certInfo.policyToString;
    };

    var getPolicyToName = function () {
        return certInfo.policyToName;
    };

    var getDistributionPoints = function () {
        return certInfo.distributionPoints;
    };

    var getAuthorityKeyID = function () {
        return certInfo.authorityKeyID;
    };

    var getUserKeyID = function () {
        return certInfo.userKeyID;
    };

    var getPublicKey = function () {
        return certInfo.publicKey;
    };

    var getKeySize = function () {
        return certInfo.keySize;
    };

    var getCertPEM = function () {
        return certInfo.certPEM;
    };

    var getEncryptCertPEM = function () {
        return certInfo.encryptCertPEM;
    };

    return {
        init: init,
        setCertInfo: setCertInfo,
        getVidRandom: getVidRandom,
        getVersion: getVersion,
        getSerial: getSerial,
        getSignatureAlgorithmID: getSignatureAlgorithmID,
        getIssueDN: getIssueDN,
        getIssuer: getIssuer,
        getIssuerToString: getIssuerToString,
        getUserDN: getUserDN,
        getUserPublicKeyAlgorithmID: getUserPublicKeyAlgorithmID,
        getValidateFrom: getValidateFrom,
        getValidateTo: getValidateTo,
        getDetailValidateTo: getDetailValidateTo,
        getSingature: getSingature,
        getUsername: getUsername,
        getPolicy: getPolicy,
        getPolicyToString: getPolicyToString,
        getPolicyToName: getPolicyToName,
        getDistributionPoints: getDistributionPoints,
        getAuthorityKeyID: getAuthorityKeyID,
        getUserKeyID: getUserKeyID,
        getPublicKey: getPublicKey,
        getKeySize: getKeySize,
        getCertPEM: getCertPEM,
        getEncryptCertPEM: getEncryptCertPEM
    };
})();