$(document).ready(function() {
	var body = $("body");
	loadAjaxContent(body);

});

// ajax loading of content items having a data-load=ajax attribute,
// if a data refresh time is specified, reload the data every data-refresh
// milliseconds
function loadAjaxContent(rootElement) {
	rootElement.find($("[data-load=ajax]")).each(function(index) {
		var refreshTime = this.getAttribute('data-refresh');
		var element = this;
		var src = this.getAttribute('data-src');
		var loaded = this.getAttribute('data-loaded');
		if (refreshTime != null)
			setInterval(function() {
				loadContent(element, src);
				// attachCommandButtons();
			}, parseInt(refreshTime));
		else 
		{
			jQuery(element).load(src, function() {
				attachCommandButtons(jQuery(element));
				loadAjaxContent(jQuery(element));
			});

		}
	});
}

// the actualt content loading function
function loadContent(element, uri) {
	$("div.active").find(jQuery(element)).each(function(index) {
		jQuery(element).load(uri);
	});
}

function attachCommandButtons(rootElement) {
	rootElement.find($('[command=true]')).each(function(index) {
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
		data : null,
		contentType : "application/json"
	});
}
