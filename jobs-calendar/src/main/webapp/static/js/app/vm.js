 

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

var categoriesVM = ko.observableArray([]);

var eventsVM = ko.observableArray([]);

var eventDetailVM ={
    eventId: ko.observable("0"),
    startDt: ko.observable("0"),
    endDt: ko.observable("0"),
    name: ko.observable("0"),
    description: ko.observable("0"),
    location: ko.observable("0"),
    eventCategories: ko.observableArray([]),
    provideraddress1: ko.observable("0"),
    provideraddress2:  ko.observable("0"),
    providercity:  ko.observable("0"),
    providerempSupport:  ko.observable("0"),
    providerid:  ko.observable("0"),
    providerjobRetention:  ko.observable("0"),
    providerjobTraining:  ko.observable("0"),
    providername:  ko.observable("0"),
    providerphone:  ko.observable("0"),
    providerpreEmployment:  ko.observable("0"),
    providerprograms:  ko.observable("0"),
    providerstate:  ko.observable("0"),
    providersupportServices:  ko.observable("0"),
    providerzip:  ko.observable("0")
    
    
    
};

var eventFilterVM = {
        categoryIDFilter: ko.observable("0")
    };


    
var eventslistVM = ko.observableArray([]);

getcategories();
getquestions(0,'yes');
    

function viewModel(){
    var self = this;
    self.events = eventsVM;
    self.question = questionVM;
    self.questionID = questionIDVM;
    self.category = categoryVM;
    self.categoryID = categoryIDVM;
    self.questionAnswer = questionAnswerVM;
    self.eventDetail = eventDetailVM;
    self.categories = categoriesVM;
    
    self.epochDate = testEpochVM;
    
     ko.bindingHandlers.dateepoch = {
        update: function (element, valueAccessor, allBindingsAccessor, viewModel) {
            var value = valueAccessor(),
            allBindings = allBindingsAccessor();
            var valueUnwrapped = ko.utils.unwrapObservable(value);
            $(element).text(utctimecnv(valueUnwrapped));
        }
    }
    
     self.filteredCategory = ko.computed(function() {
        return ko.utils.arrayFilter(self.categoryID(), function (cat) { 
            return (cat.id == self.categories());
        })
    }, self);
    
	
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
    categoryIDVM(catID);
    if (catID == 19){
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/allevents";
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
            console.log(data.event);
            
            
                 eventsVM.removeAll();
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

function geteventDetail(eventID) {
   
            urlconcat = "http://10.93.126.85:8080/jobs-calendar/api/eventDetail/" + eventID ;            
           
        var request = $.ajax({
            url:urlconcat,
            type: 'GET',
            dataType:'jsonp',
            jsonpCallback: 'eventsDetail',
            jsonp: 'callback',
            success: function (data) {
            console.log("event detail call good");
            console.log(data);
            
            eventDetailVM.eventId(data.eventId);
            eventDetailVM.startDt(data.startDt);
            eventDetailVM.endDt(data.endDt);
            eventDetailVM.name(data.name);
            eventDetailVM.description(data.description);
            eventDetailVM.location(data.location);
            $.each(data.eventCategories, function (i, item) {         
                    eventDetailVM.eventCategories.push(item);             
                    eventDetailVM.eventCategories.valueHasMutated();
            });
            eventDetailVM.provideraddress1(data.provider.address1);
            eventDetailVM.provideraddress2(data.provider.address2);
            eventDetailVM.providercity(data.provider.city);
            eventDetailVM.providerempSupport(data.provider.empSupport);
            eventDetailVM.providerid(data.provider.id);
            eventDetailVM.providerjobRetention(data.provider.jobRetention);
            eventDetailVM.providerjobTraining(data.provider.jobTraining);
            eventDetailVM.providername(data.provider.name);
            eventDetailVM.providerphone(data.provider.phone);
            eventDetailVM.providerpreEmployment(data.provider.preEmployment);
            eventDetailVM.providerprograms(data.provider.programs);
            eventDetailVM.providerstate(data.provider.state);
            eventDetailVM.providersupportServices(data.provider.supportServices);
            eventDetailVM.providerzip(data.provider.zip);
                        
    
    
            
            $.mobile.changePage("#detail", { transition: "slide"});
           
            },
            error: function (request, status, error) {
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
            
            
                 categoriesVM.removeAll();
                $.each(data, function (i, item) {         
                    categoriesVM.push(item);             
                    categoriesVM.valueHasMutated();
                    
                    
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

function eventDetail(eventId) {
    
    console.log(eventId);
   geteventDetail(eventId);
    
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


function FindEventOnMap(address) {
    
   
    
    var lat = 40.4417;
    var lon = 80.0000;

    var currentPosition = new google.maps.LatLng(lat, lon);
    var map = new google.maps.Map(document.getElementById('map_canvas'), {
        zoom: 15,
        center: currentPosition,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    if (address === null || address == undefined) {
        address = "Pittsburgh, PA";
    }

    var geocoder = new google.maps.Geocoder();

    if (geocoder) {
        geocoder.geocode({ 'address': address }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    title: address
                });
            }
            else {

                alert("invalid address" + status);
            }
        });
    }
    
     $.mobile.changePage("#google_map", { transition: "slide"});
    return true;
}  

function save2cal() {
    
    
    msgData1 = Date(eventDetailVM.startDt());
    msgData2 = Date(eventDetailVM.endDt());
   
    
    msgData3 = eventDetailVM.location();
    
   var icsMSG = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Our Company//NONSGML v1.0//EN\nBEGIN:VEVENT\nUID:me@google.com\nDTSTAMP:20120315T170000Z\nATTENDEE;CN=My Self ;RSVP=TRUE:MAILTO:me@gmail.com\nORGANIZER;CN=Me:MAILTO::me@gmail.com\nDTSTART:" + msgData1 +"\nDTEND:" + msgData2 +"\nLOCATION:test location\nSUMMARY:Our Meeting Office\nEND:VEVENT\nEND:VCALENDAR";
    i
    
    window.open( "data:text/calendar;charset=utf8," + escape(icsMSG));
    
}
   

    
    




  