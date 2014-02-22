 

var testeventsVM = {
        page: ko.observable("0"),
        size: ko.observable("10"),
       events: ko.observable("0.0.0.0"),
        server_id2: ko.observable("0.0.0.0"),
        
        node_port: ko.observable("13390")
    };

var questionsVM = ko.observableArray([]);






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
    
var eventslistVM = ko.observableArray([]);

getevents();
getquestions();
    

function viewModel(){
    var self = this;

   
    self.events = eventsVM;
    self.eventsTEST = eventslistTestVM;
    self.questions = questionsVM;
	
	}
	
ko.applyBindings(new viewModel());

var vm = new viewModel();
    

function getquestions() {
    var request = $.ajax({
            url:"http://10.93.126.85:8080/TRWIB/restful/getquestions",
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'questions',
            jsonp: 'wrapper',
            success: function (data) {
            console.log("question call good");
            console.log(data);
            
            },
            error: function (request, status, error) {
                console.log("Error status " + status); 
            }
        });
    }

    function getevents() {
    var request = $.ajax({
            url:"http://10.93.126.85:8080/TRWIB/restful/getevents",
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'events',
            jsonp: 'wrapper',
            success: function (data) {
            console.log("event call good");
            console.log(data);
             $.each(data, function (i, item) {
                                
                            
                                eventsVM.push(item);
                             
                                eventsVM.valueHasMutated();
             });
            
            },
            error: function (request, status, error) {
                console.log("Error status " + status); 
            }
        });
    }

    
    
    




  