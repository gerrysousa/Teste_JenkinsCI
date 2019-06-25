# Teste_JenkinsCI

https://www.youtube.com/watch?v=0Rdo2BCjwRk

test -DsuiteXmlFile=src/test/resources/TestSuites/PositiveTestsLocal.xml
test -DsuiteXmlFile=src/test/resources/TestSuites/PositiveTestsGrid.xml

Jenkis funciona no grid


´´´
docker-compose.exe up -d
´´´

´´´
docker run ^
  -u root ^
  --rm ^
  -d ^
  -p 8080:8080 ^
  -p 50000:50000 ^
  -v jenkins-data:/var/jenkins_home ^
  -v /var/run/docker.sock:/var/run/docker.sock ^
  jenkins/jenkins
´´´