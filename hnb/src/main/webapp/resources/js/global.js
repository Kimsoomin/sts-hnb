var Global = {
	init : function() {
		var header = document.createElement('div');
		header.id = 'header';
		document.body.appendChild(header);
		var outbox = document.createElement('div');
		outbox.id = 'outbox';
		document.body.appendChild(outbox);
		var footer = document.createElement('div');
		footer.id = 'footer';
		document.body.appendChild(footer);
		$('<div id="box"></div>').appendTo($('#outbox').empty());
	}
};
