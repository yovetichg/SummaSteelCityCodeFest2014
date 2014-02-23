$(function() {

	$('#nav-add-event').click(function(){
		$('#entry-form').show();
		$('#event-list').hide();

		console.log('nav-add-event clicked');
	});

	$('#nav-view-events').click(function(){
		$('#entry-form').hide();
		$('#event-list').show();

		console.log('nav-list-events clicked');
	});

});