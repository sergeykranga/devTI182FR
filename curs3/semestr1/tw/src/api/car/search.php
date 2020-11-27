<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
include_once '../config/core.php';
include_once '../config/database.php';
include_once '../objects/car.php';
  
$database = new Database();
$db = $database->getConnection();
  
$car = new Car($db);
  
$keywords=isset($_GET["s"]) ? $_GET["s"] : "";
  
$stmt = $car->search($keywords);
$num = $stmt->rowCount();
  
if($num>0){
  
    $cars_arr=array();
    $cars_arr["records"]=array();
  
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
  
    http_response_code(200);
    echo json_encode($cars_arr);
}else{
    http_response_code(404);
    echo json_encode(
        array("message" => "No cars found.")
    );
}
?>