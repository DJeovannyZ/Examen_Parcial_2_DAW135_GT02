FROM payara/server-full
COPY target/appweb-1.0-SNAPSHOT.war $DEPLOY_DIR


COPY postgresql-42.7.3.jar /opt/payara/appserver/glassfish/domains/domain1/lib/

RUN echo 'create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --driverclassname org.postgresql.Driver --restype javax.sql.DataSource --property user=postgres:password=PD0xT2FaXZvBGcS_yepXcA:DatabaseName=parcial2:ServerName=parcial2-daw135-14983.7tt.aws-us-east-1.cockroachlabs.cloud:portNumber=26257:DatabaseDriverVendor=Postgresql webpool' > $POSTBOOT_COMMANDS

RUN echo 'create-jdbc-resource --connectionpoolid webpool jdbc/webapp' >> $POSTBOOT_COMMANDS
