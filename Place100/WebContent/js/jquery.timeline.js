/*!
 * jQuery UI Widget-factory plugin boilerplate (for 1.8/9+)
 * Author: @addyosmani
 * Further changes: @peolanha
 * Licensed under the MIT license
 */
;(function ( $, window, document, undefined ) {
	// define your widget under a namespace of your choice
	//  with additional parameters e.g.
	// $.widget( "namespace.widgetname", (optional) - an
	// existing widget prototype to inherit from, an object
	// literal to become the widget's prototype );
	
	$.widget( "grantorino.timeline" , {
		//Options to be used as defaults
		options: {
			someValue: null
		},
		_tpl_event: ['<li class="tl-item">',
						'<div class="tl-wrap">',
							'<span class="tl-date" id="{{span_id}}">{{time}}</span>',
							'<div class="tl-content panel padder b-a">',
								'<span class="arrow left pull-up" ></span>',
								'<div>',
									'<input type="text" id="{{select_name}}" placeholder="방문일정을 선택">',
								'</div>',
							'</div>',
						'</div>',
					'</li>'
					/*'<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">',
						'<div class="modal-dialog">',
							'<div class="modal-content">',
								'<div class="modal-header">',
									'<button type="button" class="close" data-dismiss="modal" aria-label="Close">',
										'<span aria-hidden="true">&times;</span>',
									'</button>',
									'<h4 class="modal-title" id="myModalLabel">Modal title</h4>',
								'</div>',
							'<div class="modal-body">...</div>',
						'<div class="modal-footer">',
						'<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>',
						'<button type="button" class="btn btn-primary">Save changes</button>',
						'</div>',
					'</div>',
				'</div>',
			'</div>'	*/
				   ].join('\n'),

		//Setup widget (eg. element creation, apply theming
		// , bind events etc.)
		_create: function () {

			// _create will automatically run the first time
			// this widget is called. Put the initial widget
			// setup code here, then you can access the element
			// on which the widget was called via this.element.
			// The options defined above can be accessed
			// via this.options this.element.addStuff();
			// 
			// 
			this._buildContainer();
			this._buildTimeline();
		},

		// Destroy an instantiated plugin and clean up
		// modifications the widget has made to the DOM
		destroy: function () {

			// this.element.removeStuff();
			// For UI 1.8, destroy must be invoked from the
			// base widget
			$.Widget.prototype.destroy.call(this);
			// For UI 1.9, define _destroy instead and don't
			// worry about
			// calling the base widget
		},

		add: function ( event_data ) {
			//_trigger dispatches callbacks the plugin user
			// can subscribe to
			// signature: _trigger( "callbackName" , [eventObject],
			// [uiObject] )
			// eg. this._trigger( "hover", e /*where e.type ==
			// "mouseenter"*/, { hovered: $(e.target)});
			// 
			
			if ($.isArray( event_data )){
				var that = this;
				$.each(event_data, function( index, tl_event ) {
					that.add(tl_event);
				});
			} else {

				this.element.find("ul.timeline").append( 
							this._render_event(event_data) 
						); 	
			}

		},

		methodA: function ( event ) {
			this._trigger("dataChanged", event, {
				key: "someValue"
			});
		},

		_render_event: function(data){
			
			var event_html = this._tpl_event.replace('{{time}}', this._format_time(data.time) );	
			event_html = event_html.replace('{{select_name}}', data.select_name);
			event_html = event_html.replace('{{span_id}}', data.span_id);
			//event_html.replace('{{span_id}}', data.span_id);

			return event_html;

		},

		_format_time: function(time){

			var hours = time.substring(0,2);
			var minutes = time.substring(3,4);
			var ampm = hours >= 12 ? 'pm' : 'am';
			hours = hours % 12;
			hours = hours ? hours : 12; // the hour '0' should be '12'
			minutes = minutes < 10 ? '0'+minutes : minutes;

			return ( hours + ':' + minutes + ' ' + ampm);
		},

		_buildTimeline: function () {
				   

			var that = this;
			$.each(this.options.data, function( index, tl_event ) {
			  that.element.find("ul.timeline").append(that._render_event(tl_event)); 
			});
		
		},

		_buildContainer: function(){
			this.element.append('<ul class="timeline"></ul>');
		},

		// Respond to any changes the user makes to the
		// option method
		_setOption: function ( key, value ) {
			switch (key) {
			case "someValue":
				//this.options.someValue = doSomethingWith( value );
				break;
			default:
				//this.options[ key ] = value;
				break;
			}

			// For UI 1.8, _setOption must be manually invoked
			// from the base widget
			$.Widget.prototype._setOption.apply( this, arguments );
			// For UI 1.9 the _super method can be used instead
			// this._super( "_setOption", key, value );
		}
	});
	
})( jQuery, window, document );



