mvn clean package -DskipTests 

echo "Deteniendo los contenedores y borrando imagenes creadas previamente..."

docker-compose down --rmi local --volumes

echo "Iniciando la aplicacion..."

docker-compose up
