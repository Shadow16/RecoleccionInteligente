<?php
require_once 'include/funciones.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['email']) && isset($_POST['password'])) {
 
    // receiving the post params
    $email = $_POST['email'];
    $password = $_POST['password'];
 
    // get the user by email and password
    $user = getUserByEmailAndPassword($email, $password);
 
    if ($user != false) {
        // user is found
        $response["error"] = FALSE;
        $response["user"]["curp"] = $user["curp"];
        $response["user"]["email"] = $user["email"];
        $response["user"]["nombre"] = $user["nombre"];
        
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Datos incorrectos o vacios!";
        echo json_encode($response);
    }
} else {
    //required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Campos Obligatorios";
    echo json_encode($response);
}
?>