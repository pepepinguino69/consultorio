Endpoints 


Usuarios: (unicamente via post)

base: /api/auth


/signup

{username,password (min 6),email(unique),roles:["user","admin","mod"]}

/signin

{username,password}

/signout

{username,password}


Consultorio:

base:/api


Turnos:

/turnos

[sin restricciones] todos los metodos

para post y put usar el siguiente DTO

{odontologo_id,paciente_id,comienzo_turno (2022-10-10T10:00:00),fin_turno (2022-10-10T10:00:00)}

/

Odontologos 

/odontologos [solo usuarios admin] todos los metodos

POST y PUT

{nombre,apellido,matricula (unique)}

Pacientes

/pacientes [solo usuarios admin] todos los metodos

post usar el siguiente DTO

{nombre,apellido,dni (unique),fecha (2022-10-10T10:00:00),calle,localidad,ciudad}

para put

{nombre,apellido,dni (unique),fecha (2022-10-10T10:00:00)}


Domicilios [solo usuarios admin]

/domicilios

no existe metodo post


para put

{calle,localidad,ciudad}













