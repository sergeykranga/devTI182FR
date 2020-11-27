<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  
include_once '../config/database.php';
include_once '../objects/car.php';
  
$database = new Database();
$db = $database->getConnection();
  
$car = new Car($db);
  
$data = json_decode(file_get_contents("php://input"));
  
$car->id = $data->id;
$car->brand = $data->brand;
$car->model = $data->model;
$car->color = $data->color;
$car->year = $data->year;
$car->registration_number = $data->registration_number;
$car->price = $data->price;

if (empty($data->on_sale)) {
    $car->on_sale = 'false';
} else {
    $car->on_sale = 'true';
}

if($car->update()){
    http_response_code(200);
    echo json_encode(array("message" => "Car was updated."));
}else{
    http_response_code(503);
    echo json_encode(array("message" => "Unable to update a car."));
}
?>