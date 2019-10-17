<?php

include 'Conexion.php';
//Recibir datos y almacenarlos en variables

$Nombre = $_POST["Nombre"];
$Apellido = $_POST["Apellido"];
$Rut = $_POST["Rut"];
$Correo = $_POST["Correo"];
$Usuario = $_POST["Usuario"];
$Clave = $_POST["Clave"];

//Consulta para insertar

$insertar = "INSERT INTO usuarios(Nombre, Apellido, Rut, Correo, Usuario, Clave) VALUES('$Nombre', '$Apellido', '$Rut', '$Correo', '$Usuario', '$Clave')";

$verificar_correo = mysqli_query($conexion, "SELECT * FROM usuarios WHERE Correo = '$Correo'");
if(mysqli_num_rows($verificar_correoo) > 0 ){
    echo '<script>
    alert("El Correo ya esta registrado");
    window.history.go(-1);
    </script>';
    exit;
}


$verificar_usuario = mysqli_query($conexion, "SELECT * FROM usuarios WHERE Usuario = '$Usuario'");
if(mysqli_num_rows($verificar_usuario) > 0){
    echo '<script>
    alert("El usuario ya esta registrado");
    window.history.go(-1);
    </script>';
    exit;
}

//Ejecutar consulta
$resultado = mysqli_query($conexion, $insertar);
if (!$resultado){
        echo '<script>
    alert("Error al registrar usuario");
    window.history.go(-1);
    </script>';
}else{
    echo '<script>
    alert("El Usuario a sido registrado exitosamente");
    window.location="indexLogin.html";
    </script>';
   /*header('Location: /ccompras/indexLogin.html');*/
}
//Cerrar conexion
mysqli_close($conexion);