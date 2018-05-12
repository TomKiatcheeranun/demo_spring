pipeline {
    agent any
    tools {
        maven 'Maven 3.5.3'
        jdk 'jdk8'
    }
    environment{
        sonarqubeURL="http://13.251.19.114:9000"
        newVersion="1.0.0-${env.BUILD_NUMBER}"
        appName="spring-boot-build"
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh "mvn versions:set -DnewVersion=${newVersion}"
                    sh "mvn clean package sonar:sonar " +
                    "-Dsonar.host.url=${sonarqubeURL} " +
                    "-Dsonar.projectKey=${appName} " +
                    "-Dsonar.projectName=${appName} " +
                    "-Dsonar.projectVersion=${newVersion} " +
                    "-Dsonar.language=java " +
                    "-Dsonar.sources=src/ "+
                    "-Dsonar.tests=src/test/ "+
                    "-Dsonar.test.inclusions=**/*Test*/** "+
                    "-Dsonar.exclusions=**/*Test*/** "+
                    "-Dsonar.java.binaries=target/classes"
                }
            }
        }

        stage ('Quality Gate') {
            steps {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
            }
        }

    }
}
