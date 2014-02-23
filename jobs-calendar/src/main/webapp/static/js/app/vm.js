 

var testeventsVM = {
        page: ko.observable("0"),
        size: ko.observable("10"),
        events: ko.observable("0.0.0.0"),
        server_id2: ko.observable("0.0.0.0"),
        node_port: ko.observable("13390")
    };
var testEpochVM = ko.observable("1407794400000");

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


    
var eventslistVM = ko.observableArray([]);

//getevents();
getquestions(0,'yes');
    

function viewModel(){
    var self = this;
    self.events = eventsVM;
    self.question = questionVM;
    self.questionID = questionIDVM;
    self.category = categoryVM;
    self.categoryID = categoryIDVM;
    self.questionAnswer = questionAnswerVM;
    self.eventFilter = eventFilterVM;
    
    self.epochDate = testEpochVM;
    
     ko.bindingHandlers.dateepoch = {
        update: function (element, valueAccessor, allBindingsAccessor, viewModel) {
            var value = valueAccessor(),
            allBindings = allBindingsAccessor();
            var valueUnwrapped = ko.utils.unwrapObservable(value);
            $(element).text(utctimecnv(valueUnwrapped));
        }
    }
    
    
	
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
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/allevents";
            }
            else {
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/events/" + catID ;            
            };
    var request = $.ajax({
            url:"http://10.93.126.85:8080/jobs-calendar/api/allevents",
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'events',
            jsonp: 'callback',
            success: function (data) {
            console.log("event call good");
            console.log(data.event);
            
            
                
                $.each(data.event, function (i, item) {         
                    eventsVM.push(item);             
                    eventsVM.valueHasMutated();
                    
                    
                });
            
            $.mobile.changePage("#EventList", { transition: "slide"});
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


    
    
    




  