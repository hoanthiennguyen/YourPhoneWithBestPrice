/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function autocomplete(inp, arr) {
    /*the autocomplete function takes two arguments,
     the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    function appendSuggestions(e, arr) {
        var a, b, i, val = inp.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) {
            return false;
        }
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", inp.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        /*append the DIV element as a child of the autocomplete container:*/
        inp.parentNode.appendChild(a);
        /*for each item in the array...*/
        for (i = 0; i < arr.length; i++) {
            /*check if the item starts with the same letters as the text field value:*/
            if (arr[i].substr(0, val.length).toUpperCase() === val.toUpperCase()) {
                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                b.innerHTML += arr[i].substr(val.length);
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function (e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    /*close the list of autocompleted values,
                     (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    }
    function adjustFocus(e) {
        var x = document.getElementById(inp.id + "autocomplete-list");
        if (x)
            x = x.getElementsByTagName("div");
        if (e.keyCode === 40) {
            /*If the arrow DOWN key is pressed,
             increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode === 38) { //up
            /*If the arrow UP key is pressed,
             decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode === 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x)
                    x[currentFocus].click();
            }
        }
    }
    function reloadSuggestions(e) {
        let search = e.target.value;
        //space is pressed
        if (e.keyCode === 32) {
            loadSuggestions(search.trim(), suggestions => {
                arr = suggestions;
                appendSuggestions(null, arr);
            });
            //backspace is press
        } else if (e.keyCode === 8 && (search.endsWith(" ") || search === "")) {
            loadSuggestions(search.trim(), suggestions => {
                arr = suggestions;
                appendSuggestions(null, arr);
            });
        }
    }
    inp.addEventListener("input", e => appendSuggestions(e, arr));
    inp.addEventListener("keydown", adjustFocus);
    inp.addEventListener("keyup", reloadSuggestions);
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x)
            return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length)
            currentFocus = 0;
        if (currentFocus < 0)
            currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
         except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt !== x[i] && elmnt !== inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}
function loadSuggestions(search, callback) {
    let url = "SearchSuggestionController?search=" + search;
    getAJAX(url, xhttp => {
        console.log(xhttp.responseText);
        let suggestions = xhttp.responseText.split(",")
                .map(str => search ? search + " " + str : str);
        callback(suggestions);
    });
}
function onSearch() {
    let input = document.getElementById("search").value;
    getAJAX("SearchController?search=" + input, xhttp => {
        let xml = xhttp.responseXML;
        let maincontent = document.getElementById("maincontent");
        if (maincontent.firstChild)
            maincontent.removeChild(maincontent.firstElementChild);
        if (xml === null) {
            maincontent.innerHTML ="<h3>No result found</h3>";
            return false;
        }
        getAJAX("./assets/xsl/clientPhones.xsl", xhttpXSL => {
            if (document.implementation && document.implementation.createDocument) {
                let xsl = xhttpXSL.responseXML;
                let xsltProcessor = new XSLTProcessor();
                xsltProcessor.importStylesheet(xsl);
                let resultDocument = xsltProcessor.transformToFragment(xml, document);
                maincontent.appendChild(resultDocument);
            }
        });
    });
    return false;
}
window.onload = e => {
    loadSuggestions("", suggestions => {
        autocomplete(document.getElementById("search"), suggestions);
    });
};