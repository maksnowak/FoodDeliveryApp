# Uruchomienie bazy danych w kontenerze Dockera oraz załadowanie danych początkowych

docker run -p 5432:5432 --name papdb -e POSTGRES_PASSWORD=admin -d postgres
docker cp init.sql papdb:/docker-entrypoint-initdb.d/init.sql

# Uruchomienie aplikacji

cd foodAppliaction
mvn clean install -DskipTests
mvn exec:java -Dexec.mainClass="pap2023z.z09.gui.App"

