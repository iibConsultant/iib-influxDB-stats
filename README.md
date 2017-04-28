# iib-influxDB-stats
IIB 10.0.0.5 flow to process statistics messages and send them to an influxDB.

This flow does not currently use SSL so this should be done once influxDB has been configured to use SSL.

There are 3 components needed to display the statistics data:
1) This flow to process the data and place it into an influxDB
2) influxDB
3) Grafana to display the statistical information.  No grafana dashboard is yet included in this repository

This flow needs 4 pieces of IIB configuration:
1) a security id and security profile to specify the userid and password to pass to influxDB (if this is setup in influxDB)

mqsisetdbparms BKR -n INFLUX_ID -u userid -p password

mqsicreateconfigurableservice BKR -c SecurityProfiles -o INFLUX_SP -n "propagation,idToPropagateToTransport,transportPropagationConfig" -v "TRUE,STATIC ID,INFLUX_ID"

2) Change the INFLUX_DB user defined property in the flow to your influxDB database or create a TESTDB influxDB database

3) Create a local queue called STATS.SUB

4) Create an MQ subscription using the STATS.SUB queue as the destination:

DEF SUB(STATS.SUB) TOPICSTR('$SYS/Broker/+/Statistics/JSON/SnapShot/#') DEST(STATS.SUB)