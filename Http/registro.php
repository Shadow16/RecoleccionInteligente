<?php
 
require_once 'include/funciones.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['curp']) && isset($_POST['nombre']) && isset($_POST['apellidos']) && isset($_POST['telefono']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['municipio'])) {
 
    // receiving the post params
    $curp = $_POST['curp'];
    $nombre = $_POST['nombre'];
    $apellidos = $_POST['apellidos'];
    $telefono = $_POST['telefono'];
    $email = $_POST['email'];
    $password = $_POST["password"];
    $municipio = $_POST['municipio'];
 
    // check if user already exists with the same email
    if(emailExists($email)){
        // email already exists
        $response["error"] = TRUE;
        $response["error_msg"] = "Este correo ya existe: " . $email;
        echo json_encode($response);
    }else {
        // create a new user
        $user = storeUser($curp, $nombre, $apellidos, $telefono, $email, $password, $municipio);
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["user"]["curp"] = $user["curp"];
            $response["user"]["nombre"] = $user["nombre"];
            $response["user"]["apellidos"] = $user["apellidos"];
            $response["user"]["telefono"] = $user["telefono"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["password"] = $user["password"];
            $response["user"]["municipio"] = $user["municipio"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Error al conectarse al servidor!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Campos obligatorios!";
    echo json_encode($response);
}
?>