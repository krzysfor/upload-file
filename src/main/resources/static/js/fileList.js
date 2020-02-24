'use strict';

function getList(docType, idDoc) {
	var request = new XMLHttpRequest();
	//alert(docType);
	//alert(idDoc);
	//request.channel.loadFlags |= Components.interfaces.nsIRequest.LOAD_BYPASS_CACHE;
	
	request.open("GET", "http://localhost:8081/api/v1/files?type=" + docType + "&" + "linkedDocId=" + idDoc);
	//request.setRequestHeader('Cache-Control', 'no-cache');
	request.setRequestHeader("Cache-Control", "no-cache, no-store");
	request.setRequestHeader('pragma', 'no-cache');
	request.send();
	
	request.onload = function () {
		var response = JSON.parse(request.responseText);
		
		var col = [];
        for (var i = 0; i < response.length; i++) {
            for (var key in response[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }
        
        var table = document.createElement("table");
        
        var tr = table.insertRow(-1);                   // TABLE ROW.

        var th = document.createElement("th");      // TABLE HEADER.
        th.classList.add("lp");
        th.innerHTML = "Lp.";
        tr.appendChild(th);
        
        th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = "Nazwa";
        tr.appendChild(th);
        
        th = document.createElement("th");      // TABLE HEADER.
        th.classList.add("size");
        th.innerHTML = "Rozmiar";
        tr.appendChild(th);
        
        for (var i = 0; i < response.length; i++) {

        	var file = response[i];
        	
            tr = table.insertRow(-1);

            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = i+1;
            
            tabCell = tr.insertCell(-1);
            var link = 'http://localhost:8081/api/v1/downloadFile/' + file.uuid;
            tabCell.href = link;
            tabCell.innerHTML = '<a href="' + link + '">' + file.fileName + '</a>';
            //tabCell.innerHTML = file.fileName; //response[i][col[6]];
            
            tabCell = tr.insertCell(-1);
            var oriSize = Number(file.fileSize);
			var sizeInKB = oriSize / 1024;
			tabCell.innerHTML = sizeInKB.toFixed(2) + " kB";
            
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
        
	}
}


function getList2(docType, idDoc) {
	var request = new XMLHttpRequest();
	//alert(docType);
	//alert(idDoc);
	//request.channel.loadFlags |= Components.interfaces.nsIRequest.LOAD_BYPASS_CACHE;
	
	request.open("GET", "http://localhost:8081/api/v1/files?type=" + docType + "&" + "linkedDocId=" + idDoc);
	//request.setRequestHeader('Cache-Control', 'no-cache');
	request.setRequestHeader("Cache-Control", "no-cache, no-store");
	request.setRequestHeader('pragma', 'no-cache');
	request.send();
	
	request.onload = function () {
		var response = JSON.parse(request.responseText);
		//alert(response.length);
		
		var api = document.getElementById('root');
		
		while (api.hasChildNodes()) {
			api.removeChild(api.firstChild);
		   }
		
		var container= document.createElement('div');
		container.setAttribute('class', 'container');
		
		api.appendChild(container);
		
		for(var i = 0, len = response.length; i < len;  ++i){
			var file = response[i];
			//alert(file.uuid);
			//var fileDetail = JSON.parse(response[i]);
			//alert(fileDetail);
			
			var card = document.createElement('div');
			card.setAttribute('class', 'card');
			
			var pid = document.createElement('p');
			pid.textContent = i+1;
			
			var link = 'http://localhost:8081/api/v1/downloadFile/' + file.uuid;
			var aname = document.createElement('a');
			aname.setAttribute('href', link);
			aname.textContent = file.fileName;
			
			var pname = document.createElement('p');
			var oriSize = Number(file.fileSize);
			var sizeInKB = oriSize / 1024;
			pname.textContent = sizeInKB.toFixed(2) + " kB";
			
			container.appendChild(card);
			card.appendChild(pid);
			card.appendChild(aname);
			card.appendChild(pname);
			
				
		};	
}
}

