/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



window.onload = (e) => {
    let firstwebsite = document.getElementById("website1").value;
    displayOnlySubpageFromWebsite(firstwebsite);
};
function onChangeWebsite(e) {
    let website = e.value;
    displayOnlySubpageFromWebsite(website);
}
function onCrawl(form) {
    if (form.subpage.value === "") {
        alert("You must select subpage");
        return false;
    } else
        return true;
}