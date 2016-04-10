$(document).ready(function() {
    setInterval(loadImages, 1000);
});

var loadImages = function() {
    $.getJSON( "http://192.168.0.101:7891/admin/pending", function(data) {
        $("#galary").children().remove();
        var items = [];
        $.each(data, function( key, value ) {
            $.each(value, function(key2, value2) {
                $("<div>").
                addClass("col-md-4").
                html(value2.imageUrl).
                append($("<img>").attr("src", value2.imageUrl).attr("style", "width:150px;height:150px")).
                appendTo("#galary")
            })
        });
    });
};