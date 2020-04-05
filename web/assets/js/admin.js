/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
}
function onCrawl(form) {
    if (form.subpage.value === "*undefined*") {
        alert("You must select subpage");
        return false;
    } else
        return true;
}
window.onload = (e) => {
    let firstwebsite = document.getElementById("website1").value;
    displayOnlySubpageFromWebsite(firstwebsite);
};