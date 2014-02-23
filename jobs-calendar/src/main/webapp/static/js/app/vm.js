 

var testeventsVM = {
        page: ko.observable("0"),
        size: ko.observable("10"),
        events: ko.observable("0.0.0.0"),
        server_id2: ko.observable("0.0.0.0"),
        node_port: ko.observable("13390")
    };

var questionIDVM = ko.observable("2");

var questionAnswerVM = ko.observable("no");

var questionVM =  ko.observable("Need a question");
var questionIDVM = ko.observable("0");

var categoryVM = ko.observable("Needs");
var categoryIDVM = ko.observable("19");

var eventsVM = ko.observableArray([]);

var eventFilterVM = {
        categoryIDFilter: ko.observable("0")
    };

var eventslistTestVM = ko.observableArray([
    {id: ko.observable("98"),
    name: ko.observable("Recruiting for Pickers Packers"),
    startDate: ko.observable("2014-02-22"),
    endDate: ko.observable("2014-02-22"),
    locationName: ko.observable("PA Careerlink Downtown Pittsburgh"),
    locationAddr1: ko.observable("9600 Babcock Blvd"),
    locationAddr2: ko.observable(""),
    locationCity: ko.observable("Allison Park"),
    locationState: ko.observable("PA"),
    locationZipcode: ko.observable("15101"),
    description: ko.observable("When: Mon Mar 4, 2013 9:30am to 11am&nbsp; EST<br> <br>Where: CareerLink<br>Event Status: confirmed"),
    categoryID: ko.observable("12") 
    },
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
getquestions(0,'yes');
    

function viewModel(){
    var self = this;
    self.events = eventsVM;
    self.eventsTEST = eventslistTestVM;
    self.question = questionVM;
    self.questionID = questionIDVM;
    self.category = categoryVM;
    self.categoryID = categoryIDVM;
    self.questionAnswer = questionAnswerVM;
    self.eventFilter = eventFilterVM;
    
    
	
	}
	
ko.applyBindings(new viewModel());

var vm = new viewModel();
    

function getquestions(id,answer) {
    if (id == 0){
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/question";
            }
            else {
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/question/" + id + "/" + answer;            
            };
    
    var request = $.ajax({
        
            
                         
            url:urlconcat,
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'question',
            jsonp: 'callback',
            success: function (data) {
            console.log("question call good");
            
             if (!data.question) {
                 console.log("is category");
                 console.log(data.category);
                 categoryVM(data.category.category);
                 categoryIDVM(data.category.id);
                 getevents(data.category.id);
             }
            else {
              console.log("is question");    
              questionVM(data.question.question);
              questionIDVM(data.question.id);
            };
                
                  
            
            
            
            },
            error: function (request, status, error) {
                console.log("Error status " + status); 
            }
        });
    }

function getevents(catID) {
    if (catID == 19){
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/events/19";
            }
            else {
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/events/" + catID ;            
            };
    var request = $.ajax({
            url:urlconcat,
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'events',
            jsonp: 'callback',
            success: function (data) {
            console.log("event call good");
            
            
                eventsVM.removeAll();
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

   
function answerQuestion(id,answer) {
    
    console.log(answer);
    getquestions(id,answer);
    
}


    
    
    




  