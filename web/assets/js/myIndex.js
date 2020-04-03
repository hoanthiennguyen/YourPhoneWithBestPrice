/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function loadSuggestions(search, callback) {
    let url = "SearchSuggestionController?search=" + search;
    getAJAX(url, xhttp => {
        console.log(xhttp.responseText);
        let suggestions = xhttp.responseText.split(",")
                .map(str => search ? search + " " + str : str);
        callback(document.getElementById("search"), suggestions);
    });
}
function onGetSuggestion(e) {
    let search = e.target.value;
    //space is pressed
    if (e.keyCode === 32) {
        loadSuggestions(search.trim(), autocomplete);
        //backspace is press
    } else if (e.keyCode === 8 && (search.endsWith(" ") || search === "")) {
        loadSuggestions(search.trim(), autocomplete);
    }
}
function onSearch() {
    let input = document.getElementById("search").value;
    getAJAX("SearchController?search=" + input, xhttp => {
        let xml = xhttp.responseXML;
        console.log(xml);
        getAJAX("./assets/xsl/clientPhones.xsl", xhttpXSL => {
            if (document.implementation && document.implementation.createDocument) {
                let xsl = xhttpXSL.responseXML;
                let xsltProcessor = new XSLTProcessor();
                xsltProcessor.importStylesheet(xsl);
                let resultDocument = xsltProcessor.transformToFragment(xml, document);
                let maincontent = document.getElementById("maincontent");
                if (maincontent.firstChild)
                    maincontent.removeChild(maincontent.firstElementChild);
                maincontent.appendChild(resultDocument);
            }
        });
    });
    return false;
}
window.onload = e => {

    loadSuggestions("", autocomplete);
    let inputSearch = document.getElementById("search");
    inputSearch.onkeyup = onGetSuggestion;

};


