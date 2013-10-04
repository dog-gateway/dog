$(document).ready(function() {
	loadAjaxContent();

});

// ajax loading of content items having a data-load=ajax attribute,
// if a data refresh time is specified, reload the data every data-refresh
// milliseconds
function loadAjaxContent() {
	$('[data-load=ajax]').each(function(index) {
		var refreshTime = this.getAttribute('data-refresh');
		var element = this;
		var src = this.getAttribute('data-src');
		if (refreshTime != null)
			setInterval(function() {
				loadContent(element, src);
				attachCommandButtons();
			}, parseInt(refreshTime));
		else {
			loadContent(this, this.getAttribute('data-src'));
			attachCommandButtons();
		}
	});
}

// the actualt content loading function
function loadContent(element, uri) {
	jQuery(element).load(uri);
}

function attachCommandButtons() {
	$('[command=true]').each(function(index) {
		var url = this.getAttribute('command-dst');
		this.onclick = function() {
			postCommand(url)
		};
	});
}

function postCommand(commandUrl) {
	$.ajax({
		url : commandUrl,
		type : "POST",
		data: null,
		contentType : "application/json"
	});
}
