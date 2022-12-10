# CarSales
M1 project of POOA

# How to compile
To make a jar file go to the project root and type:
- `mvn clean install`
- `mvn site`
- `mvn package`
This **NEEDS** an up-to-date version of maven. It will create a `Project-1.0-SNAPSHOT.jar` jar file.

# How to execute
Two options:
- make a jar and execute: `java --module-path Dependencies/lib/ --add-modules javafx.controls,javafx.fxml -jar target/Project-1.0-SNAPSHOT.jar`
- execute: `mvn clean javafx:run`

## Authors
- Leah Kalousdian
- Louis Kurdyk
