FROM payara/server-full
COPY target/vetpet-1.0.war $DEPLOY_DIR

COPY postgresql-42.7.3.jar /opt/payara/appserver/glassfish/domains/domain1/lib/

RUN echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --driverclassname org.postgresql.Driver --restype javax.sql.DataSource --property user=djeovanz:password=vetpet:DatabaseName=vetpet:ServerName=vetpet_db:portNumber=5432:DatabaseDriverVendor=Postgresql vetpool' > $POSTBOOT_COMMANDS

RUN echo 'create-jdbc-resource --connectionpoolid vetpool jdbc/vetpet' >> $POSTBOOT_COMMANDS
