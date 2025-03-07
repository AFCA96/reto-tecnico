
# Reto técnico consideraciones

En esta parte describo consideraciones a tener en cuenta para levantar los microservicios de cliente y cuenta-movimiento

1. Base de datos usada: MySQL 8.0. Considerar username y password para establecer la conexión de base datos

2. Version de Spring Boot 3.4.3

3. Version de Java Open JDK 17

4. El json postman se encuentra en la raiz del repositorio al igual que las variables de entorno postman.

5. La base de datos en encuentra en el raíz del repositorio con el nombre "BaseDatos.sql" tal como lo especifican.

6. Para la comunicación asincrona se uso RabbitMQ como servicio para el envío de mensajes entre microservicios, se usó una imagen docker:

* docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
* El nombre de la cola usada se llama: **queueTest**

7. Se estableció comunicación síncrona entre el microservicio de cuentas-movimientos y clientes para la creación de cuentas dado que se valida si un cliente exite y si el cliente esta activo para la creación de la cuenta. 
