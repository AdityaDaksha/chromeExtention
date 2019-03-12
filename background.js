'use strict';

// This event is fired each time the user updates the text in the omnibox,
// as long as the extension's keyword mode is still active.
chrome.omnibox.onInputChanged.addListener(
  function(text, suggest) {
    console.log('inputChanged: ' + text);
    suggest([
      {content: text + " one", description: "the first one"},
      {content: text + " number two", description: "the second entry"}
    ]);
  });

// This event is fired with the user accepts the input in the omnibox.
chrome.omnibox.onInputEntered.addListener(
  function(text) {
    console.log('inputEntered: ' + text);
    //alert('You just typed "' + text + '"');
    getShortcuts(text);
    
  });

function navigate(url) {
  chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
    chrome.tabs.update(tabs[0].id, {url: url});
  });
}

/*var map = {};

map["tio"] = "https://timesofindia.indiatimes.com/";
map["times of india"] = "https://timesofindia.indiatimes.com/";
map["hindu news"] = "https://www.thehindu.com/";
map["the hindu"] = "https://www.thehindu.com/";
map["passport"] = "https://portal2.passportindia.gov.in/";



function get(k) {

    //server call to get the related matching url

    return map[k];
}*/

function getShortcuts(text) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             
             var url = "https://en.wikipedia.org/wiki/";
              if (this.responseText != "") {
                url = this.responseText;
              }  else {
                text = text.replace(" ", "_");
                url = url + text;
              }
              navigate(url);
         }
    };
    xhttp.open("GET", "http://localhost:8080/data/"+text, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(null);
}