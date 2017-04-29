## Installazione


Installare `maven` e `java 8`

Scaricare `flink 1.2.0`

## Configurazione

Modificare in `src/main/java/configuration/Configuration.java` il percorso nell'attributo `FILENAME` specificando il percorso di un file generato nella seguente maniera:

---------------------------------------------------------------------------------------------------------------------

###### #milliseconds time
WATERMARK_INTERVAL_SEC = 1000


###### #zookeeper host & kafka broker e inse
ZOOKEEPER_HOST = ;

KAFKA_BROKER_LOCAL_CONTROLLER = 54.161.196.192:2181

KAFKA_BROKER_SENSOR_LIGHT = 54.161.196.192:9092

KAFKA_BROKER_LAMP = 54.152.81.214:9092

###### #topic
LAMP_TOPIC=lamp_data

SENSOR_TOPIC=light_sensor_data

CONTROL_TOPIC=adjustment_data


###### #time parameters
JOIN_TIME_SEC = 30

MEDIAN_WINDOW_SIZE = 30

SLEEP_TIME_TRAFFIC_MLS = 30000


###### #operators parallelism
FOLD_PARALLELISM = 4

FILTER_PARALLELISM = 4

###### #percentage for adjustment computation
MIN_PERCENTAGE_LIGHT_DOUBLE = 0.4

###### #host traffic system
TRAFFIC_HOST = traffic-system.mybluemix.net;




---------------------------------------------------------------------------------------------------------------------

## Avvio

Una volta configurata tramite il file di configurazione l'applicazione può essere eseguita in de differenti modalità:

	- tramite IDE (IntelliJ - Eclipse)

	- creare il JAR del progetto tramite il comando `mvn install` eseguito da terminale nella directory contenente il file `pom.xml`

		- eseguire lo script `start-local.sh` nella directory `bin` di Flink per avviare la UI di Flink, contattabile tramite browser alla porta 
		  8081. 

		- è possibile lanciare la propria applicazione in due modi differenti:

			1) tramite UI nella sezione `Submit new Job` -> Add new -> Upload -> Selezionare il giusto JAR -> Entry Class: core.ControlApp -> 	Parallelism: X (1-2-3-4) -> Submit

			2) tramite CLI eseguendo `flink` nella directory `bin` di Flink, tramite il comando `flink run -c core.ControlApp /path/to/jar/file.jar`

