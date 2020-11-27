$(document).ready(function(){
 
    showCarsFirstPage();
 
    $(document).on('click', '.read-cars-button', function(){
        showCarsFirstPage();
    });
 
    $(document).on('click', '.pagination li', function(){
        var json_url=$(this).find('a').attr('data-page');
        showCars(json_url);
    });
});
 
function showCarsFirstPage(){
    var json_url="http://localhost/api/car/read_paging.php";
    showCars(json_url);
}
 
function showCars(json_url){
    $.getJSON(json_url, function(data){
        readCarsTemplate(data, "");
        changePageTitle("Car sale");
    });
}