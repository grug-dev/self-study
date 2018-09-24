let mapCookies:Map<string, string> = new Map<string,string>();

function addCookie ( key: string , value: string) {
    mapCookies.set(key, value);

    let expireDate = new Date();
    expireDate.setMonth ( expireDate.getMonth() + 1 ) ;
    let expire = `expires=${expireDate.toUTCString()};`;
    mapCookies.forEach (  (k , v) => {
        let value = escape(v);

        let cookieName:string = ` ${k} = ${value};${expire}`;
        console.log(cookieName);
        document.cookie = cookieName;
    });
}

function deleteCookie ( key:string) {
    let expireDate = new Date();
    expireDate.setMonth ( expireDate.getMonth() - 1 ) ;
    let expire = `expires=${expireDate.toUTCString()};`;
    let cookieName = `${key}=X;${expire}`;
    document.cookie = cookieName;
}

let printCookies = () => {
    console.log(document.cookie);
}

addCookie("nombre" , "Cristian");
printCookies();
deleteCookie("nombre");
deleteCookie("Cristian");
deleteCookie("Apellido");
printCookies();

console.log("Cookie Done!");
