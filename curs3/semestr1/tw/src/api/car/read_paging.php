<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
include_once '../config/core.php';
include_once '../shared/utilities.php';
include_once '../config/database.php';
include_once '../objects/car.php';
  
$utilities = new Utilities();
  
$database = new Database();
$db = $database->getConnection();
  
$car = new Car($db);
  
$stmt = $car->readPaging($from_record_num, $records_per_page);
$num = $stmt->rowCount();
  
if($num>0){
  
    $cars_arr=array();
    $cars_arr["records"]=array();
    $cars_arr["paging"]=array();
  
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        extract($row);
  
        $car_item=array(
            "id" => $id,
            "brand" => $brand,
            "model" => $model,
            "color" => $color,
            "year" => $year,
            "registration_number" => $registration_number,
            "price" => $price,
            "on_sale" => $on_sale
        );
  
        array_push($cars_arr["records"], $car_item);
    }
  
    $total_rows=$car->count();
    $page_url="{$home_url}car/read_paging.php?";
    $paging=$utilities->getPaging($page, $total_rows, $records_per_page, $page_url);
    $cars_arr["paging"]=$paging;

    http_response_code(200);
    echo json_encode($cars_arr);
}else{
    http_response_code(404);
    echo json_encode(
        array("message" => "No cars found.")
    );
}
?>