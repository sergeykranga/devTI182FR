$(document).ready(function(){
 
    $(document).on('click', '.read-one-car-button', function(){
        var id = $(this).attr('data-id');

        $.getJSON("http://localhost/api/car/read_one.php?id=" + id, function(data){
            var read_one_car_html=`
            
            <div id='read-cars' class='btn btn-primary pull-right m-b-15px read-cars-button'>
                <span class='glyphicon glyphicon-list'></span> Car sale
            </div>

            <table class='table table-bordered table-hover'>
            
                <tr>
                    <td class='w-30-pct'>Brand</td>
                    <td class='w-70-pct'>` + data.brand + `</td>
                </tr>
            
                <tr>
                    <td>Model</td>
                    <td>` + data.model + `</td>
                </tr>
            
                <tr>
                    <td>Color</td>
                    <td>` + data.color + `</td>
                </tr>
            
                <tr>
                    <td>Year</td>
                    <td>` + data.year + `</td>
                </tr>

                <tr>
                    <td>Registration number</td>
                    <td>` + data.registration_number + `</td>
                </tr>

                <tr>
                    <td>Price</td>
                    <td>` + data.price + `</td>
                </tr>

                <tr>
                    <td>Is on sale</td>
                    <td>` + data.on_sale + `</td>
                </tr>
            
            </table>`;

            $("#page-content").html(read_one_car_html);
            
            // chage page title
            changePageTitle("Create a car");
        });
    });
 
});