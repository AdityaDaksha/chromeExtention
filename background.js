'use strict';
  
var myCommands=[];

var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
     if (this.readyState == 4 && this.status == 200) {
        if (xhttp.responseText != "") {
          myCommands = JSON.parse(xhttp.responseText);
        } 
      }
};
xhttp.open("GET", "http://localhost:8080/loadHelp/", true);
xhttp.setRequestHeader("Content-type", "application/json");
xhttp.send(null);

  
// This event is fired each time the user updates the text in the omnibox,
// as long as the extension's keyword mode is still active.
chrome.omnibox.onInputChanged.addListener(
 function(text, suggest) {
   
   console.log('inputChanged: ' + text);

   console.log('commands: ' + myCommands);
   
   var matches = [];
   // regex used to determine if a string contains the substring `q`
   var substrRegex = new RegExp(text, 'i');

   // iterate through the pool of strings and for any string that
   // contains the substring `q`, add it to the `matches` array
   myCommands.forEach(function(command) {
     if (substrRegex.test(command)) {
     
       matches.push({content:'GoBLK',description:command});
     }
   });
   suggest(matches);
   
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

