$(document).ready(function() {
    loadImages();
    setInterval(loadImages, 5000);
});

var loadImages = function() {
    $.getJSON( "http://192.168.0.101:7891/admin/pending", function(data) {
        $("#galary").children().remove();
        var items = [];
        $.each(data, function( key, value ) {
            $.each(value, function(key2, value2) {
                $("<div>").
                addClass("col-lg-3 col-md-4 col-xs-6 thumb").
                    append($("<img>").
                    addClass("img-responsive").
                    addClass("img-thumbnail").
                    attr("src", value2.imageUrl)).
                    append($("<h1>").text("Isuru")).
                    append($("<p>").text("This photo is scheduled to be printed at 10:15")).
                appendTo("#galary")
            })
        });
    });
};