$(document).ready(function(){
 
    $(document).on('submit', '#search-car-form', function(){
        var keywords = $(this).find(":input[name='keywords']").val();
 
        $.getJSON("http://localhost/api/car/search.php?s=" + keywords, function(data){
            readCarsTemplate(data, keywords);
            changePageTitle("Search cars: " + keywords);
        });
        return false;
    });
 
});