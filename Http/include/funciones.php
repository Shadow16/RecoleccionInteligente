<?php require_once("conexion.php");?>
<?php

	function storeUser($curp, $nombre, $apellidos, $telefono, $email, $password, $municipio){
		global $connection;
		
		$query = "INSERT INTO usuarios(";
		$query .= "curp, nombre, apellidos, telefono, email, password, municipio) ";
		$query .= "VALUES('{$curp}','{$nombre}','{$apellidos}','{$telefono}','{$email}','{$password}','{$municipio}')";

		$result = mysqli_query($connection, $query);

		if($result){
			$user = "SELECT * FROM usuarios WHERE email = '{$email}'";
			$res = mysqli_query($connection, $user);

			while ($user = mysqli_fetch_assoc($res)){
				return $user;
			}
		}else{
				return false;
			}

	}


	function getUserByEmailAndPassword($email, $password){
		global $connection;
		$query = "SELECT * from usuarios where email = '{$email}' and password = '{$password}'";
	
		$user = mysqli_query($connection, $query);
		
		if($user){
			while ($res = mysqli_fetch_assoc($user)){
				return $res;
			}
		}
		else{
			return false;
		}
	}


	function emailExists($email){
		global $connection;
		$query = "SELECT email from usuarios where email = '{$email}'";

		$result = mysqli_query($connection, $query);

		if(mysqli_num_rows($result) > 0){
			return true;
		}else{
			return false;
		}
	}

?>