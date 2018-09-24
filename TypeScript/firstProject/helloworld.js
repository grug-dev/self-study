var mapCookies = new Map();
function addCookie(key, value) {
    mapCookies.set(key, value);
    var expireDate = new Date();
    expireDate.setMonth(expireDate.getMonth() + 1);
    var expire = "expires=" + expireDate.toUTCString() + ";";
    mapCookies.forEach(function (k, v) {
        var value = escape(v);
        var cookieName = " " + k + " = " + value + ";" + expire;
        console.log(cookieName);
        document.cookie = cookieName;
    });
}
function deleteCookie(key) {
    var expireDate = new Date();
    expireDate.setMonth(expireDate.getMonth() - 1);
    var expire = "expires=" + expireDate.toUTCString() + ";";
    var cookieName = key + "=X;" + expire;
    document.cookie = cookieName;
}
var printCookies = function () {
    console.log(document.cookie);
};
addCookie("nombre", "Cristian");
printCookies();
deleteCookie("nombre");
deleteCookie("Cristian");
deleteCookie("Apellido");
printCookies();
console.log("Cookie Done!");
