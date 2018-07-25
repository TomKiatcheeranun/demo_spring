pipeline {
    agent any
    tools {
        maven 'Maven 3.5.3'
        jdk 'jdk8'
    }
    environment{
        sonarqubeURL="http://localhost:9000"
        newVersion="1.0.0-${env.BUILD_NUMBER}"
        appName="log4j2-demo"
        targetNamespace="default"
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
                sh "sed -i s/#APP_NAME#/${appName}/g deployment.yaml"
                sh "sed -i s/#APP_VERSION#/${newVersion}/g deployment.yaml"
                sh "sed -i s/#APP_NAME#/${appName}/g service.yaml"
            }
        }

        stage ('Build') {
            steps {
                sh '''
                    echo "Build"
                    mvn package
                '''
            }
        }

        stage ('Build Image And Public to Docker Hub') {
            steps {
                sh '''
                    ls ${WORKSPACE}/target/
                    cp ${WORKSPACE}/target/${appName}-0.0.1-SNAPSHOT.jar ${WORKSPACE}/target/${appName}-${newVersion}.jar
                '''
                withCredentials([usernamePassword(credentialsId: 'dockerhub-selfieblue', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUsername')]) {
                    sh '''docker login -u ${dockerhubUsername} -p ${dockerhubPassword}'''
                    sh '''docker build -t birdyman/birdy/${appName}:${newVersion} .'''
                    sh '''docker push birdyman/birdy/${appName}:${newVersion}'''
                }
            }
        }

        /*stage ('Build Image And Public to Docker Hub') {
            steps {
                sh '''
                    ls ${WORKSPACE}/target/
                    cp ${WORKSPACE}/target/${appName}-0.0.1-SNAPSHOT.jar ${WORKSPACE}/target/${appName}-${newVersion}.jar
                    docker login -u birdyman -p Bird1Bird
                    docker build -t birdy/${appName}:${newVersion} .
                    docker push birdy/${appName}:${newVersion}
                '''
            }
        }*/

        stage ('Deploy Container') {
            steps {
                sh "kubectl get pods"
                /*sh "kubectl apply -f ${WORKSPACE}/deployment.yaml -n ${targetNamespace}"
                sh "kubectl apply -f ${WORKSPACE}/service.yaml -n ${targetNamespace}"*/
            }
        }

        stage ('Running Robot Framework') {
            steps {
                echo 'Deploy Successfully' 
            }
        }
    }
}
