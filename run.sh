# Uruchomienie bazy danych w kontenerze Dockera oraz załadowanie danych początkowych

# Sprawdź czy istnieje kontener o nazwie papdb
if [ "$(docker ps | grep papdb)" ]; then
    # Jeśli istnieje to go uruchom
    echo "Kontener papdb już istnieje, trwa uruchamianie..."
    docker start papdb
    echo "Kontener papdb uruchomiony"
else
    # Jeśli nie istnieje to go utwórz i załaduj dane początkowe
    echo "Kontener papdb nie istnieje, trwa tworzenie..."
    docker run -p 5432:5432 --name papdb -e POSTGRES_PASSWORD=admin -d postgres
    echo "Kontener papdb utworzony, trwa ładowanie danych początkowych..."
    docker cp init.sql papdb:/docker-entrypoint-initdb.d/init.sql
    echo "Dane początkowe załadowane"
fi

# Uruchomienie aplikacji

echo "Uruchamianie aplikacji..."
cd foodAppliaction
mvn clean install -DskipTests
mvn exec:java -Dexec.mainClass="pap2023z.z09.gui.App"

