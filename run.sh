# Uruchomienie bazy danych w kontenerze Dockera oraz załadowanie danych początkowych

# Sprawdź czy istnieje kontener o nazwie foodappdb
if [ "$(docker ps -a | grep foodappdb)" ]; then
    # Jeśli istnieje to go uruchom
    echo "Kontener foodappdb już istnieje, trwa uruchamianie..."
    docker start foodappdb
    echo "Kontener foodappdb uruchomiony"
else
    # Jeśli nie istnieje to go utwórz i załaduj dane początkowe
    echo "Kontener foodappdb nie istnieje, trwa tworzenie..."
    docker run -p 5432:5432 --name foodappdb -e POSTGRES_PASSWORD=admin -d postgres
    echo "Kontener foodappdb utworzony, trwa ładowanie danych początkowych..."
    docker cp init.sql foodappdb:/docker-entrypoint-initdb.d/init.sql
    echo "Dane początkowe załadowane"
fi

# Uruchomienie aplikacji

echo "Uruchamianie aplikacji..."
cd foodApplication
mvn clean install
mvn exec:java -Dexec.mainClass="pl.foodapp.gui.App"

