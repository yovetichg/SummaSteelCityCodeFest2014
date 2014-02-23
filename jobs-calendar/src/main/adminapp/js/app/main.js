var defaultCategoryId = 14;

$(function() {

	$('#nav-add-event').click(function() {
		showEditEvent(true);
	});

	$('#nav-view-events').click(function() {
		showEventList(defaultCategoryId);
	});


	$('#nav-category .dropdown-menu').click(function(e){
		var categoryId = $(e.target).data( "categoryid" );
		getevents(categoryId);
	});

	getcategories();

	showEventList(defaultCategoryId);
});


function showEditEvent(addEvent) {
	if (addEvent) {
		$('#event-mode').html("Add Event");

			$('#name').val("");
			$('#provider').html("");
			$('#description').html("");
			$('#startdt').val("");
			$('#enddt').val("");
	}
	else
		$('#event-mode').html("Edit Event");

	$('#nav-category').hide();

	$('#entry-form').show();
	$('#event-list').hide();
}

function showEventList(categoryId) {
	getevents(categoryId);

	$('#nav-category').show();

	$('#entry-form').hide();
	$('#event-list').show();
}

function getevents(catID) {
	if (catID == 19) {
		urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/allevents";
	} else {
		urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/events/" + catID;
	}
	var request = $.ajax({
		url: urlconcat,
		type: 'GET',
		dataType: 'jsonp',
		jsonpCallback: 'events',
		jsonp: 'callback',
		success: function(data) {
			console.log("event call good");
			console.log(data.event);

			var table = $('#event-list tbody');

			table.empty();

			$.each(data.event, function(i, item) {

				var html = '<tr>';

				html += '<td><a href="#" class="edit-event"><img class="media-object pull-left" data-eventid=' + item.eventId + ' data-src="holder.js/16x16" src="css/images/edit-icon-16x16.png"></a></td>';
				html += '<td>' + item.name + '</td>';
				html += '<td>' + item.location + '</td>';
				html += '<td>' + utctimecnv(item.startDt) + '</td>';
				html += '<td>' + utctimecnv(item.endDt) + '</td>';
				html += '<td>[Category]</td>';
				html += '</tr>';

				table.append(html);

			});


			$('.edit-event').click(function(e){
				var eventId = $(e.target).data( "eventid" );
				getevent(eventId);
			});
		},
		error: function(request, status, error) {
			console.log("Error status " + status);
		}
	});
}

function getevent(eventId) {

	var request = $.ajax({
		url: urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/eventDetail/" + eventId,
		type: 'GET',
		dataType: 'jsonp',
		jsonpCallback: 'events',
		jsonp: 'callback',
		success: function(data) {
			console.log("event call good");
			console.log(data);


			$('#name').val(data.name);
			$('#provider').html(data.location);
			$('#description').html(data.description);
			$('#startdt').val(utctimecnv(data.startDt));
			$('#enddt').val(utctimecnv(data.endDt));

			showEditEvent(false);

		},
		error: function(request, status, error) {
			console.log("Error status " + status);
		}
	});
}

function getcategories() {
    
    var request = $.ajax({
            url:"http://10.93.126.85:8080/jobs-calendar/api/categories",
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'categories',
            jsonp: 'callback',
            success: function (data) {
            console.log("categories call good");
            console.log(data);
            

            	var categories = $('#nav-category .dropdown-menu');
           
                $.each(data, function (i, item) {         

                	categories.append('<li><a href="#" data-categoryid=' + item.id + '>' + item.description + '</a></li>');
                    
                });
            
           
            },
            error: function (request, status, error) {
                console.log("Error status " + status); 
            }
        });
    
}

function utctimecnv(epochtime) {
        var date = new Date(epochtime);
        var DD = date.getDate();
        var MM = date.getMonth() + 1;
        var YY = date.getFullYear();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        if (minutes < 10) {
            minutes = '0' + minutes};
        var seconds = date.getSeconds();
        var formattedTime = " ";
        var AMPM = 'AM';
        //        var formattedTime = hours + ':' + minutes + ':' + seconds;
        if (hours > 11) {
            AMPM = 'PM';
            if (hours > 12) {
                hours = hours - 12
            };
        };

        if (epochtime > 0) {
       
        formattedTime = MM + '/' + DD + '/' + YY + ' ' + hours + ':' + minutes + ' ' + AMPM;
        };


        return formattedTime;
}