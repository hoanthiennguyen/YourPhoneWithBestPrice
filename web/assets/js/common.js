/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function displayOnlySubpageFromWebsite(website) {
    let arr = Array.from(document.getElementsByClassName("subpage"))
    arr.forEach(option => {
        if (option.getAttribute("website") !== website)
            option.style.display = "none";
        else
            option.style.display = "block";
    });
}
function createXmlHttpObj() {
    let xhttp;
    if (window.ActiveXObject) {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else {
        xhttp = new XMLHttpRequest();
    }
    return xhttp;
}
function getAJAX(url, callback)
{
    var xhttp = createXmlHttpObj();
    xhttp.open("GET", url, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(xhttp);
        }
    };
    xhttp.send();
    return xhttp;
}
function postAJAX(url, body, callback){
    var xhttp = createXmlHttpObj();
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(xhttp);
        }
    };
    xhttp.send(body);
    return xhttp;
}