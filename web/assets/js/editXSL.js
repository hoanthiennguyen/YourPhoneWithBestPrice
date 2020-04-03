/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function covertWebsiteToXSLFileName(website){
    return website.replace(/\W/g, "") + ".xsl";
}
function loadXSL(website){
    let xslFileName = covertWebsiteToXSLFileName(website);
    console.log(xslFileName);
    getAJAX("./assets/xsl/" + xslFileName, xhttp =>{
        let xslTextarea = document.getElementById("xsl");
        xslTextarea.innerHTML = xhttp.responseText;
    });
}
function displayOnlySubpageFromWebsite(website) {
    let arr = Array.from(document.getElementsByClassName("subpage"));
    arr.forEach(option => {
        if (option.getAttribute("website") !== website)
            option.style.display = "none";
        else
            option.style.display = "block";
    });
}

function onChangeWebsite(e) {
    let website = e.value;
    displayOnlySubpageFromWebsite(website);
    loadXSL(website);
}
function onTestTransform(){
    let xsl = document.getElementById("xsl").value;
    let website = document.getElementById("website").value;
    let subpage = document.getElementById("subpage").value;
    if(subpage === ""){
        alert("Please choose a subpage to test");
        return false;
    }
    let params = `xsl=${xsl}&url=${website+subpage}`;
    let xml = document.getElementById("xml");
    postAJAX("TestTransformController",params , xhttp =>{
        xml.innerHTML = xhttp.responseText;
    });
    
}
function onSave(){
    let xsl = document.getElementById("xsl").value;
    let website = document.getElementById("website").value;
    let params = `xsl=${xsl}&website=${website}`;
    postAJAX("SaveXSLController", params , xhttp =>{
        if(xhttp.responseText === "Success"){
            alert("Success");
        }  
        else
            alert(xhttp.responseText);
    });
}
function onReset(){
    location.reload();
}
window.onload = (e) => {
    let firstwebsite = document.getElementById("website1").value;
    displayOnlySubpageFromWebsite(firstwebsite);
    loadXSL(firstwebsite);
};