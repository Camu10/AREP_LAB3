# AREP_LAB3
Se realizó la implementación un servidor web capaz de recibir multiples solicitudes (no concurrentes), además de que se creo un framework parecido a Spark el cual permite publicar servicios web 'get' y 'post' que permite acceder a recursos estaticos como paginas, javascripts, imagenes y CSSs.

## Pre-requisitos
* [MAVEN](https://maven.apache.org/) - Administrador de dependencias.
* [GIT](https://git-scm.com/) - Control de versiones.
Para estar seguro de las versiónes que posee de maven, git y de java ejecute los siguientes comandos:
```
mvn -version  
git --version  
java -version  
```

## Instalación 
Para descargar el proyecto desde GitHub ejecute la siguiente linea:
```
git clone https://github.com/Camu10/AREP_LAB3.git
```

## Ejecutar
Dentro del directorio AREP_LAB3, desde la consola de comandos para compilar ejecutamos la siguiente linea:
```
mvn package
```
Para ejecutar el proyecto de manera local desde la consola de comandos ejecutamos la siguiente linea y desde un navegador buscamos `localhost:35000/` :
```
java -cp target/HttpServer-1.0-SNAPSHOT.jar edu.escuelaing.arep.app.App
```

## Ejecutando las pruebas
Para correr las pruebas ejecutamos la siguiente linea:
```
mvn test
```

## Despliegue en Heroku
Para ingresar a la aplicación web desde cualquier navegador puede hacerlo dando click [aqui](https://tallerclientesyservidores.herokuapp.com/)  
Puede acceder a los recursos `/index.html` `/mario.png` `/Apps/hola` `/Apps/informationDB`

## Construido con
* [MAVEN](https://maven.apache.org/) - Administrador de dependencias.
* [GIT](https://git-scm.com/) - Control de versiones.
* [JUNIT](https://junit.org/junit5/) - Framework para realizar y automatizar pruebas.
* [HEROKU](https://www.heroku.com/) - Plataforma para el despliegue.
* CIRCLECI - Plataforma para realizar la integración continua. [![CircleCI](https://circleci.com/gh/Camu10/AREP_LAB3.svg?style=svg)](https://app.circleci.com/pipelines/github/Camu10/AREP_LAB3)
* JAVA - Lenguaje de programación.

## Autor
* **Carlos Murillo** - [Camu10](https://github.com/Camu10)

## Licencia
Este proyecto está bajo la Licencia GNU(General Public License) - mira el archivo [LICENSE](LICENSE) para detalles.
