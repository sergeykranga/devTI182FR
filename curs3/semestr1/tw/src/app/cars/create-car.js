$(document).ready(function(){
 
    $(document).on('click', '.create-car-button', function(){
        var create_car_html=`
        <div id='read-cars' class='btn btn-primary pull-right m-b-15px read-cars-button'>
            <span class='glyphicon glyphicon-list'></span> Car sale
        </div>

        <form id='create-car-form' action='#' method='post' border='0'>
            <table class='table table-hover table-responsive table-bordered'>
                <tr>
                    <td>Brand</td>
                    <td><input type='text' name='brand' class='form-control' required /></td>
                </tr>

                <tr>
                    <td>Model</td>
                    <td><input type='text' name='model' class='form-control' required /></td>
                </tr>

                <tr>
                    <td>Color</td>
                    <td><input type='text' name='color' class='form-control' required /></td>
                </tr>
        
                <tr>
                    <td>Year</td>
                    <td><input type='number' name='year' class='form-control' required /></td>
                </tr>

                <tr>
                    <td>Registration number</td>
                    <td><input type='text' name='registration_number' class='form-control' required /></td>
                </tr>

                <tr>
                    <td>Price</td>
                    <td><input type='number' name='price' class='form-control' required /></td>
                </tr>

                <tr>
                    <td>Is on sale</td>
                    <td><input type='checkbox' name='on_sale' class='form-control' /></td>
                </tr>
        
                <!-- button to submit form -->
                <tr>
                    <td></td>
                    <td>
                        <button type='submit' class='btn btn-primary'>
                            <span class='glyphicon glyphicon-plus'></span> Create car
                        </button>
                    </td>
                </tr>
        
            </table>
        </form>`;

        $("#page-content").html(create_car_html);
        
        changePageTitle("Create a Car");
    });
 
    $(document).on('submit', '#create-car-form', function(){
        var form_data=JSON.stringify($(this).serializeObject());

        $.ajax({
            url: "http://localhost/api/car/create.php",
            type : "POST",
            contentType : 'application/json',
            data : form_data,
            success : function(result) {
                showCarsFirstPage();
            },
            error: function(xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        });
        
        return false;
    });
});