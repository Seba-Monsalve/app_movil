<?php

include 'Conexion.php';

$Usuario = $_POST['Usuario'];
$Clave = $_POST['Clave'];

//consultar a la bd
$consu = "SELECT * FROM usuarios WHERE Usuario='$Usuario' and Clave='$Clave'";

$resu=mysqli_query($conexion, $consu);

$filas=mysqli_num_rows($resu);

if($filas>0){
    header("location: bienvenido.html");
}else{
    echo 'Error al ingresar';
}
mysqli_free_result($resu);
mysqli_close($conexion);