/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = e => {
    originalXSL = document.getElementById("xsl").value;
};

function createXmlHttpObj() {
    let xhttp;
    if (window.ActiveXObject) {
        xhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else {
        xhttp = new XMLHttpRequest();
    }
    return xhttp;
}
function postAJAX(url, body, callback) {
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
function onTestTransform() {
    let xsl = document.getElementById("xsl").value;
    let website = document.getElementById("website").value;
    let subpages = document.getElementById("subpages").value;
    let firstSubpage = subpages.split("\n").map(sub => sub.trim()).filter(s => s !== "")[0];
    let params = `xsl=${xsl}&url=${website + firstSubpage}`;
    let xml = document.getElementById("xml");
    postAJAX("TestTransformController", params, xhttp => {
        xml.innerHTML = xhttp.responseText;
    });

}
function onSave() {
    let xsl = document.getElementById("xsl").value;
    let website = document.getElementById("website").value;
    let rawSubpages = document.getElementById("subpages").value.split("\n");
    let subpages = rawSubpages.map(sub => sub.trim()).filter(s => s !== "").join("\n");
    let params = `xsl=${xsl}&website=${website}&subpages=${subpages}`;
    postAJAX("AddNewWebsiteController", params, xhttp => {
        if (xhttp.responseText === "Success") {
            alert("Success");
            location.replace("admin.jsp");
        } else
            alert(xhttp.responseText);
    });
}
function onReset() {
    document.getElementById("xsl").value = originalXSL;
}