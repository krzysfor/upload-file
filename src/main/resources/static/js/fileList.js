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

