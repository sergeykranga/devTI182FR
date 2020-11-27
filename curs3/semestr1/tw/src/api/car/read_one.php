<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
include_once '../config/database.php';
include_once '../objects/car.php';
  
$database = new Database();
$db = $database->getConnection();
  
$car = new Car($db);
  
$car->id = isset($_GET['id']) ? $_GET['id'] : die();
  
$car->readOne();
  
if($car->brand!=null){
    $car_arr = array(
        "id" =>  $car->id,
        "brand" => $car->brand,
        "model" => $car->model,
        "color" => $car->color,
        "year" => $car->year,
        "registration_number" => $car->registration_number,
        "price" => $car->price,
        "on_sale" => $car->on_sale
    );

    http_response_code(200);
    echo json_encode($car_arr);
}else{
    http_response_code(404);
    echo json_encode(array("message" => "Car does not exist."));
}
?>
