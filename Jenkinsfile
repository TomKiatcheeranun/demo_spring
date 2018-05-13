pipeline {
    agent any
    tools {
        maven 'Maven 3.5.3'
        jdk 'jdk8'
    }
    environment{
        sonarqubeURL="http://localhost:9000"
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
                sh "sed -i s/#APP_NAME#/${appName}/g Dockerfile"
                sh "sed -i s/#APP_VERSION#/${newVersion}/g Dockerfile"
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
                    sleep 10
                }
            }
        }

        stage ('Quality Gate') {
            steps {
              timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }
        }

        stage ('Build Image And Public to Docker Hub') {
            steps {
                sh "cp ${WORKSPACE}/target/${appName}-${newVersion}.jar ."
                withCredentials([usernamePassword(credentialsId: 'dockerhub-selfieblue', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUsername')]) {
                    sh '''docker login -u ${dockerhubUsername} -p ${dockerhubPassword}'''
                    sh '''docker build -t selfieblue/${appName}:${newVersion} .'''
                    sh '''docker push selfieblue/${appName}:${newVersion}'''
                }
            }
        }

        stage ('Deploy Container') {
            steps {
                echo 'Deploy Successfully' 
            }
        }

        stage ('Running Robot Framework') {
            steps {
                echo 'Deploy Successfully' 
            }
        }
    }
}
