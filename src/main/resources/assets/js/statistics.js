var chart = c3.generate({
    bindto: '#chart',
    data: {
        columns: [
            ['data1']
        ]
    }
});

var pieChart = c3.generate({
    bindto: '#pieChart',
    data: {
        columns: [
            ['data1']
        ],
        "type" : "pie"
    }
});

$(document).ready(function() {
    loadData();
    setInterval(loadData, 5000);
});

function prepend(value, array) {
    var newArray = array.slice(0);
    newArray.unshift(value);
    return newArray;
}

var loadData = function() {
    $.getJSON( "http://192.168.0.101:7891/client/print/status/history", function(data) {
        var printJobEntries = data["printJobHistoryEntries"];
        if(printJobEntries.length != 0) {
               var completed = prepend('Completed', printJobEntries.map(function(x){
                   return x["printJobStatus"]["completed"]
               }));
            var submitted = prepend('Submitted', printJobEntries.map(function(x){
                return x["printJobStatus"]["allSubmitted"]
            }));
            var error = prepend('Error', printJobEntries.map(function(x){
                return x["printJobStatus"]["error"]
            }));
            chart.load({
                columns: [
                    completed, submitted, error
                ]
            });
            pieChart.load({
                columns: [
                    completed, submitted, error
                ]
            });
        }
    });
};