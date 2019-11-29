FROM 10.100.2.92:5000/f00lish/centos7_jdk8_base:v1
VOLUME /root/xcmg
WORKDIR /home
ADD ./target/gateway-server-0.0.3-SNAPSHOT.jar app.jar

ENTRYPOINT exec java -server -Xms52m -Xmx512m -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar  $EUREKA_SET
