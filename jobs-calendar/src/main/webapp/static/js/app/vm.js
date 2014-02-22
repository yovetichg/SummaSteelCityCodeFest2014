 

var testeventsVM = {
        page: ko.observable("0"),
        size: ko.observable("10"),
       events: ko.observable("0.0.0.0"),
        server_id2: ko.observable("0.0.0.0"),
        
        node_port: ko.observable("13390")
    };






var eventsVM = ko.observableArray([]);

var eventslistTestVM = ko.observableArray([
    
    {id: ko.observable("98"),
    name:ko.observable("Recruiting for Pickers Packers"),
    startDate:("2014-02-22"),
    endDate:("2014-02-22"),
    location:("PA Careerlink Downtown Pittsburgh")},
    {id: ko.observable("98"),
    name:ko.observable(" Pickers Packers"),
    startDate:("2014-02-22"),
    endDate:("2014-02-22"),
    location:("location2")},
    {id: ko.observable("100"),
    name:ko.observable("Recruiting"),
    startDate:("2014-02-22"),
    endDate:("2014-02-22"),
    location:("location 3")},
    
    
]);
    

function viewModel(){
    var self = this;

   
    self.events = eventsVM;
    self.eventsTEST = eventslistTestVM;
	
	}
	
ko.applyBindings(new viewModel());

var vm = new viewModel();



function eventDetail(eventid) {
        var request = $.ajax({

            url: "http://" + localCredentialsVM.localServerID() + "?format=JSON",
            type: 'POST',
            // data: "{\"requests\": [{\"request_id\": 0, \"session_id\": \"<your session id>\", \"get_open_alarm_latest_update\": {\"alarm_id\": <uint64>}]}"
            data: "{\"requests\": [{\"request_id\": 0, \"session_id\": 0, \"get_open_alarm_latest_update\": {\"alarm_id\": "+ alarmid + "}}]}",
            contextType: 'json',
            dataType: 'json',
            processdata: true,
            success: function (data) {

  //            console.log(data.responses[0].request_response.login.session_id);
                //console.log("Detail call good");
                $.each(data.responses, function (i, post2) {

                    eventDetailVM.channel_name(post2.request_response.get_open_alarm_latest_update.alarm.channel.name);

                });



            },
            error: function (request, status, error) {
                console.log("Error status " + status);
                console.log("Error request status text: " + request.statusText);
                console.log("Error request status: " + request.status);
                console.log("Error request response text: " + request.responseText);
                console.log("Error response header: " + request.getAllResponseHeaders());
                $("#error").html(status);
                document.getElementById('authresults').innerHTML = "Sessionid ERROR";
               
            }


        });

    }