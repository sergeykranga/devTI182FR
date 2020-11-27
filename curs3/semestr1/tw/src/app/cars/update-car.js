$(document).ready(function(){
 
    $(document).on('click', '.update-car-button', function(){
        var id = $(this).attr('data-id');

        $.getJSON("http://localhost/api/car/read_one.php?id=" + id, function(data){
        
            var brand = data.brand;
            var model = data.model;
            var color = data.color;
            var year = data.year;
            var registration_number = data.registration_number;
            var price = data.price;
            var on_sale = data.on_sale;

            var update_car_html=`
                <div id='read-cars' class='btn btn-primary pull-right m-b-15px read-cars-button'>
                    <span class='glyphicon glyphicon-list'></span> Car sale
                </div>

                <form id='update-car-form' action='#' method='post' border='0'>
                    <table class='table table-hover table-responsive table-bordered'>
                
                        <tr>
                            <td>Brand</td>
                            <td><input value=\"` + brand + `\" type='text' name='brand' class='form-control' required /></td>
                        </tr>
                
                        <tr>
                            <td>Model</td>
                            <td><input value=\"` + model + `\" type='text' name='model' class='form-control' required /></td>
                        </tr>
                
                        <tr>
                            <td>Color</td>
                            <td><input value=\"` + color + `\" type='text' name='color' class='form-control' required /></td>
                        </tr>

                        <tr>
                            <td>Year</td>
                            <td><input value=\"` + year + `\" type='number' name='year' class='form-control' required /></td>
                        </tr>

                        <tr>
                            <td>Registration number</td>
                            <td><input value=\"` + registration_number + `\" type='text' name='registration_number' class='form-control' required /></td>
                        </tr>

                        <tr>
                            <td>Price</td>
                            <td><input value=\"` + price + `\" type='number' name='price' class='form-control' required /></td>
                        </tr>

                        <tr>
                            <td>Is on sale</td>
                            <td><input value=\"` + on_sale + `\" type='checkbox' name='on_sale' class='form-control' /></td>
                        </tr>
                
                        <tr>
                
                            <td><input value=\"` + id + `\" name='id' type='hidden' /></td>
                
                            <!-- button to submit form -->
                            <td>
                                <button type='submit' class='btn btn-info'>
                                    <span class='glyphicon glyphicon-edit'></span> Update a car
                                </button>
                            </td>
                
                        </tr>
                
                    </table>
                </form>`
            ;
            
            // inject to 'page-content' of our app
            $("#page-content").html(update_car_html);
                
            // chage page title
            changePageTitle("Update a car");
        });
    });
     
    $(document).on('submit', '#update-car-form', function(){
        
        var form_data=JSON.stringify($(this).serializeObject());

        $.ajax({
            url: "http://localhost/api/car/update.php",
            type : "PUT",
            contentType : 'application/json',
            data : form_data,
            success : function(result) {
                showCarsFirstPage();
            },
            error: function(xhr, resp, text) {
                // show error to console
                console.log(xhr, resp, text);
            }
        });
        
        return false;
    });
});