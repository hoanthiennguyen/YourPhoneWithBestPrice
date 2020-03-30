/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function covertWebsiteToXSLFileName(website){
    return website.replace(/\W/g, "") + ".xsl";
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
function getAJAX(url, callback){
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
function displayOnlySubpageFromWebsite(website) {
    let arr = Array.from(document.getElementsByClassName("subpage"))
    arr.forEach(option => {
        if (option.getAttribute("website") !== website)
            option.style.display = "none";
        else
            option.style.display = "block";
    });
}
function loadXSL(website){
    let xslFileName = covertWebsiteToXSLFileName(website);
    console.log(xslFileName);
    getAJAX("./assets/xsl/" + xslFileName, xhttp =>{
        let xslTextarea = document.getElementById("xsl");
        xslTextarea.innerHTML = xhttp.responseText;
    });
}
window.onload = (e) => {
    let firstwebsite = document.getElementById("website1").value;
    displayOnlySubpageFromWebsite(firstwebsite);
    loadXSL(firstwebsite);
};

function onChangeWebsite(e) {
    let website = e.value;
    displayOnlySubpageFromWebsite(website);
    loadXSL(website);
}
function onTestTransform(){
    let xsl = document.getElementById("xsl").value;
    let website = document.getElementById("website").value;
    let subpage = document.getElementById("subpage").value;
    let params = `xsl=${xsl}&url=${website+subpage}`;
    let xml = document.getElementById("xml");
    postAJAX("TestTransformController",params , xhttp =>{
        xml.innerHTML = xhttp.responseText;
    });
    
}