<?php
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
  
if(!empty($data->brand) &&
    !empty($data->model) &&
    !empty($data->color) &&
    !empty($data->year) &&
    !empty($data->registration_number) &&
    !empty($data->price)){
  
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
  
    if($car->create()){
        http_response_code(201);
        echo json_encode(array("message" => "Car was created."));
    }else{
        http_response_code(503);
        echo json_encode(array("message" => "Unable to create a car."));
    }
} else{
    http_response_code(400);
    echo json_encode(array("message" => "Unable to create a car. Data is incomplete."));
}
?>