<?php
class Car{

    private $conn;
    private $table_name = "cars";
  
    public $id;
    public $brand;
    public $model;
    public $color;
    public $year;
    public $registration_number;
    public $price;
    public $on_sale;
  
    public function __construct($db){
        $this->conn = $db;
    }

    function read(){
    
        $query = "SELECT
                    id, brand, model, color, year, registration_number, price, on_sale
                  FROM
                    " . $this->table_name . "
                   ORDER BY
                    price ASC";
    
        $stmt = $this->conn->prepare($query);
    
        $stmt->execute();
    
        return $stmt;
    }

    function create(){
    
        $query = "INSERT INTO
                    " . $this->table_name . "
                SET
                    brand=:brand, model=:model, color=:color, year=:year, registration_number=:registration_number, price=:price, on_sale=:on_sale";
    
        $stmt = $this->conn->prepare($query);
    
        $this->brand=htmlspecialchars(strip_tags($this->brand));
        $this->model=htmlspecialchars(strip_tags($this->model));
        $this->color=htmlspecialchars(strip_tags($this->color));
        $this->year=htmlspecialchars(strip_tags($this->year));
        $this->registration_number=htmlspecialchars(strip_tags($this->registration_number));
        $this->price=htmlspecialchars(strip_tags($this->price));
        $this->on_sale=htmlspecialchars(strip_tags($this->on_sale));
    
        $stmt->bindParam(":brand", $this->brand);
        $stmt->bindParam(":model", $this->model);
        $stmt->bindParam(":color", $this->color);
        $stmt->bindParam(":year", $this->year);
        $stmt->bindParam(":registration_number", $this->registration_number);
        $stmt->bindParam(":price", $this->price);
        $stmt->bindParam(":on_sale", $this->on_sale);
    
        if($stmt->execute()){
            return true;
        }
    
        return false;
        
    }

    function readOne(){

        $query = "SELECT
                    id, brand, model, color, year, registration_number, price, on_sale
                  FROM
                    " . $this->table_name . "
                  WHERE
                    id = ?
                  LIMIT
                    0,1";
    
        $stmt = $this->conn->prepare( $query );
    
        $stmt->bindParam(1, $this->id);
    
        $stmt->execute();
    
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        $this->brand = $row['brand'];
        $this->model = $row['model'];
        $this->color = $row['color'];
        $this->year = $row['year'];
        $this->registration_number = $row['registration_number'];
        $this->price = $row['price'];
        $this->on_sale = $row['on_sale'];
    }

    function update(){
    
        $query = "UPDATE
                    " . $this->table_name . "
                SET
                    brand=:brand, 
                    model=:model, 
                    color=:color, 
                    year=:year, 
                    registration_number=:registration_number, 
                    price=:price, 
                    on_sale=:on_sale
                WHERE
                    id = :id";
    
        $stmt = $this->conn->prepare($query);
    
        $this->brand=htmlspecialchars(strip_tags($this->brand));
        $this->model=htmlspecialchars(strip_tags($this->model));
        $this->color=htmlspecialchars(strip_tags($this->color));
        $this->year=htmlspecialchars(strip_tags($this->year));
        $this->registration_number=htmlspecialchars(strip_tags($this->registration_number));
        $this->price=htmlspecialchars(strip_tags($this->price));
        $this->on_sale=htmlspecialchars(strip_tags($this->on_sale));
        $this->id=htmlspecialchars(strip_tags($this->id));
    
        $stmt->bindParam(":brand", $this->brand);
        $stmt->bindParam(":model", $this->model);
        $stmt->bindParam(":color", $this->color);
        $stmt->bindParam(":year", $this->year);
        $stmt->bindParam(":registration_number", $this->registration_number);
        $stmt->bindParam(":price", $this->price);
        $stmt->bindParam(":on_sale", $this->on_sale);
        $stmt->bindParam(':id', $this->id);
    
        if($stmt->execute()){
            return true;
        }
    
        return false;
    }

    function delete(){
    
        $query = "DELETE FROM " . $this->table_name . " WHERE id = ?";
    
        $stmt = $this->conn->prepare($query);
    
        $this->id=htmlspecialchars(strip_tags($this->id));
    
        $stmt->bindParam(1, $this->id);
    
        if($stmt->execute()){
            return true;
        }
    
        return false;
    }

    function search($keywords){
    
        $query = "SELECT
                    id, brand, model, color, year, registration_number, price, on_sale
                FROM
                    " . $this->table_name . "
                WHERE
                    brand LIKE ? OR model LIKE ? OR color LIKE ? OR price LIKE ?
                ORDER BY
                    price ASC";
        
        $stmt = $this->conn->prepare($query);
    
        $keywords=htmlspecialchars(strip_tags($keywords));
        $keywords = "%{$keywords}%";
    
        $stmt->bindParam(1, $keywords);
        $stmt->bindParam(2, $keywords);
        $stmt->bindParam(3, $keywords);
        $stmt->bindParam(4, $keywords);
    
        $stmt->execute();
    
        return $stmt;
    }

    public function readPaging($from_record_num, $records_per_page){
    
        $query = "SELECT
                    id, brand, model, color, year, registration_number, price, on_sale
                FROM
                    " . $this->table_name . "
                ORDER BY price ASC
                LIMIT ?, ?";
    
        $stmt = $this->conn->prepare( $query );
    
        $stmt->bindParam(1, $from_record_num, PDO::PARAM_INT);
        $stmt->bindParam(2, $records_per_page, PDO::PARAM_INT);
    
        $stmt->execute();
    
        return $stmt;
    }

    public function count(){
        $query = "SELECT COUNT(*) as total_rows FROM " . $this->table_name . "";
    
        $stmt = $this->conn->prepare( $query );
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        return $row['total_rows'];
    }

}
?>