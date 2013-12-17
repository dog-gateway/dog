/*
 * Dog - Admin UI
 * 
 * Copyright (c) 2013- Dario Bonino
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
var lastContent = [];

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
		else {
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
		
		var $element = jQuery(element);

		// load the element content
		$element.load(uri);

		//get the element id if available
		var id = $element.attr('id');
		
		// store the loaded content
		if (id != null)
			lastContent[id] = $element.html();

		// check if the element is trigger for other elements
		if ($element.attr('data-toggle') == 'update') {
			// if the element content is different from the last time, trigger
			// an update
			if (id != null) {
				if ((lastContent[id]==null)||($element.html() != lastContent[id])) {
					// trigger an update of the target element
					var $target = jQuery($element.attr('href'));

					// if target is not null update the target
					if ($target != null) {
						loadContent($target, $target.attr('data-src'));
					}
				}
			}
		}
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
