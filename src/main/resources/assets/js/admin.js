$(document).ready(function() {
    loadImages();
    setInterval(loadImages, 5000);
});

var loadImages = function() {
    $.getJSON( "http://127.0.0.1:7891/admin/pending", function(data) {
        $("#galary").children().remove();
        var items = [];
        $.each(data, function( key, value ) {
            $.each(value, function(key2, value2) {
                $("<div>").
                addClass("col-lg-3 col-md-4 col-xs-6 thumb").
                    append($("<img>").
                    addClass("img-responsive").
                    attr("src", value2.imageUrl)).
                appendTo("#galary")
            })
        });
    });
};