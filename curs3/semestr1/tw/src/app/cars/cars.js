function readCarsTemplate(data, keywords){
 
    var read_cars_html=`
        <form id='search-car-form' action='#' method='post'>
        <div class='input-group pull-left w-30-pct'>
 
            <input type='text' value='` + keywords + `' name='keywords' class='form-control car-search-keywords' placeholder='Search cars...' />
 
            <span class='input-group-btn'>
                <button type='submit' class='btn btn-default' type='button'>
                    <span class='glyphicon glyphicon-search'></span>
                </button>
            </span>
 
        </div>
        </form>
 
        <div id='create-car' class='btn btn-primary pull-right m-b-15px create-car-button'>
            <span class='glyphicon glyphicon-plus'></span> Create a car
        </div>
 
        <table class='table table-bordered table-hover'>
 
            <tr>
                <th class='w-10-pct'>Brand</th>
                <th class='w-10-pct'>Model</th>
                <th class='w-10-pct'>Color</th>
                <th class='w-10-pct'>Year</th>
                <th class='w-10-pct'>Registration number</th>
                <th class='w-10-pct'>Price</th>
                <th class='w-10-pct'>Is on sale</th>
                <th class='w-20-pct text-align-center'>Action</th>
            </tr>`;
 
    $.each(data.records, function(key, val) {
        read_cars_html+=`<tr>
 
            <td>` + val.brand + `</td>
            <td>` + val.model + `</td>
            <td>` + val.color + `</td>
            <td>` + val.year + `</td>
            <td>` + val.registration_number + `</td>
            <td>$` + val.price + `</td>
            <td>` + val.on_sale + `</td>
 
            <!-- 'action' buttons -->
            <td>
                <!-- read car button -->
                <button class='btn btn-primary m-r-10px read-one-car-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-eye-open'></span> Read
                </button>
 
                <!-- edit button -->
                <button class='btn btn-info m-r-10px update-car-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-edit'></span> Edit
                </button>
 
                <!-- delete button -->
                <button class='btn btn-danger delete-car-button' data-id='` + val.id + `'>
                    <span class='glyphicon glyphicon-remove'></span> Delete
                </button>
            </td>
        </tr>`;
    });
 
    read_cars_html+=`</table>`;

    if(data.paging){
        read_cars_html+="<ul class='pagination pull-left margin-zero padding-bottom-2em'>";
    
            if(data.paging.first!=""){
                read_cars_html+="<li><a data-page='" + data.paging.first + "'>First Page</a></li>";
            }

            $.each(data.paging.pages, function(key, val){
                var active_page=val.current_page=="yes" ? "class='active'" : "";
                read_cars_html+="<li " + active_page + "><a data-page='" + val.url + "'>" + val.page + "</a></li>";
            });
    
            if(data.paging.last!=""){
                read_cars_html+="<li><a data-page='" + data.paging.last + "'>Last Page</a></li>";
            }
        read_cars_html+="</ul>";
    }
 
    $("#page-content").html(read_cars_html);
}